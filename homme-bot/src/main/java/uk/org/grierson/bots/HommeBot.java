package uk.org.grierson.bots;

import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bskyb.cbs.sdlc.bots.BotException;
import com.bskyb.cbs.sdlc.bots.SingleChannelBotService;

public class HommeBot extends SingleChannelBotService {
    private static final String HOMME_PROPERTIES_FILE = "src/main/resources/homme.properties";
    private static final Logger LOGGER = LogManager.getLogger(HommeBot.class);

    public HommeBot(String channel) throws BotException {
        super(channel, Paths.get(HOMME_PROPERTIES_FILE));
        
        LOGGER.info("Started HommeBot");
        LOGGER.debug("Adding listener");
    }
    
}
