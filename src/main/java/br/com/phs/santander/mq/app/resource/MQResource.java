package br.com.phs.santander.mq.app.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.phs.santander.mq.app.service.MQMessageService;
import br.com.phs.santander.mq.domain.dto.MQMessageDTO;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/mq")
@AllArgsConstructor
public class MQResource {

    private final MQMessageService service;

    @GetMapping
    public ResponseEntity<String> transferSchedule() {
        return ResponseEntity.ok().body("Sucesso");
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody final MQMessageDTO message) {
        return ResponseEntity.ok().body(service.sendMessage(message));
    }

    @GetMapping("/receive")
    public ResponseEntity<MQMessageDTO>  receive() {
        return ResponseEntity.ok().body(service.receiveMessage());
    }

}
