package com.tradingbot.coininfoservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradingbot.coininfoservice.domain.Symbol;
import com.tradingbot.coininfoservice.domain.Ticker;
import com.tradingbot.coininfoservice.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoinServiceImpl implements CoinService,InitializingBean{
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, String> redisTemplate;
    private HashOperations<String,String,String> hashOperations;

    @Override
    public Flux<Ticker> findLatestCoinInfo() {
        return Flux.fromStream(Arrays.asList(
                Symbol.values()).stream()
                .map(symbol -> {
                    try {
                        return objectMapper.readValue(hashOperations.get(symbol.toString(),"TICKER"),Ticker.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return null;
                }))
                .distinct();
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        hashOperations = redisTemplate.opsForHash();
    }
}
