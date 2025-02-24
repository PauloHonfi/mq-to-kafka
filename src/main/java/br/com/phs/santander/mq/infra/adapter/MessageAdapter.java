package br.com.phs.santander.mq.infra.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.phs.santander.mq.domain.dto.MQMessageDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageAdapter {

    @Value("${my.mq.queue}")
    private String queue;

    @Autowired
    @Qualifier("jmsOperationsmy")
    private JmsOperations jmsOperations;

    public String send(final MQMessageDTO message) {
        String queueReturn;
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            queueReturn = objectMapper.writeValueAsString(message);
            jmsOperations.convertAndSend(this.queue, queueReturn);
        }catch(JmsException | JsonProcessingException ex){
            throw new RuntimeException(ex);
        }
        return queueReturn;
    }

    public static void main(String[] args) {
        System.out.println("Teste" + System.lineSeparator() + "Teste2");
    }
}
