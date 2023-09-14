Note that an array of integers IS an Object :
```
Object obj = new int[]{ 1, 2, 3 }; // is valid. But it is not an array of objects.
Object[] o = new int[10]; // is not valid.
```
Difference between the placement of square brackets: 
```
int[] i, j; //here i and j are both array of integers. 
int i[], j; //here only i is an array of integers. j is just an integer.
```
