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
}
