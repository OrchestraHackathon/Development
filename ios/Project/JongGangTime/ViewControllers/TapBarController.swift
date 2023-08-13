//
//  TapBarController.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/10.
//

import UIKit

class TapBarController: UITabBarController, UITabBarControllerDelegate {
    
    let rectShape = CAShapeLayer()
    let indicatorHeight: CGFloat = 3
    var indicatorWidth: CGFloat = 30
    let indicatorBottomMargin: CGFloat = 2
    let indicatorLeftMargin: CGFloat = 15
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
    }
    
    override func viewDidAppear(_ animated: Bool) {
        
        super.viewDidAppear(animated)
        rectShape.fillColor = UIColor.systemPink.cgColor
        indicatorWidth = (view.bounds.maxX / 4) // count of items
        updateTabbarIndicatorBySelectedTabIndex(index: 0) // initial position
        self.view.layer.addSublayer(rectShape)
        self.delegate = self
        
    }
    
    func updateTabbarIndicatorBySelectedTabIndex(index: Int) -> Void
    {
        
        let updatedBounds = CGRect( x: (CGFloat(index) * indicatorWidth) + indicatorLeftMargin,
                                    y: self.view.bounds.height - self.tabBar.layer.bounds.height,
                                    width: indicatorWidth - indicatorLeftMargin * 2,
                                    height: indicatorHeight)
        
        let path = CGMutablePath()
        path.addRoundedRect(in: updatedBounds, cornerWidth: 1, cornerHeight: 1)
        path.addRect(updatedBounds)
        rectShape.path = path
    }
    
    func tabBarController(_ tabBarController: UITabBarController, didSelect viewController: UIViewController) {
        
        self.updateTabbarIndicatorBySelectedTabIndex(index: tabBarController.selectedIndex)
        
    }
    
}
