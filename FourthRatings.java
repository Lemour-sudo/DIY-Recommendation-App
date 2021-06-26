import java.util.*;

public class FourthRatings {

    private double dotProduct(Rater me, Rater r) {
        ArrayList<String> meRatings = me.getItemsRated();
        ArrayList<String> rRatings = r.getItemsRated();
        double result = 0.0;
        double scale = 5.0;

        for (String meRate : meRatings) {
            if (rRatings.contains(meRate)) {
                double scaledMeRate = me.getRating(meRate) - scale;
                double scaledRRate = r.getRating(meRate) - scale;
                result += scaledMeRate * scaledRRate;
            }
        }

        return result;
    }

    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);

        for (Rater r : RaterDatabase.getRaters()) {
            if (!r.getID().equals(id)) {
                double dot = dotProduct(me, r);
                if (dot > 0.0) {
                    list.add(new Rating(r.getID(), dot));
                }
            }
        }

        Collections.sort(list, Collections.reverseOrder());

        return list;
    }

    private double getAverageByIDMinRates(String id, Integer minimalRaters) {
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();

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

    public ArrayList<Rating> getSimilarRatings(
        String id, Integer numSimilarRaters, Integer minimalRaters
    ) {
        ArrayList<Rating> movieWeightAvgs = new ArrayList<Rating>();

        ArrayList<Rating> similiraties = getSimilarities(id);
        ArrayList<Rating> topSimilarites = new ArrayList<Rating>();
        for (int i = 0; i < numSimilarRaters; i++) {
            String raterID = similiraties.get(i).getItem();
            double raterSim = similiraties.get(i).getValue();
            topSimilarites.add(new Rating(raterID, raterSim));
        }

        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String movieID : movies) {
            double totScore = 0.0;
            int numRaters = 0;

            for (Rating simRater : topSimilarites) {
                double simRating = simRater.getValue();

                Rater raterRatings = RaterDatabase.getRater(simRater.getItem());
                
                if (raterRatings.hasRating(movieID)) {
                    double movieRating = raterRatings.getRating(movieID);
                    totScore += simRating * movieRating;
                    numRaters++;
                }
            }

            if (numRaters >= minimalRaters) {
                double weightedAvg = totScore / numRaters;
                movieWeightAvgs.add(new Rating(movieID, weightedAvg));
            }
        }

        Collections.sort(movieWeightAvgs, Collections.reverseOrder());

        return movieWeightAvgs;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(
        String id, Integer numSimilarRaters, Integer minimalRaters,
        Filter filterCriteria
    ) {
        ArrayList<Rating> movieWeightAvgs = new ArrayList<Rating>();

        ArrayList<Rating> similiraties = getSimilarities(id);
        ArrayList<Rating> topSimilarites = new ArrayList<Rating>();
        for (int i = 0; i < numSimilarRaters; i++) {
            String raterID = similiraties.get(i).getItem();
            double raterSim = similiraties.get(i).getValue();
            topSimilarites.add(new Rating(raterID, raterSim));
        }

        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String movieID : movies) {
            double totScore = 0.0;
            int numRaters = 0;

            for (Rating simRater : topSimilarites) {
                double simRating = simRater.getValue();

                Rater raterRatings = RaterDatabase.getRater(simRater.getItem());
                
                if (raterRatings.hasRating(movieID)) {
                    double movieRating = raterRatings.getRating(movieID);
                    totScore += simRating * movieRating;
                    numRaters++;
                }
            }

            if (numRaters >= minimalRaters) {
                double weightedAvg = totScore / numRaters;
                movieWeightAvgs.add(new Rating(movieID, weightedAvg));
            }
        }

        Collections.sort(movieWeightAvgs, Collections.reverseOrder());

        return movieWeightAvgs;
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
