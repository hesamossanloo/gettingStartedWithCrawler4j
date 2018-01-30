import org.apache.commons.lang.time.StopWatch;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class FileWriter {
	private static final Logger LOG = Logger.getLogger(FileWriter.class.getSimpleName());

	public static synchronized void addToFileFolder(String fileName, String html) {
		Path path = Paths.get("C:/SWF_Data/Crawled_Files/crawlerExample");
		addToFolder(fileName, html, path);
	}
	private static void addToFolder(String fileName, String html, Path path) {
		StopWatch time = new StopWatch();
		time.start();

		File dir = new File(path.toUri());
		File actualFile = new File(dir, fileName);

		byte[] strToBytes = html.getBytes();

		try {
			Files.write(actualFile.toPath(), strToBytes);
		} catch (IOException e) {
			LOG.severe(e.getMessage());
		}

		time.stop();
		LOG.info("Time needed to write file " + fileName + "into Folder: " + time.toString());
	}
}
