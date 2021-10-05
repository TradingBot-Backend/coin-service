package com.tradingbot.coininfoservice.controller;

import com.tradingbot.coininfoservice.domain.Ticker;
import com.tradingbot.coininfoservice.service.CoinService;
import com.tradingbot.coininfoservice.service.TickerSinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class CoinController {
    private final TickerSinkService tickerSinkService;
    private final CoinService coinService;
    @GetMapping("/health-check")
    public String healthCheck(){
        return "health-check";
    }

    @GetMapping(value = "/coin")
    public Flux<String> getLatestCoinInfo(){return coinService.findLatestCoinInfo();}

    @GetMapping(value = "/{coin}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<String> getCoinTicker(@PathVariable String coin){
        return tickerSinkService.getTickerSink().asFlux().filter(ticker -> ticker.contains(coin));
    }
}
