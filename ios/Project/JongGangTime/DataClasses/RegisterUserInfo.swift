//
//  RegisterUserInfo.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/15.
//
import Foundation

class RegisterUserInfo {
    
    var userEmail: String
    var userNickname: String
    var userPassword: String
    var userName: String
    
    init(userEmail: String, userNickname: String, userPassword: String, userName: String) {
        self.userEmail = userEmail
        self.userNickname = userNickname
        self.userPassword = userPassword
        self.userName = userName
    }
    
    public func getUserEmail() -> String  {
        return self.userEmail
    }
    
    public func getUserNickname() -> String {
        return self.userNickname
    }
}
