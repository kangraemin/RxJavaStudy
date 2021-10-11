//
//  ControlTableViewController.swift
//  RxStudy
//
//  Created by Mingu Seo on 2021/09/16.
//

import Foundation
import UIKit

enum RxStudyCategories: String {
    case ObserverPattern = "ObserverPattern"
    case StreamWithScheduler = "StreamWithScheduler"
    case Subject = "Subject"
    case DataEmittOperators = "DataEmittOperators"
    case TransformOperators = "TransformOperators"
}

class ControlTableViewController: UIViewController {
    
    @IBOutlet weak var tableView: UITableView!
    let categories = [RxStudyCategories.ObserverPattern, RxStudyCategories.StreamWithScheduler, RxStudyCategories.Subject, RxStudyCategories.DataEmittOperators, RxStudyCategories.TransformOperators]
}

extension ControlTableViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return categories.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = self.tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)
        cell.textLabel?.text = categories[indexPath.row].rawValue
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        switch categories[indexPath.row] {
        case .ObserverPattern :
            guard let viewController = self.storyboard?.instantiateViewController(withIdentifier: "ObserverPattern") as? MainViewController else {
                fatalError()
            }
            self.navigationController?.pushViewController(viewController, animated: true)
            
        case .StreamWithScheduler :
            guard let viewController = self.storyboard?.instantiateViewController(withIdentifier: "StreamWithScheduler") as? StreamWithSchedulerViewController else {
                fatalError()
            }
            self.navigationController?.pushViewController(viewController, animated: true)
            
        case .Subject:
            guard let viewController = self.storyboard?.instantiateViewController(withIdentifier: "Subject") as? SubjectViewController else {
                fatalError()
            }
            self.navigationController?.pushViewController(viewController, animated: true)
        
        case .DataEmittOperators:
            guard let viewController = self.storyboard?.instantiateViewController(withIdentifier: "DataEmittOperators") as? DataEmittOperatorsViewController else {
                fatalError()
            }
            self.navigationController?.pushViewController(viewController, animated: true)
            
        case .TransformOperators:
            guard let viewController = self.storyboard?.instantiateViewController(withIdentifier: "TransformOperators") as? TransformOperatorsViewController else {
                fatalError()
            }
            self.navigationController?.pushViewController(viewController, animated: true)
        }
    }
}
