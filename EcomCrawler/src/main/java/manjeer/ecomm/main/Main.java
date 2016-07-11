package manjeer.ecomm.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import manjeer.ecomm.engine.CrawlerControl;
import manjeer.ecomm.engine.LinksList;
import manjeer.ecomm.engine.ParseAndIndexer;
import manjeer.ecomm.website.EcommWebsite;
import manjeer.ecomm.website.GroupOnSG;

/**
 * This application contains a web crawler that fetches every link from a
 * reference website. Links are processed in parallel so that those that
 * corresponds to product page are extracted and have their data stored to a
 * MySQL database. Products' price are exhibit as graphics. Therefore, an OCR
 * process is applied to extract the values.
 * 
 * Since it depends on OCR to obtain the relevant data, the success rate depends
 * on the quality of the graphics representing the values. This way, the
 * efficiency of this application cannot be fully guaranteed. Fine-tunning may
 * be required.
 * 
 * Crawler+producer and consumer are executed in different threads. They can run
 * indefinitely. However, a limit of products is set so that the application can
 * proceed retrieving and showing the stored data.
 * 
 * @author Manjeer on Jul 7, 2016
 * 
 */
public class Main {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		/*
		 * starts the web crawler in parallel
		 */
		List<EcommWebsite> ecommWebsites = new ArrayList<>();
		GroupOnSG groupOnSG = new GroupOnSG();
		ecommWebsites.add(groupOnSG);
		new CrawlerControl().startCrawler(ecommWebsites);

		/*
		 * Start parsing and indexing in parallel
		 */
		new ParseAndIndexer().parseUrlAndIndex(1000);
	}

}
