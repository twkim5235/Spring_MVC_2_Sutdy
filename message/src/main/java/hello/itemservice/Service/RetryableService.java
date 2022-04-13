package hello.itemservice.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class RetryableService {
    @Retryable(
            value = {IllegalStateException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 100)
    )
    public void tryRetry() {
        log.info("retry");
        int i = 1;

        if (i % 2 == 0) {
            throw new RuntimeException();
        }
        throw new IllegalStateException();
    }

    @Async(value = "asyncThread")
    public void asyncMethod(int i) {
        try {
            log.info("[시작]AsyncThread = " + i + " " + Thread.currentThread().getName());
//            Thread.sleep(500);
            log.info("[종료]AsyncThread = " + i + " " + Thread.currentThread().getName());
        } catch (Exception e) {

        }

//        } catch (InterruptedException e) {
//            e.printStackTrace();
    }

    @Bean(name = "asyncThread")
    public Executor asyncThread() {
        return Executors.newFixedThreadPool(8);
    }
}
