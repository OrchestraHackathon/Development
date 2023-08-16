//
//  Category.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/15.
//

import UIKit

class Category {
    let id: Int
    let name: String
    let color: UIColor
    
    init(id: Int, name: String, color: UIColor) {
        self.id = id
        self.name = name
        self.color = color
    }
    
    static let allCategories: [Category] = [
        Category(id: 1, name: "스터디", color: UIColor(hexCode: "FFD1DC")),
        Category(id: 2, name: "자격증", color: UIColor(hexCode: "5CB4F3")),
        Category(id: 3, name: "루틴", color: UIColor(hexCode: "FFCB7D")),
        Category(id: 4, name: "운동", color: UIColor(hexCode: "8DF35C")),
        Category(id: 5, name: "알바", color: UIColor(hexCode: "FFA2A2")),
        Category(id: 6, name: "취미", color: UIColor(hexCode: "D6A2FF"))
    ]
    
    static func id(forName name: String) -> Int? {
        return allCategories.first { $0.name == name }?.id
    }
    
    static func category(forID id: Int) -> Category? {
        return allCategories.first { $0.id == id }
    }
}
