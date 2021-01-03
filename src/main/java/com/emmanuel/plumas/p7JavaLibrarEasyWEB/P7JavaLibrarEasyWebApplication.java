package com.emmanuel.plumas.p7JavaLibrarEasyWEB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@EnableFeignClients("com.emmanuel.plumas.p7JavaLibrarEasyWEB.proxies")
public class P7JavaLibrarEasyWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(P7JavaLibrarEasyWebApplication.class, args);
		
		/* Permettre une list JSON ne comportant qu'une seule valeur dans ce json */
		ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
	}

}
