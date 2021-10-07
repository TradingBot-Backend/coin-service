package com.tradingbot.coininfoservice.handler;

import com.tradingbot.coininfoservice.domain.Ticker;
import com.tradingbot.coininfoservice.service.CoinService;
import com.tradingbot.coininfoservice.service.TickerSinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CoinHandler {

    private final TickerSinkService tickerSinkService;
    private final CoinService coinService;

    public Mono<ServerResponse> getLatestCoinInfo(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(coinService.findLatestCoinInfo().sort(Comparator.comparingDouble(Ticker::getValue).reversed())
                .collectList(), ArrayList.class);
    }
    public Mono<ServerResponse> healthCheck(ServerRequest request){
        return ServerResponse.ok().bodyValue("health-check");
    }

}
