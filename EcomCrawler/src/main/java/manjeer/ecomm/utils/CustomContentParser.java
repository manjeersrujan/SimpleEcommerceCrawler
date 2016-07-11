/**
 * 
 */
package manjeer.ecomm.utils;

import manjeer.ecomm.engine.Product;

/**
 * This class is used to parse the content of a String using regular expression.
 * It was developed to extract specific values and links from websites. This
 * task could be performed by HTML parser. However, since the tags of the
 * desired values are not well defined, a regex parser tends to be more
 * flexible.
 * 
 * 
 * @author Manjeer on Jul 10, 2016
 *
 */
public interface CustomContentParser {

	/**
	 * @param strings
	 * @return
	 * 
	 * This method parses the urlContent(Using Regex generally).The
	 * specific patterns varies for each Website. So, not focusing on
	 * that.
	 */
	public Product customURLParse(Object... strings);

	/**
	 * 
	 * This method parses the url. The specific patterns varies for each
	 * Website. So, not focusing on that.
	 * 
	 * @param strings
	 * @return
	 */
	public Product customContentParse(Object... strings);

}
