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
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initialViewButtomConstraint = viewButtomConstraint.constant
        
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
    }
    
    func setKeyboardInteraction() {
        
        self.hideKeyboardWhenTappedAround()
        
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
        performSegue(withIdentifier: "loginSuccessSG", sender: (Any).self)
    }
    
    
    @IBAction func registerButtonDidTap(_ sender: Any) {
    }
    
    func textFieldDidBeginEditing(_ textField: UITextField) {
           let textFieldFrame = textField.convert(textField.bounds, to: scrollView)
           let offset = CGPoint(x: 0, y: textFieldFrame.origin.y - (view.bounds.height / 2) + (textFieldFrame.height / 2) + 100)
           scrollView.setContentOffset(offset, animated: true)
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
    
    
    
    
    
    
}
