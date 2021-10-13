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
    case CombiningOperators = "CombiningOperators"
}

enum CellIdentifiers: String {
    case ObserverPattern = "ObserverPattern"
    case StreamWithScheduler = "StreamWithScheduler"
    case Subject = "Subject"
    case DataEmittOperators = "DataEmittOperators"
    case TransformOperators = "TransformOperators"
    case CombiningOperators = "CombiningOperators"
}


class ControlTableViewController: UIViewController {
    
    @IBOutlet weak var tableView: UITableView!
    let categories = [RxStudyCategories.ObserverPattern, RxStudyCategories.StreamWithScheduler, RxStudyCategories.Subject, RxStudyCategories.DataEmittOperators, RxStudyCategories.TransformOperators, RxStudyCategories.CombiningOperators]
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
            guard let viewController = self.storyboard?.instantiateViewController(withIdentifier: CellIdentifiers.ObserverPattern.rawValue) as? MainViewController else {
                fatalError()
            }
            self.navigationController?.pushViewController(viewController, animated: true)
            
        case .StreamWithScheduler :
            guard let viewController = self.storyboard?.instantiateViewController(withIdentifier: "StreamWithScheduler") as? StreamWithSchedulerViewController else {
                fatalError()
            }
            self.navigationController?.pushViewController(viewController, animated: true)
            
        case .Subject:
            guard let viewController = self.storyboard?.instantiateViewController(withIdentifier: CellIdentifiers.Subject.rawValue) as? SubjectViewController else {
                fatalError()
            }
            self.navigationController?.pushViewController(viewController, animated: true)
        
        case .DataEmittOperators:
            guard let viewController = self.storyboard?.instantiateViewController(withIdentifier: CellIdentifiers.DataEmittOperators.rawValue) as? DataEmittOperatorsViewController else {
                fatalError()
            }
            self.navigationController?.pushViewController(viewController, animated: true)
            
        case .TransformOperators:
            guard let viewController = self.storyboard?.instantiateViewController(withIdentifier: CellIdentifiers.TransformOperators.rawValue) as? TransformOperatorsViewController else {
                fatalError()
            }
            self.navigationController?.pushViewController(viewController, animated: true)
            
        case .CombiningOperators:
            guard let viewController = self.storyboard?.instantiateViewController(withIdentifier: CellIdentifiers.CombiningOperators.rawValue) as? CombiningOperatorsViewController else {
                fatalError()
            }
            self.navigationController?.pushViewController(viewController, animated: true)
        }
    }
}
