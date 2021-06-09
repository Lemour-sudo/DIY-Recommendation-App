import java.util.ArrayList;

public class DirectorsFilter implements Filter {
	private ArrayList<String> directors;
	
	public DirectorsFilter(ArrayList<String> directors) {
		this.directors = directors;
	}
	
	@Override
	public boolean satisfies(String id) {
		String movieDirectors = MovieDatabase.getDirector(id);

		for (String director : directors) {
			if (movieDirectors.contains(director)) {
				return true;
			}
		}

		return false;
	}

}
