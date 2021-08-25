# Reactive Programming 스터디 Repo

## 📖 스터디 소개

반응형 프로그래밍을 RxJava2와 Kotlin을 활용하여 학습하는 스터디 입니다. 

- 약 1년 반정도 현업에서 RxJava2와 Kotlin을 사용하며 겪었던 상황들을 공유하고, 학습했던 것들을 강의 형식으로 알려주는 스터디 입니다.
- Kotlin / Java의 기본 문법을 간단히 알고 있으면 좀 더 스터디에서 가져가실 수 있는것이 많을 것 같습니다.
    - 다른 언어의 ReactiveX 라이브러리를 사용하더라도, ReactiveX의 기초적인 사용 방법은 매우 비슷한 모습을 하고 있기 때문에, 다른 언어에 적용 하실떄에도 큰 무리 없을 것입니다.
- 스터디는 매주 진행 될 예정 이며, 스터디당 예상 소요 시간은 길어야 2시간 정도 입니다.
- 시간과 요일은 아직 정해지지 않았으며, 온라인으로 스터디를 진행 할 예정입니다.
    - 온라인 스터디 툴은 회의 후에 정할 예정입니다. ( Google meet / zoom ... )
- 제가 강의에 썼던 자료 / 작성한 코드는 Notion 또는 Github에 공유 하도록 하는 것을 원칙으로 합니다.
- 과제가 나갈 수 있으며, 과제를 무단으로 진행하지 않을 시 본인의 손해입니다.

## 📖 스터디 진행 커리큘럼 ( Alpha )

아직 구체적인 계획이 명확하게 정해진것은 아니며, 아래에 적힌 계획은 추후 변경 될 수 있습니다. 

1. 반응형 프로그래밍 / ReactiveX 소개
    - Observer Pattern 에 대하여
    - 반응형 프로그래밍에 대한 소개
    - ReactiveX / RxJava에 대한 소개
    - 실습 환경 구축
2. RxJava 시작하기 
    - Stream에 대한 개념 소개
    - Stream의 상태 ( onError, onSucess ... )에 대한 설명
    - ObservableSource / Observer
    - 마블 다이어그램 보는 법 소개
3. Disposable / RxJava Observable의 종류 및 특징 
    - Disposable에 대해서
    - Single / Completable / Flowable / Maybe에 대한 소개 및 각 차이점 설명
4. 다양한 연산자
    - filter / map / flatMap / switchMap ...
5. RxBinding / 결합 연산자 
    - merge / concat / combineLatest / zip ...
6. RxJava Threading / Scheduler
    - Scheduler란
    - RxJava에서 사용하고있는 Scheduler의 종류
    - Thread handling
    - MultiThreading
7. 에러 처리 연산자
    - retry / retryWhen / onErrorReturn ...
8. Subject에 대해서 
    - Subject의 특징
    - Cold Publisher / Hot Publisher의 차이점
    - 미리 만들어진 Subject들의 종류별 특징 ( PublishSubject / BehaviorSubejct / AsyncSubject ... )
9. Custom Subject / Observable 만들어서 기존의 콜백을 Stream으로 변환하기

## 📖 저에 대한 소개

- 이름 : 강래민
- 경력 : 안드로이드 개발 2년
- Github : [https://github.com/kangraemin](https://github.com/kangraemin)
- Notion TIL : [https://www.notion.so/Public-TIL-31ca6d0cded746b4b47b0f7cc2698f0c](https://www.notion.so/Public-TIL-31ca6d0cded746b4b47b0f7cc2698f0c)
- Tech blog : [https://kangraemin.github.io/](https://kangraemin.github.io/)