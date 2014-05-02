/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redink;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author bayan
 */
public class Tagger {
    
    private final String corpusPathProperty = "edu.stanford.postagger.corpus";
    private MaxentTagger tagger = null;
    private static Tagger obj = null;
    
    public synchronized static Tagger getTagger() {
        if(obj == null) {
            obj = new Tagger();
        }
        return obj;
    }

    private Tagger(){
        init();
    }

    private void init() {
        MaxentTagger tagger = null;
        String cp = Config.get(corpusPathProperty);
        tagger = new MaxentTagger(cp);
        this.tagger = tagger;
    }
    

    
    private IWord[] createWordArray(List<List<HasWord>> sentences) {
        ArrayList<IWord> words = new ArrayList<IWord>();
        int offset = 0;
        for(List<HasWord> sentence: sentences) {
            ArrayList<TaggedWord> tSentence = tagger.tagSentence(sentence);

            for(TaggedWord word : tSentence) {
                
                if(Pattern.matches("[a-zA-Z\\$]+", word.tag()) && !word.word().contains("'")) {
                    words.add(new StanfordWord(word, offset));
                    offset++;
                }
            }
        }
        IWord[] ret = new IWord[words.size()];
        words.toArray(ret);
        return ret;
    }
    
    public IWord[] tagFile(String path) {
        BufferedReader bf = null;
        FileReader fr = null;
        IWord[] words = new StanfordWord[0];
        try{
            fr = new FileReader(new File(path));
            bf = new BufferedReader(fr);
             List<List<HasWord>> sentences = MaxentTagger.tokenizeText(bf);
            words = createWordArray(sentences);
            
        }catch(IOException ex){ 
            Logger.getLogger(Tagger.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                bf.close();
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Tagger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return words;    
    }
}
