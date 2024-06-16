package lukfor.nf.test.compress;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import net.lingala.zip4j.*;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

public class ZipFileBuilder {

    private Path path;

    private String password = null;

    private String output = "temp";

    public ZipFileBuilder(Path path) throws IOException {
        this(path, new HashMap<String, Object>());
    }


    public ZipFileBuilder(Path path, Map<String, Object> options) throws IOException {
        this.path = path;
        File tempDirectory = Files.createTempDirectory("nft-compress").toFile();
        tempDirectory.deleteOnExit();
        tempDirectory.mkdirs();
        output = tempDirectory.getAbsolutePath();
        if (options.containsKey("password")) {
            this.password = (String) options.get("password");
        }
    }

    public ZipFileBuilder password(String password) {
        this.password = password;
        return this;
    }

    public boolean isValid() throws IOException {
        ZipFile zipFile = createZipFile();
        boolean valid = zipFile.isValidZipFile();
        zipFile.close();
        return valid;
    }

    public boolean isEmpty() throws IOException {
        return (path.toFile().length() == 0);
    }

    public boolean exists() throws IOException {
        return (path.toFile().exists());
    }

    public boolean isEncrypted() throws IOException {
        ZipFile zipFile = createZipFile();
        boolean encrypted = zipFile.isEncrypted();
        zipFile.close();
        return encrypted;
    }

    public int getNumberOfFiles() throws IOException {
        ZipFile zipFile = createZipFile();
        int files = 0;
        for (FileHeader header: zipFile.getFileHeaders()) {
            if (!header.isDirectory()) {
                files++;
            }
        }
        zipFile.close();
        return files;
    }

    public List<Path> extractAll() throws IOException {
        List<Path> results = new Vector<Path>();
        ZipFile zipFile = createZipFile();
        zipFile.extractAll(output);
        for (FileHeader header: zipFile.getFileHeaders()) {
            if (!header.isDirectory()) {
                results.add(Path.of(output, header.getFileName()));
            }
        }
        zipFile.close();
        return results;
    }

    public Path extract(String entry) throws IOException {
        ZipFile zipFile = createZipFile();
        zipFile.extractFile(entry, output);
        zipFile.close();
        return Path.of(output, entry);
    }

    private ZipFile createZipFile() {
        if (password == null){
            return new ZipFile(path.toFile());
        } else {
            return new ZipFile(path.toFile(), password.toCharArray());
        }
    }

}
