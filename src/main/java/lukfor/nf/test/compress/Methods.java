package lukfor.nf.test.compress;

import java.io.IOException;
import java.nio.file.Path;

public class Methods {

	public static ZipFileBuilder zip(Path path) throws IOException {
		return new ZipFileBuilder(path);
	}

	public static ZipFileBuilder zip(String path) throws IOException {
		return new ZipFileBuilder(Path.of(path));
	}
}
