//
//  CalendarTabViewController.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/11.
//

import UIKit
import Elliotable

class CalendarTabViewController: UIViewController, ElliotableDelegate, ElliotableDataSource, UITableViewDelegate, UITableViewDataSource {
    
    let courses = [ElliottEvent(courseId: "c0001", courseName: "알바", roomName: "", professor: "TEST", courseDay: .monday, startTime: "09:30", endTime: "11:00", backgroundColor: UIColor.red), ElliottEvent(courseId: "c0002", courseName: "소프트웨어공학", roomName: "", professor: "TEST", courseDay: .thursday, startTime: "12:00", endTime: "13:30", textColor: UIColor.white, backgroundColor: UIColor.blue)]
    
    private let daySymbol = ["월","화","수","목","금"]
    
    @IBOutlet weak var myTimeTableView: Elliotable!
    
    @IBOutlet weak var myFriendsTimeTableViewBorder: UIView!
    
    @IBOutlet weak var myFriendsTimeTableView: UITableView!
    
    private var friends = ["이주언","임한결", "김태규", "남수아", "박재형"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setViewUI()
        
    }
    
    //MARK: - initial View Setting function
    func setViewUI() {
        
        setTimeTable()
        myTimeTableView.delegate = self
        myTimeTableView.dataSource = self
        
        myFriendsTimeTableViewBorder.layer.borderWidth = 1
        myFriendsTimeTableViewBorder.layer.cornerRadius = 10
        myFriendsTimeTableViewBorder.layer.borderColor = UIColor.systemGray5.cgColor
        
    }
    
    func setTimeTable() {
        
        myTimeTableView.elliotBackgroundColor = UIColor.white
        myTimeTableView.borderWidth = 1
        myTimeTableView.borderColor = UIColor.systemGray5
        myTimeTableView.courseTextAlignment = .left
        myTimeTableView.textEdgeInsets = UIEdgeInsets(top: 2, left: 2, bottom: 2, right: 10)
        myTimeTableView.courseItemTextSize = 11
        myTimeTableView.layer.borderWidth = 1
        myTimeTableView.layer.cornerRadius = 10
        myTimeTableView.layer.borderColor = UIColor.systemGray5.cgColor
        
        
        
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
    
    
    
    
    
    
//MARK: - TableView functions
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return friends.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = myFriendsTimeTableView.dequeueReusableCell(withIdentifier: "FriendCell", for: indexPath) as! FriendTableViewCell
        
        cell.friendNameLabel.text = friends[indexPath.row]
        
        return cell
    }
    
    
}
