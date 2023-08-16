//
//  SettingCourseTimeViewController.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/16.
//

import UIKit
import Elliotable

class SettingCourseTimeViewController: UIViewController, ElliotableDelegate, ElliotableDataSource, UITableViewDelegate, UITableViewDataSource, UICollectionViewDelegate, UICollectionViewDataSource, UIViewControllerTransitioningDelegate, DataTransferDelegate {
    
    
    @IBOutlet weak var bottomSheetView: BottomSheetUIView!
    
    
    @IBOutlet weak var courseNameLabel: UILabel!
    
    @IBOutlet weak var courseProfessorLabel: UILabel!
    
    @IBOutlet weak var courseIntroduceLabel: UILabel!
    
    @IBOutlet weak var courseDetailsTextView: UITextView!
    
    
    @IBOutlet weak var cancleButton: UIButton!
    
    @IBOutlet weak var confirmButton: UIButton!
    
    @IBOutlet weak var addTimeComponentButton: UIButton!
    
    @IBOutlet weak var setTimeTimeTableView: Elliotable!
    private let daySymbol = ["월","화","수","목","금"]
    
    
    
    
    
    @IBOutlet weak var timeComponentTableView: UITableView!
    @IBOutlet weak var timeTableViewHeight: NSLayoutConstraint!
    
    private var tableViewCellHeight: CGFloat = 40
    
    
    
    var timeComponents : [[DateComponents]] = []
    
    var courses : [ElliottEvent] = []
    
    var courseDetailData : CourseDetail?
    
    private var courseCategories = [CourseCategory]()
    
    
    
    
    enum DayOfWeek: Int {
        case monday = 1
        case tuesday = 2
        case wednesday = 3
        case thursday = 4
        case friday = 5
        case saturday = 6
        case sunday = 7
        
        var description: String {
            switch self {
            case .sunday: return "일요일"
            case .monday: return "월요일"
            case .tuesday: return "화요일"
            case .wednesday: return "수요일"
            case .thursday: return "목요일"
            case .friday: return "금요일"
            case .saturday: return "토요일"
            }
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setViewUI()
        bottomSheetView.setupPanGesture()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(true)
        
        var tableViewHeight: CGFloat = 0
        let numberOfRows = timeComponents.count
        
        for i in (0..<numberOfRows) {
            tableViewHeight += tableViewCellHeight
        }
        timeTableViewHeight.constant = tableViewHeight
    }
    
    func setViewUI() {
        setTimeTable()
        
        self.bottomSheetView.layer.maskedCorners = [.layerMinXMinYCorner, .layerMaxXMinYCorner]
        self.bottomSheetView.layer.cornerRadius = 15.0
        self.bottomSheetView.layer.masksToBounds = false
        self.bottomSheetView.layer.shadowColor = UIColor.black.cgColor
        self.bottomSheetView.layer.shadowOffset = CGSize(width: 0, height: 2)
        self.bottomSheetView.layer.shadowOpacity = 0.5
        self.bottomSheetView.layer.shadowRadius = 5.0
        
        self.courseDetailsTextView.textContainer.lineFragmentPadding = 0
        
        self.courseNameLabel.text = courseDetailData?.courseName
        
        self.courseProfessorLabel.text = "교수:  \(courseDetailData!.professor)"
        
        self.courseIntroduceLabel.text = courseDetailData?.courseSummary
        
        self.courseDetailsTextView.text = courseDetailData?.courseDetails
        
        self.courseCategories = courseDetailData!.categories
        
        
    }
    
