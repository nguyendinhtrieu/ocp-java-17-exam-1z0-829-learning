You should know the following concepts for answering this type of questions:<br>
**Steps to check for valid override**

**First**, check the method signature (i.e. method name and the parameter list). If the signature of the method in the subclass matches the signature of the method in the super class, then it could be a valid override, otherwise it is just an overloaded method. Note that signature does not include parameter names and parameter's generic type specification.

**Second**, if it is a potential override, check the generic type specification of the parameters. If the overriding method does not use a generic type specification for the parameter type, then it is valid. The reverse is not valid i.e. the overriding method is allowed to erase the generic type specification but is not allowed to add a generic type specification if the overridden method does not have it. If both the methods have a generic type specification, then the specification must match exactly. For example, if the overridden method has **Set<Integer>**, then the overriding method can use **Set** or **Set<Integer>**. But if overridden method has **Set**, then the overriding method must also have **Set** for a valid override.

**Third**, if it is a potential override, check the return type. Java allows "covariant" returns, which means, the return type of the overriding method must be the same or be a subtype of the return type mentioned in the overridden method. Check the two return types without the generic type specification. If return type of the overriding method is covariant with respect to the return type of the overriding method (for example, **ArrayList** is covariant with **List**), then perform the same check including the generic type specification (for example, **ArrayList<CharSequence>** is covariant with **List<? extends CharSequence>)**.

Don't get confused by the presence of **<T>** in the code. The same rules of overriding still apply. The T in **<T>** is called as the "type" parameter. It is used as a place holder for whatever type is actually used while invoking the method. For example, if you call the method **<T> List<T> transform(List<T> list)** with **List<String>**, T will be typed to **String**. Thus, it will return **List<String>**. If, in another place, you call the same method with **Integer**, T will be typed to **Integer** and therefore, the return type of the method for that invocation will be **List<Integer>**

**Type erasure of generic method parameters** <br>
Remember that unlike arrays, generic collections are not reified, which means that all generic information is removed from the compiled class. Thus, Set<CharSequence> and Set<String> are converted to just Set by the compiler while generating the class file. This implies that two methods whose parameter types differ only on the type specification are not really different methods. <br>
For example,<br>
**void m(Set<CharSequence> cs), void m(Set<String> s)**, and **void m(Set<SomeOtherClass> o)** are not different method signatures at all. If you remove the type specification, they all resolve to the same signature i.e. **void m(Set x)**.

Hence, if you put them in the same class, the resulting class file will have two methods with the exact same signature. This is obviously a problem and so, the compiler rejects the code. If you put one of them in a superclass and another in a subclass, then from the compiler's perspective they constitute valid overloading, however, from the JVM's perspective it is an override and the JVM will not respect the compile time method binding done by the compiler based on the generic type specification. That is why Java does not allow this either.

The exception to this rule is that the overriding method is allowed to erase the generic type specification. For example, if the overridden method has **Set<Integer>**, then the overriding method can use **Set** or **Set<Integer>**. But if overridden method has **Set**, then the overriding method must also have **Set** for a valid override.

**Rule of Covariant Returns**<br>
An overriding method (i.e. a sub class's method) is allowed to return a sub-type of the type returned by the overridden method (i.e. super class's method).

So, first check whether the return type of the overriding method is a subtype. For example, if the overridden method returns List, the overriding method can return ArrayList but not Object.

Next, you need to check the type specification of generic types. This is a bit complicated. To determine this, you must remember the following hierarchy of subtypes. Assuming that S is a sub type of T and <<< means "is a subtype of", here are the two hierarchies:

`Hierarchy 1 : A<S> <<< A<? extends S> <<< A<? extends T>`<br>
Example: Since `Integer` is a subtype of `Number`, `List<Integer>` is a subtype of `List<? extends Integer>` and `List<? extends Integer>` is a subtype of `List<? extends Number>`. Thus, if an overridden method returns `List<? extends Integer>`, the overriding method can return `List<Integer>` but not `List<Number>` or `List<? extends Number>`.

`Hierarchy 2 : A<T> <<< A<? super T> <<< A<? super S>`
Example: `List<Number>` is a subtype of `List<? super Number>` and `List<? super Number>` is a subtype of `List<? super Integer>` Thus, if an overridden method returns `List<? super Number>`, the overriding method can return `List<Number>` but not `List<Integer>` or `List<? super Integer>`.

It is important to understand that `List<Integer>` is not a subtype of `List<Number>` even though `Integer` is a subtype of `Number`.
