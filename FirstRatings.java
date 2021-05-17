import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();

        for (CSVRecord record : parser) {
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String genres = record.get("genre");
            String director = record.get("director");
            String country = record.get("country");
            String poster = record.get("poster");
            int minutes = Integer.parseInt(record.get("minutes"));

            Movie movieRecord = new Movie(
                id,
                title,
                year,
                genres,
                director,
                country,
                poster,
                minutes
            );

            movies.add(movieRecord);
        }

        return movies;
    }

    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> result = new ArrayList<Rater>();
        HashMap<String,Rater> raters = new HashMap<String,Rater>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();

        for (CSVRecord record : parser) {
            String id = record.get("rater_id");
            String item = record.get("movie_id");
            Double rating = Double.parseDouble(record.get("rating"));

            Rater raterRecord;

            if (raters.containsKey(id)) {
                raterRecord = raters.get(id);
                
            }
            else {
                raterRecord = new Rater(id);
            }

            raterRecord.addRating(item, rating);
            raters.put(id, raterRecord);
        }

        for (Rater raterRecord : raters.values()) {
            result.add(raterRecord);
        }

        return result;
    }

    public HashMap<String,Integer> getDirectorsMovies(
        ArrayList<Movie> movies
    ) {
        HashMap<String,Integer> directorsMovies = new HashMap<String,Integer>();

        for (Movie movieItem : movies) {
            String directors = movieItem.getDirector().toLowerCase();
            for (String director : directors.split("\\s*,\\s*")) {
                if (directorsMovies.containsKey(director)) {
                    int numMovies = directorsMovies.get(director);
                    directorsMovies.put(director, numMovies+1);
                }
                else {
                    directorsMovies.put(director, 1);
                }
            }
        }

        return directorsMovies;
    }

    public HashMap<String,Integer> getDirectorsMaxMovies(
        HashMap<String,Integer> directorsMovies
    ) {
        HashMap<String,Integer> directorsMaxMovies = new HashMap<String,Integer>();

        int maxMovies = 0;
        for (String director : directorsMovies.keySet()) {
            int numMovies = directorsMovies.get(director);
            if (numMovies >= maxMovies) {
                if (numMovies > maxMovies) {
                    maxMovies = numMovies;
                    directorsMaxMovies.clear();
                }
                directorsMaxMovies.put(director, numMovies);
            }
        }

        return directorsMaxMovies;
    }

    public HashMap<String,ArrayList<Integer>> loadMovieRatings(
        String filename
    ) {
        HashMap<String,ArrayList<Integer>> movieRatings = new HashMap<String,ArrayList<Integer>>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();

        for (CSVRecord record : parser) {
            String item = record.get("movie_id");
            Integer raterID = Integer.parseInt(record.get("rater_id"));

            ArrayList<Integer> arrRatings;

            if (movieRatings.containsKey(item)) {
                arrRatings = movieRatings.get(item);
                
            }
            else {
                arrRatings = new ArrayList<Integer>();
            }
            
            arrRatings.add(raterID);
            movieRatings.put(item, arrRatings);
        }

        return movieRatings;
    }


    public void testLoadMovies() {
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        
        // System.out.println("Movies found:");
        // for (Movie movieItem : movies) {
        //     System.out.println(movieItem);
        // }
        System.out.println("Found " + movies.size() + " movies");

        int numComedy = 0;
        int longerThan150 = 0;
        for (Movie movieItem : movies) {
            if (movieItem.getGenres().contains("Comedy")) {
                numComedy++;
            }

            if (movieItem.getMinutes() > 150) {
                longerThan150++;
            }
        }
        System.out.println("\nNumber of Comedy movies: " + numComedy);
        System.out.println("\nNumber of movies longer than 150: " + longerThan150);

        HashMap<String,Integer> directorsMovies = getDirectorsMovies(movies);
        HashMap<String,Integer> directorsMaxMovies = getDirectorsMaxMovies(directorsMovies);
        System.out.println("\nDirectors with Most Number of Movies:");
        System.out.println(directorsMaxMovies.toString());
    }

    public void testLoadRaters() {
        String filename = "data/ratings.csv";

        ArrayList<Rater> raters = loadRaters(filename);
        
        // System.out.println("Raters found:");
        // for (Rater raterItem : raters) {
        //     System.out.println(raterItem);
        // }
        System.out.println("Found " + raters.size() + " raters");

        String queryRater = "193";
        int rater193 = 0;
        for (Rater raterItem : raters) {
            if (raterItem.getID().equals(queryRater)) {
                rater193 = raterItem.numRatings();
            }
        }
        System.out.println("\nRater 193 has " + rater193 + " rating(s).");
        

        ArrayList<Rater> maxRaters = new ArrayList<Rater>();
        int maxRatings = 0;
        for (Rater raterItem : raters) {
            int numRatings = raterItem.numRatings();
            if (numRatings >= maxRatings) {
                if (numRatings > maxRatings) {
                    maxRatings = numRatings;
                    maxRaters.clear();
                }
                maxRaters.add(raterItem);
            }
        }
        System.out.println("\nMost Number of Ratings: " + maxRatings);
        System.out.println("Raters with Most Number of Ratings:");
        System.out.println(maxRaters.toString());

        // HashMap<String,Integer> movieRatings = loadMovieRatings(filename);
        HashMap<String,ArrayList<Integer>> movieRatings = loadMovieRatings(filename);
        // System.out.println("\nMovie ratings:");
        // System.out.println(movieRatings.toString());
        String movieID = "1798709";
        System.out.println("\nMovie ID-" + movieID + " has " + movieRatings.get(movieID).size() + " rating(s)");

        System.out.println("\nThere are " + movieRatings.size() + " unique movies.");
    }
}
