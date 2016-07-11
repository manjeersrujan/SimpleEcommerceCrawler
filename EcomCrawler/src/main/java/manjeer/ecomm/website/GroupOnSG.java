package manjeer.ecomm.website;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import manjeer.ecomm.utils.CustomContentParser;
import manjeer.ecomm.website.GroupOnSGContentParser;

/**
 * @author Manjeer on Jul 8, 2016
 *
 */
public class GroupOnSG implements EcommWebsite {

	public GroupOnSG() {
		super();
		this.baseUrl = "http://local.groupon.sg/";
		this.urlShouldContain = Arrays.asList(new String[] { "groupon", "sg" });
		this.urlShouldNotContain = Arrays.asList(new String[] { "login", "click", "payment" });
		this.name = "GroupOn";

	}

	String name;

	String baseUrl;

	List<String> urlShouldContain = new ArrayList<>();

	List<String> urlShouldNotContain = new ArrayList<>();

	CustomContentParser urlParser = new GroupOnSGContentParser();

	/*
	 * (non-Javadoc)
	 * 
	 * @see manjeer.ecomm.website.EcommWebsite#getBaseUrl()
	 */
	@Override
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * @param baseUrl
	 *            the baseUrl to set
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manjeer.ecomm.website.EcommWebsite#getUrlShouldContain()
	 */
	@Override
	public List<String> getUrlShouldContain() {
		return urlShouldContain;
	}

	/**
	 * @param urlShouldContain
	 *            the urlShouldContain to set
	 */
	public void setUrlShouldContain(List<String> urlShouldContain) {
		this.urlShouldContain = urlShouldContain;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manjeer.ecomm.website.EcommWebsite#getUrlShouldNotContain()
	 */
	@Override
	public List<String> getUrlShouldNotContain() {
		return urlShouldNotContain;
	}

	/**
	 * @param urlShouldNotContain
	 *            the urlShouldNotContain to set
	 */
	public void setUrlShouldNotContain(List<String> urlShouldNotContain) {
		this.urlShouldNotContain = urlShouldNotContain;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GroupOnSG [name=" + name + ", baseUrl=" + baseUrl + "]";
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
	 * @return the urlParser
	 */
	public CustomContentParser getUrlParser() {
		return urlParser;
	}

	/**
	 * @param urlParser
	 *            the urlParser to set
	 */
	public void setUrlParser(CustomContentParser urlParser) {
		this.urlParser = urlParser;
	}

}
