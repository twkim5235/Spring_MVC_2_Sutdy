# Message



## 메시지, 국제화

### 메시지

다양한 메시지를 한곳에서 관리하는 기능을 메시지 기능이라한다.

ex) `message.properties` 라는 메시지 관리용 파일을 만들어서

~~~properties
item = 상품
item.id = 상품 ID
item.itemName = 상품명
item.price = 가격
item.quantity = 수량
~~~

각 HTML들은 다음과 같이 해당 데이터를 key 값으로 불러서 사용할 수 있다.

`<label for = "itemName" th:text = "#{item.itemName}"></label>`



### 국제화

메시지 파일을 각 나라별로 관리하면 된다.

ex) `messageEn.properties` 라는 메시지 관리용 파일을 만들어서

~~~properties
item = Item
item.id = Item ID
item.itemName = Item Name
item.price = Price
item.quantity = Quantity
~~~

