# Message



## 메시지, 국제화

### 메시지

다양한 메시지를 한곳에서 관리하는 기능을 메시지 기능이라한다.

ex) `messages.properties` 라는 메시지 관리용 파일을 만들어서

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

ex) `messages_en.properties` 라는 메시지 관리용 파일을 만들어서

~~~properties
item = Item
item.id = Item ID
item.itemName = Item Name
item.price = Price
item.quantity = Quantity
~~~



### 웹 애플리케이션에 메시지 적용하기

ex)

```html
<div class="py-5 text-center">
    <h2 th:text="#{page.addItem}">상품 등록 폼</h2>
</div>

<form action="item.html" th:action th:object="${item}" method="post">
    <div>
        <label for="itemName" th:text="#{label.item.itemName}">상품명</label>
        <input type="text" id="itemName" th:field="*{itemName}" class="form-control" placeholder="이름을 입력하세요">
    </div>
    <div>
        <label for="price" th:text="#{label.item.price}">가격</label>
        <input type="text" id="price" th:field="*{price}" class="form-control" placeholder="가격을 입력하세요">
    </div>
    <div>
        <label for="quantity" th:text="#{label.item.quantity}">수량</label>
        <input type="text" id="quantity" th:field="*{quantity}" class="form-control" placeholder="수량을 입력하세요">
    </div>

    <hr class="my-4">

    <div class="row">
        <div class="col">
            <button class="w-100 btn btn-primary btn-lg" type="submit" th:text="#{button.save}">상품 등록</button>
        </div>
        <div class="col">
            <button class="w-100 btn btn-secondary btn-lg"
                    onclick="location.href='items.html'"
                    th:onclick="|location.href='@{/message/items}'|"
                    type="button" th:text="#{button.cancel}">취소</button>
        </div>
    </div>

</form>
```

`th:text=#{....}` 을 이용하여 메시시를 적용할 수 있다.

**파라미터를 이용한 메시지**

ex)

`th:text=#{hello.name(${argument})}`  와 같이 파라미터를 이용하여 메시지를 적용할 수 있다.



### 스프링의 국제화 메시지 선택

스프링은 `Locale` 선택 방식을 변경할 수 있도록 `LocaleResolver` 라는 인터페이스를 제공하는데, 스프링 부트는 기본으로 `Accept-Language` Http Header를 활용하는 `AcceptHeaderLocaleResolver` 를 사용한다.