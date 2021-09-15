## **과제 내용**

RxJava2에 구현된 Stream의 구현체 ( Observable / Flowable / Maybe / Completable / Single )의 사용처를 한번 생각 해 보시고, 생각 나는데로 적어서 제출 해 주세요.

## **과제 목적**

Stream의 구현체를 목적에 맞게 사용하도록 미리 생각해보도록 합니다.

## **유의사항**

수업에서 말씀드린 Project의 root에 Homework 디렉토리를 만들어 주시고, md 파일의 형식으로 과제 작성 해 주시고 PR 남겨주세요.


## **과제수행**

**Observable**

- SearchBar에서 키보드 이벤트를 받을 때 마다 검색 API 호출하는 상황에 사용한다.

**Single**

- 네트워크 API 요청 및 모바일로컬 저장소(UserDefaults/CoreData) 호출시 사용한다.

**Flowable**

- 서버가 한번에 많은 API 요청을 처리할 때 사용한다.

**Completeable**

- Scene Navigation을 직접 코드로 관리할 때, Scene별 화면 상태 변화(transition or close) 이벤트를 처리할 때 사용한다.

**Maybe**

- 캐시 데이터를 가지고올 때 캐시값이 있으면 onsuccess에 해당 값을 발행하고, 없다면 oncomplete 발행한다.
