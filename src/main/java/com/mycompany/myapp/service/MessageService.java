/**
 * @author abdelouahabella
 * Date: Apr 06, 2023
 * Time: 10:28:14 AM
*/
package com.mycompany.myapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycompany.myapp.domain.Message;
import com.mycompany.myapp.repository.MessageRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages(String room) {
        return messageRepository.findAllByRoom(room);
    }

    public Message saveMessage(Message message) {
        // java local date time
        return messageRepository.save(message);
    }

}