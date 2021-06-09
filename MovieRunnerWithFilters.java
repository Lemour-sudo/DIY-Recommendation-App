import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        ThirdRatings thirdR = new ThirdRatings(
            "data/ratings.csv"
        );

        int numRaters = thirdR.getRaterSize();

        MovieDatabase.initialize("ratedmoviesfull.csv");
        int numMovies = MovieDatabase.size();

        System.out.println("Number of movies: " + numMovies);
        System.out.println("Number of raters: " + numRaters);

        int minimalRaters = 35;
        ArrayList<Rating> averageRatings = thirdR.getAverageRatings(minimalRaters);
        Collections.sort(averageRatings);

        System.out.println();
        System.out.println("Found " + averageRatings.size() + " movie(s)");
        // for (Rating ratingItem : averageRatings) {
        //     String title = MovieDatabase.getTitle(ratingItem.getItem());
        //     double value = ratingItem.getValue();
        //     System.out.println(value + " " + title);
        // }
    }

    public void printAverageRatingsByYear() {
        ThirdRatings thirdR = new ThirdRatings(
            "data/ratings.csv"
        );

        int numRaters = thirdR.getRaterSize();

        MovieDatabase.initialize("ratedmoviesfull.csv");
        int numMovies = MovieDatabase.size();

        System.out.println("Number of movies: " + numMovies);
        System.out.println("Number of raters: " + numRaters);

        int minimalRaters = 20;
        int queryYear = 2000;
        ArrayList<Rating> averageRatings = thirdR.getAverageRatingsByFilter(
            minimalRaters, new YearAfterFilter(queryYear)
        );
        Collections.sort(averageRatings);

        System.out.println();
        System.out.println("Found " + averageRatings.size() + " movie(s)");
        // for (Rating ratingItem : averageRatings) {
        //     String title = MovieDatabase.getTitle(ratingItem.getItem());
        //     int year = MovieDatabase.getYear(ratingItem.getItem());
        //     double value = ratingItem.getValue();
        //     System.out.println(value + " " + year + " " + title);
        // }
    }

    public void printAverageRatingsByGenre() {
        ThirdRatings thirdR = new ThirdRatings(
            "data/ratings.csv"
        );

        int numRaters = thirdR.getRaterSize();

        MovieDatabase.initialize("ratedmoviesfull.csv");
        int numMovies = MovieDatabase.size();

        System.out.println("Number of movies: " + numMovies);
        System.out.println("Number of raters: " + numRaters);

        int minimalRaters = 20;
        String queryGenre = "Comedy";
        ArrayList<Rating> averageRatings = thirdR.getAverageRatingsByFilter(
            minimalRaters, new GenreFilter(queryGenre)
        );
        Collections.sort(averageRatings);

        System.out.println();
        System.out.println("Found " + averageRatings.size() + " movie(s)");
        // for (Rating ratingItem : averageRatings) {
        //     String title = MovieDatabase.getTitle(ratingItem.getItem());
        //     String genres = MovieDatabase.getGenres(ratingItem.getItem());
        //     double value = ratingItem.getValue();
        //     System.out.println(value + " " + title);
        //     System.out.println("\t" + genres);
        // }
    }

    public void printAverageRatingsByMinutes() {
        ThirdRatings thirdR = new ThirdRatings(
            "data/ratings.csv"
        );

        int numRaters = thirdR.getRaterSize();

        MovieDatabase.initialize("ratedmoviesfull.csv");
        int numMovies = MovieDatabase.size();

        System.out.println("Number of movies: " + numMovies);
        System.out.println("Number of raters: " + numRaters);

        int minimalRaters = 5;
        int minMinutes = 105;
        int maxMinutes = 135;
        ArrayList<Rating> averageRatings = thirdR.getAverageRatingsByFilter(
            minimalRaters, new MinutesFilter(minMinutes, maxMinutes)
        );
        Collections.sort(averageRatings);

        System.out.println();
        System.out.println("Found " + averageRatings.size() + " movie(s)");
        // for (Rating ratingItem : averageRatings) {
        //     String title = MovieDatabase.getTitle(ratingItem.getItem());
        //     int time = MovieDatabase.getMinutes(ratingItem.getItem());
        //     double value = ratingItem.getValue();
        //     System.out.println(value + " Time: " + time + " " + title);
        // }
    }

    public void printAverageRatingsByDirectors() {
        ThirdRatings thirdR = new ThirdRatings(
            "data/ratings.csv"
        );

        int numRaters = thirdR.getRaterSize();

        MovieDatabase.initialize("ratedmoviesfull.csv");
        int numMovies = MovieDatabase.size();

        System.out.println("Number of movies: " + numMovies);
        System.out.println("Number of raters: " + numRaters);

        int minimalRaters = 4;
        String directorsStr = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";

        ArrayList<String> queryDirectors = new ArrayList<String>();
        for (String queryDirector : directorsStr.split("\\s*,\\s*")) {
            queryDirectors.add(queryDirector);
        }

        ArrayList<Rating> averageRatings = thirdR.getAverageRatingsByFilter(
            minimalRaters, new DirectorsFilter(queryDirectors)
        );
        Collections.sort(averageRatings);

        System.out.println();
        System.out.println("Found " + averageRatings.size() + " movie(s)");
        // for (Rating ratingItem : averageRatings) {
        //     String title = MovieDatabase.getTitle(ratingItem.getItem());
        //     String directors = MovieDatabase.getDirector(ratingItem.getItem());
        //     double value = ratingItem.getValue();
        //     System.out.println(value + " " + title);
        //     System.out.println("\t" + directors);
        // }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings thirdR = new ThirdRatings(
            "data/ratings.csv"
        );

        int numRaters = thirdR.getRaterSize();

        MovieDatabase.initialize("ratedmoviesfull.csv");
        int numMovies = MovieDatabase.size();

        System.out.println("Number of movies: " + numMovies);
        System.out.println("Number of raters: " + numRaters);

        int minimalRaters = 8;
        int queryYear = 1990;
        String queryGenre = "Drama";

        AllFilters yearAfterAndGenreFilters = new AllFilters();
        yearAfterAndGenreFilters.addFilter(new YearAfterFilter(queryYear));
        yearAfterAndGenreFilters.addFilter(new GenreFilter(queryGenre));
        

        ArrayList<Rating> averageRatings = thirdR.getAverageRatingsByFilter(
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

    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings thirdR = new ThirdRatings(
            "data/ratings.csv"
        );

        int numRaters = thirdR.getRaterSize();

        MovieDatabase.initialize("ratedmoviesfull.csv");
        int numMovies = MovieDatabase.size();

        System.out.println("Number of movies: " + numMovies);
        System.out.println("Number of raters: " + numRaters);

        int minimalRaters = 3;
        int minMinutes = 90;
        int maxMinutes = 180;
        String directorsStr = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";

        ArrayList<String> queryDirectors = new ArrayList<String>();
        for (String queryDirector : directorsStr.split("\\s*,\\s*")) {
            queryDirectors.add(queryDirector);
        }

        AllFilters minutesAndDirectorFilters = new AllFilters();
        minutesAndDirectorFilters.addFilter(new DirectorsFilter(queryDirectors));
        minutesAndDirectorFilters.addFilter(new MinutesFilter(minMinutes, maxMinutes));

        ArrayList<Rating> averageRatings = thirdR.getAverageRatingsByFilter(
            minimalRaters, minutesAndDirectorFilters
        );
        Collections.sort(averageRatings);

        System.out.println();
        System.out.println("Found " + averageRatings.size() + " movie(s)");
        // for (Rating ratingItem : averageRatings) {
        //     String title = MovieDatabase.getTitle(ratingItem.getItem());
        //     String directors = MovieDatabase.getDirector(ratingItem.getItem());
        //     double value = ratingItem.getValue();
        //     int time = MovieDatabase.getMinutes(ratingItem.getItem());

        //     System.out.println(value + " Time: " + time + " " + title);
        //     System.out.println("\t" + directors);
        // }
    }
}
