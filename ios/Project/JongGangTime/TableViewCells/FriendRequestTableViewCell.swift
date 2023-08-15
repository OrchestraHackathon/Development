//
//  FriendRequestTableViewCell.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/15.
//

import UIKit

class FriendRequestTableViewCell: UITableViewCell {
    
    
    @IBOutlet weak var friendNameLabel: UILabel!
    
    @IBOutlet weak var confirmRequestButton: UIButton!
    
    @IBOutlet weak var declineRequestButton: UIButton!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

    }

}
