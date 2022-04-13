package hello.itemservice.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableAsync
class RetryableServiceTest {

    @Autowired
    RetryableService retryableService;

    @Test
    public void Test() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            retryableService.asyncMethod(i);
        }
        Thread.sleep(1000);
    }
}