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









































































































