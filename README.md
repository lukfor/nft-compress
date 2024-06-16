# nft-compress

nf-test plugin to provide support for ZIP files.

## Requirements

- nf-test version 0.7.0 or higher

## Setup

To use this plugin you need to activate the `nft-compress` plugin in your `nf-test.config` file:

```
config {
  plugins {
    load "nft-compress@0.1.0"
  }
}
```

## Usage

nf-test extends `path` by a `zip` property that can be used to extract zip files and to get access to the entries as `path` objects.


### Examples

The `zip` property offers various features to validate zip files and access their entries:

```Groovy
def filename = process.out.zip.get(0)
assert path(filename).zip.isValid()
assert !path(filename).zip.isEncrypted()
assert path(filename).zip.getNumberOfFiles() == 3
assert path(filename).zip.extract("file_1.txt").text == "Lukas 1"
assert path(filename).zip.extract("file_2.txt").text == "Lukas 2"
assert path(filename).zip.extract("file_3.txt").text == "Lukas 3"
```

Instead of using the `zip` property of a `path` object, you could also use the `zip` function:

```Groovy
assert zip(filename).isValid()
assert !zip(filename).isEncrypted()
assert zip(filename).getNumberOfFiles() == 3
assert zip(filename).extract("file_1.txt").text == "Lukas 1"
assert zip(filename).extract("file_2.txt").text == "Lukas 2"
assert zip(filename).extract("file_3.txt").text == "Lukas 3"
```

Extracting all files in a zip file:

```
def paths = zip(filename).extractAll()
assert paths.size() == 3
```

Working with encrypted zip files:

```Groovy
assert zip(filename, password: "my-password").extract("file_1.txt").text == "Lukas 1"
//or 
assert zip(filename).password("my-password").extract("file_1.txt").text == "Lukas 1"
assert !zip(filename).isEncrypted()
```

Handling zip files that contain subfolders:

```Groovy
assert path(filename).zip.extract("subfolder/file_1.txt").text == "Lukas 1"
assert path(filename).zip.extract("subfolder/file_2.txt").text == "Lukas 2"
assert path(filename).zip.extract("subfolder/file_3.txt").text == "Lukas 3"
```

## Contact

Lukas Forer (@lukfor)