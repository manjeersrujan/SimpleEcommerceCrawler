package manjeer.ecomm.engine;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import manjeer.ecomm.common.UrlDetails;

/**
 * @author Manjeer on Jul 10, 2016
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ParseAndIndexerTest {

	@InjectMocks
	ParseAndIndexer parseAndIndexer = new ParseAndIndexer();

	@Mock
	Client client;

	LinksList linksList = LinksList.getInstance();

	@Mock
	UrlDetails urlDetails;

	@Mock
	ActionFuture<UpdateResponse> acrionFuture;

	@Test
	public void testParseUrlAndIndex() throws IOException {

		Product prod = new Product();
		prod.setAvailable(true);
		prod.setEscapedText("test1");
		prod.setId("test2");
		prod.setName("test3");
		prod.setPrice(1.0);
		prod.setUrl("test4");

		when(urlDetails.getProduct()).thenReturn(prod);
		when(client.update(any(UpdateRequest.class))).thenReturn(acrionFuture);

		linksList.put(urlDetails, 1000000L);
		parseAndIndexer.setWaitTime(100L);
		parseAndIndexer.parseUrlAndIndex(1);

		verify(client, times(1)).update(any(UpdateRequest.class));
	}

}
