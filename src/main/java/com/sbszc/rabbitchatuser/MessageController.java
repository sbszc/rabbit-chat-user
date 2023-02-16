package com.sbszc.rabbitchatuser;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<MessageDto> postMessage(@RequestBody MessageDto message) {
        MessageDto postedMessage = messageService.postMessage(message);
        return ResponseEntity.ok(postedMessage);
    }

    @GetMapping("ping")
    public String ping() {
        return "ping";
    }
}
