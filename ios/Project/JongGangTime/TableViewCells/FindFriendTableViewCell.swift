//
//  FindFriendTableViewCell.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/16.
//

import UIKit

class FindFriendTableViewCell: UITableViewCell {
    
    
    @IBOutlet weak var friendNicknameLabel: UILabel!
    
    @IBOutlet weak var friendNameLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

    }

}
