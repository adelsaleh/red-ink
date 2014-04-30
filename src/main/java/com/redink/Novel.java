package com.redink;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
	private ArrayList<Word> words;


	public Novel(String path) throws FileNotFoundException { 
		/**
		 * EFFECTS: Retrieves all words from the file in path and places 
		 * 			them in words. Words containing negative characters 
		 * THROWS: File Not Found Exception if the file does not exist
		 */
		words = new ArrayList<Word>();
		Scanner reader = new Scanner(new File(path));
		reader.useDelimiter("[^a-zA-Z\']+");
		int offset = 0;
		while(reader.hasNext()) {
			String word = reader.next().toLowerCase();
			words.add(new Word(word, offset++));
		}
		reader.close();
		
	}
	
	private ArrayList<Integer> indicesOfSentence(Word[] sentence) {
		/**
		 * EFFECTS: Finds the position of sentence in the novel
		 * RETURNS: Index of first word in the sentence
		 */
		ArrayList<Integer> indices = new ArrayList<Integer>();
		int sentenceIndex = 0;
		for(int i = 0; i < words.size(); i++) {
			if(sentence[sentenceIndex].getWord().equals(words.get(i).getWord())) {
				sentenceIndex++;
			}else{
				sentenceIndex=0;
			}
			if(sentenceIndex == sentence.length-1) {
				indices.add(i-sentenceIndex);
			}
		}
		return (indices.size() == 0) ? indices:null;
	}
	
	public ArrayList<Word> getSurroundingSentence(Word[] sentence, int radius) {		
		/**
         * EFFECTS: Generates a list of words without duplicates (a duplicate is 
         * a word with the same string and offset) sorted by offset containing the 
         * words surrounding the sentence given as far as the given radius
         * 
         * RETURNS: A list of words containing all the surrounding words
         */
		return words;
		
	}

	public ArrayList<Word> getSurroundingWords(String w, int radius) { 
        /**
         * EFFECTS: Generates a list of words without duplicates (a duplicate is 
         * a word with the same string and offset) sorted by offset containing the 
         * words surrounding with the given radius each occurrence of the word
         * 
         * RETURNS: A list of words containing all the surrounding words
         * 
         * THROWS: WordNotFoundException in case the word is not found
         */
		
		if(radius<0) throw new IllegalArgumentException();
		ArrayList<Word> surroundingWords = new ArrayList<Word>();
		for(int i=0; i<words.size(); i++){
			if(words.get(i).getWord().equals(w)){
				for(Word word : getSurroundingWords(i, radius)) {
					surroundingWords.add(word);
				}
			}
		}
		Collections.sort(surroundingWords);
		for(int i=0; i<surroundingWords.size()-1; i++){
			if(surroundingWords.get(i).equals(surroundingWords.get(i+1)) || surroundingWords.get(i).getWord().equals(w)){
				surroundingWords.remove(i);
				i--;
			}
		}
        return surroundingWords; 
    }

	public ArrayList<Word> getSurroundingWords(int offset, int radius) { 
        /**
         * EFFECTS: Gets the words from offset-radius to offset+radius
         *          inclusive within radius radius
         * RETURNS: A list containing all the words
         * THROWS: IllegalArgumentException if offset<=0
         *          or if offset is bigger than the size of the novel.
         */
		if(radius<=0) throw new IllegalArgumentException();
		ArrayList<Word> surroundingWords = new ArrayList<Word>();
		if(offset-radius<0){
			for(int j=0; j<=offset+radius; j++){
				if(offset!=j) surroundingWords.add(words.get(j));
			}
		} else if(offset+radius>=words.size()){
			for(int j=offset-radius; j<words.size(); j++){
				if(offset!=j) surroundingWords.add(words.get(j));
			}
		} else {
			for(int j=offset-radius; j<=offset+radius; j++){
				if(offset!=j) surroundingWords.add(words.get(j));
			}
		}
		
        return surroundingWords; 
    }
}
