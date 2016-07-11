package manjeer.ecomm.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import manjeer.ecomm.common.SearchResults;
import manjeer.ecomm.utils.QueryExecutor;

/**
 * @author Manjeer on Jul 9, 2016
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {

		while (true) {
			BufferedReader inp = null;
			try {
				inp = new BufferedReader(new InputStreamReader(System.in));

				System.out.println(" \n\n------------------------------------------------------- \n");

				System.out.println("Enter Search Query: ");
				String queryString = inp.readLine();

				SearchResults searchResults = new QueryExecutor().executeQuery(queryString);

				if (searchResults == null
						|| ((searchResults.getProducts() == null || searchResults.getProducts().size() == 0)
								&& searchResults.getSuggestedString() == null)) {
					System.out.println("No results and No Suggessions. Please enter proper string.");
					continue;
				}
				if (searchResults.getSuggestedString() != null) {
					System.out.println("\n\n\n Did you mean : " + searchResults.getSuggestedString() + "\n\n\n");
				}

				if (searchResults.getProducts().size() > 0) {
					System.out.println(" --------------------------  Results  ------------------------ \n");

					int i = 1;
					for (Map<String, Object> x : searchResults.getProducts()) {
						System.out.println(i + ") " + x.get("url"));
						i++;
					}
				} else {
					System.out.println("No results Found for: " + queryString);
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (inp != null) {
					inp.close();
				}
				return;
			}
		}

	}

}
