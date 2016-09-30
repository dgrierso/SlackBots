package uk.org.grierson.bots;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ullink.slack.simpleslackapi.*;
import com.ullink.slack.simpleslackapi.impl.*;

public class HommeBot {
    private static final String DEFAULT_PROPERTIES_FILE = "src/main/resources/homme.properties";
    private static final Logger LOGGER = LogManager.getLogger(HommeBot.class);

    private Properties hommeProperties;
    private SlackSession session;
    
    public HommeBot() throws HommeBotException {
        this(Paths.get(DEFAULT_PROPERTIES_FILE));
    }
    
    public HommeBot(Path propertiesFile) throws HommeBotException {
        LOGGER.debug("Initialising HommeBot using properties from {}", propertiesFile.toString());
        
        try (InputStream stream = Files.newInputStream(propertiesFile)) {
            hommeProperties.load(stream);
        }
        catch ( IOException e ) {
            throw new HommeBotException("Unable to load properties file [" + propertiesFile + "]", e);

        }
        
        session = SlackSessionFactory.createWebSocketSlackSession("my-bot-auth-token");
        this.connect();
        
        //session.addMessagePostedListener(arg0);
        
    }

    private void connect() throws HommeBotException {
        if ( session != null ) {
            try {
                session.connect();
            }
            catch ( IOException e ) {
                throw new HommeBotException("Unable to connect", e);
            }
        }
        else {
            throw new HommeBotException("Session not yet initialised");
        }
    }
    
    
}
