package manjeer.ecomm.utils;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.suggest.Suggest.Suggestion;
import org.elasticsearch.search.suggest.Suggest.Suggestion.Entry;
import org.elasticsearch.search.suggest.Suggest.Suggestion.Entry.Option;
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder;

import manjeer.ecomm.common.SearchResults;

/**
 * @author Manjeer on Jul 10, 2016
 *
 */
public class QueryExecutor {

	public SearchResults executeQuery(String queryString) {
		if (StringUtils.isEmpty(queryString)) {
			return null;
		}

		SearchResponse actionGet = EsClient.INSTANCE.client.prepareSearch("ecomm").setTypes("product")
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.multiMatchQuery(queryString, "escapedText.*^3", "escapedText"))
				.addSuggestion(new TermSuggestionBuilder("Suggest1").field("escapedText").text(queryString)).setFrom(0)
				.setSize(20).execute().actionGet();

		SearchResults searchResults = new SearchResults();

		if (actionGet.getHits() != null && actionGet.getHits().totalHits() > 0) {
			for (SearchHit hit : actionGet.getHits()) {
				searchResults.setProduct(hit.getSource());
			}
		}

		for (Suggestion<? extends Entry<? extends Option>> x : actionGet.getSuggest()) {
			for (Entry<? extends Option> entries : x.getEntries()) {
				for (Option options : entries.getOptions()) {
					searchResults.setSuggestedString(
							queryString.replace(entries.getText().string(), options.getText().string()));
				}
			}
		}

		return searchResults;
	}

}
