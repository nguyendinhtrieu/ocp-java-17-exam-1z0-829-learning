To answer questions on Collections, just read and understand the following text thoroughly.

The core collection interfaces are the interfaces used to manipulate collections, and to pass them from one method to another. The basic purpose of these interfaces is to allow collections to be manipulated independently of the details of their representation.

When you understand how to use these interfaces, you know most of what there is to know about the framework. The core collections interfaces are shown below: 

```
java.util.Collection
|
+--Set           ,         List       
   |                        |       
   +--SortedSet             +  ArrayList, LinkedList, Vector


java.util.Map
|
+--SortedMap , (Hashtable) 
```

The core collection interfaces form a hierarchy: a Set is a special kind of Collection, and a SortedSet is a special kind of Set, and so forth. Note also that the hierarchy consists of two distinct trees: a Map is not a true Collection.

To keep the number of core collection interfaces manageable, the JDK doesn't provide separate interfaces for each variant of each collection type. (Among the possible variants are immutable, fixed-size, and append-only.) Instead, the modification operations in each interface are designated optional: a given implementation may not support some of these operations. If an unsupported operation is invoked, a collection throws an UnsupportedOperationException . Implementations are responsible for documenting which of the optional operations they support. All of the JDK's general purpose implementations support all of the optional operations.

The four sections that follow teach you how to use each of the four basic core collection interfaces. In particular, they describe the idioms that allow you to use these interfaces effectively.

#### Collection:
The Collection interface is the root of the collection hierarchy. A Collection represents a group of objects, known as its elements. Some Collection implementations allow duplicate elements and others do not. Some are ordered and others unordered. The JDK doesn't provide any direct implementations of this interface: It provides implementations of more specific subinterfaces like Set and List. This interface is the least common denominator that all collections implement. Collection is used to pass collections around and manipulate them when maximum generality is desired.

#### Set:
A Set is an UNORDERED collection that CANNOT contain duplicate elements. As you might expect, this interface models the mathematical set abstraction. It is used to represent sets like the cards comprising a poker hand, the courses making up a student's schedule, or the processes running on a machine. HashSet is a commonly used Set implementation.

#### List:
A List is an ORDERED collection (sometimes called a sequence). Lists CAN contain duplicate elements. The user of a List generally has precise control over where in the List each element is inserted. The user can access elements by their integer index (position). ArrayList is the most commonly used List implementation.

#### Map:
A Map is an object that maps keys to values. Maps cannot contain duplicate keys: Each key can map to at most one value. HashMap is the most commonly used Map implementation.

The last two core collection interfaces (SortedSet and SortedMap) are merely sorted versions of Set and Map. In order to understand these interfaces, you have to know how order is maintained among objects.

Object Ordering</br>
There are two ways to order objects: The Comparable interface provides automatic natural order on classes that implement it, while the Comparator interface gives the programmer complete control over object ordering. Note that these are not core collection interfaces, but underlying infrastructure.

#### SortedSet:
A SortedSet is a Set that maintains its elements in ascending order. Several additional operations are provided to take advantage of the ordering. The SortedSet interface is used for things like word lists and membership rolls. TreeSet is a commonly used SortedSet implementation.

#### SortedMap:
A SortedMap is a Map that maintains its mappings in ascending key order. It is the Map analogue of SortedSet. The SortedMap interface is used for apps like dictionaries and telephone directories. TreeMap is a commonly used SortedMap implementation. 

--------------------------------------------------------------
A NavigableMap is a SortedMap (which in turn extends Map) extended with navigation methods returning the closest matches for given search targets. Methods lowerEntry, floorEntry, ceilingEntry, and higherEntry return Map. Entry objects associated with keys respectively less than, less than or equal, greater than or equal, and greater than a given key, returning null if there is no such key. Similarly, methods lowerKey, floorKey, ceilingKey, and higherKey return only the associated keys.

All of these methods are designed for locating, not traversing, entries.

A NavigableMap may be accessed and traversed in either ascending or descending key order. The descendingMap method returns a view of the map with the senses of all relational and directional methods inverted. The performance of ascending operations and views is likely to be faster than that of descending ones. Methods subMap, headMap, and tailMap differ from the like-named SortedMap methods in accepting additional arguments describing whether lower and upper bounds are inclusive versus exclusive. Submaps of any NavigableMap must implement the NavigableMap interface.

This interface additionally defines methods firstEntry, pollFirstEntry, lastEntry, and pollLastEntry that return and/or remove the least and greatest mappings, if any exist, else returning null.

Implementations of entry-returning methods are expected to return Map.Entry pairs representing snapshots of mappings at the time they were produced, and thus generally do not support the optional Entry.setValue method. Note however that it is possible to change mappings in the associated map using method put.

Methods subMap(K, K), headMap(K), and tailMap(K) are specified to return SortedMap to allow existing implementations of SortedMap to be compatibly retrofitted to implement NavigableMap, but extensions and implementations of this interface are encouraged to override these methods to return NavigableMap. Similarly, SortedMap.keySet() can be overridden to return NavigableSet.

-------------------------------------------------------------------
Deque is an important interface for the exam. To answer the questions, you must remember that a Deque can act as a Queue as well as a Stack. Based on this fact, you can deduce the following:

1. Since Queue is a FIFO structure (First In First Out i.e. add to the end and remove from the front), it has methods offer(e)/add(e)(for adding an element to the end or tail) and poll()/remove()(for removing an element from the front or head) for this purpose.  Note that offer and add are similar while poll and remove are similar.
2. Since Stack is a LIFO structure (Last In First Out i.e. add to the front and remove from the front), it provides methods push(e) and pop() for this purpose, where push adds to the front and pop removes from the front.<br>

Besides the above methods, Deque also has variations of the above methods. But it is easy to figure out what they do:

pollFirst()/pollLast() - poll is a Queue method. Therefore pollFirst and pollLast will remove elements from the front and from the end respectively. removeFirst()/removeLast() - These are Deque specific methods. They will remove elements from the front and from the end respectively. These methods differ from pollFirst/pollLast only in that they throw an exception if this deque is empty.

offerFirst(e)/offerLast(e) - offer is a Queue method. Therefore offerFirst and offerLast will add elements to the front and to the end respectively.<br>
addFirst(e)/addLast(e) - add is a Queue method. Therefore addFirst and addLast will add elements to the front and to the end respectively.

peek(), peekFirst(): return the first element from the front of the queue but does not remove it from the queue.<br>
peekLast() : returns the last element from the end of the queue but does not remove it from the queue.<br>
element(): retrieves, but does not remove, the head of the queue represented by this deque (in other words, the first element of this deque). This method differs from peek only in that it throws an exception if this deque is empty.

Notice that there is no pushFirst(e) and pushLast(e).

You may wonder why there are multiple methods for the same thing such as offer(e) and add(e). Well, they are not exactly same. add(e) throws an exception if the element cannot be added to the queue because of lack of capacity, while offer(e) does not. There are similar differences in other methods but they are not too important for the exam.
