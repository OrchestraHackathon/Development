//
//  ViewControllerExtension.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/12.
//

import UIKit

class ViewControllerExtension: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

    }
}

extension UIViewController {
    
    func hideKeyboardWhenTappedAround() {
           let tap = UITapGestureRecognizer(target: self, action: #selector(UIViewController.dismissKeyboard))
           tap.cancelsTouchesInView = false
           view.addGestureRecognizer(tap)
       }

       @objc func dismissKeyboard() {
           view.endEditing(true)
       }
}
