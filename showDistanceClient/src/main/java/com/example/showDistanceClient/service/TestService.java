package com.example.showDistanceClient.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestService {

    @Autowired
    private EurekaClient eurekaClient;

    @HystrixCommand(fallbackMethod = "failed")
    public String data(){
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("1", false);
        String serviceBaseUrl = instanceInfo.getHomePageUrl();
        String result = new RestTemplate().getForObject(serviceBaseUrl + "data", String.class);
        return result;
    }

    public String failed() {
        return "Service is not available now. Please try later";
    }

}
