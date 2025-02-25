package br.com.phs.santander.mq.domain.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MQMessageDTO {

    private String message;
    private LocalDateTime inclusionTime;

}
