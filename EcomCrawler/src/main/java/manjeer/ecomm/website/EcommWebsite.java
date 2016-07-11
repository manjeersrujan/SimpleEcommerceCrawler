package manjeer.ecomm.website;

import java.util.List;

import manjeer.ecomm.utils.CustomContentParser;

public interface EcommWebsite {

	/**
	 * @return the baseUrl
	 */
	String getBaseUrl();

	/**
	 * @return the urlShouldContain
	 */
	List<String> getUrlShouldContain();

	/**
	 * @return the urlShouldNotContain
	 */
	List<String> getUrlShouldNotContain();

	/**
	 * @return the urlParser
	 */
	CustomContentParser getUrlParser();

}