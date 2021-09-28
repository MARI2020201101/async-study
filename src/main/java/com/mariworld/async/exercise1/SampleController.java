package com.mariworld.async.exercise1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SampleController {

    @GetMapping("/rest/{index}")
    public String rest(@PathVariable("index")Long index) throws InterruptedException {
        log.info("rest {}", index);
        Thread.sleep(1000);
        return "rest "+ index;
    }
}
