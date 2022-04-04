# Thymleaf

## 타임리프 기본기능
### 타임리프 특징
- 서버사이드
- 네츄럴 템플릿
- 스프링 통합 지원



**서버 사이드 HTML 렌더링(SSR)**<br>
타임리프는 백엔드 서버에서 HTML을 동적으로 렌더링 하는 용도로 사용된다.



**네츄럴 템플릿**

타임리프는 순수 HTML을 최대한 유지하는 특징이 있다.



### 타임리프 기본 기능



#### 타임리프 사용 선언

`<html xmlns:th="http://www.thymeleaf.org">`



#### 기본 표현식

타임리프는 다음과 같은 기본 표현식들을 제공한다.

-  간단한 표현:

  - 변수 표현식: ${...}

  - 선택 변수 표현식: *{...}

  - 메시지 표현식: #{...}

  - 링크 URL 표현식: @{...}

  - 조각 표현식: ~{...}

  

-  리터럴

  - 텍스트: 'one text', 'Another one!',...

  - 숫자: 0, 34, 3.0, 12.3,...

  - 불린: true, false

  - 널: null

  - 리터럴 토큰: one, sometext, main,...

  

-  문자 연산:

  - 문자합치기: + 

  - 리터럴 대체: |The name is ${name}|

  

- 산술 연산:

  - Binary operators: +, -, *, /, %

  - Minus sign (unary operator): -



- 불린 연산: 

  - Binary operators: and, or
  - Boolean negation (unary operator): !, not

  

- 비교와 동등:

  - 비교:>,<,>=,<=(gt,lt,ge,le)

  - 동등 연산: ==, != (eq, ne)



- 조건 연산:

  - If-then: (if) ? (then)

  - If-then-else: (if) ? (then) : (else)

  -  Default: (value) ?: (defaultvalue



- 특별한 토큰:
  - No-Operation: _



### 텍스트 - text, text

타임 리프는 기본적으로 HTML 태그의 속성에 기능을 정의해서 동작한다. 

HTML의 콘텐츠(content)에 데이터를 출력할 때는  `th:text` 를 사용하면 된다.

HTML 태그의 속성이 아니라 HTML 콘텐츠 영역안에서 직접 데이터를 출력하고 싶으면 `[[...]]`을 사용하면 된다.



#### Esacpe

HTML 문서는 <,>같은 특수 문자를 기반으로 정의된다. 따라서 뷰 템플릿으로 HTML 화면을 생성할 때는 출력하는 데이터에 이러한 특수 문자가 있는 것을 주의해서 사용해야 한다.



#### HTML 엔티티

웹 브라우저는 `<` 를 HTML 태그의 시작으로 인식한다. 따라서 `<` 를 태그의 시작이 아니라 문자로 표현할 수 있는 방법이 필요한데, 이것을 HTML 엔티티라 한다. 그리고 이렇게 HTML에서 사용하는 특수문자를 HTML 엔티티로 변경하는 것을 이스케이프(escape)라 한다. 그리고 **타임리프가 제공하는 `th:text`, `[[...]]` 은 기본적으로 이스케이프(escape)를 제공한다.**

- `<` -> `&lt;`
- `>` -> `&gt;`
- 기타 수많은 엔티티가 존재한다.



#### Unescape

타임리프는 다음의 두가지 기능을 제공한다.

`th:text` -> `th:utext`

`[[...]]` -> `[(...)]`



### 변수 - SpringEL

타임리프에서 변수를 사용할 때는 변수 표현식을 사용한다.

**변수 표현식: `%{...}`**

그리고 이 변수 표현식에슨 스프링 EL이라는 스프링이 제공하는 표현식을 사용할 수 있다.

```html
<ul>Object
    <li>${user.username} = <span th:text="${user.username}"></span></li>
    <li>${user['username']} = <span th:text="${user['username']}"></span></li>
    <li>${user.getUsername()} = <span th:text="${user.getUsername()}"></span></li>
</ul>
<ul>List
    <li>${users[0].username} = <span th:text="${users[0].username}"></span></li>
    <li>${users[0]['username']} = <span th:text="${users[0]['username']}"></span></li>
    <li>${users[0].getUsername()} = <span th:text="${users[0].getUsername()}"></span></li>
</ul>
<ul>Map
    <li>${userMap['userA'].username} = <span th:text="${userMap['userA'].username}"></span></li>
    <li>${userMap['userA']['username']} = <span th:text="${userMap['userA']['username']}"></span></li>
    <li>${userMap['userA'].getUsername()} = <span th:text="${userMap['userA'].getUsername()}"></span></li>
</ul>
```



#### 지역 변수 선언

`th:with` 를 사용하면 지역변수를 사용할 수 있다. 지역 변수는 선언한 태그 안에서만 사용할 수 있다.

~~~html
<h1>지역 변수 - (th:with)</h1>
<div th:with="first=${users[0]}">
    <p>처음 사람의 이름은 <span th:text="${first.username}"></span></p>
</div>
~~~





























































































