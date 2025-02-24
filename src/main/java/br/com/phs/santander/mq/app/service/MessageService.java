package br.com.phs.santander.mq.app.service;

import org.springframework.stereotype.Service;

import br.com.phs.santander.mq.domain.dto.MQMessageDTO;
import br.com.phs.santander.mq.domain.usecase.MessageUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class MessageService {

    private MessageUseCase useCase;

    public String sendMessage(final MQMessageDTO message) {
        log.info("Message: " + message.getMessage() + " Destination: " + message.getDestination());
        return useCase.sendMessage(message);
    }

}
