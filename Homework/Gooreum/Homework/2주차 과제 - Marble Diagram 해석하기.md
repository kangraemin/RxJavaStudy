## **과제 내용**

- [Rx operator documents](http://reactivex.io/documentation/operators.html)에서 operator의 Marble Diagram만 보고, 어떤 역할을 하는 operator 인지 유추 해 봅시다.
- 갯수는 자율에 맡기나, operator 2개 이상 해보시면 좋을 것 같습니다.

## **과제 목적**

- Marble diagram 보는 법을 익혀 operator / stream의 행동을 좀 더 간단하게 확인 할 수 있도록 합니다.

## **유의사항**

- 먼저 operator에 대한 설명을 참조하지 않고 Marble diagram만 보고 분석 해 보시고, 해당 operator가 어떤 operator인지 찾아 보시는것을 권장드립니다.


## **1.Creating Observables**
**Just**
![just](https://user-images.githubusercontent.com/48742165/132246478-92744dd4-a455-4425-9b5f-0d51de326003.png)
- 하나의 아이템을 발행하고 종료되는 스트림을 생성한다.
<br />

**from**
![from](https://user-images.githubusercontent.com/48742165/132247137-37da7407-5bb1-42a2-9cca-2e40e62a5f0b.png)

- 배열 타입의 데이터를 추가하면 from 연산자를 통해 배열이 가지고 있는 데이터의 수만큼의 item이 발행되고 종료되는 스트림을 생성한다.
<br />

**range** 
![range](https://user-images.githubusercontent.com/48742165/132247194-899a741b-034d-4e4c-ba78-5220d98a6e69.png)
- n부터 시작해서 m-1까지 1씩 증가하는 아이템이 발행되고 종료되는 스트림을 생성한다.
<br />

**repeat**
![repeat](https://user-images.githubusercontent.com/48742165/132247793-8d6237dd-ef5b-4355-874b-f0cfcdeccd99.png)
- 특정 데이터를 반복해서 발행하는 스트림을 생성한다. 특별한 지시 없이 stream은 종료되지 않는다.
<br />

**deffer** 
![deffer](https://user-images.githubusercontent.com/48742165/132247178-a37eb5ee-9472-47ba-9bac-907cfd3c3423.png)
- Red 그룹을 포함한 stream이 각각의 아이템을 발행하고 종료되면, Green 그룹을 포함한 stream이 각각의 아이템을 발행후 종료되는 스트림을 생성한다.
<br />

**empty**
![empty](https://user-images.githubusercontent.com/48742165/132247185-6efaeb0b-962d-41bb-84f2-85814efb9e0c.png)

- 어떤 onNext 이벤트도 발행하지 않고 종료하는 스트림을 생성한다.
<br />

**never**
![never](https://user-images.githubusercontent.com/48742165/132247193-e7fcb369-9ae2-40b7-bc2c-53cd8ba6bef3.png)

- 어떤 이벤트도 발행하지 않는 스트림을 생성한다.
<br />

**throw**
![throw](https://user-images.githubusercontent.com/48742165/132247207-af72767d-d9b5-471d-bf52-2eabc0712af3.png)![ignoreElementsAt](https://user-images.githubusercontent.com/48742165/132247554-723479b6-70e8-4780-8d13-fce3492fff4f.png)

- 에러 이벤트를 발행하는 스트림을 생성한다.
<br />

## **2.Filtering**

### **ignoreElements**
![ignoreElementsAt](https://user-images.githubusercontent.com/48742165/132247573-a58c1be2-25da-4b0f-87c1-63fa835d5047.png)

- stream이 발행하고 있는 모든 이벤트를 무시한다.
<br />

### **elementAt**
![elementAt](https://user-images.githubusercontent.com/48742165/132247183-36b177fb-15e8-459b-abeb-25b65163232f.png)

- 스트림이 발행하는 특정 인덱스까지의 이벤트를 무시하고 이후의 이벤트부터 발행한다.
<br />

### **filter**
![filtering](https://user-images.githubusercontent.com/48742165/132247189-7e7c36d7-4afe-4bbc-882e-54b21b593c93.png)

- 스트림의 아이템 중 filter 조건식에 맞는 아이템만 방출한다.
<br />

### **skip**
![skip](https://user-images.githubusercontent.com/48742165/132247199-0e463843-87ec-4b14-a9e8-5f377d04f9a1.png)

- 스트림이 발행하고 있는 요소 중 n번째 요소 이후의 아이템부터 발행한다.
<br />

### **skipLast**
![skipLast](https://user-images.githubusercontent.com/48742165/132247201-8fd4db33-6058-4a2e-a356-ff7c226a4912.png)

- 스트림이 발행항 아이템 중 마지막부터 n번째 까지의 아이템만을 발행한다.
<br />

### **take**
![take](https://user-images.githubusercontent.com/48742165/132247204-e55d42de-ff42-4a08-92bd-af9faa2af8ff.png)

- 스트림이 발행한 아이템 중 처음부터 n개까지의 아이템만을 발행한다.

<br />

### **takeLast**
![takeLast](https://user-images.githubusercontent.com/48742165/132247205-369d3c90-703e-47cc-a56c-0659199f6568.png)
- 스트림이 발행한 아이템 중 마지막 한개의 아이템만을 발행한다.
<br />

### **distinct**
![distinct](https://user-images.githubusercontent.com/48742165/132247181-17766a26-6b6e-4949-b13f-0032d2bd6792.png)

- 스트림이 발행하는 아이템 중 중복되는 아이템은 하나만 발행하도록 한다.
<br />
### **debounce**
![debounce](https://user-images.githubusercontent.com/48742165/132247175-022bda40-610a-4f5a-b9bf-41bf821b76bf.png)

- 스트림이 발행하는 아이템 중에서 특정 시간이 지난 후에 발행된 아이템만을 발행하게 해준다.
- 특정 시간이 지나기 전에 발행된 아이템들은 무시된다.
<br />

## **3.Transforming**

### **map**
![map](https://user-images.githubusercontent.com/48742165/132247191-a11ea0e3-cd90-4b87-9c8c-2a390ec31668.png)

- 스트림이 발행하는 각각의 아이템에 map 연산자 조건식을 연산하여 그 결과값을 다시 발행하도록 한다.

<br />

### **buffer**
![buffer](https://user-images.githubusercontent.com/48742165/132247169-33c7a1fe-2071-4ad8-b83b-875930a5c85f.png)

- 스트림이 발행하는 이벤트들을 여러개 묶어서 발행한다.

<br />

### **groupBy**
![groupBy](https://user-images.githubusercontent.com/48742165/132247944-b04e5c39-7a60-44b6-85f5-b68c92f607b7.png)

- 하나의 스트림에서 같은 형식을 가진 이벤트들을 묶어서 각각 새로운 스트림으로 발행하고 각 스트림이 종료한 뒤, 메인 스트림이 종료된다.
<br />

## **4.Combining**

**startWith**
![startWith](https://user-images.githubusercontent.com/48742165/132247564-0868c1da-a293-45f9-a56b-60382218170d.png)


- 기존 스트림에 초기값을 추가한다.
<br />

### **merge**
![merge](https://user-images.githubusercontent.com/48742165/132247561-dbcdbf24-60cc-428d-89c3-ef08f39abbfb.png)

- 두 개의 스트림을 하나의 스트림으로 만들어 주는데, 두 개의 스트림이 각각 이벤트를 발행한 시간을 고려하여 순차적으로 합친다.

