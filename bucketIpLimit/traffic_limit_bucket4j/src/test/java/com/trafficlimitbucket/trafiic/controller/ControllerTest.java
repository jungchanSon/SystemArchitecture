package com.trafficlimitbucket.trafiic.controller;

import com.trafficlimitbucket.trafiic.repository.IpRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    Controller controller;

    @Test
    void number() throws Exception {
        for(int i=0; i<30; i++) {
            ResultActions perform = mockMvc.perform(
                    MockMvcRequestBuilders.get("/number")
            );
            perform.andExpect(result -> System.out.println( result
                    .getResponse()
                    .getContentAsString())
            );

        }
        Thread.sleep(1000*6);
        for(int i=0; i<30; i++) {
            ResultActions perform = mockMvc.perform(
                    MockMvcRequestBuilders.get("/number")
            );
            perform.andExpect(result -> System.out.println( result
                    .getResponse()
                    .getContentAsString())
            );

        }
    }
}