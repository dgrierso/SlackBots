package uk.org.grierson.bots;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ullink.slack.simpleslackapi.SlackUser;

public class Scorecard {
    private static final Logger LOGGER = LogManager.getLogger(Scorecard.class);
    public static final Scorecard SCORES = new Scorecard();
    
    private HashMap<SlackUser, Double> internalScorecard = new HashMap<>();

    private Scorecard() { }
    
    public void addScore(SlackUser user, double d) {
        LOGGER.trace("Adding score [" + d + "] to user [" + user.getRealName() + "]");
        
        internalScorecard.compute(user, (k,v) -> v == null ? d : v + d);
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Here's your scorecard\n");
        
        Iterable<SlackUser> iter = internalScorecard.keySet();
        
        iter.forEach(user -> str.append(user.getRealName() + " : " + internalScorecard.get(user) + "\n"));
        
        str.append("Done.");
        
        return str.toString();
    }
}
