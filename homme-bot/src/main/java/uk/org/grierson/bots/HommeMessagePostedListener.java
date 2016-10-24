package uk.org.grierson.bots;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bskyb.cbs.sdlc.bots.*;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.SlackUser;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;

public class HommeMessagePostedListener extends MessagePostedListener {
    private static final Logger LOGGER = LogManager.getLogger(HommeMessagePostedListener.class);

    public HommeMessagePostedListener(HommeBot bot) {
        super(bot);
        LOGGER.trace("Added as a listener"); 
    }

    @Override
    public void onEvent(SlackMessagePosted event, SlackSession session) {
        LOGGER.trace("Received message posted event");
        LOGGER.trace("Channel == [" + event.getChannel().getName() + "]");

        // For direct messages we use the processDirectMessage() method.
        if ( event.getChannel().isDirect() ) {
            LOGGER.debug("Got a direct message from [" + event.getSender().getRealName() + "]");
            
            try {
                ((HommeBot) bot).processDirectMessage(event);
            }
            catch ( BotException e ) {
                LOGGER.error("Unable to send a message to [" + event.getSender().getRealName() + "]", e); 
            }
            
            return;
        }
            
        // Only process events from the correct channel for this bot and ignore our own messages.
        if ( ! event.getChannel().getName().equals(bot.getChannel().getName()) || 
             event.getSender().getId().equals(session.sessionPersona().getId()) ) {
            return;
        }
        
        SlackUser sender = event.getSender();
        String message = event.getMessageContent();
        
        LOGGER.trace("Sender == [" + sender.getId() + "]");
        LOGGER.trace("Received Message == [" + message + "]");
        
        ((HommeBot) bot).processChannelMessage(event);
    }
}
