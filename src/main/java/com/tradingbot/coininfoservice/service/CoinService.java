package com.tradingbot.coininfoservice.service;

import com.tradingbot.coininfoservice.domain.Ticker;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public interface CoinService {
    Flux<Ticker> findDistinctTopBySymbol(String symbol);

    Flux<Ticker> findDistinctLastBySymbolAfterOrderByVolumeAsc(String symbol);
}
