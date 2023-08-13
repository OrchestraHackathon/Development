//
//  MyPageTabViewController.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/11.
//

import UIKit

class MyPageTabViewController: UIViewController {
    
    
    @IBOutlet weak var myPageButton: UIButton!
    
    @IBOutlet weak var completedCourseButton: UIButton!
    
    @IBOutlet weak var profileImageButton: UIButton!
    
    @IBOutlet weak var selfIntroduceLabel: UILabel!
    
    @IBOutlet weak var userEmailLabel: UILabel!
    
    @IBOutlet weak var userNameLabel: UILabel!
    
    @IBOutlet weak var userNicknameLabel: UILabel!
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    

    @IBAction func logoutButtonDidTap(_ sender: Any) {
    }
    

    @IBAction func unregisterButtonDidTap(_ sender: Any) {
    }
    
    
    @IBAction func editSelfIntroduceButtonDidTap(_ sender: Any) {
    }
    
    
    @IBAction func myPageButtonDidTap(_ sender: Any) {
        self.completedCourseButton.setTitleColor(.opaqueSeparator, for: .normal)
        self.myPageButton.setTitleColor(.black, for: .normal)
    }
    
    
    @IBAction func completedCourseButtonDidTap(_ sender: Any) {
        self.myPageButton.setTitleColor(.opaqueSeparator, for: .normal)
        self.completedCourseButton.setTitleColor(.black, for: .normal)
    }
    
    
}
