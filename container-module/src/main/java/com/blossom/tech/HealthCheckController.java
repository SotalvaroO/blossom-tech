package com.blossom.tech;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/health-check")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<?> ok(){
        return ResponseEntity.ok().build();
    }

}
