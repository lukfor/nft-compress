package lukfor.nf.test.compress;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class PathExtension {

	public static ZipFileBuilder getZip(Path self) throws FileNotFoundException, IOException {
		return new ZipFileBuilder(self);
	}

	public static ZipFileBuilder zip(Path self, Map<String, Object> options) throws FileNotFoundException, IOException {
		return new ZipFileBuilder(self, options);
	}

}
