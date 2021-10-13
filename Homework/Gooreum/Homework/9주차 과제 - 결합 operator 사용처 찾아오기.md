### **과제 내용**

1. RxBinding / 결합연산자를 활용하여 아래의 View를 구현 해 주세요

`로그인뷰

email EditText
password EditText
로그인 버튼 

과제 
email / password 둘 다 1글자 이상인 경우만 로그인 버튼을 활성화 시키면 됩니다.`

1. Merge / Concat / ConcatEager / Zip / CombineLatest 를 활용할 곳을 생각해보고 적어주세요.

### **과제 수행**

Combining Operator 공통 조건 : 

> **Note:** Observable sequences are strongly typed. You can only concatenate
sequences whose elements are of the same type!
> 

> If you try to concatenate sequences of different types, brace yourself for
compiler errors. The Swift compiler knows when one sequence is an
Observable<String> and the other an Observable<Int> so it will not allow
you to mix them.
> 

### 1.Merge

- **[개념]**
    - 여러개의 스트림을 하나의 스트림으로 합칠 때 사용하는 operator
    - 각 스트림에서 이벤트가 발행 된 즉시 합쳐진 스트림에서 이벤트가 발행됨
    - merge하려는 stream들의 데이터 타입이 동일해야함.

- **[예시]**
    - 넷플릭스 홈 화면을 보면 여러 영화 카테고리가 있음.
    - 각각의 카테고리 별로 해당하는 API를 호출하는 형식이라면 merge연산자를 사용하여 처리해도 될것 같습니다.
    - 다만 merge 내부에서 하나의 stream이라도 에러가 발생한다면 전체 stream이 dispose 버린다.
        - RxJava에서는 MergeDelayError를 이용하여 대응가능.
            - MergeDelayError는 merge 내부의 stream중 에러가 발생한 부분은 merge Stream에서 가장 마지막에 방출되도록 처리해준다.
            - RxSwift에는 없고, RxSwift Github에 MergeDelayError 만들어 달라는 이슈가 있음.
                
                ([https://github.com/ReactiveX/RxSwift/issues/1191](https://github.com/ReactiveX/RxSwift/issues/1191)**)**
                
            - MergeDelayError를 기존의 연산자들을 이용해서 Observable을 extension 하여 custom 메소드로 만든 사람이 있음..([https://github.com/ReactiveX/RxSwift/issues/1824#issuecomment-506364576](https://github.com/ReactiveX/RxSwift/issues/1824#issuecomment-506364576))

### 2.Concat

- **[개념]**
    - 여러개의 스트림을 하나의 스트림으로 합칠때 사용하는 operator
    - 앞의 stream이 onComplete 되어야 뒤의 stream을 구독후 발행한다.

- **[예시]**
    - 순서를 지켜서 호출해야 하는 API들이 있을 때 사용할 수 있을 것 같습니다.
    - 아직 구체적으로 어떤 실제 비지니스 로직에 처리할 수 있을지는 잘 모르겠습니다.

### 3.ConcatEager ( RxSwift에는 없습니다.)

- **[개념]**
    - 여러개의 스트림을 하나의 스트림으로 합칠때 사용하는 operator
    - 앞의 stream이 onComplete되지 않더라도 뒤의 stream을 구독하여 발행 한다.
    - 앞 stream이 onComplete되면 곧바로 뒤의 stream의 아이템을 방출한다.

- **[예시]**
    - Concat처럼 순서를 지켜서 호출해야 하는 API들이 있을 때 사용할 수 있을 것 같습니다.
    - 다만 Concat보다 빨리 처리가 될 수 있을 것 같네요.
    - 아직 구체적으로 어떤 실제 비지니스 로직에 처리할 수 있을지는 잘 모르겠습니다.

### 4.Zip

- **[개념]**
    - 여러개의 스트림을 하나로 합치는데, 각 스트림에서 발행된 데이터들을 조합하여 새로운 데이터를 만들어 발행하는 operator
    - 여러개의 스트림에서 발행된 데이터들의 순서가 짝이 맞아야 데이터가 발행됨

- **[예시]**
    - 3개의 이미지와 각 이미지 관련 설명을 서버에 업로드시 두 작업을 각각 하나의 stream으로 나눈뒤 zip연산자로 묶어 처리할 수 있을 것 같습니다.

### 5.CombineLatest

- **[개념]**
    - zip과 비슷하게 여러개의 스트림을 하나로 합치는데, 각 스트림에서 발행된 데이터들을 조합하여 새로운 데이터를 만들어 발행하는 operator
    - zip은 여러개의 스트림에서 발행된 데이터들의 순서가 짝이 맞아야 데이터가 발행되었지만, `combineLatest`는 짝이 맞지 않더라도 다른 스트림에서 이전에 발행 되었던 데이터를 활용하여 데이터가 발행됨

- **[예시]**
    - 로그인 시 아이디와 비밀번호 유효성이 모두 통과되어야 로그인 버튼을 활성화 시켜준다.
