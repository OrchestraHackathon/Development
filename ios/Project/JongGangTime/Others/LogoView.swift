//
//  LogoView.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/16.
//

import UIKit

class LogoView: UIView {

    override init(frame: CGRect) {
        super.init(frame: frame)
        setupLogo()
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setupLogo()
    }

    private func setupLogo() {
        // 시계
        let clockLayer = CAShapeLayer()
        let clockPath = UIBezierPath(ovalIn: CGRect(x: 0, y: 0, width: 100, height: 100))
        clockLayer.path = clockPath.cgPath
        clockLayer.strokeColor = UIColor.blue.cgColor
        clockLayer.lineWidth = 4
        clockLayer.fillColor = UIColor.white.cgColor
        self.layer.addSublayer(clockLayer)

        // 시계의 시, 분 바늘
        let hourHand = CALayer()
        hourHand.backgroundColor = UIColor.blue.cgColor
        hourHand.frame = CGRect(x: 49, y: 20, width: 2, height: 30)
        self.layer.addSublayer(hourHand)

        let minuteHand = CALayer()
        minuteHand.backgroundColor = UIColor.blue.cgColor
        minuteHand.frame = CGRect(x: 49, y: 20, width: 2, height: 40)
        minuteHand.anchorPoint = CGPoint(x: 0.5, y: 1.0)
        minuteHand.transform = CATransform3DMakeRotation(CGFloat.pi / 2, 0, 0, 1)
        self.layer.addSublayer(minuteHand)

        // 책
        let bookLayer = CALayer()
        bookLayer.backgroundColor = UIColor.blue.cgColor
        bookLayer.frame = CGRect(x: 20, y: 110, width: 60, height: 80)
        bookLayer.cornerRadius = 5
        self.layer.addSublayer(bookLayer)
        
        let lineSpacing: CGFloat = 10
        for i in 0..<3 {
            let lineLayer = CALayer()
            lineLayer.backgroundColor = UIColor.white.cgColor
            lineLayer.frame = CGRect(x: 20, y: 115 + (CGFloat(i) * lineSpacing), width: 60, height: 5)
            self.layer.addSublayer(lineLayer)
        }
    }
}

