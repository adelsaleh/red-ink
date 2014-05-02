package com.redink;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bayan
 */
public class Config {
    private static final String configFile = "configuration.properties";
    private static final Properties p = init();
    private static Properties init() {
        Properties p;
        p = new Properties();
        FileInputStream fis = null; 
        System.out.println("Haha");
        try {
            fis = new FileInputStream(new File(configFile));
            p.load(fis);
        } catch (IOException ex) {
            Logger.getLogger(Tagger.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Tagger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return p;
    }
    public static String get(String key) {
        System.out.println(p);
        return p.getProperty(key);
    }
    
}
