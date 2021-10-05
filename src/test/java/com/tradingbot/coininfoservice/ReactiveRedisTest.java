package com.tradingbot.coininfoservice;


import com.tradingbot.coininfoservice.config.ReactiveRedisConfig;
import com.tradingbot.coininfoservice.domain.Ticker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
public class ReactiveRedisTest {
    @Autowired ReactiveRedisOperations reactiveRedisOperations;
    ReactiveHashOperations<String,String, Ticker> reactiveHashOperations;
    @BeforeEach
    void setUp(){
        reactiveHashOperations = reactiveRedisOperations.opsForHash();
    }
    @Test
    public void getData(){
        System.out.println(reactiveHashOperations.get("MA120","ETH").log().block());
    }
}
