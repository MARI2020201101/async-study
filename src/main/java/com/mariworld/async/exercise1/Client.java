package com.mariworld.async.exercise1;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class Client implements Runnable{
    private RestTemplate restTemplate = new RestTemplate();
    private Long index;

    public Client(Long index){
        this.index= index;
    }

    public void sendApi(){
        String url ="http://localhost:8080/rest";
        ResponseEntity<String> response = restTemplate.getForEntity(url+"/{index}", String.class, this.index);

    }
    @SneakyThrows
    @Override
    public void run() {
        StopWatch sw = new StopWatch();
        sw.start();
        sendApi();
        Thread.sleep(100);
        sw.stop();
        System.out.println("time : " + sw.getTotalTimeSeconds());
    }
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
    /*    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.initialize();
*/


        for(long i=0;i<100;i++){

            Client client = new Client(i);

            new Thread(client).start();

          //  executor.execute(client);
        }



    }


}
