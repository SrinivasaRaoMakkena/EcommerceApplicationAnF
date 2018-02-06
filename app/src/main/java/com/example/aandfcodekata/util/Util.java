package com.example.aandfcodekata.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Srinivas on 2/6/2018.
 */

public class Util {
    static Context context;

    public  Util(Context a) {
        this.context = a;
    }

    public static final String NEW_LINE = "\n";
    private static final String HTML_UNDERLINE_BEGIN = "<u>";
    private static final String HTML_UNDERLINE_END = "</u>";
    private static final String HTML_NEW_LINE = "<br />";

    public static Spanned addUnderLine(String string) {
        if (string == null || string.trim().length() == 0) {
            return Html.fromHtml("");
        }

        string = string.trim();
        StringBuffer buffer = new StringBuffer(HTML_UNDERLINE_BEGIN);

        int index = string.indexOf(NEW_LINE);
        while (index > 0) {
            buffer.append(string.substring(0, index));
            buffer.append(HTML_UNDERLINE_END);
            buffer.append(HTML_NEW_LINE);
            buffer.append(HTML_UNDERLINE_BEGIN);
            string = string.substring(++index);
            index = string.indexOf(NEW_LINE);
        }
        buffer.append(string);
        buffer.append(HTML_UNDERLINE_END);

        return Html.fromHtml(buffer.toString());
    }

    public static  List<String> extractUrls(String text) {
        List<String> containedUrls = new ArrayList<String>();
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find()) {
            containedUrls.add(text.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }

        return containedUrls;
    }


}
