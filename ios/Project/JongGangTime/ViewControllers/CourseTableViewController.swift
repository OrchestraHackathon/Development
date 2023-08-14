//
//  CourseTabViewController.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/11.
//

import UIKit

class CourseTableViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, UIScrollViewDelegate {
   
    
    @IBOutlet weak var registerCourseButton: UIButton!
    
    private let apiCaller = APICaller()
    
    
    
    @IBOutlet weak var searchCourseTextField: PaddingtextField!
    
    @IBOutlet weak var courseTableView: UITableView!
    
    private var data = [Course]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        apiCaller.fetchData(completion: {[weak self] result in
            switch result {
            case .success(let data):
                DispatchQueue.main.sync {
                    self?.data.append(contentsOf: data)
                    self?.courseTableView.reloadData()
                }
                
            case .failure(_):
                break
            }
        })
        
        setViewUI()
        
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
    }
    
    private func setViewUI() {
        self.registerCourseButton.layer.cornerRadius = self.registerCourseButton.layer.bounds.height / 2
        self.searchCourseTextField.layer.cornerRadius = searchCourseTextField.layer.bounds.height / 2
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
        cell.courseExplainationLabel?.text = data[indexPath.row].courseDetails
        cell.courseRegisterPeopleLabel?.text = "수강: \(data[indexPath.row].registerPeople)명"
        cell.courseTypeLabel?.text = data[indexPath.row].categoryName
        

        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 80
    }
    
    
    
    
    
    
    
    
    private func createSpinnerFooter() -> UIView {
        let footerView = UIView(frame: CGRect(x: 0, y: 0, width: view.frame.size.width, height: 100))
        
        let spinner = UIActivityIndicatorView()
        spinner.center = footerView.center
        footerView.addSubview(spinner)
        spinner.startAnimating()
        
        return footerView
    }
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        let position = scrollView.contentOffset.y
        if position > (courseTableView.contentSize.height - 100 - scrollView.frame.size.height) {
            //fetch more data
            guard !apiCaller.isPaginating else {
                //we are already fetching more data
                return
            }
            
            self.courseTableView.tableFooterView = createSpinnerFooter()
            
            apiCaller.fetchData(pagination: true) { [weak self] result in
                DispatchQueue.main.async {
                    self?.courseTableView.tableFooterView = nil
                }
                switch result {
                case .success(let moreData) :
                    self?.data.append(contentsOf: moreData)
                    DispatchQueue.main.async {
                        self?.courseTableView.reloadData()
                    }
                case .failure(_):
                    break
                }
                
            }
        }
    }
    
}
