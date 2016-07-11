/**
 * 
 */
package manjeer.ecomm.engine;

import edu.uci.ics.crawler4j.crawler.CrawlController.WebCrawlerFactory;
import manjeer.ecomm.website.EcommWebsite;

/**
 * @author Manjeer on Jul 8, 2016
 *
 */
public class ProductCrawlerFactory implements WebCrawlerFactory<ProductCrawler> {
	
	EcommWebsite ecommWebsite = null;

	public ProductCrawlerFactory(EcommWebsite ecommWebsite) {
		this.ecommWebsite = ecommWebsite;
	}

	/* (non-Javadoc)
	 * @see edu.uci.ics.crawler4j.crawler.CrawlController.WebCrawlerFactory#newInstance()
	 */
	@Override
	public ProductCrawler newInstance() throws Exception {
		return new ProductCrawler(this.ecommWebsite);
	}

}
