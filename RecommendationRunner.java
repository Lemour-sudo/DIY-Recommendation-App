import java.util.ArrayList;

public class RecommendationRunner implements Recommender {
    
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> movies = new ArrayList<String>();

        int queryYear = 2015;
        String queryGenre = "Mystery";

        AllFilters manyFilters = new AllFilters();
        manyFilters.addFilter(new GenreFilter(queryGenre));
        manyFilters.addFilter(new YearAfterFilter(queryYear));

        movies = MovieDatabase.filterBy(manyFilters);

        return movies;
    }

    public void printRecommendationsFor(String webRaterID) {
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");

        FourthRatings fourthR = new FourthRatings();

        int numSimilarRaters = 20;
        int minimalRaters = 5;
        ArrayList<Rating> weightedAvgRatings = fourthR.getSimilarRatings(
            webRaterID, numSimilarRaters, minimalRaters
        );

        // System.out.println("Found " + weightedAvgRatings.size() + " movie(s)");
        int maxDisplayMovies = 20;
        int numDisplayMovies = 0;
        System.out.println("<table>");
        for (Rating ratingItem : weightedAvgRatings) {
            String title = MovieDatabase.getTitle(ratingItem.getItem());
            double value = ratingItem.getValue();
            System.out.println("<tr> <td>" + value + "</td> <td>" + title + "</td> </tr>");

            if (numDisplayMovies > maxDisplayMovies) {
                break;
            }
            numDisplayMovies++;
        }

        if (weightedAvgRatings.size() == 0) {
            System.out.println("No movies found.");
        }
        System.out.println("</table>");
    }
}
