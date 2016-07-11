package manjeer.ecomm.engine;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;

import manjeer.ecomm.common.UrlDetails;
import manjeer.ecomm.utils.EsClient;

public class ParseAndIndexer {

	private Client ESClient = EsClient.INSTANCE.client;

	long waitTime = 100000000000L;

	/*
	 * Singleton instance containing the list of links tobe
	 */
	LinksList pList = LinksList.getInstance();

	public void parseUrlAndIndex(int productCount) throws IOException {

		/*
		 * Control the number products that can be crawled
		 */
		for (int productCounter = 0; productCounter < productCount;) {
			UrlDetails urlDetails = pList.get(waitTime);
			if (urlDetails == null) {
				continue;
			}
			Product prod = urlDetails.getProduct();
			if (prod == null) {
				System.out.println("No product found in the page: " + urlDetails.toString());
			} else {

				XContentBuilder builder = jsonBuilder().startObject().field("id", prod.getId())
						.field("name", prod.getName()).field("price", prod.getPrice())
						.field("isAvailable", prod.isAvailable()).field("url", prod.getUrl())
						.field("escapedText", prod.getEscapedText()).endObject();
				IndexRequest indexRequest = new IndexRequest("ecomm", "product", prod.getId()).source(builder.string());

				UpdateRequest updateRequest = new UpdateRequest("ecomm", "product", prod.getId()).doc(builder)
						.upsert(indexRequest);

				ESClient.update(updateRequest).actionGet();

				System.out.println("Product : " + prod.getId());
				productCounter++;
			}
		}
	}

	/**
	 * @return the eSClient
	 */
	public Client getESClient() {
		return ESClient;
	}

	/**
	 * @param eSClient
	 *            the eSClient to set
	 */
	public void setESClient(Client eSClient) {
		ESClient = eSClient;
	}

	/**
	 * @return the pList
	 */
	public LinksList getpList() {
		return pList;
	}

	/**
	 * @param pList
	 *            the pList to set
	 */
	public void setpList(LinksList pList) {
		this.pList = pList;
	}

	/**
	 * @return the waitTime
	 */
	public long getWaitTime() {
		return waitTime;
	}

	/**
	 * @param waitTime
	 *            the waitTime to set
	 */
	public void setWaitTime(long waitTime) {
		this.waitTime = waitTime;
	}

}
