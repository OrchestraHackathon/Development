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
    
    
    
    @IBOutlet weak var mainScrollView: UIScrollView!
    

    @IBOutlet weak var friendRequestTableView: UITableView!
    
    @IBOutlet weak var friendRequestTableViewHeight: NSLayoutConstraint!
    

    
    
    
    @IBOutlet weak var myFriendTableView: UITableView!
    
    @IBOutlet weak var myFriendTableViewHeight: NSLayoutConstraint!
    
    
    
    @IBOutlet weak var findFriendBaseView: UIView!
    
    @IBOutlet weak var findFriendTableView: UITableView!
    
    
    
    
    
    private var tableViewCellHeight: CGFloat = 40
    
    private var tableViewSectionHeight: CGFloat = 20
    
    
    var sendedRequests = ["남보우", "이주언"]
    var receivedRequests = ["박지원", "이준영"]
    
    var friendRequests : [[String]]!
    
    var friends = ["임한결", "김태규", "남수아", "박재형"]
    
    var nicknameMatchList = ["정재연", "재연", "정재", "정"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        friendRequests = [sendedRequests, receivedRequests]
        setViewUI()
        setUpKeyboardSetting()

    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(true)
        
        //friendRequestTableView Height
        var tableViewHeight: CGFloat = 0
        let numberOfRows = sendedRequests.count + receivedRequests.count

        for i in (0..<numberOfRows) {
            tableViewHeight += tableViewCellHeight
        }
        
        tableViewHeight += tableViewSectionHeight * 2
        tableViewHeight += 50

        friendRequestTableViewHeight.constant = tableViewHeight
        

        //myFriendTableView Height
        var friendTableViewHeight: CGFloat = 0
        let numberOfFriends = friends.count
        
        for i in (0..<numberOfFriends) {
            friendTableViewHeight += tableViewCellHeight
        }
        
        friendTableViewHeight += tableViewSectionHeight
        friendTableViewHeight += 30

        myFriendTableViewHeight.constant = friendTableViewHeight
        
        mainScrollView.updateConstraints()

    }
    
    func setViewUI() {
        
        setupTextField()
        
        friendRequestTableView.layer.borderWidth = 1
        friendRequestTableView.layer.cornerRadius = 10
        friendRequestTableView.layer.borderColor = UIColor.systemGray5.cgColor
        
        myFriendTableView.layer.borderWidth = 1
        myFriendTableView.layer.cornerRadius = 10
        myFriendTableView.layer.borderColor = UIColor.systemGray5.cgColor
        
        findByNicknameTextField.layer.cornerRadius = findByNicknameTextField.layer.bounds.height / 2
    }
    
    private func setupTextField() {
        findByNicknameTextField.addTarget(self, action: #selector(textFieldDidTouchedDown(_:)), for: .touchDown)
    }
    
    func setUpKeyboardSetting() {
        self.hideKeyboardWhenTappedAround()
    }
    
    
    
    //MARK: - Segue function
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "ShowFriendTimeTableSG2",
           let destinationVC = segue.destination as? FriendTimeTableViewController,
           let indexPath = sender as? IndexPath {
            let selectedFriend = friends[indexPath.row] // friends는 해당 아이템들의 배열이라고 가정합니다.
               destinationVC.friendName = selectedFriend
        }
    }
    
    
    
    
    @objc func textFieldDidTouchedDown(_ textField: PaddingtextField) {
        UIView.animate(withDuration: 0.2) {
            self.backToRequestViewButton.isHidden = false
            self.findFriendBaseView.isHidden = false
        }
    }
    
    
    
    @IBAction func backToRequestViewButtonDidTap(_ sender: Any) {
        UIView.animate(withDuration: 0.2) {
            self.backToRequestViewButton.isHidden = true
            self.findFriendBaseView.isHidden = true
        }
    }

    
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        
        if tableView == friendRequestTableView
        {
            let headerView = UIView()

            let headerLabel = UILabel()
            
            if section == 0 {
                headerLabel.text = "수락 대기중인 요청"
            } else {
                headerLabel.text = "내가 보낸 요청"
            }

            headerLabel.font = UIFont.boldSystemFont(ofSize: 16)
            headerLabel.frame = CGRect(x: 16, y: 0, width: tableView.bounds.width - 32, height: 28)
            headerLabel.textAlignment = .left
            headerLabel.center.y = headerView.center.y

            headerView.addSubview(headerLabel)

            return headerView
        }else if tableView == myFriendTableView{
            let headerView = UIView()

            let headerLabel = UILabel()
            
            headerLabel.text = "내 친구"

            headerLabel.font = UIFont.boldSystemFont(ofSize: 16)
            headerLabel.frame = CGRect(x: 16, y: 0, width: tableView.bounds.width - 32, height: 28)
            headerLabel.textAlignment = .left
            headerLabel.center.y = headerView.center.y

            headerView.addSubview(headerLabel)

            return headerView
        } else {
            let headerView = UIView()
            
            headerView.isHidden = true

            return headerView
        }
        
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return tableViewSectionHeight
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if tableView == findFriendTableView {
            return 50
        }
        return tableViewCellHeight
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        if tableView == friendRequestTableView {
            return 2
        } else if tableView == myFriendTableView{
            return 1
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
           
        } else if tableView == myFriendTableView {
            return friends.count
        } else {
            return nicknameMatchList.count
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
        } else if tableView == myFriendTableView {
            
            let cell = myFriendTableView.dequeueReusableCell(withIdentifier: "MyFriendTableViewCell", for: indexPath) as! MyFriendTableViewCell
            
            cell.friendNameLabel.text = friends[indexPath.row]
            
            return cell
        } else {
            
            let cell = findFriendTableView.dequeueReusableCell(withIdentifier: "FindFriendTableViewCell", for: indexPath) as! FindFriendTableViewCell
            
            cell.friendNameLabel.text = nicknameMatchList[indexPath.row]
            
            return cell
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if tableView == myFriendTableView
        {
            performSegue(withIdentifier: "ShowFriendTimeTableSG2", sender: indexPath)
        }
    }

   

}
