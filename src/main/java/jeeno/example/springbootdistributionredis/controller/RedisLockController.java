package jeeno.example.springbootdistributionredis.controller;

import jeeno.example.springbootdistributionredis.ReturnDTO;
import jeeno.example.springbootdistributionredis.config.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author Jeeno
 * @version 0.0.1
 * @date 2019/9/2 20:35
 */
@RestController
@Slf4j
public class RedisLockController {

    @Resource
    private Properties props;

    @Resource
    private RedisLockRegistry redisLockRegistry;

    /**
     * the redis key for distributed locker
     */
    private final String LOCKER_KEY = "DISTRIBUTED_KEY";

    /**
     * the expired time of redis-distributed locker(seconds)
     */
    private final Integer LOCKER_EXPIRE = 5;

    @GetMapping("/call")
    public ReturnDTO<String> DistributionCalled() throws InterruptedException {
        log.info("function called.(port:{})", props.getPort());

        Lock redisLock = redisLockRegistry.obtain(LOCKER_KEY);
        boolean first = redisLock.tryLock(LOCKER_EXPIRE, TimeUnit.SECONDS);
        log.info("({}) first time try the locker:{}", props.getPort(), first);

        // stop the process for several seconds.
        TimeUnit.SECONDS.sleep(LOCKER_EXPIRE * 2);

        boolean second = redisLock.tryLock(LOCKER_EXPIRE, TimeUnit.SECONDS);
        log.info("({}) second time try the locker:{}", props.getPort(), second);

        redisLock.unlock();
        redisLock.unlock();

        return ReturnDTO.<String>builder()
                .data("your request has been processed.")
                .state(ReturnDTO.State.SUCCESS)
                .msg("you've called func:DistributionCalled").build();
    }
}
