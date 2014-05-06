/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redink;

/**
 *
 * @author bayan
 */
public class Bridge {
        public void getNovel(String path) {
            final Novel n = new Novel(path);
            Client.b.openNovelView(n);
        }

        public void loadMap(int radius, String[] tags) {
            
        }
    }
//