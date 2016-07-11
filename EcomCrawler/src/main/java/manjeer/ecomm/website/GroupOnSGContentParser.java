package manjeer.ecomm.website;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import edu.uci.ics.crawler4j.parser.HtmlParseData;
import manjeer.ecomm.common.UrlDetails;
import manjeer.ecomm.engine.Product;
import manjeer.ecomm.utils.CustomContentParser;

/**
 * @author Manjeer on Jul 10, 2016
 *
 */
public class GroupOnSGContentParser implements CustomContentParser {

	@Override
	public Product customURLParse(Object... objects) {

		if (objects != null && objects.length > 0 && objects[0] != null && objects[0] instanceof UrlDetails) {
			UrlDetails urlDetails = (UrlDetails) objects[0];
			String urlString = urlDetails.getUrl();
			if (urlString.contains("deals")) {
				String[] urlParts = urlString.split("\\?");
				String[] urlTokens = urlParts[0].split("/");
				if (urlTokens.length > 2 && StringUtils.isNumeric(urlTokens[urlTokens.length - 1])) {
					Product p = new Product();
					p.setName(urlTokens[urlTokens.length - 2]);
					p.setId(urlTokens[urlTokens.length - 1]);
					p.setPrice(RandomUtils.nextDouble(0, 10000L));
					p.setUrl(urlString);
					if (urlDetails.getPage() != null && urlDetails.getPage().getParseData() != null
							&& urlDetails.getPage().getParseData() instanceof HtmlParseData) {

						HtmlParseData htmlParseData = (HtmlParseData) urlDetails.getPage().getParseData();
						p.setEscapedText(htmlParseData.getText());
					}

					if (urlParts.length > 1 && urlParts[1] != null) {
						if (urlParts[1].toLowerCase().contains("sold")) {
							p.setAvailable(false);
						}
					}
					return p;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	/* (non-Javadoc)
	 * @see manjeer.ecomm.utils.CustomContentParser#customContentParse(java.lang.Object[])
	 * 
	 * Not implemented. But this is the only place where w ecan write all parsing, Regex logic.
	 * 
	 * Don't need to worry about the other implementations of crawling while adding another 
	 */
	@Override
	public Product customContentParse(Object... strings) {
		// TODO Auto-generated method stub
		return null;
	}
}
