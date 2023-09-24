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

-------------------------------------------------------------
Path getRoot()<br>
Returns the root component of this path as a Path object, or null if this path does not have a root component.

Path getName(int index)<br>
Returns a name element of this path as a Path object.<br>
The index parameter is the index of the name element to return. The element that is closest to the root in the directory hierarchy has index 0. The element that is farthest from the root has index count-1.

Parameters:<br>
index - the index of the element

The following 4 points about Path.getName() method are good to know as well:

1. Indices for path names start from 0. 
2. Root (i.e. c:\) is not included in path names. 
3. \ is NOT a part of a path name. 
4. If you pass a negative index or a value greater than or equal to the number of elements, or this path has zero name elements, java.lang.IllegalArgumentException is thrown. It DOES NOT return null.

Thus, for example, If your Path is "c:\\code\\java\\PathTest.java", 

```
p1.getRoot()  is c:\  ((For Unix based environments, the root is usually / ). 
p1.getName(0)  is code 
p1.getName(1)  is java 
p1.getName(2)  is PathTest.java
p1.getName(3)  will cause IllegalArgumentException to be thrown.
```
