package com.mycompany.myapp.socket;

import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.SocketIOClient;
import com.mycompany.myapp.domain.Message;
import com.mycompany.myapp.domain.enumeration.MessageType;
import com.mycompany.myapp.service.MessageService;

@Service
public class SocketService {


    private final MessageService messageService;

    public SocketService(MessageService messageService) {
        this.messageService = messageService;
    }


    public void sendSocketMessage(SocketIOClient senderClient, Message message, String room) {
        for (
                SocketIOClient client : senderClient.getNamespace().getRoomOperations(room).getClients()) {
            if (!client.getSessionId().equals(senderClient.getSessionId())) {
                client.sendEvent("read_message",
                        message);
            }
        }
    }

    public void saveMessage(SocketIOClient senderClient, Message message) {

        Message msg = new Message();
        msg.setContent(message.getContent());
        msg.setRoom(message.getRoom());
        msg.setMessageType(MessageType.CLIENT);
        msg.setUsername(message.getUsername());

        Message storedMessage = messageService.saveMessage(msg);
        sendSocketMessage(senderClient, storedMessage, message.getRoom());
    }

    public void saveInfoMessage(SocketIOClient senderClient, String message, String room) {
        Message msg = new Message();
        msg.setContent(message);
        msg.setRoom(room);
        msg.setMessageType(MessageType.SERVER);
        // add date not java.time.Instant but  date from import java.util.Date;
        System.out.println(msg);
        Message storedMessage = messageService.saveMessage(msg);
        sendSocketMessage(senderClient, storedMessage, room);
    }
}
