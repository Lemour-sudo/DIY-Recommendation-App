
/**
 * Links both the Movies and Raters objects together.
 * 
 * @author Tshepo Mosoeunyane
 * @version v.1.0.0
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }

    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings firstR = new FirstRatings();
        myMovies = firstR.loadMovies(moviefile);
        myRaters = firstR.loadRaters(ratingsfile);
    }

    public int getMovieSize() {
        return myMovies.size();
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    private double getAverageByIDMinRates(String id, Integer minimalRaters) {
        int numRates = 0;
        double totalRating = 0.0;

        for (Rater raterItem : myRaters) {
            double rating = raterItem.getRating(id);
            if (rating > 0) {
                totalRating += rating;
                numRates++;
            }
        }

        if (numRates < minimalRaters) {
            return 0.0;
        }

        return totalRating / numRates;
    }

    public double getMovieAverage(String id) {
        int numRates = 0;
        double totalRating = 0.0;

        for (Rater raterItem : myRaters) {
            double rating = raterItem.getRating(id);
            if (rating > 0) {
                totalRating += rating;
                numRates++;
            }
        }

        return totalRating / numRates;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();

        for (Movie movieItem : myMovies) {
            String movieID = movieItem.getID();
            double avgRating = getAverageByIDMinRates(movieID, minimalRaters);

            if (avgRating > 0.0) {
                Rating selectedRating = new Rating(movieID, avgRating);
                avgRatings.add(selectedRating);
            }
        }

        return avgRatings;
    }

    public String getTitle(String id) {
        String title = "ID was not found.";

        for (Movie movieItem : myMovies) {
            if (movieItem.getID().equals(id)) {
                title = movieItem.getTitle();
            }
        }

        return title;
    }

    public String getID(String title) {
        String id = "NO SUCH TITLE.";

        for (Movie movieItem : myMovies) {
            if (movieItem.getTitle().equals(title)) {
                return movieItem.getID();
            }
        }

        return id;
    }
    
}
