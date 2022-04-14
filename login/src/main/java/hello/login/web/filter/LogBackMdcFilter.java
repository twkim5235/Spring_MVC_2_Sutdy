package hello.login.web.filter;

import ch.qos.logback.classic.turbo.MDCFilter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogBackMdcFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("MDC logback init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        log.info("hello MDC");

        String uuid = UUID.randomUUID().toString();

        try {
            MDC.put("userId", uuid);
            LogBackMdcFilter.log.info("mdc test");
            chain.doFilter(request, response);
            MDC.clear();
        } catch (Exception e) {
            throw e;
        } finally {
            LogBackMdcFilter.log.info("finish MDC Filter");
        }


    }

    @Override
    public void destroy() {
        log.info("MDC logback destroy");
    }
}
