
/**
 * Links both the Movies and Raters objects together.
 * 
 * @author Tshepo Mosoeunyane
 * @version v.1.0.0
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("data/ratings.csv");
    }

    public ThirdRatings(String ratingsfile) {
        FirstRatings firstR = new FirstRatings();
        myRaters = firstR.loadRaters(ratingsfile);
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    private double getAverageByIDMinRates(String id, Integer minimalRaters) {
        int numRates = 0;
        double totalRating = 0.0;

        for (EfficientRater raterItem : myRaters) {
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

        for (EfficientRater raterItem : myRaters) {
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        for (String movieID : movies) {
            double avgRating = getAverageByIDMinRates(movieID, minimalRaters);

            if (avgRating > 0.0) {
                Rating selectedRating = new Rating(movieID, avgRating);
                avgRatings.add(selectedRating);
            }
        }

        return avgRatings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(
        int minimalRaters, Filter filterCriteria
    ) {
        ArrayList<Rating> moviesRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);

        for (String movieID : movies) {
            double movieRating = getAverageByIDMinRates(movieID, minimalRaters);

            if (movieRating > 0.0) {
                moviesRatings.add(new Rating(movieID, movieRating));
            }
        }

        // System.out.println("moviesRatings");
        // System.out.println(moviesRatings);

        return moviesRatings;
    }
    
}
