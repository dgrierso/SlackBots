package uk.org.grierson.bots;

import com.ullink.slack.simpleslackapi.*;
import com.ullink.slack.simpleslackapi.events.*;
import com.ullink.slack.simpleslackapi.listeners.*;

public class MessagePostedListener implements SlackMessagePostedListener {
    private SlackChannel listeningChannel;
    
    public MessagePostedListener(HommeBot hommeBot) {
        listeningChannel = hommeBot.getChannel();
    }

    @Override
    public void onEvent(SlackMessagePosted event, SlackSession session) {
        if ( ! event.getChannel().equals(listeningChannel) ) {
            return;
        }
        
        
    }

}
