import org.apache.commons.lang.time.StopWatch;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by hesam.ossanloo on 24.01.2018.
 */
public class TestTheCrawler {

	@Test
	public void writeToFileTest(){
		StopWatch time = new StopWatch();
		time.start();
		Path path = Paths.get("C:/SWF_Data/Crawled_Files");
		File dir = new File(path.toUri());
		File actualFile = new File(dir, "test1.html");

		byte[] strToBytes = "<html><body>Test1</body></html>".getBytes();

		try {
			Files.write(actualFile.toPath(), strToBytes);
		} catch (IOException e) {
		}

		time.stop();
	}
}
