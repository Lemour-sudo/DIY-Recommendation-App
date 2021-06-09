public class Tester {
    public static void main(String[] args) {
        FirstRatings fileR = new FirstRatings();
        MovieRunnerAverage movieAvg = new MovieRunnerAverage();
        MovieRunnerWithFilters movieAvgFilt = new MovieRunnerWithFilters();

        // // testLoadMovies
        // fileR.testLoadMovies();

        // // testLoadRaters
        // fileR.testLoadRaters();

        // // test printAverageRatings
        // movieAvgFilt.printAverageRatings();
        
        // // test getAverageRatingOneMovie
        // movieAvg.getAverageRatingOneMovie("Vacation");

        // // test printAverageRatingsByYear
        // movieAvgFilt.printAverageRatingsByYear();

        // // test printAverageRatingsByGenre
        // movieAvgFilt.printAverageRatingsByGenre();
        
        // // test printAverageRatingsByMinutes
        // movieAvgFilt.printAverageRatingsByMinutes();

        // // test printAverageRatingsByDirectors
        // movieAvgFilt.printAverageRatingsByDirectors();

        // // test printAverageRatingsByYearAfterAndGenre
        // movieAvgFilt.printAverageRatingsByYearAfterAndGenre();

        // test printAverageRatingsByDirectorsAndMinutes 
        movieAvgFilt.printAverageRatingsByDirectorsAndMinutes();
    }
}
