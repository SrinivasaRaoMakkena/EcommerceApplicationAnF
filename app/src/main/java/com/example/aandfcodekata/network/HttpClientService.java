package com.example.aandfcodekata.network;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Srinivas on 2/5/2018.
 */

public class HttpClientService {



    public static String networkCalling() {
        URL url;
        HttpURLConnection httpURLConnection;
        String s = "";
        try {

            url = new URL("https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
                int data = in.read();

                while(data != -1){
                    s += (char)data;

                }
                System.out.println(s);
            } finally {
                httpURLConnection.disconnect();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
return s;
    }

}
