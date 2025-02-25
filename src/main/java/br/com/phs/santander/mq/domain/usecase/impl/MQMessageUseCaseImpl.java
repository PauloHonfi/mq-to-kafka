package br.com.phs.santander.mq.domain.usecase.impl;

import org.springframework.stereotype.Component;

import br.com.phs.santander.mq.domain.dto.MQMessageDTO;
import br.com.phs.santander.mq.domain.usecase.MQMessageUseCase;
import br.com.phs.santander.mq.infra.adapter.MQMessageAdapter;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MQMessageUseCaseImpl implements MQMessageUseCase {

    private final MQMessageAdapter adapter;
    
    @Override
    public String sendMessage(final MQMessageDTO message) {
        return adapter.send(message);
    }

    @Override
    public MQMessageDTO receiveMessage() {
        return adapter.receive();
    }

}
