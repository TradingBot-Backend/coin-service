package com.tradingbot.coininfoservice.service;

import lombok.Getter;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.io.IOException;

@Component
@EnableKafka
@Getter
public class TickerSinkService {
    private Sinks.Many<String> tickerSink = Sinks.many().multicast().directBestEffort();

    @KafkaListener(topics={"BTC","ETH","XLM","XRP","LTC"
            ,"EOS", "ADA", "TRX", "LINK","BCH"} , groupId="rest-group")
    public void consume(String message) throws IOException {
        this.tickerSink.tryEmitNext(message);
    }
}
