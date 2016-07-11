/**
 * 
 */
package manjeer.ecomm.engine;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import manjeer.ecomm.common.UrlDetails;
import manjeer.ecomm.website.EcommWebsite;

/**
 * This web crawler is a producer of product links. It writes links to a global
 * singleton. Written based on the BasicCrawler from Yasser Ganjisaffar.
 * 
 * Further improvement could be to check if the webpage contains a product or
 * not before adding to the list of links. The current implementation adds every
 * link.
 * 
 * @author Manjeer on Jul 7, 2016
 * 
 */
public class ProductCrawler extends WebCrawler {

	EcommWebsite ecommWebsite = null;

	private final static Pattern SPECIAL_FILE_TYPES = Pattern
			.compile(".*(\\.(css|js|bmp|gif|jpe?g" + "|png|tiff?|mid|mp2|mp3|mp4" + "|wav|avi|mov|mpeg|ram|m4v|pdf|ico"
					+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	private final List<String> socialKeyWords = Arrays
			.asList(new String[] { "twitter", "facebook", "instagram", "linkedin" });

	public ProductCrawler(EcommWebsite ecommWebsite) {
		this.ecommWebsite = ecommWebsite;
	}

	/*
	 * (non-Javadoc) Limit the domain of search.
	 * 
	 * @see edu.uci.ics.crawler4j.crawler.WebCrawler#shouldVisit(edu.uci.ics.
	 * crawler4j .url.WebURL)
	 */
	@Override
	public boolean shouldVisit(Page page, WebURL url) {
		String href = url.getURL().toLowerCase();
		LinksList pl = LinksList.getInstance();

		if (SPECIAL_FILE_TYPES.matcher(href).matches()) {
			return false;
		}

		if (ecommWebsite != null && ecommWebsite.getUrlShouldContain() != null) {
			for (String str : ecommWebsite.getUrlShouldContain()) {
				if (!href.contains(str.toLowerCase())) {
					return false;
				}
			}
		}
		if (ecommWebsite != null && ecommWebsite.getUrlShouldNotContain() != null) {
			for (String str : ecommWebsite.getUrlShouldNotContain()) {
				if (href.contains(str.toLowerCase())) {
					return false;
				}
			}
		}

		return !pl.isDuplicate(new UrlDetails(href, this.ecommWebsite, page)) && !isSocial(href);
	}

	/*
	 * (non-Javadoc) Action to take when a page is visited.
	 * 
	 * @see
	 * edu.uci.ics.crawler4j.crawler.WebCrawler#visit(edu.uci.ics.crawler4j.
	 * crawler.Page)
	 */
	@Override
	public void visit(Page page) {

		String url = page.getWebURL().getURL();

		/*
		 * singleton instance of the ProductList
		 */
		LinksList pl = LinksList.getInstance();

		/*
		 * store the url
		 */
		pl.put(new UrlDetails(url, this.ecommWebsite, page), 100000000000L);
	}

	private boolean isSocial(String href) {
		for (String socialKeyWord : socialKeyWords) {
			return href.toLowerCase().contains(socialKeyWord);
		}
		return false;
	}
}
