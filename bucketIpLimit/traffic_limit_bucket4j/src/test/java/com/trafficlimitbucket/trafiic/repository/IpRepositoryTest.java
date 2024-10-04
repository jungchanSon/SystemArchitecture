package com.trafficlimitbucket.trafiic.repository;

import io.github.bucket4j.Bucket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IpRepositoryTest {

    @Autowired
    IpRepository repository;

    @Test
    public void repositoryTest() {
        boolean b = repository.existsById("1");
        System.out.println("b = " + b);
    }
}