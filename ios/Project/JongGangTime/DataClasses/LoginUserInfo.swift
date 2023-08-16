//
//  LoginUserInfo.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/15.
//

import Foundation

struct LoginUserResponse: Codable {
    let isSuccess: Bool
    let responseCode: Int
    let responseMessage: String
    let result: LoginUserInfo
}

struct LoginUserInfo: Codable {
    let userId: Int
    let accessToken: String
    let refreshToken: String
}


