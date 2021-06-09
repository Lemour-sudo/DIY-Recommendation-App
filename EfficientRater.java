/**
 * Provides basic Rater fields and methods to uniquely identify different users
 * allow convenient interactivity between Rater objects and Movie objects.
 * 
 * @author Tshepo Mosoeunyane 
 * @version 11-5-2021
 */

import java.util.*;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if (myRatings.containsKey(item)){
            return true;
        }
        
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if (myRatings.containsKey(item)) {
            return myRatings.get(item).getValue();
        }
        
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();

        for(Rating item : myRatings.values()){
            list.add(item.getItem());
        }
        
        return list;
    }

    // Returns a string of the rater's information
    public String toString() {
        String result = "Rater id=" + myID;
        return result;
    }
}
