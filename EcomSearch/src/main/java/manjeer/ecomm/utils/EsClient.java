package manjeer.ecomm.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * @author Manjeer on Jul 8, 2016
 *
 */
public enum EsClient {
	INSTANCE;

	public Client client = null;
	public BulkProcessor bulkProcessor;

	EsClient() {
		Settings settings = Settings.builder().put("client.transport.sniff", false)
				.put("cluster.name", "elasticsearch_z001v3f")
				/*
				 * To make sure it works in your environment even if cluster
				 * name is different
				 */
				.put("client.transport.ignore_cluster_name", true).build();

		TransportClient transportClient = TransportClient.builder().settings(settings).build();

		String[] esServers = new String[] { "localhost" };
		for (String serverString : esServers) {
			try {
				transportClient
						.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(serverString), 9300));
			} catch (UnknownHostException e) {
				System.out.println("Creating transport client failed");
				e.printStackTrace();
			}
		}
		client = transportClient;
		bulkProcessor = BulkProcessor.builder(client, new BulkProcessor.Listener() {

			@Override
			public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
				System.out.println("After bulk posting to elastic search");
			}

			@Override
			public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
				System.out.println("Error executing bulkprocessor. Error: " + failure);
			}

			@Override
			public void beforeBulk(long executionId, BulkRequest request) {
				System.out.println("Before bulk posting to elastic search");

			}
		}).setBulkActions(1000).setConcurrentRequests(1).build();

	}

	public Client getClient() {
		return this.client;
	}

}
