//
//  PostLoginInfo.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/15.
//

import UIKit

class PostLoginInfo : Codable {
    let email: String
    let password: String
    
    enum CodingKeys: String, CodingKey {
        case email = "email"
        case password = "password"
    }
    
    init(email: String, password: String) {
        self.email = email
        self.password = password
    }
}
