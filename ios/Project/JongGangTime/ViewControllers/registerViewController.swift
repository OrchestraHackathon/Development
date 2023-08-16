//
//  registerViewController.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/11.
//

import UIKit

class registerViewController: UIViewController, UITextFieldDelegate {
    
    
    @IBOutlet weak var registerScrollView: UIScrollView!
    
    @IBOutlet weak var registerEmailTextField: PaddingtextField!
    
    @IBOutlet weak var registerPasswordTextField: PaddingtextField!
    
    @IBOutlet weak var confirmPasswordTextField: PaddingtextField!
    
    @IBOutlet weak var registerNameTextField: PaddingtextField!
    
    @IBOutlet weak var registerNicknameTextField: PaddingtextField!
    
    
    var textFields: [UITextField] {
        [registerEmailTextField, registerPasswordTextField, confirmPasswordTextField, registerNameTextField, registerNicknameTextField]
    }
    
    
    @IBOutlet weak var registerViewButtomConstraint: NSLayoutConstraint!
    
    private var initialRegisterViewButtomConstraint: CGFloat!
    
    
    var registerEmail: String = ""
    var registerNickname: String = ""
    var registerPassword: String = ""
    var registerName: String = ""
    
    var registerUserInfo: ((RegisterUserInfo) -> Void)?
    
