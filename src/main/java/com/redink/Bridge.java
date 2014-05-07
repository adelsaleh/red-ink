/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redink;

import java.util.Arrays;
import org.json.JSONArray;
/**
 *
 * @author bayan
 */
public class Bridge {
        public void getNovel(String path) {
            final Novel n = new Novel(path);
            Client.b.openNovelView(n);
        }

        public void loadMap(int radius, String tagsJson) {
            System.out.println(tagsJson);
            String[] tags;
            JSONArray arr = new JSONArray(tagsJson);
            tags = new String[arr.length()];
            for(int i = 0; i < tags.length;i++) {
                tags[i] = arr.getString(i);
            }
            System.out.println(Arrays.toString(tags));
           Client.b.openMapView(radius, tags);
        }
    }
//