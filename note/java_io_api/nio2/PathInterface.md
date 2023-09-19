You should go through the JavaDoc API description of Paths.get method : https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/nio/file/Paths.html#get(java.net.URI) Here are the details for your convenience:
```
public static Path get(String first,  String... more
```
Converts a path string, or a sequence of strings that when joined form a path string, to a Path. If more does not specify any elements then the value of the first parameter is the path string to convert. If more specifies one or more elements then each non-empty string, including first, is considered to be a sequence of name elements (see Path) and is joined to form a path string. The details as to how the Strings are joined is provider specific but typically they will be joined using the name-separator as the separator. For example, if the name separator is "/" and getPath("/foo","bar","gus") is invoked, then the path string "/foo/bar/gus" is converted to a Path. A Path representing an empty path is returned if first is the empty string and more does not contain any non-empty strings.

The Path is obtained by invoking the getPath method of the default FileSystem.

Note that while this method is very convenient, using it will imply an assumed reference to the default FileSystem and limit the utility of the calling code. Hence it should not be used in library code intended for flexible reuse. A more flexible alternative is to use an existing Path instance as an anchor, such as:
```
Path dir = ...      Path path = dir.resolve("file"); 
```

-----------------------------------------------------------
Please go through the following description of Path.resolve() method as given in JavaDoc API:

public Path resolve(Path other)

Resolve the given path against this path.

If the other parameter is an absolute path then this method trivially returns other. If other is an empty path then this method trivially returns this path. Otherwise this method considers this path to be a directory and resolves the given path against this path. In the simplest case, the given path does not have a root component, in which case this method joins the given path to this path and returns a resulting path that ends with the given path. Where the given path has a root component then resolution is highly implementation dependent and therefore unspecified.

Parameters:
other - the path to resolve against this path

Returns:
the resulting path
