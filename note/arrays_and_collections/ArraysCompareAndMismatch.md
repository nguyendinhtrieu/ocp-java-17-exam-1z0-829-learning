Java 9 has added a several new methods to java.lang.Arrays class, of which, compare and mismatch methods are covered by the OCP Java 11 exam.

These static methods are meant to compare two arrays of the same types. Although they can compare arrays of primitives as well as reference types, they are most useful for comparing primitive arrays. Efficient comparison of primitive arrays is a common requirement in data processing. Since these methods compare data in chunks, they are able to perform substantially better than custom code that compares elements individually. That is probably why the OCP Java 11 exam has questions on these methods.

If you browse through the API JavaDoc, you will notice a large number of variants for these methods.  These methods are heavily overloaded but practically, there are only two flavors that you need to understand for the purpose of the exam. The rest are mere replicas; one for each data type

Before getting into the details of the methods, you need to understand the meaning of the two words that are used often in the description for these methods.

1. **Lexicographical** - This simply means "dictionary order" i.e. the way in which words are ordered in a dictionary. If you were to determine which one of the two words would come before the other in a dictionary, you would compare the words letter by letter starting from the first position. For example, the word "children" will appear before (and can be considered smaller) than the word "chill" because the first four letters of the two words are the same but the letter at the fifth position in "children" (i.e. d) comes before (or is smaller than) the letter at the fifth position in "chill" (i.e. l). Observe that lengthwise, the word "children" is bigger than "chill" but length is not the criteria here. For the same reason, an array containing 12345 will appear before an array containing 1235. 
2. **Prefix** - This refers to the common part of the two arrays. In the above example, "chil" can be called as the prefix while comparing "children" and "chill". Similarly, 123 is the prefix while comparing 12345 and 1235.

Let's check out the compare and mismatch methods now: 

1. `public static int compare(int[] a, int[] b)` - Compares two int arrays lexicographically.
- If the two arrays share a common prefix then the lexicographic comparison is the result of comparing two elements, as if by Integer.compare(int, int), at an index within the respective arrays that is the prefix length. Otherwise, one array is a proper prefix of the other and, lexicographic comparison is the result of comparing the two array lengths. (See mismatch(int[], int[]) for the definition of a common and proper prefix.)
- A null array reference is considered lexicographically less than a non-null array reference. Two null array references are considered equal.
- It returns 0 if the first and second array are equal and contain the same elements in the same order; a value less than 0 if the first array is lexicographically less than the second array; and a value greater than 0 if the first array is lexicographically greater than the second array.
- For example, the following code will print -1 because the first array has smaller integer at index 2. 
```
int[] ia1 = { 0, 1, 2, 6};
int[] ia2 = { 0, 1, 5};
System.out.print(Arrays.compare(ia1, ia2)); //prints -1
```
- Observe that the returned value (-1) does not depend on the amount of different between the two elements (2 - 5 is -3). 
2. `public static int mismatch(int[] a, int[] b)` - Finds and returns the index of the first mismatch between two int arrays, otherwise return -1 if no mismatch is found. The index will be in the range of 0 (inclusive) up to the length (inclusive) of the smaller array.
- If the two arrays share a common prefix then the returned index is the length of the common prefix and it follows that there is a mismatch between the two elements at that index within the respective arrays. If one array is a proper prefix of the other then the returned index is the length of the smaller array and it follows that the index is only valid for the larger array. Otherwise, there is no mismatch.
- Two non-null arrays, a and b, share a common prefix of length pl if the following expression is true:
```
     pl >= 0 &&
     pl < Math.min(a.length, b.length) &&
     Arrays.equals(a, 0, pl, b, 0, pl) &&
     a[pl] != b[pl]
```
- Note that a common prefix length of 0 indicates that the first elements from each array mismatch. Two non-null arrays, a and b, share a proper prefix if the following expression is true:
```
     a.length != b.length &&
     Arrays.equals(a, 0, Math.min(a.length, b.length),
                   b, 0, Math.min(a.length, b.length)) 
```
- For example, the following code will print 2 because the arrays differ at index 2. 
```
int[] ia1 = { 0, 1, 2, 6};
int[] ia2 = { 0, 1, 5};
System.out.println(Arrays.mismatch(ia1, ia2)); //prints 2
```
- Observe that 2 is also the length of the prefix, which is {0, 1}.
- You should now go through the JavaDoc API description of the various overloaded compare and mismatch methods. You will notice that they all work in the same basic fashion.