    var isValidConfirmPwd : Bool?
    
    
    
    
    //MARK: - view Lifecycle function
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setKeyboardInteraction()
        setViewUI()
        setupTextField()
        
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self, name: UIResponder.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.removeObserver(self, name: UIResponder.keyboardWillHideNotification, object: nil)
    }
    
    
    
    //MARK: - initial setting function
    
    private func setViewUI() {
            
        registerEmailTextField.layer.borderColor = UIColor.systemGray5.cgColor
        registerPasswordTextField.layer.borderColor = UIColor.systemGray5.cgColor
        registerNameTextField.layer.borderColor = UIColor.systemGray5.cgColor
        registerNicknameTextField.layer.borderColor = UIColor.systemGray5.cgColor
        confirmPasswordTextField.layer.borderColor = UIColor.systemGray5.cgColor
        
    }
    
    private func setupTextField() {
        textFields.forEach{tf in
            tf.addTarget(self, action: #selector(textFieldEditingChanged(_:)), for: .editingChanged)
        }
    }
    
    
    
    
    //MARK: - mainer Function
    @objc func textFieldEditingChanged(_ sender: UITextField) {
        
        let text = sender.text ?? ""
        
        switch sender{
        case registerEmailTextField:
//            self.isValidEmail = text.isValidEmail()
            self.registerEmail = text
            
        case registerNicknameTextField:
//            self.isValidNickname = text.count > 2
            self.registerNickname = text
            
        case registerPasswordTextField:
//            self.isValidPassword = text.isValidPassword()
            self.registerPassword = text
            
        case confirmPasswordTextField:
            self.isValidConfirmPwd = (self.registerPassword == text)
            
        case registerNameTextField:
//            self.isValidName = text.count > 1
            self.registerName = text
            
        default:
            fatalError("Missing TextField...")
        }
    
    }
    
    //MARK: - Button Action function
    
    
    @IBAction func dismissButtonDidTap(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
    
    @IBAction func doneButtonDidTap(_ sender: Any) {
        
        let data = RegisterUserInfo(userEmail: self.registerEmail, userNickname: self.registerNickname, userPassword: self.registerPassword, userName: self.registerName)
        
        //print(data.userEmail)

        postRegisterInfo(data: data)
        
        self.dismiss(animated: true, completion: nil)
    }
    
    func textFieldDidBeginEditing(_ textField: UITextField) {
        let textFieldFrame = textField.convert(textField.bounds, to: registerScrollView)
        let offset = CGPoint(x: 0, y: textFieldFrame.origin.y - (view.bounds.height / 2) + (textFieldFrame.height / 2) + 100)
        registerScrollView.setContentOffset(offset, animated: true)
    }
    
    
    //MARK: - API manegement function
    
//    func postRegisterInfo(data: RegisterUserInfo) {
//
//        guard let serverUrl = Bundle.main.object(forInfoDictionaryKey: "ServerUrl") as? String else {
//            print("Error: serverUrl not found in Info.plist")
//            return
//        }
//
//        let postData = PostRegisterInfo(email: data.userEmail, password: data.userPassword, name: data.userName
//                                        , nickname: data.userNickname)
//
//        guard let uploadData = try? JSONEncoder().encode(postData)
//        else {return}
//
//        let url = URL(string: "\(serverUrl)/users/sign-up")
//
//        var request = URLRequest(url: url!)
//
//        request.httpMethod = "POST"
//
//        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
//
//        let task = URLSession.shared.uploadTask(with: request, from: uploadData) { (data, response, error) in
//
//            if let e = error {
//                NSLog("An error has occured: \(e.localizedDescription)")
//                return
//            }
//
//            print("userRegisterInfo Post success")
//            print("\n\(postData)")
//        }
//
//        task.resume()
//
//    }
    
    // 응답을 디코드하기 위한 구조체를 정의합니다.
    struct RegisterResponse: Codable {
        let isSuccess: Bool
        let responseCode: Int
        let responseMessage: String
        let result: RegisteredUser
    }

    struct RegisteredUser: Codable {
        let id: Int
        let email: String
        let username: String
        let nickname: String
    }

    func postRegisterInfo(data: RegisterUserInfo) {
        guard let serverUrl = Bundle.main.object(forInfoDictionaryKey: "ServerUrl") as? String else {
            print("Error: serverUrl not found in Info.plist")
            return
        }
        
        let postData = PostRegisterInfo(email: data.userEmail, password: data.userPassword, name: data.userName, nickname: data.userNickname)
        
        guard let uploadData = try? JSONEncoder().encode(postData) else {
            return
        }
        
        let url = URL(string: "\(serverUrl)/users/sign-up")!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        
        let task = URLSession.shared.uploadTask(with: request, from: uploadData) { (data, response, error) in
               if let e = error {
                   NSLog("An error has occured: \(e.localizedDescription)")
                   return
               }
               
               guard let data = data else {
                   print("Data was not received from the server.")
                   return
               }

               do {
                   let apiResponse = try JSONDecoder().decode(RegisterResponse.self, from: data)
                   if apiResponse.isSuccess {
                       print("userRegisterInfo Post success")
                       print("\n\(postData)")

                       // 회원가입에 성공하면 postTimeTable()을 호출합니다.
                       self.postTimeTable(completion: { success in
                           if success {
                               print("TimeTable post request succeeded.")
                           } else {
                               print("TimeTable post request failed.")
                           }
                       })
                   } else {
                       print("Registration failed with message: \(apiResponse.responseMessage)")
                   }
               } catch {
                   print("Error decoding registration response: \(error)")
               }
           }
           task.resume()
    }

    
    func postTimeTable(completion: @escaping (Bool) -> Void) {
        guard let serverUrl = Bundle.main.object(forInfoDictionaryKey: "ServerUrl") as? String else {
            print("Error: serverUrl not found in Info.plist")
            completion(false)
            return
        }
        
        guard let accessToken = UserDefaults.standard.string(forKey: "accessToken") else {
            print("Error: accessToken not found in UserDefaults")
            completion(false)
            return
        }
        
        let url = URL(string: "\(serverUrl)/time-table")!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        
        // Add the access token to the request header
        request.setValue("Bearer \(accessToken)", forHTTPHeaderField: "Authorization")

        let task = URLSession.shared.dataTask(with: request) { (data, response, error) in
            if let e = error {
                print("An error occurred: \(e.localizedDescription)")
                completion(false)
                return
            }
            
            guard let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode == 200 else {
                completion(false)
                return
            }

            // Handle response data as needed...
            
            completion(true)
        }
        
        task.resume()
    }

    
    
    
    
    
    
    
    //MARK: - keyboard manegement function
    
    func setKeyboardInteraction() {
        
        self.hideKeyboardWhenTappedAround()
        
        initialRegisterViewButtomConstraint = registerViewButtomConstraint.constant

        
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
    
    @objc func keyboardWillShow(_ notification: NSNotification) {
        // Move the view only when the emailTextField or the passwordTextField are being edited
        if registerEmailTextField.isEditing || registerPasswordTextField.isEditing || registerNameTextField.isEditing || registerNicknameTextField.isEditing ||
            confirmPasswordTextField.isEditing{
            moveViewWithKeyboard(notification: notification, viewBottomConstraint: self.registerViewButtomConstraint, keyboardWillShow: true)
        }
        
    }
    
    @objc func keyboardWillHide(_ notification: NSNotification) {
        moveViewWithKeyboard(notification: notification, viewBottomConstraint: self.registerViewButtomConstraint, keyboardWillShow: false)
        
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
            viewBottomConstraint.constant = initialRegisterViewButtomConstraint + keyboardHeight
        }else {
            //            viewBottomConstraint.constant = viewBottomConstraint.constant - (safeAreaExists ? keyboardHeight : keyboardHeight)
            viewBottomConstraint.constant = initialRegisterViewButtomConstraint
        }
        
        // Animate the view the same way the keyboard animates
        let animator = UIViewPropertyAnimator(duration: keyboardDuration, curve: keyboardCurve) { [weak self] in
            // Update Constraints
            self?.view.layoutIfNeeded()
        }
        
        // Perform the animation
        animator.startAnimation(afterDelay: 0.055)
        
    }
    
    
    
    
}
