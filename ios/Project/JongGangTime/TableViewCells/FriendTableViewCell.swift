//
//  FriendTableViewCell.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/15.
//

import UIKit

class FriendTableViewCell: UITableViewCell {
    
    
    @IBOutlet weak var friendNameLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

    }

}
