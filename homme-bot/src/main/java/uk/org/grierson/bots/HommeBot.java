package uk.org.grierson.bots;

import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bskyb.cbs.sdlc.bots.BotException;
import com.bskyb.cbs.sdlc.bots.SingleChannelBotService;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;

public class HommeBot extends SingleChannelBotService {
    private static final String HOMME_PROPERTIES_FILE = "src/main/resources/homme.properties";
    private static final Logger LOGGER = LogManager.getLogger(HommeBot.class);
    
    public HommeBot(String channel) throws BotException {
        super(channel, Paths.get(HOMME_PROPERTIES_FILE));
        
        LOGGER.info("Started HommeBot on [" + channel + "]");
        
        LOGGER.debug("Adding listener");
        addMessagePostedListener(new HommeMessagePostedListener(this));
    }
    
    public boolean messageContainsTriplet(String message) {
        return message.contains("how");
    }

    public void processDirectMessage(SlackMessagePosted event) throws BotException {
        String message = event.getMessageContent();
        String response = "";
        
        switch ( message ) {
            case "help" :
                response = getHelp();
                break;
            case "scorecard" :
                response = getScorecard();
                break;
            default:
                response = unknownCommand();
        }
        
        sendDirectMessage(event.getSender(), response);
    }

    private String unknownCommand() {
        return "I'm sorry I don't understand that command - try \"help\"";
    }

    private String getScorecard() {
        // TODO Auto-generated method stub
        return "This will return a scorecard";
    }

    private String getHelp() throws BotException {
        return getProperty("help-text");
    }
}
