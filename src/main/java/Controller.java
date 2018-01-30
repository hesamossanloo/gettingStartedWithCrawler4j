import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * Created by hesam.ossanloo on 24.01.2018.
 */
public class Controller {
	public static void main(String[] args) throws Exception {
		String crawlStorageFolder = "C:\\SWF_Data\\Crawled_Files\\crawlerExample";
		int numberOfCrawlers = 4;

		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);
		config.setIncludeHttpsPages(true);
		config.setMaxPagesToFetch(100);

        /*
         * Instantiate the controller for this crawl.
         */
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		robotstxtConfig.setEnabled(false);
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
//		controller.addSeed("https://alternativeto.net/");
//		controller.addSeed("https://sourceforge.net/");
		controller.addSeed("https://sourceforge.net/directory/");
//		controller.addSeed("https://sourceforge.net/directory/?page_4000");
//		controller.addSeed("https://projects.apache.org/projects.html");

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
		controller.start(MyCrawler.class, numberOfCrawlers);
	}
}
