package uk.org.grierson.bots;

import org.apache.logging.log4j.*;

public class HommeGame {
    private static final Logger LOGGER = LogManager.getLogger(HommeGame.class);
    private static HommeBot bot;
    
    public static void main(String[] args) {
        LOGGER.info("Starting HommeGame");
        
        try {
            bot = new HommeBot();
            
            while ( bot.isRunning() ) {
                Thread.sleep(1000);
            }
        }
        catch ( HommeBotException | InterruptedException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
