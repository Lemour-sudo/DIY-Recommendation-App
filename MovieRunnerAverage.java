/**
 * Utilities for displaying movie average ratings for 
 * movies is in the SecondRatings class.
 * 
 * @author Tshepo Mosoeunyane
 * @version v.1.0.0
 */


import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        SecondRatings secondR = new SecondRatings(
            "data/ratedmoviesfull.csv", "data/ratings.csv"
        );
        ThirdRatings thirdR = new ThirdRatings(
            "data/ratings.csv"
        );

        int numMovies = secondR.getMovieSize();
        int numRaters = secondR.getRaterSize();

        System.out.println("Number of movies: " + numMovies);
        System.out.println("Number of raters: " + numRaters);

        int minimalRaters = 12;
        ArrayList<Rating> averageRatings = secondR.getAverageRatings(minimalRaters);
        Collections.sort(averageRatings);

        System.out.println();
        System.out.println(averageRatings.size() + " movie(s) have atleast " + minimalRaters + " ratings");
        // for (Rating ratingItem : averageRatings) {
        //     String title = secondR.getTitle(ratingItem.getItem());
        //     double value = ratingItem.getValue();
        //     System.out.println(value + " " + title);
        // }
    }

    public void getAverageRatingOneMovie(String title) {
        SecondRatings secondR = new SecondRatings(
            "data/ratedmoviesfull.csv", "data/ratings.csv"
        );

        String queryID = secondR.getID(title);

        double avgRating = secondR.getMovieAverage(queryID);
        System.out.println(title + ": " + avgRating);
    }

}
