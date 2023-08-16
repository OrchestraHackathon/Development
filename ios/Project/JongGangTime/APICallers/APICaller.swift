////
////  APICaller.swift
////  JongGangTime
////
////  Created by 정재연 on 2023/08/13.
////
//
//import Foundation
//
//class APICaller {
//    var isPaginating = false
//    
//    func fetchData(pagination:Bool = false, completion: @escaping (Result<[Course], Error>) -> Void){
//        
//        if pagination {
//            isPaginating = true
//        }
//        
//        DispatchQueue.global().asyncAfter(deadline: .now() + (pagination ? 3 : 2) , execute: {
//            let originalData = [
//                Course(courseId: 1, courseName: "알바", professor: "노현아", categoryName: "알바", courseDetails: "아르바이트 시간 관리를 위한 과목입니다.", registerPeople: 103),
//                Course(courseId: 2, courseName: "프로그래밍 기초", professor: "이코드", categoryName: "스터디", courseDetails: "프로그래밍의 기초를 배우는 과목입니다.", registerPeople: 150),
//                Course(courseId: 3, courseName: "디자인 원론", professor: "박아트", categoryName: "스터디", courseDetails: "디자인의 기본 원칙과 원리를 배우는 과목입니다.", registerPeople: 75),
//                
//            ]
//            
//            let newData = [Course(courseId: 4, courseName: "경영학 개론", professor: "이경영", categoryName: "스터디", courseDetails: "기업 경영의 기본적인 원리를 학습하는 과목입니다.", registerPeople: 200),
//                           Course(courseId: 5, courseName: "데이터 분석 기초", professor: "정데이터", categoryName: "자격증", courseDetails: "데이터를 분석하는 기본적인 방법을 배우는 과목입니다.", registerPeople: 120)]
//            
//            completion(.success(pagination ? newData : originalData))
//            
//            if pagination {
//                self.isPaginating = false
//            }
//        })
//    }
//}
