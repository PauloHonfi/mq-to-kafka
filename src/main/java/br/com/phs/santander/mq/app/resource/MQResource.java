package br.com.phs.santander.mq.app.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.phs.santander.mq.app.service.MessageService;
import br.com.phs.santander.mq.domain.dto.MQMessageDTO;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/mq")
@AllArgsConstructor
public class MQResource {

    private final MessageService service;

    @GetMapping
    public ResponseEntity<String> transferSchedule() {
        return ResponseEntity.ok().body("Sucesso");
    }


    @GetMapping("/send")
    public ResponseEntity<String> send() {

        String mqQueue = service.sendMessage(MQMessageDTO.builder().message("Teste").destination("Destination test").build());
        return ResponseEntity.ok().body(mqQueue);
    }


}
