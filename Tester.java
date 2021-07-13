public class Tester {
    public static void main(String[] args) {
        MovieRunnerSimilarRatings movieRun = new MovieRunnerSimilarRatings();
        RecommendationRunner recoRun = new RecommendationRunner();

        // // test printAverageRatings 
        // movieRun.printAverageRatings();

        // // test printAverageRatingsByYearAfterAndGenre 
        // movieRun.printAverageRatingsByYearAfterAndGenre();

        // // test printSimilarRatings 
        // movieRun.printSimilarRatings();

        // // test printSimilarRatingsByGenre 
        // movieRun.printSimilarRatingsByGenre();

        // // test printSimilarRatingsByDirector  
        // movieRun.printSimilarRatingsByDirector();

        // // test printSimilarRatingsByGenreAndMinutes   
        // movieRun.printSimilarRatingsByGenreAndMinutes();

        // // test printSimilarRatingsByYearAfterAndMinutes   
        // movieRun.printSimilarRatingsByYearAfterAndMinutes();

        // test printSimilarRatingsByYearAfterAndMinutes   
        recoRun.printRecommendationsFor("167");
    }
}
