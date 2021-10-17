# RxJava 7주차

## Create

[https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/SQo/image/rRJfoEWA1zixjuCJQbaIF_qvBRc.png](https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/SQo/image/rRJfoEWA1zixjuCJQbaIF_qvBRc.png)

각 구현체에 맞게 Emitter와 onNext onComplete 메소드를 활용 하여 데이터 방출한다

create는 onNext onComplete 함수들을 활용해서 처리 해야할때 유용하게 쓰일것 같다

## Just

![https://cdn-images-1.medium.com/max/800/0*bhYoYg_O-GlXlikv.png](https://cdn-images-1.medium.com/max/800/0*bhYoYg_O-GlXlikv.png)

데이터를 바로 방출할때 사용한다

just는 바로 방출 될때 쓰는 것들로 간단하게 토스트 메세지에 쓰면 좋을 듯싶다 

## Defer

![https://blog.kakaocdn.net/dn/dys216/btqygsEptw2/VLIdD8GK2vBwfNc9Rk2fz0/img.png](https://blog.kakaocdn.net/dn/dys216/btqygsEptw2/VLIdD8GK2vBwfNc9Rk2fz0/img.png)

defer는 구독하는 시점에 데이터 방출이 시작 되는 operator이다

defer는 네트워크 통신 결과에 따라 분기 처리 할 때 사용할수 있을 것 같다

## fromIterable

![http://reactivex.io/documentation/operators/images/from.c.png](http://reactivex.io/documentation/operators/images/from.c.png)

fromIterable은 내부 아이템들을 하나하나 꺼내서 발행 해주는 operator다

리스트를 하나 하나씩 보여줄때 좋을것 같다.

## Interval

![http://reactivex.io/documentation/operators/images/interval.c.png](http://reactivex.io/documentation/operators/images/interval.c.png)

Interval은 일정한 시간 간격을두고 방출하는 operator이다.

단순하게 생각했을때 시간 타이머 같은걸로 사용하면 좋을것 같다.

## Range

![http://reactivex.io/documentation/operators/images/range.c.png](http://reactivex.io/documentation/operators/images/range.c.png)

Range는 n부터 시작하고 1씩 증가하는 숫자를 m개까지 방출하는 operator

stream에서 반복적으로 사용할때 좋을것 같다.