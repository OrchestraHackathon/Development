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

    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setViewUI()
        
        //       fetchCourseDetail()
        
        let dummyData = createDummyData()
        updateUI(with: dummyData)
    }
    
    func setViewUI() {
        self.addToTimeTableButton.layer.cornerRadius = self.addToTimeTableButton.layer.bounds.height / 2

    }
    
    func createDummyData() -> CourseDetail {
        return CourseDetail(courseId: 1,
                            courseName: "식단과 운동",
                            professor: "김영양",
                            registerPeople: 30,
                            categories: [CourseCategory(categoryId: 1), CourseCategory(categoryId: 2)],
                            courseSummary: "밥묵자",
                            courseDetails: "하얗게 피어난 얼음 꽃 하나가 달가운 바람에 얼굴을 내밀어 눈부신 하늘에 ㅁㄴㅇㄹㅁㄴㄹ")
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
    
    
    @IBAction func dissmissbuttonDidTap(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
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
