## 서블릿 필터

### 서블릿 필터 소개 

필터는 서블릿이 지원하는 수문장이다. 필터의 특성은 다음과 같다

**필터 흐름**

~~~
HTTP 요청 -> WAS -> 필터 -> 서블릿 -> 컨트롤러
~~~

필터를 적용하면 필터가 호출된 다음에 서블릿이 호출된다. 그래서 모든 고객의 요청 로그를 남기는 요구사항이 있다면 필터를 사용하면 된다. 참고로 필터는 특정 URL 패턴에 적용할 수 있다. `/*` 이라고 하면 모든 요청에 필터가 적용된다.

스프링을 사용하는 경우 여기서 말하는 서블릿은 스프링의 디스패처 서블릿으로 생각하면된다.



**필터 제한**

~~~
HTTP 요청 -> WAS -> 필터 -> 서블릿 -> 컨트롤러 //로그인 사용자
HTTP 요청 -> WAS -> 필터(막힘. 적절하지 않은 요청이라 판단) //비로그인 사용자
~~~

필터에서 적절하지 않은 요청이라고 판단하면 거기에서 끝을 낼 수도 있다. 그래서 로그인 여부를 체크하기에 딱 좋다.



**필터 체인**

~~~
HTTP 요청 -> WAS -> 필터1 -> 필터2 -> 필터3 -> 서블릿 -> 컨트롤러
~~~

필터는 체인으로 구성되는데, 중간에 필터를 자유롭게 추가할 수 있다. 예를 들어서 로그를 남기는 필터를 먼저 적용하고, 그 다음에 로그인 여부를 체크하는 필터를 만들 수 있다.



 **필터 인터페이스**

~~~java
public interface Filter {

    public default void init(FilterConfig filterConfig) throws ServletException {}

   
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException;

    public default void destroy() {}
}
~~~

필터 인터페이스를 구현하고 등록하면 서블릿 컨테이너가 필터를 싱글톤 객체로 생성하고, 관리한다.

- `init()` : 필터 초기화 메서드, 서블릿 컨테이너가 생성될 때 호출된다.
- `doFilter()` : 고객의 요청이 올 때 마다 해당 메서드가 호출된다. 필터의 로직을 구현하면 된다.
- `destroy()` : 필터 종료 메서드, 서블릿 컨테이너가 종료될 때 호출된다.



### Logfilter 구현

```java
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();

        try {
            log.info("REQUEST [{}][{}]", uuid, requestURI);
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("RESPONSE [{}][{}]", uuid, requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
```

1. Filter를 구현하기위해 `Filter [Java.sevlert]` 인터페이스를 상속받는다.
2. Http 요청이 들어오면 `doFilter()`가 호출된다.
   1. doFilter에 filter가 호출 시 수행할 로직을 구현한다.
3. `chain.doFilter` 를 호출하면 다음에 필터가 있으면 필터를 호출하고, 필터가 없으면 서블릿이 호출된다.
   - **꼭 호출해줘야 한다. 그래야만 Controller에 접근할 수 있기 때문이다. 즉 호출을 안하면 다음으로 안넘어간다.**



```java
@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}
```

필터를 등록하는 방법은 여러가지가 있지만 스프링 부트를 사용하면 `FilterRegistrationBean`을 사용해서 등록하면 된다.

- `setFilter()`: 사용자가 직접 만든 필터를 할당해준다.
- `setOrder()`: 필터는 체인으로 동작한다. `setOrder()` 를 통해 필터의 순서를 정한다.

- `addUrlPatterns`: 필터가 호출되는 url을 설정해준다 `"/*"` 로 설정 시 모든 Url에서 필터가 호출된다.
