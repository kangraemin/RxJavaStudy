# RxJava 9주차

## merge

[https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F99AB4B3A5C245B2704](https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F99AB4B3A5C245B2704)

merge는 첫번째 두번째 스트림에서 발행된 아이템들이 하나의 스트림에서 합쳐져 발행한다.

merge같은 경우 먼저 입력되는 데이터가 그대로 발행되기 때문에 폴링 형식의 채팅 같은곳에서 쓰면 좋을것 같다. 

## concat

![https://i.stack.imgur.com/hqkgB.png](https://i.stack.imgur.com/hqkgB.png)

여러 스트림을 하나로 합쳐주는데 합칠때 첫번째 스트림이 끝나면 두번째 스트림이 발행되는 순서가 보장 되는 operator이다.

concat은 여러 단계가 필요한 api를 사용할때 쓰면 좋을것 같다.

## concatEager

![https://blog.kakaocdn.net/dn/uI9hh/btqGAewNwtt/WMQ2dIzvbJ6sILAuQMBWf0/img.png](https://blog.kakaocdn.net/dn/uI9hh/btqGAewNwtt/WMQ2dIzvbJ6sILAuQMBWf0/img.png)

concateager는 concat과 비슷하지만 차이점은 앞에 스트림이 호출하면서 뒤에 스트림도 미리 발행 해놓아 앞 스트림이 끝나면 뒷 스트림을 바로 방출한다

concatEager같은 경우 많은 api를 호출하는 Home 화면에 쓰면 좋을것 같다

## zip

[https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F9982FC3B5C233D1914](https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F9982FC3B5C233D1914)

zip은 각각의 n번째 해당하는 아이템끼리 결합 하여 하나의 스트림으로 발행 한다

zip은 결합해서 보내야 하는 데이터들 api 처리할떄 쓰면 좋을것 같다

## CombineLatest

[https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FoXoKz%2FbtqGywENxlt%2FcsMw9dBOUnBnA0nf7eN7uk%2Fimg.png](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FoXoKz%2FbtqGywENxlt%2FcsMw9dBOUnBnA0nf7eN7uk%2Fimg.png)

소스 스트림 중 하나가 항목을 방출할 때마다 결합하여 스트림을 하나의 스트림 으로 병합한다.

combineLatest는 로그인이나 회원 가입때 빈값 확인할때 쓰면 좋을거 같다.