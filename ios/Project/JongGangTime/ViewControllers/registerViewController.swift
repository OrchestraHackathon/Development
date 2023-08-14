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
    
    @IBOutlet weak var registerViewButtomConstraint: NSLayoutConstraint!
    
    private var initialRegisterViewButtomConstraint: CGFloat!
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initialRegisterViewButtomConstraint = registerViewButtomConstraint.constant
        
        setKeyboardInteraction()
        setViewUI()

    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self, name: UIResponder.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.removeObserver(self, name: UIResponder.keyboardWillHideNotification, object: nil)
    }
    
    private func setViewUI() {
        
        registerEmailTextField.layer.borderColor = UIColor.systemGray5.cgColor
        registerPasswordTextField.layer.borderColor = UIColor.systemGray5.cgColor
        registerNameTextField.layer.borderColor = UIColor.systemGray5.cgColor
        registerNicknameTextField.layer.borderColor = UIColor.systemGray5.cgColor
        confirmPasswordTextField.layer.borderColor = UIColor.systemGray5.cgColor
    }
    
    
    
    @IBAction func dismissButtonDidTap(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
    
    @IBAction func doneButtonDidTap(_ sender: Any) {
    }
    
    func textFieldDidBeginEditing(_ textField: UITextField) {
        let textFieldFrame = textField.convert(textField.bounds, to: registerScrollView)
        let offset = CGPoint(x: 0, y: textFieldFrame.origin.y - (view.bounds.height / 2) + (textFieldFrame.height / 2) + 100)
        registerScrollView.setContentOffset(offset, animated: true)
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
