package br.com.phs.santander.mq.domain.usecase;

import br.com.phs.santander.mq.domain.dto.MQMessageDTO;

public interface MessageUseCase {

    public String sendMessage(final MQMessageDTO message);
}
