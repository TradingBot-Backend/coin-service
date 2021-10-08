package com.tradingbot.coininfoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class CoinInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinInfoServiceApplication.class, args);
	}
	

}
