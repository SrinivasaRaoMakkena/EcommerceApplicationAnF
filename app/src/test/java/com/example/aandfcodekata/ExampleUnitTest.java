package com.example.aandfcodekata;

import com.example.aandfcodekata.util.Util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void extractUrls() throws Exception {
        assertEquals("https://www.abercrombie.com/shop/us/womens-new-arrivals", Util.extractUrls("whatareyoudoinghttps://www.abercrombie.com/shop/us/womens-new-arrivals how about you").get(0));
    }



}