package hello.itemservice.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

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
}
