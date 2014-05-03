package com.redink;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Novel {
    /**
     * OVERVIEW: A list of words gotten from a file without
     *           punctuation.
     */

     /*
      * AF(words)=A list of all the tagged words contained in
      * the novel provided at filename.
      *
      * Representation Invariant: words contains no punctuation and contains
      *                           uninteresting words
      */
	private IWord[] words;
    protected String rawText;

    static String readFile(String path, Charset encoding) throws IOException {
      byte[] encoded = Files.readAllBytes(Paths.get(path));
      return new String(encoded, encoding);
    }
    
	public Novel(String path) { 
    /**
     * EFFECTS: Retrieves all words from the file in path and places
     * 			them in words. Words containing negative characters
     * THROWS: File Not Found Exception if the file does not exist
     */
        try {
            rawText = readFile(path, Charset.forName("UTF-8"));
        } catch (IOException ex) {
            Logger.getLogger(Novel.class.getName()).log(Level.SEVERE, null, ex);
        }
	    words = Tagger.getTagger().tagFile(path);
	}    
    public String getRawText() {
        return rawText;
    }
	
	public ArrayList<Integer> indicesOfSentence(IWord[] sentence) {
		/**
		 * EFFECTS: Finds the position of sentence in the novel
		 * RETURNS: Index of first word in the sentence
		 */
		ArrayList<Integer> indices = new ArrayList<Integer>();
		int sentenceIndex = 0;
		for(int i = 0; i < words.length; i++) {
            if(sentenceIndex == sentence.length) {
				indices.add(i-sentenceIndex);
                sentenceIndex = 0;
			}
			if(sentence[sentenceIndex].getWord().equals(words[i].getWord())) {
				sentenceIndex++;
			}else{
                sentenceIndex = 0;
            }
			
		}
		return indices;
	}
	
	public IWord[] getSurroundingSentence(IWord[] sentence, int radius) {		
		/**
         * EFFECTS: Generates a list of words without duplicates (a duplicate is 
         * a word with the same string and offset) sorted by offset containing the 
         * words surrounding the sentence given as far as the given radius
         * 
         * RETURNS: A list of words containing all the surrounding words
         */
        ArrayList<Integer> indices = indicesOfSentence(sentence);
        LinkedList<IWord[]> wordList = new LinkedList<IWord[]>();
        if(indices.size()==0) {
            return new IWord[]{};
        }
        wordList.push(getPrecedingWords(indices.get(0), radius));
        for(int i = 0; i < indices.size()-1; i++) {
            int index = indices.get(i);
            int nextIndex = indices.get(i+1);
            if(nextIndex-index > radius) {
                wordList.push(getSucceedingWords(index+sentence.length-1, radius));
                wordList.push(getPrecedingWords(nextIndex, radius));
            }else{
                wordList.push(getSucceedingWords(index+sentence.length-1, nextIndex-index-1));
            }
        }
        wordList.push(getSucceedingWords(indices.get(indices.size()-1)+sentence.length-1, radius));
        
		return concatMulti(wordList);
	}

	public IWord[] getSurroundingWords(String w, int radius) { 
        /**
         * EFFECTS: Generates a list of words without duplicates (a duplicate is 
         * a word with the same string and offset) sorted by offset containing the 
         * words surrounding with the given radius each occurrence of the word
         * 
         * RETURNS: A list of words containing all the surrounding words
         * 
         * THROWS: WordNotFoundException in case the word is not found
         */
		
        
        return getSurroundingSentence(new IWord[]{new StanfordWord(w)}, radius); 
    }

	public IWord[] getSurroundingWords(int offset, int radius) { 
        /**
         * EFFECTS: Gets the words from offset-radius to offset+radius
         *          inclusive within radius radius
         * RETURNS: A list containing all the words
         * THROWS: IllegalArgumentException if offset<=0
         *          or if offset is bigger than the size of the novel.
         */
		if(radius<=0) throw new IllegalArgumentException();
	    IWord[] preceding = getPrecedingWords(offset, radius);
        IWord[] succeeding = getSucceedingWords(offset, radius);
        
		IWord[] ret = concat(preceding, succeeding);

        return ret; 
    }

    private static IWord[] concatMulti(LinkedList<IWord[]> wordLists) {
        /**
         * EFFECTS: Returns an 
         */
        if(wordLists.size() == 0) {
            return new IWord[0];
        }
        while(wordLists.size() > 1) {
            IWord[] w1 = wordLists.pop();
            IWord[] w2 = wordLists.pop();
            wordLists.push(concat(w2, w1));
        }
        return wordLists.remove();
    }

    private static IWord[] concat(IWord[] w1, IWord[] w2) {
        /**
         * EFFECTS: Returns an array containing all the elements
         */
        IWord[] ret = new IWord[w1.length+w2.length];
        for(int i = 0; i < w1.length; i++) {
            ret[i] = w1[i];
        }

        for(int i = w1.length; i < ret.length; i++) {
            ret[i] = w2[i-w1.length];
        }
        return ret;
    }

    private IWord[] getPrecedingWords(int offset, int radius) {
        /**
         * EFFECTS: Gets the words preceding offset by radius
         * RETURNS: List of the words
         */
        int start = offset - radius;
        start = (start > 0) ? start : 0;
        IWord[] preceding;
		preceding = new IWord[offset-start];
        for(int i = start; i < offset; i++) {
            preceding[i-start] = words[i];
        }
        return preceding;
    }

    private IWord[] removeDuplicates(IWord[] words, String removeWord) {
        /**
         * Removes duplicate words from words
         */
        if(words.length < 2) {
            return words;
        }
        IWord[] ret = new IWord[words.length];
        int index = 0;
		for(int i=0; i<words.length-1; i++){
			if(!(words[i].equals(words[i+1]))
                        && !(words[i].getWord().equals(removeWord))){
                ret[index]=words[i];
                index++;
			}
		}
        if( !words[words.length-2].equals(words[words.length-1]) 
                &&!words[words.length-1].getWord().equals(removeWord)) {
            ret[index]=words[words.length-1];
            index++;
		}
        return Arrays.copyOf(ret, index);
    }
    
    private IWord[] removeDuplicates(IWord[] words) {
        /**
         * Removes duplicate words from words
         */

        IWord[] ret = new IWord[words.length];
        int index = 0;
		for(int i=0; i<words.length-1; i++){
			if( !words[i].equals(words[i+1])){
                
                ret[index]=words[i];
                index++;
			}
		}
        if( !words[words.length-2].equals(words[words.length-1])){        
            ret[index]=words[words.length-1];
            index++;
		}
        return Arrays.copyOf(ret, index);
    }

    private IWord[] getSucceedingWords(int offset, int radius) {
        /**
         * EFFECTS: Gets the words succeeding offset by radius
         * RETURNS: List of the words
         */
        int start = offset + 1;
        if(start >= words.length) {
            return new IWord[0];
        }
        
        int end = offset+radius;
        end = (end < words.length-1) ? end : words.length-1;
        IWord[] succeeding = new IWord[end+1-start];
        for(int i = start; i <= end; i++) {
            succeeding[i-start] = words[i];
        }
        return succeeding;
    }


    public String toHTML(String locationClass) {
        StringBuilder sb = new StringBuilder("<p>");
        LocationExtractor ex = new LocationExtractor(this);
        Location[] locations = ex.locations();
        LinkedList<Integer> indices = new LinkedList<Integer>();
        for(Location loc : locations) {
            ArrayList<Integer> tmp = indicesOfSentence(loc.getLocationName());
            for(Integer index : tmp) {
                indices.add(index);
                indices.add(index+loc.getLocationName().length-1);
            }
        }
        Collections.sort(indices);
        boolean before = true;
        for(int i = 0; i < words.length; i++) {
           if(indices.size() != 0) { 
                if(i != indices.peek()) {
                    sb.append(words[i].getWord());
                } else {
                    if(before){
                        sb.append("<span class='"+locationClass+"'>");
                        sb.append(words[i].getWord());
                        before = false;
                        indices.remove();
                    }else if(!before) {
                        sb.append(words[i].getWord());
                        sb.append("</span>");
                        before = true;
                        indices.remove();
                    }
                }
           }else{
               sb.append(words[i].getWord());
           }
           sb.append(" ");
        }
        sb.append("</p>");
        return sb.toString();
    }
}
