package com.tradingbot.coininfoservice.service;

import com.tradingbot.coininfoservice.domain.Ticker;
import com.tradingbot.coininfoservice.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class CoinServiceImpl implements CoinService {
    private final CoinRepository coinRepository;

    @Override
    public Flux<Ticker> findDistinctTopBySymbol(String symbol) {
        return coinRepository.findDistinctTopBySymbol(symbol);
    }

    @Override
    public Flux<Ticker> findDistinctLastBySymbolAfterOrderByVolumeAsc(String symbol) {
        return coinRepository.findDistinctLastBySymbolAfterOrderByVolumeAsc(symbol);
    }
}
