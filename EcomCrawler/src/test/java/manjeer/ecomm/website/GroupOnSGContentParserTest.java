package manjeer.ecomm.website;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import junit.framework.Assert;
import manjeer.ecomm.common.UrlDetails;
import manjeer.ecomm.engine.Product;

/**
 * @author Manjeer on Jul 10, 2016
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class GroupOnSGContentParserTest {

	GroupOnSGContentParser groupOnSGContentParser = new GroupOnSGContentParser();

	UrlDetails urlDetails = new UrlDetails(
			"https://www.groupon.sg/deals/shopping/gg-groupon-001-c-000001-rv-vwv-iaf-151/94550019", null, null);

	@Test
	public void testCustomURLParse() {
		urlDetails = new UrlDetails(
				"https://www.groupon.sg/deals/shopping/gg-groupon-001-c-000001-rv-vwv-iaf-151/94550019", null, null);

		Product product = groupOnSGContentParser.customURLParse(urlDetails);
		Assert.assertTrue(product.getId().equals("94550019"));
	}

	@Test
	public void testCustomURLParse1() {
		urlDetails = new UrlDetails(
				"https://www.groupon.sg/deals/shopping/gg-groupon-001-c-000001-rv-vwv-iaf-151/94550019", null, null);

		Product product = groupOnSGContentParser.customURLParse(urlDetails);
		Assert.assertTrue(product.getName().equals("gg-groupon-001-c-000001-rv-vwv-iaf-151"));
	}

	@Test
	public void testCustomURLParse2SoldOut() {
		urlDetails = new UrlDetails(
				"https://www.groupon.sg/deals/shopping/gg-groupon-001-c-000001-rv-vwv-iaf-151/94550019?soldout", null,
				null);
		Product product = groupOnSGContentParser.customURLParse(urlDetails);
		Assert.assertFalse(product.isAvailable());

	}

	@Test
	public void testCustomURLParse3PageContent() {
		/*
		 * To be written
		 */

	}

	public void testCustomContentParse() {

	}
}
