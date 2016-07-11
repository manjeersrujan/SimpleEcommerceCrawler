/**
 * 
 */
package manjeer.ecomm.engine;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.url.WebURL;
import junit.framework.Assert;
import manjeer.ecomm.website.GroupOnSG;

/**
 * 
 * @author Manjeer on Jul 7, 2016
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductCrawlerTest {

	@Mock
	GroupOnSG groupOnSG;

	@Mock
	Page page;

	@Mock
	WebURL url;

	@Test
	public void testShoudVisit1() {
		ProductCrawler productCrawler = new ProductCrawler(groupOnSG);
		when(url.getURL()).thenReturn("test1.mp4");
		Assert.assertFalse(productCrawler.shouldVisit(page, url));
	}

	@Test
	public void testShoudVisit2() {
		ProductCrawler productCrawler = new ProductCrawler(groupOnSG);
		when(url.getURL()).thenReturn("Notcontain--String/test1.mp4");
		List<String> list = new ArrayList<>();
		list.add("containString");
		when(groupOnSG.getUrlShouldContain()).thenReturn(list);
		Assert.assertFalse(productCrawler.shouldVisit(page, url));
	}

	@Test
	public void testShoudVisit3() {
		ProductCrawler productCrawler = new ProductCrawler(groupOnSG);
		when(url.getURL()).thenReturn("containString--test1");
		List<String> list = new ArrayList<>();
		list.add("containString");
		when(groupOnSG.getUrlShouldContain()).thenReturn(list);

		List<String> list1 = new ArrayList<>();
		list1.add("test");
		when(groupOnSG.getUrlShouldNotContain()).thenReturn(list1);
		Assert.assertFalse(productCrawler.shouldVisit(page, url));
	}

	@Test
	public void testShoudVisit4() {
		ProductCrawler productCrawler = new ProductCrawler(groupOnSG);
		when(url.getURL()).thenReturn("containString/No--te--st--facebook");
		List<String> list = new ArrayList<>();
		list.add("containString");
		when(groupOnSG.getUrlShouldContain()).thenReturn(list);

		List<String> list1 = new ArrayList<>();
		list.add("test");
		when(groupOnSG.getUrlShouldNotContain()).thenReturn(list1);
		Assert.assertFalse(productCrawler.shouldVisit(page, url));
	}

	@Test
	public void testShoudVisit5() {
		ProductCrawler productCrawler = new ProductCrawler(groupOnSG);
		when(url.getURL()).thenReturn("containString--No--te--st-NoDuplicate");
		List<String> list = new ArrayList<>();
		list.add("containString");
		when(groupOnSG.getUrlShouldContain()).thenReturn(list);

		List<String> list1 = new ArrayList<>();
		list1.add("test");
		when(groupOnSG.getUrlShouldNotContain()).thenReturn(list1);
		Assert.assertTrue(productCrawler.shouldVisit(page, url));
	}

	@Test
	public void testVisit1() {
		ProductCrawler productCrawler = new ProductCrawler(groupOnSG);
		when(url.getURL()).thenReturn("containString--No--te--st-NoDuplicate");
		when(page.getWebURL()).thenReturn(url);
		productCrawler.visit(page);
	}

}
