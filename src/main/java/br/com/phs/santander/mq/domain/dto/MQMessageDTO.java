package br.com.phs.santander.mq.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MQMessageDTO {

    private String message;
    private String destination;

}
