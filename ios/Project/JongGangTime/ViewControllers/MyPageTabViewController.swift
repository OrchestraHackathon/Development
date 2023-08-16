//
//  MyPageTabViewController.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/11.
//

import UIKit

class MyPageTabViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    
    
    
    @IBOutlet weak var myPageButton: UIButton!
    
    @IBOutlet weak var completedCourseButton: UIButton!
    
    @IBOutlet weak var profileImageButton: UIButton!
    
    @IBOutlet weak var selfIntroduceLabel: UILabel!
    
    @IBOutlet weak var userEmailLabel: UILabel!
    
    @IBOutlet weak var userNameLabel: UILabel!
    
    @IBOutlet weak var userNicknameLabel: UILabel!
    
    @IBOutlet weak var completedCourseTableView: UITableView!
    
    
    let dummyCourseDatas = [
        Course(courseId: 1, courseName: "알바", professor: "노현아", categoryName: "알바", courseDetails: "아르바이트 시간 관리를 위한 과목입니다.", registerPeople: 103),
        Course(courseId: 2, courseName: "프로그래밍 기초", professor: "이코드", categoryName: "스터디", courseDetails: "프로그래밍의 기초를 배우는 과목입니다.", registerPeople: 150),
        Course(courseId: 3, courseName: "디자인 원론", professor: "박아트", categoryName: "스터디", courseDetails: "디자인의 기본 원칙과 원리를 배우는 과목입니다.", registerPeople: 75),
        Course(courseId: 4, courseName: "해커톤", professor: "다섯이서왔스융", categoryName: "스터디", courseDetails: "우리들의 오케스트라 1악장 입니다.", registerPeople: 90),
        
    ]
    
    
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
        self.completedCourseTableView.isHidden = true

    }
    
    
    @IBAction func completedCourseButtonDidTap(_ sender: Any) {
        self.myPageButton.setTitleColor(.opaqueSeparator, for: .normal)
        self.completedCourseButton.setTitleColor(.black, for: .normal)
        self.completedCourseTableView.isHidden = false
    }
    
    
    
    //MARK: - TableView function
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        dummyCourseDatas.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = completedCourseTableView.dequeueReusableCell(withIdentifier: "CompleteCourseCell", for: indexPath) as! CompleteCourseTableViewCell
    
        
        cell.courseTitleLabel.text = dummyCourseDatas[indexPath.row].courseName
        
        cell.courseProfessorLabel.text = dummyCourseDatas[indexPath.row].professor
        
        cell.courseSumarryLabel.text = dummyCourseDatas[indexPath.row].courseDetails
        
        cell.courseRegisterPeopleLabel.text = "수강: \(dummyCourseDatas[indexPath.row].registerPeople)"
        
        cell.courseTypeLabel.text = dummyCourseDatas[indexPath.row].categoryName
        
        let background = UIView()
        background.backgroundColor = .clear
        cell.selectedBackgroundView = background
        
        if cell.courseTitleLabel.text == "해커톤" {
            cell.completedCourseButton.setTitle("수료증 보기", for: .normal)
            cell.completedCourseButton.setTitleColor(UIColor.darkGray, for: .normal)
            cell.completedCourseButton.backgroundColor = UIColor(hexCode: "E7E7E7")
            cell.completedCourseButton.addTarget(self, action: #selector(buttonDidTap(_:)), for: .touchUpInside)

        }

        
        return cell
    }
    
    @objc func buttonDidTap(_ sender: UIButton) {
        
        performSegue(withIdentifier: "ShowCertificateSG", sender: nil)
        
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 130
    }
    
}
