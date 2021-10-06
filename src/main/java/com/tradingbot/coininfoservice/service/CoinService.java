package com.tradingbot.coininfoservice.service;

import com.tradingbot.coininfoservice.domain.Ticker;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface CoinService {
    Flux<Ticker> findLatestCoinInfo();
}
