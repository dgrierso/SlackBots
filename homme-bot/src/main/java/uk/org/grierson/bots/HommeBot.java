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
    private static TripletList triplets = TripletList.TRIPLETS;
    private static Scorecard scorecard = Scorecard.SCORES;

    public HommeBot() throws BotException {
        super(null, Paths.get(HOMME_PROPERTIES_FILE));
        
        //LOGGER.info("Started HommeBot on [" + channel + "]");
        LOGGER.trace("Adding listener");
        addMessagePostedListener(new HommeMessagePostedListener(this));
        
        LOGGER.info("Game started");
    }
    
    public void processChannelMessage(SlackMessagePosted event) {
        String message = event.getMessageContent();

        if ( triplets.containsMatchingTriplet(message) ) {
            LOGGER.debug("Got a triplet from [" + message + "]");
            this.sendMessage("[Hiccup]");
            LOGGER.trace("Sent hiccup");
            
            if ( message.length() == 3 ) {
                // got a winner so assign points to the winner
                scorecard.addScore(event.getSender(), 1.1);
            }
        }
    }

    public void processDirectMessage(SlackMessagePosted event) throws BotException {
        String message = event.getMessageContent();
        String response;
        
        switch ( message ) {
            case "help" :
                response = getHelp();
                break;
                
            case "scorecard" :
                response = getScorecard();
                break;
                
            case "shutdown" :
                response = "Bye, bye!";
                LOGGER.info("Shutting down due to command from " + event.getSender().getRealName());
                this.shutdown();
                break;
                
            default:
                response = unknownCommand();
        }
        
        sendDirectMessage(event.getSender(), response);
    }

    private String unknownCommand() throws BotException {
        return getProperty("unknown-command");
    }

    private String getScorecard() {
        return scorecard.toString();
    }

    private String getHelp() throws BotException {
        return getProperty("help-text");
    }
}
