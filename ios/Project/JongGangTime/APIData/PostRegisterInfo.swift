//
//  PostRegisterInfo.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/15.
//

import UIKit

class PostRegisterInfo : Codable {
    
    let email: String
    let password: String
    let name: String
    let nickname: String
    
    enum CodingKeys: String, CodingKey {
        
        case email = "email"
        case password = "password"
        case name = "userName"
        case nickname = "userNickname"
    }
    
    init(email: String, password: String, name: String, nickname: String) {
        self.email = email
        self.password = password
        self.name = name
        self.nickname = nickname
    }
}
