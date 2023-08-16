//
//  TimeComponentTableViewCell.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/16.
//

import UIKit

class TimeComponentTableViewCell: UITableViewCell {
    
    
    @IBOutlet weak var dayOfTheWeekLabel: UILabel!
    
    @IBOutlet weak var timePeriodLabel: UILabel!
    
    @IBOutlet weak var cellBorderView: UIView!
    
    @IBOutlet weak var deleteTimeComponentButton: UIButton!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

    }

}
