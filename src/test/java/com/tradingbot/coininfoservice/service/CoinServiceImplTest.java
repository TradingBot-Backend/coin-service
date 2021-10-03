package com.tradingbot.coininfoservice.service;

import com.tradingbot.coininfoservice.repository.CoinRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class CoinServiceImplTest {
    CoinServiceImpl coinService;
    @Autowired
    private CoinRepository coinRepository;

    @BeforeEach
    void setUp() {
        coinService = new CoinServiceImpl(coinRepository);
    }


    @Test @DisplayName("find distinct top by symbol")
    void findDistinctTopBySymbol() {
        coinService.findDistinctTopBySymbol("ETH").doOnNext(System.out::println).subscribe();
    }

    @Test
    void findDistinctLastBySymbolAfterOrderByVolumeAsc() {
    }
}