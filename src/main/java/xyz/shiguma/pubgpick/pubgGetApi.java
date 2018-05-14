/*package xyz.shiguma.pubgpick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;


public class pubgGetApi {

    public static void main(String[] args) {
        SysSystem.out.println("aaaaaaaaaaaaaaaaaaaaa");      URL url = null;
        try {
            url = new URL("https://api.playbattlegrounds.com/shards/pc-as/players?filter%5BplayerNames%5D=Shiguma_wq");//https://api.playbattlegrounds.com/status/ /https://api.playbattlegrounds.com/shards/pc-as/players?filter[playerNames]=Shiguma_wq
        } catch (MalformedURLException e) {
            System.out.println("1");
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            assert url != null;
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            System.out.println("2");
            e.printStackTrace();
        }
        try {
            assert conn != null;
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            System.out.println("3");
            e.printStackTrace();
        }
        conn.setRequestProperty("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyZWI3Nzc2MC0xOTdlLTAxMzYtOTY2Mi02MWFjOTE3MDJiMmEiLCJpc3MiOiJnYW1lbG9ja2VyIiwiaWF0IjoxNTIyNzY3ODQ4LCJwdWIiOiJibHVlaG9sZSIsInRpdGxlIjoicHViZyIsImFwcCI6InB1YmctcmF0ZS1ib3QiLCJzY29wZSI6ImNvbW11bml0eSIsImxpbWl0IjoxMH0.RT83_TzKA7svIkS3CSfMf8PrseH5z4Ob6ev6Te7VXDU");
        conn.setRequestProperty("Accept", "application/vnd.api+json");

        try {
            conn.getInputStream();
            StringBuilder result = new StringBuilder();
            //responseの読み込み
            final InputStream in = conn.getInputStream();
            final InputStreamReader inReader = new InputStreamReader(in);
            final BufferedReader bufferedReader = new BufferedReader(inReader);
            String line;
            while((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            bufferedReader.close();
            inReader.close();
            in.close();

            System.out.println(result.toString());
        } catch (IOException e1) {
            e1.printStackTrace();

        }
    }
}
*/

