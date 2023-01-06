package com.patterns.gamma.behavioral;

/*
 * A central component that facilitates communication between components, without the individual components
 * being aware of one another, or having direct access to each other.
 */

import java.util.ArrayList;
import java.util.List;

class User {
    private List<String> chatLog = new ArrayList<>();
    public String name;
    public ChatRoom chatRoom;

    public User(String name) {
        this.name = name;
    }

    public void receiveMessage(String sender, String message) {
        String s = sender + ": '" + message + "'";
        System.out.println("[" + name + "'s chat session]" + s);
        chatLog.add(s);
    }

    public void say(String message) {
        chatRoom.broadcastMessage(name, message);
    }

    public void privateMessage(String receiver, String message) {
        chatRoom.message(name, receiver, message);
    }
}

/*
* The ChatRoom class acts as the mediator in this demo.
*/

class ChatRoom {
    private List<User> people = new ArrayList<>();

    public void joinRoom(User user) {
        String message = user.name + " has joined";
        broadcastMessage("room", message);

        user.chatRoom = this;
        people.add(user);
    }

    public void broadcastMessage(String sender, String message) {
        for (User user : people) {
            if (!user.name.equals(sender)) {
                user.receiveMessage(sender, message);
            }
        }
    }

    public void message(String sender, String receiver, String message) {
        people.stream().filter(p -> p.name.equals(receiver))
                .findFirst()
                .ifPresent(user -> user.receiveMessage(sender, message));
    }
}

class MediatorChatRoomDemo {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();
        User peter = new User("Peter");
        User bill = new User("Bill");

        chatRoom.joinRoom(peter);
        chatRoom.joinRoom(bill);

        peter.say("Hi all");
        bill.say("Hello Peter, what's happening?");

        User milton = new User("Milton");
        chatRoom.joinRoom(milton);
        milton.privateMessage("Peter", " I believe you have my stapler.");
    }
}
