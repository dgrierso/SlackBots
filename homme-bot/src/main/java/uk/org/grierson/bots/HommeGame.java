package uk.org.grierson.bots;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bskyb.cbs.sdlc.bots.BotException;

public class HommeGame {
    private static final Logger LOGGER = LogManager.getLogger(HommeGame.class);

    public static void main(String[] args) {
        LOGGER.info("Starting HommeGame");

        try {
            HommeBot bot = new HommeBot("webhook-test");

            // TODO : Somehow need to pass through a call back to the new
            // MessageListener to get it to do something with the message using
            // the callback.

            while ( bot.isRunning() ) {
                Thread.sleep(100);
            }
        }
        catch ( BotException | InterruptedException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
