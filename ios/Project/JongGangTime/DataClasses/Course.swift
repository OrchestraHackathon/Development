//
//  Course.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/14.
//

import Foundation

struct Course: Codable {
    let courseId: Int
    let courseName: String
    let professor: String
    let categoryName: String
    let courseDetails: String
    let registerPeople: Int
}

struct CourseResponse: Codable {
    let isSuccess: Bool
    let responseCode: Int
    let responseMessage: String
    let result: CourseResult
}

struct CourseResult: Codable {
    let contents: [Course]
    let page: Int
    let isLast: Bool
}
