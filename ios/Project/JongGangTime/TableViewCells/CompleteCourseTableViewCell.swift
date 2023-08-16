//
//  CompleteCourseTableViewCell.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/17.
//

import UIKit

class CompleteCourseTableViewCell: UITableViewCell {
    
    
    @IBOutlet weak var courseTitleLabel: UILabel!
    
    @IBOutlet weak var courseProfessorLabel: UILabel!
    
    @IBOutlet weak var courseSumarryLabel: UILabel!
    
    
    @IBOutlet weak var courseRegisterPeopleLabel: UILabel!
    
    
    @IBOutlet weak var courseTypeLabel: PaddingLabel!
    
    
    @IBOutlet weak var completedCourseButton: UIButton!
    
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

    }

}
