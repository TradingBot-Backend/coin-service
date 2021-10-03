package com.tradingbot.coininfoservice.repository;

import com.tradingbot.coininfoservice.domain.Ticker;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@EnableReactiveMongoRepositories
@Repository
public interface CoinRepository extends ReactiveMongoRepository<Ticker, String> {
    Flux<Ticker> findDistinctTopBySymbol(String symbol);
    Flux<Ticker> findDistinctLastBySymbolAfterOrderByVolumeAsc(String symbol);
}
