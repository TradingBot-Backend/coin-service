package com.tradingbot.coininfoservice.controller;

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

    @GetMapping("/health_check")
    public String healthCheck(){
        return "health_check";
    }
 /*   @GetMapping("")
    public Mono<String> getCoins(){
        return Mono.just("coins");
    }
*/
    //없는 코인이면 그거에 대한 답변 줘야징
    @GetMapping(value = "/{coin}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<String> getCoinTicker(@PathVariable String coin){
        return tickerSinkService.getTickerSink().asFlux().filter(ticker -> ticker.contains(coin));
    }
}
