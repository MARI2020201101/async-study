package com.mariworld.async.exercise1;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;


import java.util.concurrent.*;

import java.util.concurrent.atomic.AtomicLong;

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
        Thread.sleep(1000);
        sw.stop();
        log.info("time : " + sw.getTotalTimeSeconds());
    }
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(1000);
        CyclicBarrier barrier = new CyclicBarrier(1001);
        AtomicLong counter = new AtomicLong(0);
        StopWatch main = new StopWatch();

        main.start();
        for(long i=0;i<1000;i++){
        es.submit(()-> {

            Client c = new Client(counter.addAndGet(1));
            c.run();
            barrier.await();
            return null;
            });
        }
        barrier.await();

        main.stop();
        log.info("total elapsed time:" + main.getTotalTimeSeconds());
        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);

   }


}
