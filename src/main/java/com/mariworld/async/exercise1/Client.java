package com.mariworld.async.exercise1;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class Client implements Runnable{
    private RestTemplate restTemplate = new RestTemplate();


    public void sendApi(){
        String url ="http://localhost:8080/rest";
        ResponseEntity<String> response = restTemplate.getForEntity(url+"/{index}", String.class, 1L);
        log.info("response : " +response);
    }
    @SneakyThrows
    @Override
    public void run() {
        sendApi();
        Thread.sleep(100);
    }
    public static void main(String[] args) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.initialize();

        for(int i=0;i<100;i++){
            Client client = new Client();
            executor.execute(client);
        }





    }


}
