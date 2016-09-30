package uk.org.grierson.bots;

import org.apache.logging.log4j.*;

public class HommeGame {
    private static final Logger LOGGER = LogManager.getLogger(HommeGame.class);
    private static HommeBot bot;
    
    public static void main(String[] args) {
        LOGGER.trace("Starting HommeGame");
        
        try {
            bot = new HommeBot();
        }
        catch ( HommeBotException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
