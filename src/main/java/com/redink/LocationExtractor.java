package com.redink;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.TaggedWord;
import java.util.*;

public class LocationExtractor {
    /**
     * OVERVIEW: Contains the list of locations in a novel
     */

     /*
      * AF(novel)= le | le contains the list of locations
      * in the novel.
      */
	private Novel novel;
    private static final String corpusPath = "edu.stanford.ner.corpus";
    
    AbstractSequenceClassifier<CoreLabel> classifier;
    
    ArrayList<Location> locations = new ArrayList<Location>();
	public LocationExtractor(){
       if (classifier == null) initClassifier(); 
    }

	public LocationExtractor(Novel novel) { 
        this.novel = novel;
    }

    private void initClassifier() { 
        classifier = CRFClassifier.getClassifierNoExceptions(Config.get(corpusPath));
    }
    
    private Location[] extractLocationFromWordArray(IWord[] words) {
        ArrayList<TaggedWord> taggedWords = new ArrayList<TaggedWord>();
        for(int i = 0; i < words.length; i++) {
            IWord word = words[i];
            System.out.println(word);
            taggedWords.add(new TaggedWord(word.getWord(), word.getTag().toString()));
        }
        List<CoreLabel> classified = classifier.classifySentence(taggedWords);
        ArrayList<CoreLabel> ret = new ArrayList<CoreLabel>();
        for(int i = 0; i < classified.size(); i++) {
            System.out.println(classified.get(i).originalText());
            if(classified.get(i).ner() != null && classified.get(i).ner().equals("LOCATION")) {
                ret.add(classified.get(i));
            }
        }
        Location[] locations = new Location[ret.size()];
        for(int i = 0; i < locations.length; i++) {
            locations[i] = Geolocation.getLocation(ret.get(i).originalText());
        }
        return locations;
    } 
	public Location[] locations() { 
	/**
	 * EFFECTS: Retrieves all locations from novel
	 * RETURNS: An Iterator over the locations found in the novel	
	 */
        return extractLocationFromWordArray(novel.words);
	}

	public boolean isLocation(IWord[] words) { 
	/**
	 * EFFECTS: Checks if words contains a location	
	 * RETURNS: true if words contains a location, false otherwise
	 */
		return extractLocationFromWordArray(words).length == 0; 
	}
}
