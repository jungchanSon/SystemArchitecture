package com.trafficlimitbucket.trafiic.filter;

import com.trafficlimitbucket.trafiic.repository.IpRepository;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class LimitIpFilter extends GenericFilterBean {

    int MAX_TOKEN = 10;
    int REFILL_TOKEN = 5;
    Duration REFILL_DURATION = Duration.ofSeconds(5);

    Map<String, Bucket> cache = new ConcurrentHashMap<>();

    private Bucket createBucket() {

        return Bucket.builder()
                .addLimit(Bandwidth.builder()
                        .capacity(MAX_TOKEN)
                        .refillIntervally(REFILL_TOKEN, REFILL_DURATION)
                        .build())
                .build();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String remoteAddr = servletRequest.getRemoteAddr();
        Bucket bucket = cache.computeIfAbsent(remoteAddr, (k)-> createBucket());

        if(bucket.tryConsume(1)) {
            log.info("LimitIpFilter: 토큰 컨슘 성공");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            log.info("LimitIpFilter: 토큰 컨슘 실패");
            servletResponse.setContentType("text/plain");
            servletResponse.getWriter().append("요청 제한");
        }

    }
}
