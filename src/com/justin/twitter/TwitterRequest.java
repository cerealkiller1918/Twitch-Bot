package com.justin.twitter;

import com.justin.logger.Logger;
import com.justin.stackTrace.StackTrace;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by Justin Frasier on 07/26/16.
 */
public class TwitterRequest {

    private static String consumerKey = null;
    private static String consumerSecret = null;
    private static String accessToken = null;
    private static String accessTokenSecret = null;


    public static void friendFeed(){
        try {
            getLoginInfo();
            List <Status> statuses = twitter().getHomeTimeline();
            for(Status st : statuses){
               // Logger.console(" " + st.getUser().getName() + " >>> " + st.getText());
            }
        } catch (TwitterException e) {
           StackTrace.message(e);
        }
    }

    public static void userFeed(){
        try {
            List<Status> status = twitter().getUserTimeline();
            for (Status st : status){
               // Logger.console(" "+st.getUser().getName()+" >>>> "+ st.getText());
            }

        }catch(Exception e){
            StackTrace.message(e);
        }
    }

    private static void getLoginInfo(){

        ArrayList<String> strings = GetLoginInfo.info();
        if (!strings.isEmpty()){
            consumerKey = strings.get(0);
            consumerSecret = strings.get(1);
            accessToken = strings.get(2);
            accessTokenSecret = strings.get(3);
        }
    }

    private static Twitter twitter(){
        getLoginInfo();
        ConfigurationBuilder builder = new ConfigurationBuilder();

        builder
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);

        TwitterFactory twitterFactory = new TwitterFactory(builder.build());
        return (Twitter) twitterFactory.getInstance();
    }

}
