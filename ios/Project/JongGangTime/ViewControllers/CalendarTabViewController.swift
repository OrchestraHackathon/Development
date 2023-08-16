//
//  CalendarTabViewController.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/11.
//

import UIKit
import Elliotable

class CalendarTabViewController: UIViewController, ElliotableDelegate, ElliotableDataSource, UITableViewDelegate, UITableViewDataSource {
    
    var courses : [ElliottEvent] = []
    
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
        
        myFriendsTimeTableViewBorder.layer.borderWidth = 1
        myFriendsTimeTableViewBorder.layer.cornerRadius = 10
        myFriendsTimeTableViewBorder.layer.borderColor = UIColor.systemGray5.cgColor
        
    }
    
    func setTimeTable() {
        
        myTimeTableView.delegate = self
        myTimeTableView.dataSource = self
        
        myTimeTableView.elliotBackgroundColor = UIColor.white
        myTimeTableView.borderWidth = 1
        myTimeTableView.borderColor = UIColor.systemGray5
        myTimeTableView.courseTextAlignment = .left
        myTimeTableView.textEdgeInsets = UIEdgeInsets(top: 2, left: 2, bottom: 2, right: 10)
        myTimeTableView.courseItemTextSize = 11
        myTimeTableView.layer.borderWidth = 1
        myTimeTableView.layer.cornerRadius = 10
        myTimeTableView.layer.borderColor = UIColor.systemGray5.cgColor
        
        fetchTimeTable { result in
            guard let timeTableResult = result else {
                print("Failed to fetch the timetable.")
                return
            }
            
            for courseInfo in timeTableResult.courseInfos {
                
                let courseId = courseInfo.courseId
                let courseName = courseInfo.courseName
                let roomName = courseInfo.courseProfessor
                let professor = courseInfo.courseProfessor
                let courseDay = courseInfo.day
                let startTime = courseInfo.startTime
                let endTime = courseInfo.endTime
                
                
                // 아래의 textColor, backgroundColor는 상수로 사용되므로 별도로 옵셔널 바인딩을 하지 않아도 됩니다.
                let newElliottEvent = ElliottEvent(courseId: String(courseId),
                                                   courseName: courseName,
                                                   roomName: roomName,
                                                   professor: professor,
                                                   courseDay: ElliotDay(rawValue: courseDay)!,
                                                   startTime: startTime,
                                                   endTime: endTime,
                                                   textColor: UIColor.white,
                                                   backgroundColor: UIColor.systemGray)
                
                // 새로운 이벤트를 courses 배열에 추가할 수 있습니다. (만약 courses가 var로 선언된 배열이라면)
                self.courses.append(newElliottEvent)
                
                DispatchQueue.main.async {
                    
                    self.myTimeTableView.reloadData()
                    
                }
                
                
                
            }
        }
        
    }
    
    
    //MARK: - Segue function
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "ShowFriendTimeTableSG",
           let destinationVC = segue.destination as? FriendTimeTableViewController,
           let indexPath = sender as? IndexPath {
            let selectedFriend = friends[indexPath.row] // friends는 해당 아이템들의 배열이라고 가정합니다.
            destinationVC.friendName = selectedFriend
        }
    }
    
    
    
    //MARK: - API Caller function
    
    struct TimeTableResponse: Codable {
        let isSuccess: Bool
        let responseCode: Int
        let responseMessage: String
        let result: TimeTableResult
    }
    
    struct TimeTableResult: Codable {
        let timeTableName: String
        let courseInfos: [CourseInfo]
    }
    
    struct CourseInfo: Codable {
        let day: Int
        let startTime: String
        let endTime: String
        let courseName: String
        let courseProfessor: String
        let courseId: Int
    }
    
    func fetchTimeTable(completion: @escaping (TimeTableResult?) -> Void) {
        guard let serverUrl = Bundle.main.object(forInfoDictionaryKey: "ServerUrl") as? String else {
            print("Error: serverUrl not found in Info.plist")
            completion(nil)
            return
        }
        
        guard let accessToken = UserDefaults.standard.string(forKey: "accessToken") else {
            print("Error: accessToken not found in UserDefaults")
            completion(nil)
            return
        }
        
        let url = URL(string: "\(serverUrl)/time-table")! // 여기서 yourEndpoint를 실제 엔드포인트로 변경해야 합니다.
        var request = URLRequest(url: url)
        request.httpMethod = "GET"
        
        // Add the access token to the request header
        request.setValue("Bearer \(accessToken)", forHTTPHeaderField: "Authorization")
        
        let task = URLSession.shared.dataTask(with: request) { (data, response, error) in
            if let e = error {
                print("An error occurred: \(e.localizedDescription)")
                completion(nil)
                return
            }
            
            guard let data = data else {
                print("Data was not received from the server.")
                completion(nil)
                return
            }
            
            do {
                let apiResponse = try JSONDecoder().decode(TimeTableResponse.self, from: data)
                if apiResponse.isSuccess {
                    completion(apiResponse.result)
                } else {
                    print("Failed with message: \(apiResponse.responseMessage)")
                    completion(nil)
                }
            } catch {
                print("Error decoding timetable response: \(error)")
                completion(nil)
            }
        }
        task.resume()
    }
    
    
    
    
    //MARK: - elliotable setting function
    
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
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        performSegue(withIdentifier: "ShowFriendTimeTableSG", sender: indexPath)
    }
    
    
    
    
    
    
}
