/**
 * 
 */
package manjeer.ecomm.engine;

import java.io.Serializable;

/**
 * Class to represent products from the website.
 * 
 * @author Manjeer on Jul 7, 2016
 *
 */
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4157007060191830629L;
	private String id;
	private String name;
	private double price;
	private boolean isAvailable = true;
	private String url;
	private String escapedText;

	public Product() {
	}

	/**
	 * @param id
	 *            Product unique ID
	 * @param name
	 *            Product title/name
	 * @param price
	 *            Product price
	 */
	public Product(String id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the isAvailable
	 */
	public boolean isAvailable() {
		return isAvailable;
	}

	/**
	 * @param isAvailable
	 *            the isAvailable to set
	 */
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", isAvailable=" + isAvailable + ", url="
				+ url + "]";
	}

	/**
	 * @return the escapedText
	 */
	public String getEscapedText() {
		return escapedText;
	}

	/**
	 * @param escapedText the escapedText to set
	 */
	public void setEscapedText(String escapedText) {
		this.escapedText = escapedText;
	}
}
