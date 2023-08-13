//
//  registerViewController.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/11.
//

import UIKit

class registerViewController: UIViewController {
    
    
    @IBOutlet weak var registerScrollView: UIScrollView!
    
    
    @IBOutlet weak var profileImageButton: UIButton!
    
    @IBOutlet weak var registerEmailTextField: PaddingtextField!
    
    @IBOutlet weak var registerPasswordTextField: PaddingtextField!
    
    @IBOutlet weak var registerNameTextField: PaddingtextField!
    
    @IBOutlet weak var registerNicknameTextField: PaddingtextField!
    
    @IBOutlet weak var registerViewButtomConstraint: NSLayoutConstraint!
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setKeyboardInteraction()
        setViewUI()

    }
    
    private func setViewUI() {
        
        registerEmailTextField.layer.borderColor = UIColor.systemGray5.cgColor
        registerPasswordTextField.layer.borderColor = UIColor.systemGray5.cgColor
        registerNameTextField.layer.borderColor = UIColor.systemGray5.cgColor
        registerNicknameTextField.layer.borderColor = UIColor.systemGray5.cgColor
    }
    
    
    
    @IBAction func dismissButtonDidTap(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
    
    @IBAction func doneButtonDidTap(_ sender: Any) {
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
        if registerEmailTextField.isEditing || registerPasswordTextField.isEditing || registerNameTextField.isEditing || registerNicknameTextField.isEditing {
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
            
            let newOffset = CGPoint(x: registerScrollView.contentOffset.x, y: registerScrollView.contentOffset.y + 150)
            registerScrollView.setContentOffset(newOffset, animated: true)

            viewBottomConstraint.constant = keyboardHeight + (safeAreaExists ? 0 : viewBottomConstraint.constant)
        }else {
            viewBottomConstraint.constant = viewBottomConstraint.constant - (safeAreaExists ? keyboardHeight : keyboardHeight)
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
