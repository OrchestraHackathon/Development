//
//  LoginViewController.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/11.
//

import UIKit

class LoginViewController: UIViewController, UITextFieldDelegate {
    
    
    @IBOutlet weak var emailTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    
    
    @IBOutlet weak var scrollView: UIScrollView!
    @IBOutlet weak var viewButtomConstraint: NSLayoutConstraint!
    
    @IBOutlet weak var loginButton: UIButton!
    
    private var initialViewButtomConstraint: CGFloat!
    
    
    var loginEmail : String = ""
    var loginPassword: String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setKeyboardInteraction()
        setViewUI()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self, name: UIResponder.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.removeObserver(self, name: UIResponder.keyboardWillHideNotification, object: nil)
    }
    
    func setViewUI() {
        
        emailTextField.layer.borderColor = UIColor.systemGray5.cgColor
        passwordTextField.layer.borderColor = UIColor.systemGray5.cgColor
        passwordTextField.isSecureTextEntry = true
        
    }
    
    func setKeyboardInteraction() {
        
        self.hideKeyboardWhenTappedAround()
        
        initialViewButtomConstraint = viewButtomConstraint.constant
        
        
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(self.keyboardWillShow),
            name: UIResponder.keyboardWillShowNotification,
            object: nil)
        
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(self.keyboardWillHide),
            name: UIResponder.keyboardWillHideNotification,
            object: nil)
    }
    
    @IBAction func loginButtonDidTap(_ sender: Any) {
        
        postLoginInfo(email: loginEmail, password: loginPassword) {success in
            if success {
                self.performSegue(withIdentifier: "loginSuccessSG", sender: (Any).self)
            }else {
                DispatchQueue.main.async {
                    self.shakeButton(self.loginButton, originalTitle: "로그인", newTitle: "로그인 실패")
                }
            }
        }
    }
    
    
    @IBAction func registerButtonDidTap(_ sender: Any) {
    }
    
    func textFieldDidBeginEditing(_ textField: UITextField) {
        let textFieldFrame = textField.convert(textField.bounds, to: scrollView)
        let offset = CGPoint(x: 0, y: textFieldFrame.origin.y - (view.bounds.height / 2) + (textFieldFrame.height / 2) + 100)
        scrollView.setContentOffset(offset, animated: true)
    }
    
    func textFieldDidEndEditing(_ textField: UITextField) {
        let text = textField.text ?? ""
        
        switch textField {
            
        case emailTextField:
            self.loginEmail = text
        case passwordTextField:
            self.loginPassword = text
            
        default:
            fatalError("Missing TextField")
            
        }
    }
    
    
    @objc func keyboardWillShow(_ notification: NSNotification) {
        // Move the view only when the emailTextField or the passwordTextField are being edited
        if emailTextField.isEditing || passwordTextField.isEditing {
            moveViewWithKeyboard(notification: notification, viewBottomConstraint: self.viewButtomConstraint, keyboardWillShow: true)
        }
        
    }
    
    @objc func keyboardWillHide(_ notification: NSNotification) {
        moveViewWithKeyboard(notification: notification, viewBottomConstraint: self.viewButtomConstraint, keyboardWillShow: false)
    }
    
    
    func moveViewWithKeyboard(notification: NSNotification, viewBottomConstraint: NSLayoutConstraint, keyboardWillShow: Bool) {
        // Keyboard's size
        guard let keyboardSize = (notification.userInfo?[UIResponder.keyboardFrameEndUserInfoKey] as? NSValue)?.cgRectValue else { return }
        let keyboardHeight = keyboardSize.height
        
        // Keyboard's animation duration
        let keyboardDuration = notification.userInfo![UIResponder.keyboardAnimationDurationUserInfoKey] as! Double
        
        // Keyboard's animation curve
        let keyboardCurve = UIView.AnimationCurve(rawValue: notification.userInfo![UIResponder.keyboardAnimationCurveUserInfoKey] as! Int)!
        
        let safeAreaExists = (self.view?.window?.safeAreaInsets.bottom != 0) // Check if safe area exists
        
        // Change the constant
        if keyboardWillShow {
            
            //            viewBottomConstraint.constant = keyboardHeight + (safeAreaExists ? 0 : viewBottomConstraint.constant)
            viewBottomConstraint.constant = initialViewButtomConstraint + keyboardHeight
        }else {
            //            viewBottomConstraint.constant = viewBottomConstraint.constant - (safeAreaExists ? keyboardHeight : keyboardHeight)
            viewBottomConstraint.constant = initialViewButtomConstraint
        }
        
        // Animate the view the same way the keyboard animates
        let animator = UIViewPropertyAnimator(duration: keyboardDuration, curve: keyboardCurve) { [weak self] in
            // Update Constraints
            self?.view.layoutIfNeeded()
        }
        
        // Perform the animation
        animator.startAnimation(afterDelay: 0.055)
        
    }
    
    
    struct APIResponse: Codable {
        let isSuccess: Bool
        let responseCode: Int
        let responseMessage: String
        let result: Result
        
        struct Result: Codable {
            let usersId: Int
            let accessToken: String
            let refreshToken: String
        }
    }
    
    func postLoginInfo(email: String, password: String, completion: @escaping (Bool) -> Void) {
        guard let serverUrl = Bundle.main.object(forInfoDictionaryKey: "ServerUrl") as? String else {
            print("Error: serverUrl not found in Info.plist")
            completion(false)
            return
        }

        let postLoginInfo = PostLoginInfo(email: email, password: password)

        guard let uploadData = try? JSONEncoder().encode(postLoginInfo) else {
            completion(false)
            return
        }

        let url = URL(string: "\(serverUrl)/users/login")

        var request = URLRequest(url: url!)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")

        let task = URLSession.shared.uploadTask(with: request, from: uploadData) { (data, response, error) in
            if let e = error {
                NSLog("An error has occurred: \(e.localizedDescription)")
                DispatchQueue.main.async {
                    completion(false)
                }
                return
            }
            
            guard let data = data, let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode == 200 else {
                DispatchQueue.main.async {
                    completion(false)
                }
                return 
            }

            do {
                let apiResponse = try JSONDecoder().decode(APIResponse.self, from: data)
                if apiResponse.isSuccess {
                    UserDefaults.standard.set(apiResponse.result.accessToken, forKey: "accessToken")
                    DispatchQueue.main.async {
                        completion(true)
                    }
                    
                } else {
                    DispatchQueue.main.async {
                        completion(false)
                    }
                }
            } catch {
                NSLog("Error decoding: \(error)")
                DispatchQueue.main.async {
                    completion(false)
                }
            }
        }
        task.resume()
    }

    
    
    
    func shakeButton(_ button: UIButton, duration: TimeInterval = 0.5, originalTitle: String, newTitle: String) {
        
        let animationKey = "shake"
        button.layer.removeAnimation(forKey: animationKey)
        
        let kAnimation = CAKeyframeAnimation(keyPath: "transform.translation.x")
        kAnimation.timingFunction = CAMediaTimingFunction(name: CAMediaTimingFunctionName.linear)
        kAnimation.duration = duration
        
        var needOffset = button.frame.width * 0.15,
            values = [CGFloat]()
        
        let minOffset = needOffset * 0.1
        
        repeat {
            
            values.append(-needOffset)
            values.append(needOffset)
            needOffset *= 0.5
        } while needOffset > minOffset
        
        values.append(0)
        kAnimation.values = values
        button.layer.add(kAnimation, forKey: animationKey)
        
        // Change the title of the button
        button.setTitle(newTitle, for: .normal)
        
        // Change the title back to the original title after the duration of the shake animation
        DispatchQueue.main.asyncAfter(deadline: .now() + duration + 0.5) {
            button.setTitle(originalTitle, for: .normal)
        }
    }
    
    
}

