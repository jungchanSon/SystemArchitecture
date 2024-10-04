package com.trafficlimitbucket.trafiic.repository;

import io.github.bucket4j.Bucket;
import org.springframework.data.repository.CrudRepository;

public interface IpRepository extends CrudRepository<Bucket, String> {
}
