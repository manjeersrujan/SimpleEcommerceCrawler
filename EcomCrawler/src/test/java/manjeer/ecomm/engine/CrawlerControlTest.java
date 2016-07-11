package manjeer.ecomm.engine;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import junit.framework.Assert;
import manjeer.ecomm.website.EcommWebsite;
import manjeer.ecomm.website.GroupOnSG;
import static org.mockito.Mockito.*;

/**
 * @author Manjeer on Jul 10, 2016
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CrawlerControlTest {

	CrawlerControl crawlerControl = new CrawlerControl();

	@Mock
	GroupOnSG groupOnSG;

	@Test
	public void nullTestCase() throws Exception {
		Assert.assertFalse(crawlerControl.startCrawler(null));
	}

	@Test
	public void emptyTestCase() throws Exception {
		Assert.assertFalse(crawlerControl.startCrawler(new ArrayList<EcommWebsite>()));
	}

	@Test
	public void happyTestCase() throws Exception {
		when(groupOnSG.getBaseUrl()).thenReturn("invalidUrl");
		ArrayList<EcommWebsite> ecommWebsites = new ArrayList<EcommWebsite>();
		ecommWebsites.add(groupOnSG);
		Assert.assertTrue(crawlerControl.startCrawler(ecommWebsites));
	}

}
