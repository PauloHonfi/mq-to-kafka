package br.com.phs.santander.mq.infra.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.phs.santander.mq.domain.dto.MQMessageDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MQMessageService {

    @Value("${my.mq.queue}")
    private String queue;

    @Autowired
    @Qualifier("jmsOperationsmy")
    private JmsOperations jmsOperations;

    private final ObjectMapper mapper;

    public MQMessageService() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    public String send(final MQMessageDTO message) {
        String queueReturn;
        try{
            message.setInclusionTime(LocalDateTime.now());
            queueReturn = mapper.writeValueAsString(message);
            jmsOperations.convertAndSend(this.queue, queueReturn);
        }catch(JmsException | JsonProcessingException ex){
            throw new RuntimeException(ex);
        }
        return queueReturn;
    }

    public MQMessageDTO receive() {
        try {
            String message = (String) jmsOperations.receiveAndConvert(this.queue);
            log.info(message);
            return mapper.readValue(message, MQMessageDTO.class);
        } catch (JmsException | JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
