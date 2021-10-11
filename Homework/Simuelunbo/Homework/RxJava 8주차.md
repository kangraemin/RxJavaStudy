# RxJava 8주차

## Take

![https://i.stack.imgur.com/q4oTZ.png](https://i.stack.imgur.com/q4oTZ.png)

take는 발행된 데이터중 처음에서 n개만큼 발행한다.

take는 로그인 시도 할때 비밀번호 n번 실패 했을때 로그인 자체를 막아 놓을때 쓰일것 같다.

## Skip

![https://blog.kakaocdn.net/dn/djAZfE/btqygBACJGf/xhBKZ9uIuGWVc0tpcla3lK/img.png](https://blog.kakaocdn.net/dn/djAZfE/btqygBACJGf/xhBKZ9uIuGWVc0tpcla3lK/img.png)

skip은 발행된 데이터중 n번째 이후에 아이템만 발행한다.

skip은 마땅히 떠올릴만한 예는 생각 안나지만 앞에 n번째 데이터 까지 거를만한 상황에 쓰면 좋을거 같다.

## filter

[https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWAOPiiBNIlI632FsHJuuDIH8Pc67O_LH39A&usqp=CAU](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWAOPiiBNIlI632FsHJuuDIH8Pc67O_LH39A&usqp=CAU)

filter는 해당조건이 true일때만 데이터 방출 할수 있게한다.

상황에 따라서 원하는 데이터들을 골라서 뽑아쓸때 좋을거 같다.

## Map

![https://blog.kakaocdn.net/dn/bKWFZp/btqzI33Nrjl/yCAzgSQe78vGrwRKUK6aLK/img.png](https://blog.kakaocdn.net/dn/bKWFZp/btqzI33Nrjl/yCAzgSQe78vGrwRKUK6aLK/img.png)

Map은 발행된 데이터를 변형할때 쓰인다

Map은 말그대로 데이터 타입을 변형하거나 등에 자주 쓰일것 같다. 

## FlatMap

![https://miro.medium.com/max/700/1*_Y63YvBMyq8gnTkO_g3etQ.png](https://miro.medium.com/max/700/1*_Y63YvBMyq8gnTkO_g3etQ.png)

flatMap은 데이터 Stream을 변경하고 새로운 Stream을 구독후 발행 되는 데이터를 발행 하는 operator이다.

flatMap은 Stream을 다른 Stream으로 변형 할때 사용한다.

## SwitchMap

![https://woovictory.github.io/img/rx_switchmap.png](https://woovictory.github.io/img/rx_switchmap.png)

switchMap은 발행된 데이터를 해당 함수로 변환 한다음 발행 하는데 새로운 데이터가 들어 올경우 이전 동작 멈추고 현재 들어온 작업을 수행한다.

switchmap은 여러개 값이 요청(?) 또는 발행 될때 마지막 값만 처리하고 싶을때 쓰면 유용할것 같다.

## ConcatMap

[https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FOsOr4%2Fbtq4HwBVSLS%2F3zWnKNkg2K264Jjh5EwOHK%2Fimg.png](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FOsOr4%2Fbtq4HwBVSLS%2F3zWnKNkg2K264Jjh5EwOHK%2Fimg.png)

ConcatMap은 발행된 데이터를 해당 함수로 변환 한다음 발행 하는데 SwitchMap과는 다르게 새로운 데이터가 들어오면 중단시키지 않고 대기하였다가 순서대로 발행 시킨다

ConcatMap은 순서가 보장 되어야 하는 api 이런것에 쓰면 되지 않을까 싶다.