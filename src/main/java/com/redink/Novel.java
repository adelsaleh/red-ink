package com.redink;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Arrays;

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
	private Word[] words;


	public Novel(String path) throws FileNotFoundException { 
		/**
		 * EFFECTS: Retrieves all words from the file in path and places 
		 * 			them in words. Words containing negative characters 
		 * THROWS: File Not Found Exception if the file does not exist
		 */
		ArrayList<Word> wordList = new ArrayList<Word>();
		Scanner reader = new Scanner(new File(path));
		reader.useDelimiter("[^a-zA-Z\']+");
		int offset = 0;
		while(reader.hasNext()) {
			String word = reader.next().toLowerCase();
			wordList.add(new Word(word, offset++));
		}
		reader.close();
		words = new Word[wordList.size()];
		wordList.toArray(words); 
	}
	
	private ArrayList<Integer> indicesOfSentence(Word[] sentence) {
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
	
	public Word[] getSurroundingSentence(Word[] sentence, int radius) {		
		/**
         * EFFECTS: Generates a list of words without duplicates (a duplicate is 
         * a word with the same string and offset) sorted by offset containing the 
         * words surrounding the sentence given as far as the given radius
         * 
         * RETURNS: A list of words containing all the surrounding words
         */
        ArrayList<Integer> indices = indicesOfSentence(sentence);
        LinkedList<Word[]> wordList = new LinkedList<Word[]>();
        if(indices.size()==0) {
            return new Word[]{};
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

	public Word[] getSurroundingWords(String w, int radius) { 
        /**
         * EFFECTS: Generates a list of words without duplicates (a duplicate is 
         * a word with the same string and offset) sorted by offset containing the 
         * words surrounding with the given radius each occurrence of the word
         * 
         * RETURNS: A list of words containing all the surrounding words
         * 
         * THROWS: WordNotFoundException in case the word is not found
         */
		
        
        return getSurroundingSentence(new Word[]{new Word(w)}, radius); 
    }

	public Word[] getSurroundingWords(int offset, int radius) { 
        /**
         * EFFECTS: Gets the words from offset-radius to offset+radius
         *          inclusive within radius radius
         * RETURNS: A list containing all the words
         * THROWS: IllegalArgumentException if offset<=0
         *          or if offset is bigger than the size of the novel.
         */
		if(radius<=0) throw new IllegalArgumentException();
	    Word[] preceding = getPrecedingWords(offset, radius);
        Word[] succeeding = getSucceedingWords(offset, radius);
        
		Word[] ret = concat(preceding, succeeding);

        return ret; 
    }

    private static Word[] concatMulti(LinkedList<Word[]> wordLists) {
        /**
         * EFFECTS: Returns an 
         */
        if(wordLists.size() == 0) {
            return new Word[0];
        }
        while(wordLists.size() > 1) {
            Word[] w1 = wordLists.pop();
            Word[] w2 = wordLists.pop();
            wordLists.push(concat(w2, w1));
        }
        return wordLists.remove();
    }

    private static Word[] concat(Word[] w1, Word[] w2) {
        /**
         * EFFECTS: Returns an array containing all the elements
         */
        Word[] ret = new Word[w1.length+w2.length];
        for(int i = 0; i < w1.length; i++) {
            ret[i] = w1[i];
        }

        for(int i = w1.length; i < ret.length; i++) {
            ret[i] = w2[i-w1.length];
        }
        return ret;
    }

    private Word[] getPrecedingWords(int offset, int radius) {
        /**
         * EFFECTS: Gets the words preceding offset by radius
         * RETURNS: List of the words
         */
        int start = offset - radius;
        start = (start > 0) ? start : 0;
        Word[] preceding;
		preceding = new Word[offset-start];
        for(int i = start; i < offset; i++) {
            preceding[i-start] = words[i];
        }
        return preceding;
    }

    private Word[] removeDuplicates(Word[] words, String removeWord) {
        /**
         * Removes duplicate words from words
         */
        if(words.length < 2) {
            return words;
        }
        Word[] ret = new Word[words.length];
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
    
    private Word[] removeDuplicates(Word[] words) {
        /**
         * Removes duplicate words from words
         */

        Word[] ret = new Word[words.length];
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

    private Word[] getSucceedingWords(int offset, int radius) {
        /**
         * EFFECTS: Gets the words succeeding offset by radius
         * RETURNS: List of the words
         */
        int start = offset + 1;
        if(start >= words.length) {
            return new Word[0];
        }
        
        int end = offset+radius;
        end = (end < words.length-1) ? end : words.length-1;
        Word[] succeeding = new Word[end+1-start];
        for(int i = start; i <= end; i++) {
            succeeding[i-start] = words[i];
        }
        return succeeding;
    }
}
