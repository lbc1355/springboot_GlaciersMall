package com.lioch3cooh.glaciersmall.config;

import com.lioch3cooh.glaciersmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class KeyExpiredListener extends KeyExpirationEventMessageListener {

    @Autowired
    private OrderService orderService;


    public KeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        String s = new String(message.getBody());
        if (s.contains("order")) {
            String[] orders = s.split("order");
            String orderId = orders[1];
            orderService.setOrderStatus(2, orderId);
        }

        super.onMessage(message, pattern);
    }
}
