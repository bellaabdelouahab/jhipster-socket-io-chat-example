import React, { useEffect, useState } from 'react';
// import { RiSendPlaneLine, RiSendPlaneFill } from 'react-icons/ri';
import { useSocket } from 'app/costumHook/useSocket';
import { useFetch } from 'app/costumHook/useFetch';
import { MessageItem } from './MessageItem';
import { MessageList } from './MessageList';
import { useAppSelector } from 'app/config/store';



type Props = {
  room: string;
  username: string;
};

const Message: React.FC<Props> = ({ room, username }) => {
  const { isConnected, socketResponse, sendData } = useSocket(room, username);
  const [messageInput, setMessageInput] = useState('');
  const [messageList, setMessageList] = useState([]);

  const { responseData } = useFetch('/message/' + room);

  useEffect(() => {
    if (responseData) {
      setMessageList([...responseData, ...messageList]);
    }
  }, [responseData]);

  useEffect(() => {
    console.log('Socket Response: ', socketResponse);
    addMessageToList(socketResponse);
  }, [socketResponse]);

  const addMessageToList = (val) => {
    if (val.room === '') return;
    setMessageList(prevList => [...prevList, val]);

  };

  const sendMessage = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log("start send message");
    
    if (messageInput !== '') {
      sendData({
        content: messageInput,
      });
      addMessageToList({
        content: messageInput,
        username: username,
        createdDateTime: new Date(),
        messageType: 'CLIENT',
      });
      setMessageInput('');
    }
  };

  return (
    <div className="message_root_div">
      <span className="room_name">Room: {room} </span>
      <span className="user_name">Welcome: {username} </span>
      <div className="message_component">
        <MessageList username={username} messageList={messageList} />
        <form className="chat-input" onSubmit={e => sendMessage(e)}>
          <input type="text" value={messageInput} onChange={e => setMessageInput(e.target.value)} placeholder="Type a message" />
          <button type="submit">
            {/* {messageInput === '' ? <RiSendPlaneLine size={25} /> : <RiSendPlaneFill color="#2671ff" size={25} />} */}
          </button>
        </form>
      </div>
    </div>
  );
};

export default Message;


export const timeStampConverter = time => {
  const date = new Date(time);
  const minute = date.getMinutes();
  const hour = date.getHours();
  return `${hour}:${minute}`;
};
