package com.mango.usercenter.rocketmq;


import com.mango.usercenter.system.domain.dto.messaging.UserAddBonusMsgDTO;
import com.mango.usercenter.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Configuration
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddBonusStreamConsumer  {
    private final IUserService userService;


    @Bean
    public Consumer<UserAddBonusMsgDTO> addBonusConsumer() {
        return msg -> {
            userService.addBonus(UserAddBonusMsgDTO.builder().userId(msg.getUserId()).bonus(msg.getBonus())
                            .event("CONTRIBUTE")
                            .description("投稿加积分..")
                            .build());
            log.info(Thread.currentThread().getName() + " Receive addBonus TOPIC New Messages: " + msg.getBonus() +"积分"
                   );
        };
    }


}
