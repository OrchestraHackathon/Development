//
//  SelectDayViewController.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/17.
//

import UIKit

class SelectDayViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    private var dimmingView: UIView?
    
    let dayOfWeek: [DayOfWeek] = [.sunday, .monday, .tuesday, .wednesday, .thursday, .friday, .saturday]
    
    var selectedDay : DayOfWeek?
    
    var selectedDayTimePeriod : [String]?
    
    weak var delegate: DataTransferDelegate?
    
    @IBOutlet weak var startTimeDatePicker: UIDatePicker!
    
    @IBOutlet weak var endTimeDatePicker: UIDatePicker!
    

    
    enum DayOfWeek: Int {
        case monday = 1
        case tuesday = 2
        case wednesday = 3
        case thursday = 4
        case friday = 5
        case saturday = 6
        case sunday = 7
        
        var description: String {
            switch self {
            case .sunday: return "일요일"
            case .monday: return "월요일"
            case .tuesday: return "화요일"
            case .wednesday: return "수요일"
            case .thursday: return "목요일"
            case .friday: return "금요일"
            case .saturday: return "토요일"
            }
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        setViewUI()
        
        startTimeDatePicker.locale = Locale(identifier: "ko_KR")
        endTimeDatePicker.locale = Locale(identifier: "ko_KR")
        
        startTimeDatePicker.minuteInterval = 10
        endTimeDatePicker.minuteInterval = 10
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        UIView.animate(withDuration: 0.3) {
            self.dimmingView?.backgroundColor = UIColor.black.withAlphaComponent(0.5)
        }
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        
        UIView.animate(withDuration: 0.3) {
            self.dimmingView?.backgroundColor = UIColor.black.withAlphaComponent(0)
        } completion: { _ in
            self.dimmingView?.removeFromSuperview()
        }
    }
    
    func setViewUI() {
        dimmingView = UIView(frame: view.bounds)
        dimmingView?.backgroundColor = UIColor.black.withAlphaComponent(0)
        if let dimmingView = dimmingView {
            presentingViewController?.view.addSubview(dimmingView)
        }
        
        
    
    }
    
    
    
    @IBAction func cancelButtonDidTap(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
    
    
    @IBAction func confirmButtonDidTap(_ sender: Any) {
        
        let startTime = startTimeDatePicker.date
        let formatter = DateFormatter()
        formatter.locale = Locale(identifier: "ko_KR")
        formatter.dateFormat = "HH:mm"
        let startTimeString = formatter.string(from: startTime)
        
        let endTime = endTimeDatePicker.date
        let endFormatter = DateFormatter()
        endFormatter.locale = Locale(identifier: "ko_KR")
        endFormatter.dateFormat = "HH:mm"
        let endTimeString = formatter.string(from: endTime)
        
        selectedDayTimePeriod = ["\(selectedDay!.rawValue)", startTimeString, endTimeString]

        
        delegate?.transferTimePeriod(data: selectedDayTimePeriod!)
        
        print("delegate success!!!\n\n\n")
        self.dismiss(animated: true, completion: nil)
        
    }
    
    
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return dayOfWeek.count
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        self.selectedDay = dayOfWeek[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return dayOfWeek[row].description
    }
    
    
    
    
    
    



}
