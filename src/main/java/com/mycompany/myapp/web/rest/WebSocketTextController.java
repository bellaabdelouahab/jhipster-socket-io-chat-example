package com.mycompany.myapp.web.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.myapp.domain.Message;
import com.mycompany.myapp.service.MessageService;

@RestController
@RequestMapping("/message")
public class WebSocketTextController {

    private final MessageService messageService;

    public WebSocketTextController(MessageService messageService) {
        this.messageService = messageService;
    }
    
    @GetMapping("/{room}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String room) {
        return ResponseEntity.ok(messageService.getMessages(room));
    }

}