/**
 * 
 */
package manjeer.ecomm.engine;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import manjeer.ecomm.common.UrlDetails;

/**
 * This class holds the list of links found by the web crawler. These links are
 * consumed by the product parser in search of relevant product information. A
 * synchronization guarantees the mutual exclusion when accessing the list.
 * 
 * The class itself is implemented as a Singleton to guarantee that every part
 * of code is sharing the same list of links.
 * 
 * @author Manjeer on Jul 7, 2016
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class LinksListTest {

	LinksList linksList = LinksList.getInstance();

	@Test
	public void testSingleton() {
		for (int i = 0; i < 10; i++) {
			Assert.assertTrue(LinksList.getInstance() == linksList);
		}
	}

	@Test
	public void testPutAndGet() {
		UrlDetails ud = new UrlDetails("test1", null, null);
		linksList.put(ud, 10);
		Assert.assertTrue(ud.equals(linksList.get(10)));

	}

	@Test
	public void testAvoidingDuplicates() {
		UrlDetails ud = new UrlDetails("test1", null, null);
		linksList.put(ud, 10);
		linksList.get(10L);
		linksList.put(ud, 10);
		Assert.assertNull(linksList.get(10));
	}

}