## **과제 내용**

1. PublishSubject / BehaviorSubject / AsyncSubject / ReplaySubject를 어떨 때 사용하면 좋을 지 찾아서 공유 해 주세요.
2. Subject를 활용하여 데이터를 발행 / 구독 해 보고, 어떻게 동작하는지 확인 해 주세요. 수업때 보았던 예시코드( [cbfbad1](https://github.com/kangraemin/RxJavaStudy/commit/cbfbad1ad23e9a3dca9d5aba3d110c6227a17590) )들을 활용해도 좋고, 직접 만들어 보셔도 좋습니다

## **과제 목적**

Subject의 활용처를 미리 생각 해 볼 수 있습니다.Subject의 사용법에 익숙 해 질 수 있습니다.

## **과제 수행**

**1.PublishSubject**

- **[정의]**
    - 구독 시점 이전에 발행된 데이터는 무시하고, 구독 시점 이후에 발행된 데이터를 발행하는 Subject 입니다.
- **[예시]**
    - 네트워크 호출과 같은 서비스 로직 처리시 에러가 발생했을 때, 에러창을 보여줄 때 사용할 수 있다.
    
    ```swift
    class ViewViewModel {    
        ...
       let onShowError = PublishSubject<SingleButtonAlert>()
      ....
         func dealLogic() {
            ...
                에러 발생 {
                 self?.onShowError.do(:onNext(okAlert))
            ....
    }
    
    class ViewController { 
    
    ...logic 처리
            
    ...에러 밣생 
            viewModel.
                .onShowError
                .map { [weak self] in self?.presentSingleButtonDialog(alert: $0)
                .subscribe()
                .disposed(by:disposeBag)
        ...
    }
    ```
    

**2.BehaviorSubject**

- **[정의]**
    - 구독 시점 이전에 발행된 데이터 중 가장 최근에 발행된 데이터 하나만 발행하고, 구독 시점 이후에 발행된 데이터를 발행하는 Subject
- **[예시]**
    - 이메일, 비밀번호 등과 같은 텍스트 필드 유효성 검사시에 사용한다.
    - email텍스트 필드 값을 behaviorsubject에 빈 문자열 값을 초기값으로 주고, 텍스트 필드와 behaviorsubject를 바인딩(구독) 시킨뒤, map 연산자를 적용하여 텍스트필드 입력값들에 대한 유효성 검사(bool)를 한다.
    - map 연산 적용후 true라면 '제출'버튼이 enable하게, false라면 disable한 상태로 변경해준다.
    
    ```swift
    class ViewController: UIViewController {
        // Outlets
        @IBOutlet weak var submitButton: UIButton!
        @IBOutlet weak var emailTextField: UITextField!
        
        // Rx variables
        // 2
        var emailSubject = BehaviorSubject<String>(value: "")
        // 3
        let disposeBag = DisposeBag()
        
        override func viewDidLoad() {
            super.viewDidLoad()
            setupBindings()
        }
        
        // 4
        func setupBindings() {
            // bind textfield value to emailSubject
            // then dispose in disposeBag (created at step 3)
            emailTextField.rx.text.bind(to: emailSubject).disposed(by: disposeBag)
            
            // 5
            emailSubject
                .map { $0!.validateEmail() }
                .bind(to: submitButton.rx.isEnabled)
                .disposed(by: disposeBag)
        }
    }
    
    // 6
    extension String {
        func validateEmail() -> Bool {
            let emailRegex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}"
            let predicate = NSPredicate(format:"SELF MATCHES %@", emailRegex)
            return predicate.evaluate(with: self)
        }
    }
    ```
    
    출처 : [https://betterprogramming.pub/form-validation-in-ios-apps-made-easy-with-rxswift-266123042bb6](https://betterprogramming.pub/form-validation-in-ios-apps-made-easy-with-rxswift-266123042bb6)
    

**3.AsyncSubject**

- **[정의]**
    - 데이터 발행이 완료되면, 데이터 발행 완료 직전에 발행된 데이터만 발행하는 Subject
- **[예시]**
    - SNS에서 좋아요 버튼을 연속으로 여러번 클릭할 수 있는데, 마지막 최종 클릭한 이벤트만 받아서 서버에 넘겨줄때 사용할 수 있겠다.

**4.ReplaySubject**

- **[정의]**
    - 구독 시점 이전에 발행된 데이터 모두를 발행하고, 구독 시점 이후에 발행된 데이터를 발행하는 Subject 입니다.
- **[예시]**
    - 모바일과 디바이스(IoT) 간 블루투스(BLE) 통신을 위해 페어링 후, 디바이스 동작 제어를 위해 characteristic 값을 디바이스로부터 받아올 때 사용할 수 있을듯.
        
        (PublishSubject로 진행해도 될것 같긴 합니다..)
        
    
