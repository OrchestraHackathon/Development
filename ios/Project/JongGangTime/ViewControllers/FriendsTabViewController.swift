//
//  FriendsTabViewController.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/11.
//

import UIKit

class FriendsTabViewController: UIViewController,UITableViewDelegate, UITableViewDataSource {

    
    
    
    @IBOutlet weak var backToRequestViewButton: UIButton!
    
    @IBOutlet weak var findByNicknameTextField: PaddingtextField!
    
    @IBOutlet weak var friendRequestTableView: UITableView!
    
    var sendedRequests = ["남보우", "이주언", "박지원", "이준영"]
    var receivedRequests = ["남보우", "이주언", "박지원", "이준영"]
    
    var friendRequests : [[String]]!
    
    var friends = ["임한결", "김태규", "남수아", "박재형"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        friendRequests = [sendedRequests, receivedRequests]
        setViewUI()

    }
    
    func setViewUI() {
        friendRequestTableView.layer.borderWidth = 1
        friendRequestTableView.layer.cornerRadius = 10
        friendRequestTableView.layer.borderColor = UIColor.systemGray5.cgColor
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        if tableView == friendRequestTableView {
            if section == 0 {
                return "수락 대기중인 요청"
            } else {
                return "내가 보낸 요청"
            }
        }
        return ""
    }
    
    
    func numberOfSections(in tableView: UITableView) -> Int {
        if tableView == friendRequestTableView {
            return 2
        } else {
            return 1
        }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if tableView == friendRequestTableView {
            
            if section == 0 {
                return sendedRequests.count
            } else {
                return receivedRequests.count
            }
           
        } else {
            return friends.count
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if tableView == friendRequestTableView {
                    
            let cell = friendRequestTableView.dequeueReusableCell(withIdentifier: "FriendRequestCell", for: indexPath) as! FriendRequestTableViewCell
            
            cell.friendNameLabel.text = friendRequests[indexPath.section][indexPath.row]
            
            if indexPath.section == 1 {
                cell.confirmRequestButton.isHidden = true
            }
            
            return cell
        } else {
            return FriendTableViewCell()
        }
    }

   

}
