package com.redink;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Tag;

public class WordCloud {
    /**
     * OVERVIEW: WordCloud: A set of (word, occurrence) tuple displayed in 
     * a compressed image.
     */

     /*
      * AF(location, histogram): An image containing histogram.keys.
      */
    private IWord[] location;
    private IWord[] sentence;
    private static final String keyConfig = "com.mashape.wordcloud.key";
    private String url;
    public WordCloud(){}

    public WordCloud(IWord[] location, IWord[] sentence) throws Exception { 
        this.location = location;
        this.sentence= sentence;
        StringBuilder sb = new StringBuilder();
        for(IWord word : sentence) {
            String w = word.getWord();
            sb.append(w);
        }
        HttpResponse<JsonNode> request = null;
        try {
             request = Unirest.post("https://gatheringpoint-word-cloud-maker.p.mashape.com/index.php")
                            .header("X-Mashape-Authorization", Config.get(keyConfig))
                            .field("height", Integer.toString(200))
                            .field("width", Integer.toString(300))
                            .field("config", "n/a")
                            .field("textblock", sb.toString())
                            .asJson();
        } catch (UnirestException ex) {
            Logger.getLogger(WordCloud.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(request.getBody());
        url = request.getBody().getObject().getString("url");
    } 

    
    public String getURL() {
    /**
     * EFFECTS: Generates the wordcloud image, writes it to
     *             persistent storage and returns the url
     * RETURNS: The URL of the image
     * THROWS: IOException in case disk write fails
     *         InvalidArgumentsException in case
     *         either histogram or location is empty.
     */
        return this.url;
    }
}
