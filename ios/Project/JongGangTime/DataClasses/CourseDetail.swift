//
//  CourseDetail.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/15.
//

import Foundation

struct CourseDetailResponse: Codable {
        let isSuccess: Bool
        let responseCode: Int
        let responseMessage: String
        let result: CourseDetail
    }

    struct CourseDetail: Codable {
        let courseId: Int
        let courseName: String
        let professor: String
        let registerPeople: Int
        let categories: [CourseCategory]
        let courseSummary: String
        let courseDetails: String
    }
    
struct CourseCategory: Codable {
    let categoryId: Int
}
