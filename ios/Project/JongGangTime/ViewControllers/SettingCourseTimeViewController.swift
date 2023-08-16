//
//  SettingCourseTimeViewController.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/16.
//

import UIKit
import Elliotable

class SettingCourseTimeViewController: UIViewController, ElliotableDelegate, ElliotableDataSource, UITableViewDelegate, UITableViewDataSource, UICollectionViewDelegate, UICollectionViewDataSource {
    
    
    
    
    
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
        case sunday = 1
        case monday = 2
        case tuesday = 3
        case wednesday = 4
        case thursday = 5
        case friday = 6
        case saturday = 7
        
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
        //과목등록API호출
    }
    
    
    @IBAction func addTimeComponentDidTap(_ sender: Any) {
        
        
        
        
        
            
            var newTimeComponent = [DateComponents(hour: 14, minute: 30, weekday: 2), DateComponents(hour: 16, minute: 30, weekday: 2)]
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



}
