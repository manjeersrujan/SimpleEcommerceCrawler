/**
 * 
 */
package manjeer.ecomm.engine;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

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
public final class LinksList {

	/**
	 * Max number of links stored in this list.
	 */
	private static final int LIMIT_NUMBER_LINKS = 10000;

	/**
	 * Holds the unique instance of this class.
	 */
	private static LinksList linksList;

	/**
	 * Internal list of links controlled by the implemented mutex.
	 */
	private LinkedList<UrlDetails> links;

	/**
	 * Internal list of links controlled by the implemented mutex.
	 */
	private Set<UrlDetails> linkMap;

	/**
	 * Amount of links stored in this list.
	 */
	private int numberOfLinks = 0;

	/**
	 * Protect the constructor to avoid new instances.
	 */
	private LinksList() {
		links = new LinkedList<UrlDetails>();
		linkMap = new HashSet<UrlDetails>();
	}

	/**
	 * Returns the unique instance of this class.
	 * 
	 * @return The singleton instance.
	 */
	public static LinksList getInstance() {
		if (linksList == null) {
			linksList = new LinksList();
		}
		return linksList;
	}

	/**
	 * Pop a link from the list.
	 * 
	 * @return a link.
	 */
	public synchronized UrlDetails get(long waitTime) {
		while (numberOfLinks <= 0) {
			try {
				wait(waitTime);
				if (numberOfLinks <= 0) {
					return null;
				}
			} catch (InterruptedException e) {
			}
		}
		numberOfLinks--;
		notifyAll();
		return links.pop();
	}

	/**
	 * Push a link to the list.
	 * 
	 * @param link
	 */
	public synchronized void put(UrlDetails urlDetails, long waitTime) {
		while (numberOfLinks >= LIMIT_NUMBER_LINKS) {
			try {
				wait(waitTime);
				if (numberOfLinks >= LIMIT_NUMBER_LINKS) {
					return;
				}
			} catch (InterruptedException e) {
			}
		}
		if (!linkMap.contains(urlDetails)) {
			links.push(urlDetails);
			linkMap.add(urlDetails);
			numberOfLinks++;
			notifyAll();
		}

	}

	public synchronized boolean isDuplicate(UrlDetails urlDetails) {
		return linkMap.contains(urlDetails);
	}
}