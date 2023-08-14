//
//  CourseTableViewCell.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/13.
//

import UIKit

class CourseTableViewCell: UITableViewCell {
    
    @IBOutlet weak var courseTitleLabel: UILabel!
    
    @IBOutlet weak var courseProfessorLabel: UILabel!
    
    @IBOutlet weak var courseExplainationLabel: UILabel!
    
    @IBOutlet weak var courseTypeLabel: PaddingLabel!
    
    @IBOutlet weak var courseRegisterPeopleLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

    }

}
