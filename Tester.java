public class Tester {
    public static void main(String[] args) {
        FirstRatings fileR = new FirstRatings();
        MovieRunnerAverage movieAvg = new MovieRunnerAverage();

        // // testLoadMovies
        // fileR.testLoadMovies();

        // // testLoadRaters
        // fileR.testLoadRaters();

        // test printAverageRatings
        movieAvg.printAverageRatings();

        // // test getAverageRatingOneMovie
        // movieAvg.getAverageRatingOneMovie("Vacation");
    }
}
