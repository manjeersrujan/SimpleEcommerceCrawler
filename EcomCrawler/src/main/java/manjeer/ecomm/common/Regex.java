/**
 * 
 */
package manjeer.ecomm.common;

/**
 * This class holds the regular expressions used to identify values and links
 * within a URL content.
 * 
 * @author Manjeer on Jul 7, 2016
 * 
 */
public class Regex {
	
	public final static String M_PRODUKTIV_PRODUCT = "<h2 style=\"text-align:left;\">(.*?)</h2>";
	public final static String M_PRODUKTIV_ID = "<div .*?Bestellnummer: (.*?)</div>";
	public final static String M_PRODUKTIV_PRICE_LINK = "<b>Unser Preis:</b></span> <img src=\"(.*?)\".*?/>";

}
