//
//  BottomSheetUIView.swift
//  JongGangTime
//
//  Created by 정재연 on 2023/08/16.
//

import UIKit

class BottomSheetUIView: UIView {

    private let minHeight: CGFloat = 60.0
    private let defaultHeight: CGFloat = 150.0
    private let maxHeight: CGFloat = 600.0
    private var viewHeightState: ViewHeightState = .defaulted

    @IBOutlet var viewHeightConstraint: NSLayoutConstraint!

    func setupPanGesture() {
        let panGesture = UIPanGestureRecognizer(target: self, action: #selector(handlePanGesture(_:)))
        addGestureRecognizer(panGesture)
    }

    @objc func handlePanGesture(_ gesture: UIPanGestureRecognizer) {

        let translation = gesture.translation(in: self)
        let velocity = gesture.velocity(in: self)

        if gesture.state == .changed {
            // Calculate new height based on gesture
            var newHeight =  viewHeightConstraint!.constant - translation.y

            if(newHeight <= minHeight) {
                newHeight = minHeight
            }else if newHeight >= maxHeight {
                newHeight = maxHeight
            }


            // Apply new height
            viewHeightConstraint?.constant = newHeight
            self.superview?.layoutIfNeeded()

            // Reset the translation
            gesture.setTranslation(CGPoint.zero, in: self)

        }else if gesture.state == .ended {

            if velocity.y < 0 {
                expandView()
            }
            else if velocity.y > 0 {
                collapseView()
            }
        }
    }


    public func getViewState() -> ViewHeightState {
        return self.viewHeightState
    }

    public func expandView() {

        switch viewHeightState {
        case .defaulted:
            viewHeightConstraint.constant = maxHeight
            viewHeightState = .expanded
        case .shrinked:
            viewHeightConstraint.constant = defaultHeight
            viewHeightState = .defaulted

        case .expanded:
            return // 이미 확장된 상태이므로 아무것도 하지 않음
        }

        UIView.animate(withDuration: 0.2) {
            self.layoutIfNeeded()
            self.superview?.layoutIfNeeded()
        }

    }

    public func collapseView() {

        switch viewHeightState {
        case .defaulted:
            viewHeightConstraint.constant = minHeight
            viewHeightState = .shrinked

        case .shrinked:
            return // 이미 축소된 상태이므로 아무것도 하지 않음
        case .expanded:
            viewHeightConstraint.constant = defaultHeight
            viewHeightState = .defaulted
        }

        UIView.animate(withDuration: 0.2) {
            self.layoutIfNeeded()
            self.superview?.layoutIfNeeded()
        }

    }

    enum ViewHeightState {
        case defaulted
        case shrinked
        case expanded
    }


}