    func setTimeTable() {
        
        setTimeTimeTableView.delegate = self
        setTimeTimeTableView.dataSource = self
        
        setTimeTimeTableView.elliotBackgroundColor = UIColor.white
        setTimeTimeTableView.borderWidth = 1
        setTimeTimeTableView.borderColor = UIColor.systemGray5
        setTimeTimeTableView.courseTextAlignment = .left
        setTimeTimeTableView.textEdgeInsets = UIEdgeInsets(top: 2, left: 2, bottom: 2, right: 10)
        setTimeTimeTableView.courseItemTextSize = 11
        setTimeTimeTableView.layer.borderWidth = 1
        setTimeTimeTableView.layer.cornerRadius = 10
        setTimeTimeTableView.layer.borderColor = UIColor.systemGray5.cgColor
        
        
        
    }
    
    
    
    
    @IBAction func cancelButtonDidTap(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
    
    
    @IBAction func doneButtonDidTap(_ sender: Any) {
        
        let details: [CourseDetails] = courses.map {
               CourseDetails(day: $0.courseDay.rawValue, startTime: $0.startTime, endTime: $0.endTime)
           }

           for course in courses {
               // 2. 각 코스에 대한 sendCourseDetails 함수 호출
               sendCourseDetails(timeTabledId: Int(course.courseId)!, courseDetails: details) { isSuccess in
                   if isSuccess {
                       print("요청에 성공하였습니다. Course ID: \(course.courseId)")
                       self.dismiss(animated: true, completion: nil)
                   } else {
                       print("요청에 실패하였습니다. Course ID: \(course.courseId)")
                       self.dismiss(animated: true, completion: nil)
                   }
               }
           }

        
    }
    
    
    @IBAction func addTimeComponentDidTap(_ sender: Any) {
        
        performSegue(withIdentifier: "SelectTimeComponentSG", sender: nil)
        
    }
    
    func transferTimePeriod(data: [String]) {
        
        print("data accecped!!\n\n\n\n\n")
        guard data.count == 3, let weekdayInt = Int(data[0]) else {
            return
        }
        
        let startComponents = data[1].split(separator: ":").map(String.init)
        let endComponents = data[2].split(separator: ":").map(String.init)
        
        guard startComponents.count == 2, endComponents.count == 2,
              let startHour = Int(startComponents[0]),
              let startMinute = Int(startComponents[1]),
              let endHour = Int(endComponents[0]),
              let endMinute = Int(endComponents[1]) else {
            return
        }
        
        let startDateComponent = DateComponents(hour: startHour, minute: startMinute, weekday: weekdayInt)
        let endDateComponent = DateComponents(hour: endHour, minute: endMinute, weekday: weekdayInt)
        
        let newTimeComponent = [startDateComponent, endDateComponent]
        
        timeComponents.append(newTimeComponent)
        
        
        // courseDetailData에서 필요한 정보를 가져오는데, 모든 값이 있는지 확인합니다.
        guard let courseId = courseDetailData?.courseId,
              let courseName = courseDetailData?.courseName,
              let roomName = courseDetailData?.professor,
              let professor = courseDetailData?.professor,
              let courseDay = newTimeComponent.first?.weekday,
              let startHour = newTimeComponent.first?.hour,
              let startMinute = newTimeComponent.first?.minute,
              let endHour = newTimeComponent.last?.hour,
              let endMinute = newTimeComponent.last?.minute else {
            // 하나라도 값이 없는 경우에는 함수를 종료합니다.
            return
        }
        
        let startTime = "\(startHour):\(startMinute)"
        let endTime = "\(endHour):\(endMinute)"
        
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
        courses.append(newElliottEvent)
        
        DispatchQueue.main.async {
            self.timeComponentTableView.reloadData()
            self.setTimeTimeTableView.reloadData()
            
            // 높이 계산 최적화
            self.timeTableViewHeight.constant = CGFloat(self.timeComponents.count) * self.tableViewCellHeight
        }
        
        
    }
    
    
    
    //MARK: - API caller function
    
    // 모델 정의
    struct CourseDetails: Codable {
        let day: Int
        let startTime: String
        let endTime: String
    }

    struct CourseDetailsRequestBody: Codable {
        let courseDetails: [CourseDetails]
    }

    struct ApiResponse: Codable {
        let isSuccess: Bool
        let responseCode: Int
        let responseMessage: String
    }
    
    func sendCourseDetails(timeTabledId: Int, courseDetails: [CourseDetails], completion: @escaping (Bool) -> Void) {
        guard let serverUrl = Bundle.main.object(forInfoDictionaryKey: "ServerUrl") as? String else {
            print("Error: serverUrl not found in Info.plist")
            completion(false)
            return
        }
        
        guard let accessToken = UserDefaults.standard.string(forKey: "accessToken") else {
            print("Error: accessToken not found in UserDefaults")
            completion(false)
            return
        }
        
        let url = URL(string: "\(serverUrl)/time-table-course-detail/\(timeTabledId)")!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        
        // 헤더 설정
        request.setValue("Bearer \(accessToken)", forHTTPHeaderField: "Authorization")
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        
        // Body 설정
        let requestBody = CourseDetailsRequestBody(courseDetails: courseDetails)
        guard let httpBody = try? JSONEncoder().encode(requestBody) else {
            print("Failed to encode request body.")
            completion(false)
            return
        }
        request.httpBody = httpBody
        
        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            if let e = error {
                print("An error occurred: \(e.localizedDescription)")
                completion(false)
                return
            }
            
            guard let data = data, let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode == 200 else {
                print("Error in response or status code is not 200.")
                completion(false)
                return
            }
            
            do {
                let response = try JSONDecoder().decode(ApiResponse.self, from: data)
                if response.isSuccess {
                    print(response.responseMessage)
                    completion(true)
                } else {
                    print(response.responseMessage)
                    completion(false)
                }
            } catch {
                print("Decoding error: \(error)")
                completion(false)
            }
        }
        
        task.resume()
    }


    
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
//        if segue.identifier == "SelectTimeComponentSG"
//        {
//            let popupController = segue.destination
//            popupController.modalPresentationStyle = .custom
//            popupController.transitioningDelegate = self
//
//        }
        
