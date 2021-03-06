# Reactive Programming μ€ν°λ Repo

## π μ€ν°λ μκ°

λ°μν νλ‘κ·Έλλ°μ RxJava2μ Kotlinμ νμ©νμ¬ νμ΅νλ μ€ν°λ μλλ€. 

- μ½ 1λ λ°μ λ νμμμ RxJava2μ Kotlinμ μ¬μ©νλ©° κ²ͺμλ μν©λ€μ κ³΅μ νκ³ , νμ΅νλ κ²λ€μ κ°μ νμμΌλ‘ μλ €μ£Όλ μ€ν°λ μλλ€.
- Kotlin / Javaμ κΈ°λ³Έ λ¬Έλ²μ κ°λ¨ν μκ³  μμΌλ©΄ μ’ λ μ€ν°λμμ κ°μ Έκ°μ€ μ μλκ²μ΄ λ§μ κ² κ°μ΅λλ€.
    - λ€λ₯Έ μΈμ΄μ ReactiveX λΌμ΄λΈλ¬λ¦¬λ₯Ό μ¬μ©νλλΌλ, ReactiveXμ κΈ°μ΄μ μΈ μ¬μ© λ°©λ²μ λ§€μ° λΉμ·ν λͺ¨μ΅μ νκ³  μκΈ° λλ¬Έμ, λ€λ₯Έ μΈμ΄μ μ μ© νμ€λμλ ν° λ¬΄λ¦¬ μμ κ²μλλ€.
- μ€ν°λλ λ§€μ£Ό μ§ν λ  μμ  μ΄λ©°, μ€ν°λλΉ μμ μμ μκ°μ κΈΈμ΄μΌ 2μκ° μ λ μλλ€.
- μκ°κ³Ό μμΌμ μμ§ μ ν΄μ§μ§ μμμΌλ©°, μ¨λΌμΈμΌλ‘ μ€ν°λλ₯Ό μ§ν ν  μμ μλλ€.
    - μ¨λΌμΈ μ€ν°λ ν΄μ νμ νμ μ ν  μμ μλλ€. ( Google meet / zoom ... )
- μ κ° κ°μμ μΌλ μλ£ / μμ±ν μ½λλ Notion λλ Githubμ κ³΅μ  νλλ‘ νλ κ²μ μμΉμΌλ‘ ν©λλ€.
- κ³Όμ κ° λκ° μ μμΌλ©°, κ³Όμ λ₯Ό λ¬΄λ¨μΌλ‘ μ§ννμ§ μμ μ λ³ΈμΈμ μν΄μλλ€.

## π μ€ν°λ μ§ν μ»€λ¦¬νλΌ ( Alpha )

μμ§ κ΅¬μ²΄μ μΈ κ³νμ΄ λͺννκ² μ ν΄μ§κ²μ μλλ©°, μλμ μ ν κ³νμ μΆν λ³κ²½ λ  μ μμ΅λλ€. 
1. λ°μν νλ‘κ·Έλλ° / ReactiveX μκ°
    - Observer Pattern μ λνμ¬
    - λ°μν νλ‘κ·Έλλ°μ λν μκ°
    - ReactiveX / RxJavaμ λν μκ°
    - μ€μ΅ νκ²½ κ΅¬μΆ
2. RxJava μμνκΈ° 
    - Streamμ λν κ°λ μκ°
    - Streamμ μν ( onError, onSucess ... )μ λν μ€λͺ
    - ObservableSource / Observer
    - λ§λΈ λ€μ΄μ΄κ·Έλ¨ λ³΄λ λ² μκ°
3. Disposable / RxJava Observableμ μ’λ₯ λ° νΉμ§ 
    - Disposableμ λν΄μ
    - Single / Completable / Flowable / Maybeμ λν μκ° λ° κ° μ°¨μ΄μ  μ€λͺ
4. Subscribe / Consumer, Action interface μ λν΄μ
    - Consumer, Action Interface
    - Subscribe operator
5. RxJava Threading / Scheduler
    - Schedulerλ
    - RxJavaμμ μ¬μ©νκ³ μλ Schedulerμ μ’λ₯
    - observeOn / subscribeOn
    - MultiThreading
6. Subjectμ λν΄μ
    - Subjectμ νΉμ§
    - Cold Publisher / Hot Publisherμ μ°¨μ΄μ 
    - λ―Έλ¦¬ λ§λ€μ΄μ§ Subjectλ€μ μ’λ₯λ³ νΉμ§ ( PublishSubject / BehaviorSubejct / AsyncSubject ... )
7. λ€μν μ°μ°μ - μμ±
    - create / just / defer / interval / ...
8. λ€μν μ°μ°μ - νλ¦ μ μ΄ 
    - filter / map / flatMap / switchMap
9. λ€μν μ°μ°μ - κ²°ν© / RxBinding
    - RxBinding
    - merge / concat / combineLatest / zip ...
10. λ€μν μ°μ°μ - μλ¬ μ²λ¦¬ 
    - retry / retryWhen / onErrorReturn ...
11. Reactive Stream / RxJava
    - Observable vs Flowable
    - Subscription
    - Publisher / Subscriber
12. MultiCasting / UniCasting
    - Subject
    - Connectable Observable
    - publish / refCount / share
13. RxJavaλ₯Ό μ§μνλ Android λΌμ΄λΈλ¬λ¦¬λ₯Ό μ¬μ© ν΄λ³΄κΈ°
    - Retrofitκ³Ό μ°λ ν΄λ³΄κΈ°
    - Roomκ³Ό μ°λ ν΄λ³΄κΈ°
14. Custom Subject / Observable λ§λ€μ΄μ κΈ°μ‘΄μ μ½λ°±μ StreamμΌλ‘ λ³ννκΈ°
    - RxBinding κ΅¬νμ²΄ μ΄ν΄λ³΄κΈ°

## π μ μ λν μκ°

- μ΄λ¦ : κ°λλ―Ό
- κ²½λ ₯ : μλλ‘μ΄λ κ°λ° 2λ
- Github : [https://github.com/kangraemin](https://github.com/kangraemin)
- Notion TIL : [https://www.notion.so/Public-TIL-31ca6d0cded746b4b47b0f7cc2698f0c](https://www.notion.so/Public-TIL-31ca6d0cded746b4b47b0f7cc2698f0c)
- Tech blog : [https://kangraemin.github.io/](https://kangraemin.github.io/)
