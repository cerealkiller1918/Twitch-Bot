package com.justin.test;


import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import com.justin.database.DataBase;
import com.justin.twitter.TwitterRequest;
import com.justin.weather.Weather;
import com.justin.youtube.YoutubeViewer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Justin on 07/24/16.
 */
public class Test {

    public static void main(String[] args) {
        Weather weather = new Weather();
        System.out.println(weather.getForcast("Shreveport","LA"));

    }
}
