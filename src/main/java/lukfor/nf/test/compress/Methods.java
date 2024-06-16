package lukfor.nf.test.compress;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Methods {

	public static ZipFileBuilder zip(Path path) throws IOException {
		return new ZipFileBuilder(path);
	}

	public static ZipFileBuilder zip(Map<String, Object> options, Path path) throws IOException {
		return new ZipFileBuilder(path, options);
	}

	public static ZipFileBuilder zip(String path) throws IOException {
		return new ZipFileBuilder(Path.of(path));
	}

	public static ZipFileBuilder zip(Map<String, Object> options, String path) throws IOException {
		return new ZipFileBuilder(Path.of(path), options);
	}

}
