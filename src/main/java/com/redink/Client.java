package com.redink;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import netscape.javascript.JSObject;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
 
 
@SuppressWarnings("restriction")
public class Client extends Application {
    private Scene scene;
    public static Browser b = new Browser();
    @Override public void start(Stage stage) {
        // create the scene
        stage.setTitle("Web View");
        scene = new Scene(b,750,500, Color.web("#666970"));
        stage.setScene(scene);        
        stage.show();
    }
 
    public static void main(String[] args){
        launch(args);
    }

}



@SuppressWarnings("restriction")
class Browser extends Region {
 
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
     
	public Browser() {
        //apply the styles
        getStyleClass().add("browser");
        // load the web page
        File f = new File("src/main/html/index.html");
        try {
            webEngine.load(f.toURI().toURL().toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(Browser.class.getName()).log(Level.SEVERE, null, ex);
        }webEngine.setJavaScriptEnabled(true);
         webEngine.getLoadWorker().stateProperty().addListener(
            new ChangeListener<Worker.State>() {
                public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, Worker.State oldState, Worker.State newState) {
                    if (newState == Worker.State.SUCCEEDED) {                        
                        JSObject jso = (JSObject) webEngine.executeScript("window");
                        jso.setMember("java", new Bridge());
                    }

                }
            });
        
        //add the web view to the scene
        getChildren().add(browser);
    }
    @SuppressWarnings("unused")
	private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    public void openNovelView(final Novel n) {
        ChangeListener cl = new ChangeListener<Worker.State>() {
            public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {
                
                String html = n.toHTML("label label-primary").replace("``", "\"").replace("''", "\"").replace("'", "\\\'");
                
                Document d = webEngine.getDocument();
                if (newState == Worker.State.SUCCEEDED) {                        
                    webEngine.executeScript("document.getElementById('novelDisplay').innerHTML='"+html+"'");

                }
                try {
                    PrintWriter out = new PrintWriter("/tmp/text");
                    String htmlout = (String) webEngine.executeScript("document.getElementById('lalala').innerHTML");
                    out.write(htmlout);
                    out.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Browser.class.getName()).log(Level.SEVERE, null, ex);
                }
                webEngine.getLoadWorker().stateProperty().removeListener(this);
            }
        };
        webEngine.getLoadWorker().stateProperty().addListener(cl);
            
    }
    
    public void openMapView () {

    }
 
    @Override 
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }
 
    @Override 
    protected double computePrefWidth(double height) {
        return 750;
    }
 
    @Override 
    protected double computePrefHeight(double width) {
        return 500;
    }

    
}

