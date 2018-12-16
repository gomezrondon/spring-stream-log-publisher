package com.gomezrondon.springstreamlogpublisher;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@EnableBinding(Source.class)
@RestController
public class EndPointController {

    private final Source mysource;

    @Autowired
    public EndPointController(Source mysource) {
        this.mysource = mysource;
    }

    @PostMapping("/brief")
    public String publishMessage(@RequestBody String payload) {

        System.out.println(payload);

        //send message to channel
       mysource.output().send(MessageBuilder.withPayload(payload).build());

        return "success";
    }

}
