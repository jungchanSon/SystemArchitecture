package com.trafficlimitbucket.trafiic.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value="ip", timeToLive = 30)
public class RequestIP {

    @Id
    private String id;

    private String ip;

}
