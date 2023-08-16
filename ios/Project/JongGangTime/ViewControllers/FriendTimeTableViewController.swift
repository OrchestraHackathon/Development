//
//  FriendTimeTableViewController.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/16.
//

import UIKit
import Elliotable

class FriendTimeTableViewController: UIViewController, ElliotableDelegate, ElliotableDataSource  {
    
    let courses = [ElliottEvent(courseId: "c0001", courseName: "알바", roomName: "", professor: "TEST", courseDay: .monday, startTime: "09:30", endTime: "11:00", backgroundColor: UIColor.red), ElliottEvent(courseId: "c0002", courseName: "소프트웨어공학", roomName: "", professor: "TEST", courseDay: .thursday, startTime: "12:00", endTime: "13:30", textColor: UIColor.white, backgroundColor: UIColor.blue)]
    
    private let daySymbol = ["월","화","수","목","금"]

    @IBOutlet weak var friendProfileImageView: UIButton!
    
    @IBOutlet weak var friendNameLabel: UILabel!
    
    @IBOutlet weak var friendNicknameLabel: UILabel!
    
    @IBOutlet weak var friendAboutMeTextView: UITextView!
    
    var friendName : String?
    
    
    @IBOutlet weak var friendTimeTableView: Elliotable!
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setViewUI()
    }
    
    func setViewUI() {
        
        setTimeTable()
        
        friendNameLabel.text = friendName
        
    }
    
    func setTimeTable() {
        
        friendTimeTableView.delegate = self
        friendTimeTableView.dataSource = self
        
        friendTimeTableView.elliotBackgroundColor = UIColor.white
        friendTimeTableView.borderWidth = 1
        friendTimeTableView.borderColor = UIColor.systemGray5
        friendTimeTableView.courseTextAlignment = .left
        friendTimeTableView.textEdgeInsets = UIEdgeInsets(top: 2, left: 2, bottom: 2, right: 10)
        friendTimeTableView.courseItemTextSize = 11
        friendTimeTableView.layer.borderWidth = 1
        friendTimeTableView.layer.cornerRadius = 10
        friendTimeTableView.layer.borderColor = UIColor.systemGray5.cgColor
    }
    
    func elliotable(elliotable: Elliotable, at textPerIndex: Int) -> String {
        return self.daySymbol[textPerIndex]
    }
      
    func numberOfDays(in elliotable: Elliotable) -> Int {
        return self.daySymbol.count
    }
    
    func courseItems(in elliotable: Elliotable) -> [ElliottEvent] {
        return courses
    }
    
    func elliotable(elliotable: Elliotable, didSelectCourse selectedCourse: ElliottEvent) {
        return
    }
    
    func elliotable(elliotable: Elliotable, didLongSelectCourse longSelectedCourse: ElliottEvent) {
        return
    }

}
