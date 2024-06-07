package lukfor.nf.test.compress;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public class PathExtension {

	public static ZipFileBuilder getZip(Path self) throws FileNotFoundException, IOException {
		return new ZipFileBuilder(self);
	}

}
