package com.twitchViewer;

import java.util.ArrayList;

public class ChatMessages {

    ArrayList<String> chatEntries;

    void clearMessages(){
        chatEntries.clear();
    }

    void addMessage(String message){
        chatEntries.set(0, message);
    }

    public String getMessage(){
        return chatEntries.get(0);
    }


}
