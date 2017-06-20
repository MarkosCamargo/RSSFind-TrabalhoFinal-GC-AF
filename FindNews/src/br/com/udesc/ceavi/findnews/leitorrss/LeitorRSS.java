/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.udesc.ceavi.findnews.leitorrss;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

public class LeitorRSS {

    public static void main(String[] args) throws MalformedURLException, IOException, IllegalArgumentException, FeedException {

//        System.out.println(readRSSFeed("http://rss.cnn.com/rss/edition.rss"));
        System.out.println("----------------  ECONOMIA UOL RSS FEED   ---------------------------");
        System.out.println(readRSSFeed("http://rss.uol.com.br/feed/economia.xml", "ISO-8859-1"));
        System.out.println("----------------  VALOR RSS FEED   ---------------------------");
        System.out.println(readRSSFeed("http://www.valor.com.br/financas/rss", "UTF-8"));
//
//        System.out.println("----------------  INFOMONEY RSS FEED   ---------------------------");
//        System.out.println(readRSSFeed("http://www.infomoney.com.br/blogs/cambio/de-olho-no-dolar/rss"));
//

        //biblioteca rome.jar
//        System.out.println("----------------  VALOR RSS FEED   ---------------------------");
//        URL feedUrl = new URL("http://www.valor.com.br/financas/rss");
//        System.out.println(feedUrl.toString());
//        Charset inputCharset = Charset.forName("UTF-8");
        System.out.println("----------------  ECONOMIA UOL RSS FEED   ---------------------------");
        URL feedUrl = new URL("http://rss.uol.com.br/feed/economia.xml");
        Charset inputCharset = Charset.forName("ISO-8859-1");
      
        HttpURLConnection httpcon = (HttpURLConnection) feedUrl.openConnection();
//        System.out.println(  httpcon.getHeaderFields().toString());
        // Reading the feed
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new InputStreamReader(httpcon.getInputStream(), inputCharset));
        List entries = feed.getEntries();
        Iterator itEntries = entries.iterator();

        while (itEntries.hasNext()) {
            SyndEntry entry = (SyndEntry) itEntries.next();
            System.out.println("Titulo: " + entry.getTitle());
            System.out.println("Link: " + entry.getLink());
            System.out.println("Autor: " + entry.getAuthor());
            System.out.println("Data de Publicaçao: " + entry.getPublishedDate());
            System.out.println("Descriçao: " + entry.getDescription().getValue().trim());
            System.out.println();
        }

    }

    public static String readRSSFeed(String urlAddress, String charset) {
        try {
            URL rssUrl = new URL(urlAddress);

            Charset inputCharset = Charset.forName(charset);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream(), inputCharset));
            String sourceCode = "";
            String line;
            while ((line = in.readLine()) != null) {
                int titleEndIndex = 0;
                int titleStartIndex = 0;
                int descriptionEndIndex = 0;
                int descriptionStartIndex = 0;
                while (titleStartIndex >= 0) {
                    titleStartIndex = line.indexOf("<title>", titleEndIndex);
                    if (titleStartIndex >= 0) {
                        titleEndIndex = line.indexOf("</title>", titleStartIndex);
                        sourceCode += "TITLE: " + line.substring(titleStartIndex + "<title>".length(), titleEndIndex).replace("<![CDATA[", "") + "\nDESCRIPTION";
                    }
                    descriptionStartIndex = line.indexOf("<description>", descriptionEndIndex);
                    if (descriptionStartIndex >= 0) {
                        descriptionEndIndex = line.indexOf("</description>", descriptionStartIndex);
                        sourceCode += line.substring(descriptionStartIndex + "<description>".length(), descriptionEndIndex).replace("<![CDATA[", "")  + "\n";
                    }
                }
            }
            in.close();
            return sourceCode;
        } catch (MalformedURLException ue) {
            System.out.println("Malformed URL");
        } catch (IOException ioe) {
            System.out.println("Something went wrong reading the contents");
        }
        return null;
    }
}
