//
//  CourseRegisterViewController.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/14.
//

import UIKit

class CourseRegisterViewController: UIViewController, UICollectionViewDelegate, UICollectionViewDataSource, UITextFieldDelegate {
    
    private let categoryItems: [String] = ["스터디", "자격증", "루틴", "운동", "알바", "취미", "기타"]
    
    @IBOutlet weak var courseTitleTextField: PaddingtextField!
    
    @IBOutlet weak var courseIntroduceTextField: PaddingtextField!
    
    @IBOutlet weak var courseDetsilsTextView: UITextView!
    
    let textViewPlaceHolder = "과목에 대한 자세한 설명을 입력해 주세요"
    
    @IBOutlet weak var registerCourseButton: UIButton!
    

    @IBOutlet weak var scrollView: UIScrollView!
    
    @IBOutlet weak var viewButtomConstraint: NSLayoutConstraint!
    
    private var initialViewButtomConstraint : CGFloat!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initialViewButtomConstraint = viewButtomConstraint.constant
        
        setViewUI()
        setKeyboardInteraction()
        
        let tapGesture = UITapGestureRecognizer(target: self, action: #selector(didTapTextView(_:)))
        view.addGestureRecognizer(tapGesture)
    }
    
    @objc
    private func didTapTextView(_ sender: Any) {
        view.endEditing(true)
    }
    
    func setViewUI() {
        courseTitleTextField.layer.borderColor = UIColor.systemGray5.cgColor
        courseIntroduceTextField.layer.borderColor = UIColor.systemGray5.cgColor
        courseDetsilsTextView.layer.borderColor = UIColor.systemGray5.cgColor
        courseDetsilsTextView.textContainerInset = UIEdgeInsets(top: 10, left: 10, bottom: 10, right: 5)
        courseDetsilsTextView.text = textViewPlaceHolder
        courseDetsilsTextView.textColor = UIColor.placeholderText
        courseDetsilsTextView.delegate = self
        
        registerCourseButton.layer.cornerRadius = registerCourseButton.layer.bounds.height / 2
    }
    
    
    @IBAction func dismissButtonDidTap(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
    
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return categoryItems.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "CategoryCell", for: indexPath) as! CategoryCollectionViewCell
        
        cell.categoryItemLabel?.text = categoryItems[indexPath.row]
        return cell
    }
    
}

extension CourseRegisterViewController: UITextViewDelegate {
    
    func textViewDidBeginEditing(_ textView: UITextView) {
        let textViewFrame = textView.convert(textView.bounds, to: scrollView)
        let offset = CGPoint(x: 0, y: textViewFrame.origin.y - (view.bounds.height / 2) + (textViewFrame.height / 2) + 100)
        scrollView.setContentOffset(offset, animated: true)
        
        if textView.text == textViewPlaceHolder {
            textView.text = nil
            textView.textColor = .black
        }
    }
    
    func textViewDidEndEditing(_ textView: UITextView) {
        if textView.text.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty {
            textView.text = textViewPlaceHolder
            textView.textColor = .lightGray
        }
    }
    
    func textView(_ textView: UITextView, shouldChangeTextIn range: NSRange, replacementText text: String) -> Bool {
        let inputString = text.trimmingCharacters(in: .whitespacesAndNewlines)
        guard let oldString = textView.text, let newRange = Range(range, in: oldString) else { return true }
        let newString = oldString.replacingCharacters(in: newRange, with: inputString).trimmingCharacters(in: .whitespacesAndNewlines)
        
        return true
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
    
    func textFieldDidBeginEditing(_ textField: UITextField) {
        let textFieldFrame = textField.convert(textField.bounds, to: scrollView)
        let offset = CGPoint(x: 0, y: textFieldFrame.origin.y - (view.bounds.height / 2) + (textFieldFrame.height / 2) + 100)
        scrollView.setContentOffset(offset, animated: true)
    }
    
    
    @objc func keyboardWillShow(_ notification: NSNotification) {
        // Move the view only when the emailTextField or the passwordTextField are being edited
        
            moveViewWithKeyboard(notification: notification, viewBottomConstraint: self.viewButtomConstraint, keyboardWillShow: true)
        
        
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
