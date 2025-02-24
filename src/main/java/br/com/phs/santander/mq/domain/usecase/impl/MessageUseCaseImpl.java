package br.com.phs.santander.mq.domain.usecase.impl;

import org.springframework.stereotype.Component;

import br.com.phs.santander.mq.domain.dto.MQMessageDTO;
import br.com.phs.santander.mq.domain.usecase.MessageUseCase;
import br.com.phs.santander.mq.infra.adapter.MessageAdapter;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MessageUseCaseImpl implements MessageUseCase {

    private final MessageAdapter adapter;
    @Override
    public String sendMessage(final MQMessageDTO message) {
        return adapter.send(message);
    }

}
