import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings() {
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        FourthRatings fourthR = new FourthRatings();

        int numRaters = RaterDatabase.size();
        int numMovies = MovieDatabase.size();

        System.out.println("Number of movies: " + numMovies);
        System.out.println("Number of raters: " + numRaters);

        int minimalRaters = 35;
        ArrayList<Rating> averageRatings = fourthR.getAverageRatings(minimalRaters);
        Collections.sort(averageRatings);

        System.out.println();
        System.out.println("Found " + averageRatings.size() + " movie(s)");
        // for (Rating ratingItem : averageRatings) {
        //     String title = MovieDatabase.getTitle(ratingItem.getItem());
        //     double value = ratingItem.getValue();
        //     System.out.println(value + " " + title);
        // }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");

        FourthRatings fourthR = new FourthRatings();

        int numRaters = RaterDatabase.size();
        int numMovies = MovieDatabase.size();

        System.out.println("Number of movies: " + numMovies);
        System.out.println("Number of raters: " + numRaters);

        int minimalRaters = 8;
        int queryYear = 1990;
        String queryGenre = "Drama";

        AllFilters yearAfterAndGenreFilters = new AllFilters();
        yearAfterAndGenreFilters.addFilter(new YearAfterFilter(queryYear));
        yearAfterAndGenreFilters.addFilter(new GenreFilter(queryGenre));
        

        ArrayList<Rating> averageRatings = fourthR.getAverageRatingsByFilter(
            minimalRaters, yearAfterAndGenreFilters
        );
        Collections.sort(averageRatings);

        System.out.println();
        System.out.println("Found " + averageRatings.size() + " movie(s)");
        // for (Rating ratingItem : averageRatings) {
        //     String title = MovieDatabase.getTitle(ratingItem.getItem());
        //     String genres = MovieDatabase.getGenres(ratingItem.getItem());
        //     double value = ratingItem.getValue();
        //     int year = MovieDatabase.getYear(ratingItem.getItem());

        //     System.out.println(value + " " + year + " " + title);
        //     System.out.println("\t" + genres);
        // }
    }

    public void printSimilarRatings() {
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        FourthRatings fourthR = new FourthRatings();

        int numRaters = RaterDatabase.size();
        int numMovies = MovieDatabase.size();

        System.out.println("Number of movies: " + numMovies);
        System.out.println("Number of raters: " + numRaters);

        String raterID = "71";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        ArrayList<Rating> weightedAvgRatings = fourthR.getSimilarRatings(
            raterID, numSimilarRaters, minimalRaters
        );

        System.out.println();
        System.out.println("Found " + weightedAvgRatings.size() + " movie(s)");
        for (Rating ratingItem : weightedAvgRatings) {
            String title = MovieDatabase.getTitle(ratingItem.getItem());
            double value = ratingItem.getValue();
            System.out.println(value + " " + title);
            break;
        }
    }

    public void printSimilarRatingsByGenre() {
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        FourthRatings fourthR = new FourthRatings();

        int numRaters = RaterDatabase.size();
        int numMovies = MovieDatabase.size();

        System.out.println("Number of movies: " + numMovies);
        System.out.println("Number of raters: " + numRaters);

        String raterID = "964";
        int numSimilarRaters = 20;
        int minimalRaters = 5;

        String genre = "Mystery";

        ArrayList<Rating> weightedAvgRatings = fourthR.getSimilarRatingsByFilter(
            raterID, numSimilarRaters, minimalRaters, new GenreFilter(genre)
        );

        System.out.println();
        System.out.println("Found " + weightedAvgRatings.size() + " movie(s)");
        for (Rating ratingItem : weightedAvgRatings) {
            String title = MovieDatabase.getTitle(ratingItem.getItem());
            double value = ratingItem.getValue();
            System.out.println(value + " " + title);
            break;
        }
    }

    public void printSimilarRatingsByDirector() {
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        FourthRatings fourthR = new FourthRatings();

        int numRaters = RaterDatabase.size();
        int numMovies = MovieDatabase.size();

        System.out.println("Number of movies: " + numMovies);
        System.out.println("Number of raters: " + numRaters);

        String raterID = "120";
        int numSimilarRaters = 10;
        int minimalRaters = 2;

        String directorsStr = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";

        ArrayList<String> queryDirectors = new ArrayList<String>();
        for (String queryDirector : directorsStr.split("\\s*,\\s*")) {
            queryDirectors.add(queryDirector);
        }
        ArrayList<Rating> weightedAvgRatings = fourthR.getSimilarRatingsByFilter(
            raterID, numSimilarRaters, minimalRaters, new DirectorsFilter(queryDirectors)
        );

        System.out.println();
        System.out.println("Found " + weightedAvgRatings.size() + " movie(s)");
        for (Rating ratingItem : weightedAvgRatings) {
            String title = MovieDatabase.getTitle(ratingItem.getItem());
            double value = ratingItem.getValue();
            System.out.println(value + " " + title);
            break;
        }
    }

    public void printSimilarRatingsByGenreAndMinutes() {
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        FourthRatings fourthR = new FourthRatings();

        int numRaters = RaterDatabase.size();
        int numMovies = MovieDatabase.size();

        System.out.println("Number of movies: " + numMovies);
        System.out.println("Number of raters: " + numRaters);

        String raterID = "168";
        int numSimilarRaters = 10;
        int minimalRaters = 3;

        String queryGenre = "Drama";
        int minMinutes = 80;
        int maxMinutes = 160;
        AllFilters genreAndMinutesFilter = new AllFilters();
        genreAndMinutesFilter.addFilter(new GenreFilter(queryGenre));
        genreAndMinutesFilter.addFilter(new MinutesFilter(minMinutes, maxMinutes));

        ArrayList<Rating> weightedAvgRatings = fourthR.getSimilarRatingsByFilter(
            raterID, numSimilarRaters, minimalRaters, genreAndMinutesFilter
        );

        System.out.println();
        System.out.println("Found " + weightedAvgRatings.size() + " movie(s)");
        for (Rating ratingItem : weightedAvgRatings) {
            String title = MovieDatabase.getTitle(ratingItem.getItem());
            double value = ratingItem.getValue();
            System.out.println(value + " " + title);
            break;
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        FourthRatings fourthR = new FourthRatings();

        int numRaters = RaterDatabase.size();
        int numMovies = MovieDatabase.size();

        System.out.println("Number of movies: " + numMovies);
        System.out.println("Number of raters: " + numRaters);

        String raterID = "314";
        int numSimilarRaters = 10;
        int minimalRaters = 5;

        int queryYear = 1975;
        int minMinutes = 70;
        int maxMinutes = 200;
        AllFilters genreAndMinutesFilter = new AllFilters();
        genreAndMinutesFilter.addFilter(new YearAfterFilter(queryYear));
        genreAndMinutesFilter.addFilter(new MinutesFilter(minMinutes, maxMinutes));

        ArrayList<Rating> weightedAvgRatings = fourthR.getSimilarRatingsByFilter(
            raterID, numSimilarRaters, minimalRaters, genreAndMinutesFilter
        );

        System.out.println();
        System.out.println("Found " + weightedAvgRatings.size() + " movie(s)");
        for (Rating ratingItem : weightedAvgRatings) {
            String title = MovieDatabase.getTitle(ratingItem.getItem());
            double value = ratingItem.getValue();
            System.out.println(value + " " + title);
            break;
        }
    }

}
