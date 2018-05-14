/*package xyz.shiguma.pubgpick;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OAuth {
    public static void oauth(String args[]) throws Exception{
        Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer("Xstj6EdlszseIQ7rPiOprcW5O", "1wBMUqt9U6BTDopLnoLfCD1Sjp8U5Zv8QNqYQX8ljOdaClzlDl");
        RequestToken requestToken = twitter.getOAuthRequestToken();
        AccessToken accessToken = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (null == accessToken) {
            System.out.println("Open the following URL and grant access to your account:");
            System.out.println(requestToken.getAuthorizationURL());
            System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
            String pin = br.readLine();
            try{
                if(pin.length() > 0){
                    accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                }else{
                    accessToken = twitter.getOAuthAccessToken();
                }
            } catch (TwitterException te) {
                if(401 == te.getStatusCode()){
                    System.out.println("Unable to get the access token.");
                }else{
                    te.printStackTrace();
                }
            }
        }
        storeAccessToken(twitter.verifyCredentials().getId() , accessToken);
        Status status = twitter.updateStatus(args[0]);
        System.out.println("Successfully updated the status to [" + status.getText() + "].");
        System.exit(0);
    }
    private static void storeAccessToken(long userId, AccessToken accessToken){
        System.out.println("userId: " + userId);
        System.out.println("accessToken: " + accessToken);
        //accessToken.getToken() を保存
        //accessToken.getTokenSecret() を保存
    }
}
*/