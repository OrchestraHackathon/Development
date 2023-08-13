//
//  APICaller.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/13.
//

import Foundation

class APICaller {
    var isPaginating = false
    
    func fetchData(pagination:Bool = false, completion: @escaping (Result<[String], Error>) -> Void){
        
        if pagination {
            isPaginating = true
        }
        
        DispatchQueue.global().asyncAfter(deadline: .now() + (pagination ? 3 : 2) , execute: {
            let originalData = [
                "Apple",
                "Google"
            ]
            
            let newData = ["dam", "mbti","cute"]
            
            completion(.success(pagination ? newData : originalData))
            
            if pagination {
                self.isPaginating = false
            }
        })
    }
}
