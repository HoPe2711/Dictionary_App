package com.example.dictionary_ui.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleApiTranslate {

  public GoogleApiTranslate(){

  }

  public String translate(String langFrom, String langTo, String text) throws IOException {
    String urlStr = "https://script.google.com/macros/s/AKfycbyw6-MNCE-_-Pum2ZL3yxZzevbPzLWhsueBLmawKKeRQnMLSyY/exec" +
        "?q=" + URLEncoder.encode(text, "UTF-8") +
        "&target=" + langTo +
        "&source=" + langFrom;
    URL url = new URL(urlStr);
    StringBuilder response = new StringBuilder();
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("User-Agent", "Mozilla/5.0");
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    return response.toString();
  }

}
