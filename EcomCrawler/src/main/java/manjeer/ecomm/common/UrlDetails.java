package manjeer.ecomm.common;

import edu.uci.ics.crawler4j.crawler.Page;
import manjeer.ecomm.engine.Product;
import manjeer.ecomm.website.EcommWebsite;

/**
 * @author Manjeer on Jul 8, 2016
 *
 */
public class UrlDetails {
	String url;
	EcommWebsite ecommWebsite;
	Page page;

	public UrlDetails(String url, EcommWebsite ecommWebsite, Page page) {
		super();
		this.url = url;
		this.ecommWebsite = ecommWebsite;
		this.page = page;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the ecommWebsite
	 */
	public EcommWebsite getEcommWebsite() {
		return ecommWebsite;
	}

	/**
	 * @param ecommWebsite
	 *            the ecommWebsite to set
	 */
	public void setEcommWebsite(EcommWebsite ecommWebsite) {
		this.ecommWebsite = ecommWebsite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UrlDetails [url=" + url + ", ecommWebsite=" + ecommWebsite + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UrlDetails other = (UrlDetails) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * @return
	 */
	public Product getProduct() {
		Product product = this.ecommWebsite.getUrlParser().customContentParse(this);
		if (product == null) {
			return this.ecommWebsite.getUrlParser().customURLParse(this);
		}

		return product;
	}
}
