package uk.org.grierson.bots;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bskyb.cbs.sdlc.bots.*;

public class HommeGame {
    private static final Logger LOGGER = LogManager.getLogger(HommeGame.class);
    private static TripletList triplets = TripletList.TRIPLETS;
    
    public static void main(String[] args) {
        LOGGER.info("Starting HommeGame");

        try {
            triplets.isMatchingTriplet("foo");
        }
        catch ( Exception e ) {
            LOGGER.debug(e);
        }
        
        try {
            HommeBot bot = new HommeBot("webhook-test");

            while ( bot.isRunning() ) {
                Thread.sleep(100);
            }
        }
        catch ( BotException | InterruptedException e ) {
            LOGGER.error(e.getClass().getName() + " thrown: ", e);
        }
    }
}
