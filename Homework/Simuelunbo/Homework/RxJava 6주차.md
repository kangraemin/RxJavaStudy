# RxJava 6주차

# PublishSubject

---

![https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/PublishSubject.png](https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/PublishSubject.png)

PublishSubject는 구독 시점의 발행된 데이터는 무시하고 구독 시점 이후에만 발행된 데이터를 발행으로 사용 한다. 따라서 버튼 클릭 입력 같은곳이나 라이브 스트리밍(?)에 쓰이면 좋을듯 싶다.

# BehaviorSubject

---

[https://t1.daumcdn.net/cfile/tistory/99712C425E47A68F32](https://t1.daumcdn.net/cfile/tistory/99712C425E47A68F32)

BehaviorSubject는 구독하는 시점의 가장 최근의 발행된 값을 받는다. 그리고 default값을 추가하여 기본적으로 방출할 데이터를 처리할수 있다. 검색 할때 자동 완성으로 보여줄때 쓰이면 좋을거 같다.

# AsyncSubject

---

![https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/AsyncSubject.png](https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/AsyncSubject.png)

AsyncSubject는 이전의 데이터는 무시하고 발행 완료직전의 데이터만 발행해서 보여준다.

영상을 중단하고 나중에 이어서 볼때 사용할수 있을거 같다. 

# ReplaySubject

---

![https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/ReplaySubject.u.png](https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/ReplaySubject.u.png)

ReplaySubject는 옵저버가 구독을 시작한 시점과 관계 없이 배출한 모든 항목들을 모든 옵저버에게 배출한다.

ReplaySubject 같은 경우는 어디에 쓰여야 할지 잘 모르겠다 업데이트 할때 굳이(?) 라는 생각도 들고