import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by hesam.ossanloo on 24.01.2018.
 */
public class MyCrawler extends WebCrawler {

	//	private final static Pattern FILTERS = Pattern.compile("https://alternativeto\\.net((/software/[^/]+/)|(/(\\?page=[0-9]+)?(\\?|\\&)sort=likes))?");
	private final static Pattern FILTERS = Pattern.compile("https://sourceforge\\.net/(projects/[^/]+/\\?source=directory(-featured)?)|(directory\\/\\?page=[0-9]+)");
//	private final static Pattern FILTERS = Pattern.compile("https://projects\\.apache\\.org/projects*\\.html");
//	private final static Pattern FILTERS = Pattern.compile("https://projects\\.apache\\.org/project.html");
//	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
//			+ "|png|mp3|mp4|zip|gz))$");

	/**
	 * This method receives two parameters. The first parameter is the page
	 * in which we have discovered this new url and the second parameter is
	 * the new url. You should implement this function to specify whether
	 * the given url should be crawled or not (based on your crawling logic).
	 * In this example, we are instructing the crawler to ignore urls that
	 * have css, js, git, ... extensions and to only accept urls that start
	 * with "http://www.ics.uci.edu/". In this case, we didn't need the
	 * referringPage parameter to make the decision.
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		return FILTERS.matcher(href).matches();
	}

	/**
	 * This function is called when a page is fetched and ready
	 * to be processed by your program.
	 */
	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		System.out.println("URL: " + url);

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			Set<WebURL> links = htmlParseData.getOutgoingUrls();

			System.out.println("Text length: " + text.length());
			System.out.println("Html length: " + html.length());
			System.out.println("Number of outgoing links: " + links.size());
			if (shouldIcrawlPage(url)) {
				String replacedURL = url;
				replacedURL = replacedURL.replace("?", "");
				replacedURL = replacedURL.replace(":", "");
				replacedURL = replacedURL.replace("|", "");
				replacedURL = replacedURL.replace("<", "");
				replacedURL = replacedURL.replace(">", "");
				replacedURL = replacedURL.replace("*", "");
				replacedURL = replacedURL.replace("\"", "");
				replacedURL = replacedURL.replace("\\", "");
				replacedURL = replacedURL.replace("/", "");
				replacedURL = replacedURL.replace(".", "");
				replacedURL = replacedURL.replace("=", "");
				this.addToFolder(replacedURL + ".html", html);
			}
		}
	}

	private boolean shouldIcrawlPage(String href) {
		//AlternativeTo
//		boolean ret = href.startsWith("https://alternativeto.net/software");
//		Sourceforge
		boolean ret = href.endsWith("/?source=directory-featured") || href.endsWith("/?source=directory");
		//Apache
//		boolean ret = href.startsWith("https://projects.apache.org/project.html");
//		||href.contains("project.html?");
		return ret;
	}

	protected void addToFolder(String replacedURL, String html) {
		FileWriter.addToFileFolder(replacedURL, html);
	}
}