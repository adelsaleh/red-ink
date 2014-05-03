package com.redink;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
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
       if (classifier == null) initClassifier(); 
        this.novel = novel;
    }

    private void initClassifier() { 
        classifier = CRFClassifier.getClassifierNoExceptions(Config.get(corpusPath));
    }
    
    private Location[] extractLocationFromWordArray(IWord[] words) {
        ArrayList<TaggedWord> taggedWords = new ArrayList<TaggedWord>();
        List<CoreLabel> classified =new ArrayList<CoreLabel>(); 
        ArrayList<TaggedWord> tmp1 = new ArrayList<TaggedWord>();
        for(int i = 0; i < words.length; i++) {
            IWord word = words[i];
            System.out.println(word);
            TaggedWord tw = new TaggedWord(word.getWord(), word.getTag().toString());
            taggedWords.add(tw);
            tmp1.add(tw);
            if(taggedWords.get(taggedWords.size()-1).tag() == ".") {
                List<CoreLabel> tmp = classifier.classifySentence(tmp1);
                for(CoreLabel cb: tmp) {
                    System.err.println(cb.tag());
                    classified.add(cb);
                }
                tmp1.clear();
            }
        }
        ArrayList<CoreLabel> ret = new ArrayList<CoreLabel>();
        for(int i = 0; i < classified.size(); i++) {
            System.out.println(classified.get(i).tag());
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

    public Location[] extractLocationFromRawText(String text) {
        List<List<CoreLabel>> ners = classifier.classify(text);
        ArrayList<CoreLabel> ret = new ArrayList<CoreLabel>();
        for(List<CoreLabel> classified : ners) { 
            for(int i = 0; i < classified.size(); i++) {
                String ner = classified.get(i).get(CoreAnnotations.AnswerAnnotation.class);
                if(ner != null && ner.equals("LOCATION")) {
                    ret.add(classified.get(i));
                }
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
        return extractLocationFromRawText(novel.rawText);
	}

	public boolean isLocation(IWord[] words) { 
	/**
	 * EFFECTS: Checks if words contains a location	
	 * RETURNS: true if words contains a location, false otherwise
	 */
		return extractLocationFromWordArray(words).length == 0; 
	}
}
