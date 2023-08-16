//
//  DetailCourseViewController.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/14.
//

import UIKit

class DetailCourseViewController: UIViewController, UICollectionViewDelegate, UICollectionViewDataSource {
    
    
    @IBOutlet weak var courseNameButton: UIButton!
    
    
    @IBOutlet weak var courseProfessorLabel: UILabel!
    
    
    @IBOutlet weak var courseSummaryLabel: UILabel!
    
    
    @IBOutlet weak var courseDetailsTextView: UITextView!
    
    
    @IBOutlet weak var courseRegisterPeopleLabel: UILabel!
    
    
    @IBOutlet weak var addToTimeTableButton: UIButton!
    
    var courseId : Int?
    
    private var data = [CourseCategory]()
    
    var dummyData : CourseDetail = CourseDetail(courseId: 1,
                                                courseName: "식단과 운동",
                                                professor: "김영양",
                                                registerPeople: 30,
                                                categories: [CourseCategory(categoryId: 1), CourseCategory(categoryId: 2)],
                                                courseSummary: "밥묵자",
                                                courseDetails: "하얗게 피어난 얼음 꽃 하나가달가운 바람에 얼굴을 내밀어아무 말 못했던 이름도 몰랐던지나간 날들에 눈물이 흘러 차가운 바람에 숨어 있다한줄기 햇살에 몸 녹이다그렇게 너는 또 한번 내게 온다 좋았던 기억만그리운 마음만니가 떠나간 그 길 위에이렇게 남아 서있다잊혀질 만큼만괜찮을 만큼만눈물 머금고 기다린 떨림 끝에다시 나를 피우리라")
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setViewUI()
        
        //       fetchCourseDetail()
        
        dummyData = createDummyData()
        updateUI(with: dummyData)
        
    }
    
    func setViewUI() {
        self.addToTimeTableButton.layer.cornerRadius = self.addToTimeTableButton.layer.bounds.height / 2
        self.courseDetailsTextView.textContainer.lineFragmentPadding = 0

        
    }
    
    func createDummyData() -> CourseDetail {
        return CourseDetail(courseId: 1,
                            courseName: "식단과 운동",
                            professor: "김영양",
                            registerPeople: 30,
                            categories: [CourseCategory(categoryId: 1), CourseCategory(categoryId: 2)],
                            courseSummary: "밥묵자",
                            courseDetails: "하얗게 피어난 얼음 꽃 하나가달가운 바람에 얼굴을 내밀어아무 말 못했던 이름도 몰랐던지나간 날들에 눈물이 흘러 차가운 바람에 숨어 있다한줄기 햇살에 몸 녹이다그렇게 너는 또 한번 내게 온다 좋았던 기억만그리운 마음만니가 떠나간 그 길 위에이렇게 남아 서있다잊혀질 만큼만괜찮을 만큼만눈물 머금고 기다린 떨림 끝에다시 나를 피우리라")
    }
    
    func fetchCourseDetail() {
        let urlString = "https://your-api-endpoint.com/courses/\(courseId)"
        
        guard let url = URL(string: urlString) else {
            print("Invalid URL")
            return
        }
        
        let task = URLSession.shared.dataTask(with: url) { [weak self] data, response, error in
            if let error = error {
                print("Error fetching data: \(error.localizedDescription)")
                return
            }
            
            guard let data = data else {
                print("No data fetched")
                return
            }
            
            do {
                let decoder = JSONDecoder()
                let courseResponse = try decoder.decode(CourseDetailResponse.self, from: data)
                
                if courseResponse.isSuccess {
                    DispatchQueue.main.async {
                        self?.updateUI(with: courseResponse.result)
                    }
                } else {
                    print("Error: \(courseResponse.responseMessage)")
                }
            } catch {
                print("Decoding error: \(error.localizedDescription)")
                
            }
            
        }
        
        task.resume()
    }
    
    func updateUI(with courseDetail: CourseDetail) {
        courseNameButton.setTitle(courseDetail.courseName, for: .normal)
        courseProfessorLabel.text = courseDetail.professor
        courseSummaryLabel.text = courseDetail.courseSummary
        courseDetailsTextView.text = courseDetail.courseDetails
        courseRegisterPeopleLabel.text = "\(courseDetail.registerPeople)명이 이 과목을 수강중입니다"
        data = courseDetail.categories
        // ... You can add more UI updates as per your requirement ...
    }
    
    //MARK: - Segue function
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "SetCourseTimeSG",
           let destinationVC = segue.destination as? SettingCourseTimeViewController {
            
            destinationVC.courseDetailData = dummyData

        }

    }
    
    //MARK: - Button Action
    
    @IBAction func dissmissbuttonDidTap(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }


    @IBAction func addToTimeTableButtonDidTap(_ sender: Any) {
        self.performSegue(withIdentifier: "SetCourseTimeSG", sender: (Any).self)
    }
    
    
    
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return data.count
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "DetailCategoryCell", for: indexPath) as! DetailCategoryCollectionViewCell
        
        cell.categoryLabel?.text = Category.category(forID: data[indexPath.row].categoryId)?.name
        cell.categoryLabel?.backgroundColor = Category.category(forID: data[indexPath.row].categoryId)?.color
        return cell
    }
    
}





















