package manjeer.ecomm.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Manjeer on Jul 9, 2016
 *
 */
public class SearchResults {

	List<Map<String, Object>> products = new ArrayList<Map<String, Object>>();

	String suggestedString = null;

	/**
	 * @return the suggestedString
	 */
	public String getSuggestedString() {
		return suggestedString;
	}

	/**
	 * @param suggestedString
	 *            the suggestedString to set
	 */
	public void setSuggestedString(String suggestedString) {
		this.suggestedString = suggestedString;
	}

	/**
	 * @return the products
	 */
	public List<Map<String, Object>> getProducts() {
		return products;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(List<Map<String, Object>> products) {
		this.products = products;
	}

	public void setProduct(Map<String, Object> product) {
		this.products.add(product);
	}

}
