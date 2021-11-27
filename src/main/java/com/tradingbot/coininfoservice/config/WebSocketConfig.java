package com.tradingbot.coininfoservice.config;

import com.tradingbot.coininfoservice.service.TickerSinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
@EnableWebFlux
public class WebSocketConfig {
    private final TickerSinkService tickerSinkService;

    @Bean
    public HandlerMapping handlerMapping() {
        Map<String, WebSocketHandler> map = new HashMap<>();
        //Lambda로 바꾸기
        map = Collections.singletonMap("/ws/coins",
                session -> session.send(tickerSinkService
                        .getTickerSink()
                        .asFlux().
                        map(session::textMessage).
                        doOnNext(System.out::println)));

        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.initApplicationContext();
        mapping.setOrder(10);
        mapping.setUrlMap(map);
        return mapping;
    }

    /*
    @Bean
    public WebSocketHandlerAdapter handlerAdapter(){
        return new WebSocketHandlerAdapter(webSocketService());
    }

    @Bean
    public WebSocketService webSocketService(){
        return new HandshakeWebSocketService(new ReactorNettyRequestUpgradeStrategy());
    }*/
}