//
//  CourseTabViewController.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/11.
//

import UIKit

class CourseTableViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, UIScrollViewDelegate {
   
    
    @IBOutlet weak var registerCourseButton: UIButton!
    
//    private let apiCaller = APICaller()
    
    
    
    @IBOutlet weak var searchCourseTextField: PaddingtextField!
    
    @IBOutlet weak var courseTableView: UITableView!
    
    private var data = [Course]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
//        apiCaller.fetchData(completion: {[weak self] result in
//            switch result {
//            case .success(let data):
//                DispatchQueue.main.sync {
//                    self?.data.append(contentsOf: data)
//                    self?.courseTableView.reloadData()
//                }
//
//            case .failure(_):
//                break
//            }
//        })
        
//        fetchCourses { (response) in
//            if let response = response {
//                // 데이터를 여기에서 처리하세요. 예를 들어, `response.result.content`를 사용하여 코스 목록에 접근할 수 있습니다.
//                self.data.append(contentsOf: response.result.content)
//                for course in response.result.content {
//                    print(course.courseName)
//                }
//            } else {
//                print("Failed to fetch courses from server")
//            }
        fetchCourses { (response) in
            if let content = response?.result.content {
                self.data.append(contentsOf: content)
                for course in content {
                    print(course.courseName)
                }
            } else {
                print("Failed to fetch courses from server")
            }
            
            DispatchQueue.main.sync {
                self.courseTableView.reloadData()
            }
        }

        
        

        
        setViewUI()
        setKeyboard()
        
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
    }
    
    private func setViewUI() {
        self.registerCourseButton.layer.cornerRadius = self.registerCourseButton.layer.bounds.height / 2
        self.searchCourseTextField.layer.cornerRadius = searchCourseTextField.layer.bounds.height / 2
    }
    
    func setKeyboard() {
        self.hideKeyboardWhenTappedAround()
    }
    
    
    
    @IBAction func registerCourseButtonDidTap(_ sender: Any) {
        
        
    }
    
    
    
    
    
    
    
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = courseTableView.dequeueReusableCell(withIdentifier: "courseThumbnailCell", for: indexPath) as! CourseTableViewCell
//        cell.courseTypeLabel.layer.cornerRadius = cell.courseTypeLabel.layer.bounds.height / 2
//        cell.courseTypeLabel.clipsToBounds = true
//        cell.courseTypeLabel.layer.masksToBounds = true
        
        cell.courseTitleLabel?.text = data[indexPath.row].courseName
        cell.courseProfessorLabel?.text = data[indexPath.row].professor
        cell.courseExplainationLabel?.text = data[indexPath.row].courseSummary
        cell.courseRegisterPeopleLabel?.text = "수강: \(data[indexPath.row].registerPeople)명"
        cell.courseTypeLabel?.text = data[indexPath.row].categoryName
        

        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 80
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        performSegue(withIdentifier: "showDetailCourseSG", sender: indexPath)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showDetailCourseSG",
           let destinationVC = segue.destination as? DetailCourseViewController,
           let indexPath = sender as? IndexPath {
            let selectedCourse = data[indexPath.row] // coursesArray는 해당 아이템들의 배열이라고 가정합니다.
            destinationVC.courseId = selectedCourse.courseId
        }
    }


    struct ApiResponse: Codable {
        let isSuccess: Bool
        let responseCode: Int
        let responseMessage: String
        let result: ResultContent
    }

    struct ResultContent: Codable {
        let content: [Course]
        let page: Int
        let last: Bool
    }

    struct Course: Codable {
        let courseId: Int
        let courseName: String
        let professor: String
        let categoryName: String
        let courseSummary: String
        let registerPeople: Int
    }
    
    func fetchCourses(completion: @escaping (ApiResponse?) -> Void) {
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

        let url = URL(string: "\(serverUrl)/courses")! // 여기에 실제 엔드포인트를 지정하세요.
        var request = URLRequest(url: url)
        request.httpMethod = "GET"
        request.setValue("Bearer \(accessToken)", forHTTPHeaderField: "Authorization")

        let task = URLSession.shared.dataTask(with: request) { (data, response, error) in
            if let e = error {
                print("An error occurred: \(e.localizedDescription)")
                completion(nil)
                return
            }
            
            guard let data = data else {
                print("No data received from server")
                completion(nil)
                return
            }

            do {
                let apiResponse = try JSONDecoder().decode(ApiResponse.self, from: data)
                completion(apiResponse)
            } catch {
                print("Error decoding the response: \(error)")
                completion(nil)
            }
        }
        
        task.resume()
    }


    
    
    
    
    
    
    
//    private func createSpinnerFooter() -> UIView {
//        let footerView = UIView(frame: CGRect(x: 0, y: 0, width: view.frame.size.width, height: 100))
//
//        let spinner = UIActivityIndicatorView()
//        spinner.center = footerView.center
//        footerView.addSubview(spinner)
//        spinner.startAnimating()
//
//        return footerView
//    }
    
//    func scrollViewDidScroll(_ scrollView: UIScrollView) {
//        let position = scrollView.contentOffset.y
//        if position > (courseTableView.contentSize.height - 100 - scrollView.frame.size.height) {
//            //fetch more data
//            guard !apiCaller.isPaginating else {
//                //we are already fetching more data
//                return
//            }
//
//            self.courseTableView.tableFooterView = createSpinnerFooter()
//
//            apiCaller.fetchData(pagination: true) { [weak self] result in
//                DispatchQueue.main.async {
//                    self?.courseTableView.tableFooterView = nil
//                }
//                switch result {
//                case .success(let moreData) :
//                    self?.data.append(contentsOf: moreData)
//                    DispatchQueue.main.async {
//                        self?.courseTableView.reloadData()
//                    }
//                case .failure(_):
//                    break
//                }
//
//            }
//        }
//    }
    
}
