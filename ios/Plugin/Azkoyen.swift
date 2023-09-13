import Foundation

@objc public class Azkoyen: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
