//
//  DataTransferDelegate.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/17.
//

import Foundation

protocol DataTransferDelegate: AnyObject {
    func transferTimePeriod(data: [String])
}
