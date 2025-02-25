package br.com.phs.santander.mq.app.service;

import org.springframework.stereotype.Service;

import br.com.phs.santander.mq.domain.dto.MQMessageDTO;
import br.com.phs.santander.mq.domain.usecase.MQMessageUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class MQMessageService {

    private MQMessageUseCase useCase;

    public String sendMessage(final MQMessageDTO message) {
        log.info("Sending message: " + message.toString());
        return useCase.sendMessage(message);
    }

    public MQMessageDTO receiveMessage() {
        log.info("Receive Messages from queue.");
        return useCase.receiveMessage();
    }
}
