package manjeer.ecomm.engine;

import java.util.List;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.CrawlController.WebCrawlerFactory;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import manjeer.ecomm.website.EcommWebsite;

/**
 * This class configures the web crawler and starts it. The features were
 * configured for the specific usage in the ecommerce parser.
 * 
 * Basic on the BasicCrawlController from Yasser Ganjisaffar
 * 
 * @author Manjeer on Jul 7, 2016
 * 
 */
public class CrawlerControl {

	public boolean startCrawler(List<EcommWebsite> ecommWebsites) throws Exception {

		if (ecommWebsites == null || ecommWebsites.isEmpty()) {
			return false;
		}

		for (EcommWebsite ecommWebsite : ecommWebsites) {
			if (ecommWebsite.getBaseUrl() == null) {
				return false;
			}
		}

		CrawlConfig config = new CrawlConfig();

		// folder where intermediate crawl data is stored.
		config.setCrawlStorageFolder("crawlerData");

		/*
		 * Be polite: Make sure that we don't send more than 1 request per
		 * second (1000 milliseconds between requests).
		 */
		config.setPolitenessDelay(1);

		/*
		 * This config parameter can be used to set your crawl to be resumable
		 * (meaning that you can resume the crawl from a previously
		 * interrupted/crashed crawl). Note: if you enable resuming feature and
		 * want to start a fresh crawl, you need to delete the contents of
		 * rootFolder manually.
		 */
		config.setResumableCrawling(false);

		/*
		 * Instantiate the controller for this crawl.
		 */
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

		for (EcommWebsite ecommWebsite : ecommWebsites) {
			controller.addSeed(ecommWebsite.getBaseUrl());
			/*
			 * Start the crawl. This is a blocking operation, meaning that your
			 * code will reach the line after this only when crawling is
			 * finished. The parameter '1' corresponds to the number of threads.
			 * A single thread is sufficient for this case.
			 */
			WebCrawlerFactory<ProductCrawler> crawlerFactory = new ProductCrawlerFactory(ecommWebsite);
			controller.startNonBlocking(crawlerFactory, 5);
		}

		return true;

	}
}
