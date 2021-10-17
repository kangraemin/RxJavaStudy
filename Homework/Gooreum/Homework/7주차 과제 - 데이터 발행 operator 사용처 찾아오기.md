## **과제 내용**

1. Create / Defer / Just 등 수업때 다루었던 데이터 발행 operator를 어떨 때 사용하면 좋은지 각 Operator 별로 생각 해와서 공유 해 주세요.
2. Create / Defer / Just 등을 활용하여 데이터를 발행 / 구독 해 보고, 어떻게 동작하는지 확인 해 주세요. 수업때 보았던 예시코드( [7ebaaab](https://github.com/kangraemin/RxJavaStudy/commit/7ebaaab6d65f413a4ad2f1048ae842bb43e6a1ad) )들을 활용해도 좋고, 직접 만들어 보셔도 좋습니다

## **과제 목적**

데이터 발행 operator의 활용처를 미리 생각 해 볼 수 있습니다.데이터 발행 operator의 사용법에 익숙 해 질 수 있습니다.

## **과제 수행**

### **1. Create**

- **[개념]**
    - 각 Stream의 구현체에 맞는 Emitter를 활용하여 데이터를 방출하는 operator 이며, 구독자가 Stream을 구독 할 때마다 데이터를 방출.
        - 이미 계산된 결과를 재사용하고 싶다면, cache() 연산자를 사용해주면 된다.
    - 기본적으로 Main 쓰레드에서 작동함.
        - 예를 들어 print()문이 observable 구독 이전과 이후에 있다면 print() 함수부터 시작해서 observable 작업이 완료된후 마지막 print()가 출력된다.
        - observable에서 (스케쥴러) 연산자로 요청하지 않는 이상 절대 RxJava(RxSwift)는 다른 스레드 풀을 통해 실행하지 않는다.
            
            → Rx를 사용했다고 해서 무조건 비동기로 동작하는 것은 아니다. 
            
                Rx를 사용하면 스케줄러 조정이 쉽다는 점에서 비동기 처리가 쉬워지는 것 같음.
            
            ```swift
            print("Start")
            Observable<String>.create { emitter in 
                emitter.onNext("1")
                emitter.onNext("2")
                emitter.onCompleted()
                return Disposables.create()
            }
            .subscribe(onNext: { 
                print($0)
            }, 
                onCompleted: { 
                print("onCompleted")
             })
            .disposed(by: disposebag)
            print("End")
            
            결과
            Start
            1
            2
            onCompleted
            End
            ```
            
        
- **[예시]**
    - 이미지를 다운받고, 다운받은 이미지를 onNext로 방출 후 onCompleted처리, 이미지 처리시 에러가 날 경우 onError 이벤트를 방출한다.
    - 다른 연산자들을 통해서도 위의 사용사례 구현이 가능하겠으나, 반드시 create 연산자에만 적합한 예시가 생각나지 않네요..

## **2.Just**

- **[개념]**
    - 객체 선언 즉시 구독하지 않더라도 데이터 방출 시작하는 operator.
    - 한번 방출 된 데이터는, 내부적으로 저장되어 있기 때문에 observable을 재구독시 데이터 재발행을 하지 않고 저장된 데이터를 방출.
    - 데이터가 방출되는 동안 Thread를 펜딩시키기 때문에, 사용에 유의 할 필요가 있음.
- **[예시]**
    - 에러처리시 default 값을 가진 stream을 던져줄때 사용할 수 있을 것 같네요.

### **3.defer(RxSwift 에선 Deferred 연산자)**

- **[개념]**
    - Stream 객체를 구독하는 시점에 데이터 방출이 시작되도록 하는 operator.
    - 상태에 따라 다른 데이터를 처리해야할 필요가 있을 경우 대처할 수 있다.
- **[예시]**
    - 앱 설정에서 switch 버튼의 상태에 따라 알림 취소 및 알림 등록 로직처리 할 때 사용할 수 있을 것 같습니다.
    - 네트워크 연결이 되어 있다면 API 호출을 하고, 네트워크 연결이 되어 있지 않다면 로컬 DB에서 데이터 가져오는 처리를 할 수 있음.
    

### **4.FromCallable(RxSwift에서는 지원하지 않는 연산자입니다.)**

- **[개념]**
    - Callable 인터페이스를 구현한 객체 내부의 함수를 동작시키고, call 함수의 결과값을 방출해주는 operator
    - 구독자가 Stream을 구독 할 때 데이터 발행을 시작.
- **[예시]**
    - 비동기적으로 네트워크 및 DB쿼리 작업시 사용할 수 있음.

### **5.FromIterable(RxSwift에서는 지원하지 않는 연산자입니다.)**

- **[개념]**
    - Iterable 인터페이스를 구현하고 있는 객체를 인자로 받고, 객체 내부의 아이템들을 하나하나 꺼내어 데이터를 발행 해주는 operator
- **[예시]**
    - BLE 통신시 디바이스 작동 제어를 위해 UUID와 characteristic 값을 받아와야 한다.
    - 디바이스로부터 UUID(보통 여러개임)를 먼저 받아온뒤, FromIterable 연산자를 통해 각 UUID를 호출하여 UUID에 포함하는 characteristic 값을 받아온다.

### **6.Interval**

- **[개념]**
    - 일정한 시간 간격을 두고 0부터 시작하여 1씩 증가하는 숫자를 방출하는 operator
- **[예시]**
    - 휴대폰 인증 타임제한을 걸때 사용한다.

### **7.Timer**

- **[개념]**
    - 일정 시간이 지난 뒤, 아이템을 방출하고 데이터 방출 완료 이벤트를 방출하는 operator.
    - 별다른 Scheduler 지정 하지 않을 시, Computation에서 데이터 방출합니다.
- **[예시]**
    - 3초간 스플래시 화면을 보여주고, 다음 화면으로 넘어갈때 사용할 수 있을 것 같습니다.

### **8.Range**

- **[정의]**
    - n부터 시작하고, 1씩 증가하는 숫자를 m개를 방출하는 operator
- **[예시]**
    - for문을 대신해서 사용할 수 있을 것 같네요.