        if segue.identifier == "SelectTimeComponentSG",
               let popupController = segue.destination as? SelectDayViewController { // `NextViewController`는 실제 목적지 뷰 컨트롤러의 클래스 이름으로 변경해주세요.
                popupController.delegate = self
                popupController.modalPresentationStyle = .custom
                popupController.transitioningDelegate = self
            }
    }
    
    //MARK: - tableView function
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        
        return tableViewCellHeight
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return timeComponents.count
    }
    
    
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = timeComponentTableView.dequeueReusableCell(withIdentifier: "TimeComponentTableViewCell", for: indexPath) as! TimeComponentTableViewCell
        
        let weekday = timeComponents[indexPath.row].first?.weekday
        let startHour = timeComponents[indexPath.row].first?.hour
        let startMinute = timeComponents[indexPath.row].first?.minute
        let endHour = timeComponents[indexPath.row].last?.hour
        let endMinute = timeComponents[indexPath.row].last?.minute
        
        cell.dayOfTheWeekLabel.text = DayOfWeek(rawValue: weekday ?? 1)?.description
        cell.timePeriodLabel.text = String(format: "%02d:%02d - %02d:%02d", startHour ?? "12", startMinute ?? "12", endHour ?? "12", endMinute ?? "12")
        
        let background = UIView()
        background.backgroundColor = .clear
        cell.selectedBackgroundView = background
        
        cell.cellBorderView.layer.cornerRadius = cell.cellBorderView.layer.bounds.height / 2
        cell.cellBorderView.layer.borderColor = UIColor.opaqueSeparator.cgColor
        
        cell.deleteTimeComponentButton.tag = indexPath.row
        cell.deleteTimeComponentButton.addTarget(self, action: #selector(deleteButtonTapped(_:)), for: .touchUpInside)
        return cell
    }
    
    @objc func deleteButtonTapped(_ sender: UIButton) {
        
        let indexToDelete = sender.tag
        timeComponents.remove(at: indexToDelete)
        courses.remove(at: indexToDelete)
        
        DispatchQueue.main.async {
            self.timeComponentTableView.reloadData()
            
            var tableViewHeight: CGFloat = 0
            let numberOfRows = self.timeComponents.count
            
            for i in (0..<numberOfRows) {
                tableViewHeight += self.tableViewCellHeight
            }
            self.timeTableViewHeight.constant = tableViewHeight
            self.setTimeTimeTableView.reloadData()
        }
        
    }
    
    
    //MARK: - collectioinview function
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return courseCategories.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "SetTimeCategoryCell", for: indexPath) as! SetTimeCategoryCollectionViewCell
        
        cell.categoryLabel?.text = Category.category(forID: courseCategories[indexPath.row].categoryId)?.name
        cell.categoryLabel?.backgroundColor = Category.category(forID: courseCategories[indexPath.row].categoryId)?.color
        return cell
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
    
    
    func presentationController(forPresented presented: UIViewController, presenting: UIViewController?, source: UIViewController) -> UIPresentationController? {
        return CustomPresentationController(presentedViewController: presented, presenting: presenting)
    }
    
    @objc func presentPopup(_ sender: Any) {
        let popupController = UIViewController()
        popupController.view.backgroundColor = .white
        popupController.modalPresentationStyle = .custom
        popupController.transitioningDelegate = self
        present(popupController, animated: true)
    }
    
}

class CustomPresentationController: UIPresentationController {
    override var frameOfPresentedViewInContainerView: CGRect {
        let bounds = containerView!.bounds
        return CGRect(x: 0, y: bounds.height - 250, width: bounds.width, height: 250)
    }
}



