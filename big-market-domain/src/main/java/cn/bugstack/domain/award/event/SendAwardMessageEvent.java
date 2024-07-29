package cn.bugstack.domain.award.event;

import cn.bugstack.types.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SendAwardMessageEvent extends BaseEvent<SendAwardMessageEvent.SendAwardMessage> {

    @Value("$spring.rabbitmq.topic.send_award")
    private String topic;

    @Override
    public EventMessage<SendAwardMessageEvent.SendAwardMessage> buildEventMessage(SendAwardMessageEvent.SendAwardMessage data) {
        return EventMessage.<SendAwardMessage>builder()
                .id(RandomStringUtils.randomNumeric(11))
                .timestamp(new Date())
                .data(data)
                .build();

    }

    @Override
    public String topic() {
        return topic;
    }
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SendAwardMessage{
        private String userId;

        private Integer awardId;

        private String awardTitle;
    }
}
