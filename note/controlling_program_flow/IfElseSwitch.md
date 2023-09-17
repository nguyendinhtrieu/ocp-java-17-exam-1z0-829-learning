### Common rules for switch statements as well as switch expressions
1. Only String, byte, char, short, int, (and their wrapper classes Byte, Character, Short, and Integer), and enums can be used as types of a switch selector expression (aka switch condition/variable). 
2. The case constants must be assignable to the switch variable. For example, if your switch variable is of class String, your case labels must use Strings as well. 
3. The switch variable must be big enough to hold all the case constants. For example, if the switch variable is of type char, then none of the case constants can be greater than 65535 because a char's range is from 0 to 65535. For example, the following will not compile because 200 cannot be assigned to the variable b, which can only hold values from -128 to 127.
```
byte b = 10;
switch(b){
    case 100 : System.out.print(100); //OK
    case 200 :  System.out.print(200); //will not compile
} 
```
4. All case labels should be COMPILE TIME CONSTANTS. This means, you can have literals such as 'a', 5, 10, or a final variable, the value of which is computed at compile time such as int I = 2*3; For example, the following is valid:
```
int x = 10;
final int I = 2*3;
switch(x){
    case I : System.out.println(x); //this is valid because I is a compile time constant
}
```
5. No two of the case constant expressions associated with a switch statement/expression may have the same value. 
6. The default label is always optional for switch statement and required for a switch expression only when the case labels are not exhaustive (meaning, they do not cover all the possible values of the selector expression). 
7. At most one default label may be associated with the same switch statement/expression.

-------------------------------------------------------------------------------------------
### Important points on switch expressions
Remember the following points about switch expressions, which were added in Java 14.
The most important thing to understand is that a switch expression is an expression, which means, it has a value of its own and it must appear on the right hand side of an assignment. The old switch statement is just a statement, it doesn't have a value of its own.
1. The most important thing to understand is that a **switch expression** is an **expression**, which means, it has a value of its own and it must appear on the right hand side of an assignment. The old switch statement is just a statement, it doesn't have a value of its own.
2. **Syntax:** A switch expression can be written using the old syntax (which is written using a colon) as well using the new syntax (which is written using an arrow).
   1. **switch expression using old syntax:** The old style uses a colon and an "expression statement" (an expression statement is an expression that can be used as a statement as well, e.g. assignment, pre/post increment, method invocation, and instance creation). The following example shows various ways in which a value may be returned from a switch rule:<br>
   //old sytax - uses colon(:) and "expression statement" in the case rules
   
       ```   
       int j = switch(someIntVar) { //here, someIntVar is the "selector expression"
          case 0: { yield 0; }  //a block containing a yield statement;
             case 1: aMethodThatReturnsAnInt(); //it is a valid expression statement
             //case 1:  2+3; //this line is NOT valid because 2+3 is not a valid expression statement
          default: yield someIntVar; //using yield as a expression statement directly
       }; //semi-colon is required here
       ```
   2. **switch expression using new syntax:** The new style uses an arrow and an expression. It does not require the yield keyword. For example:
      ```
      //new sytax - uses arrow(->)
      int j = switch(someIntVar) {
         case 0 -> { yield 0; }  //a block containing a yield statement;
            case 1 -> methodThatReturnsAnInt();
            case 2 -> 2+3; //this line is valid because any expression can be used in the new syntax
         default -> someIntVar; //no yield here
      };//semi-colon is required here
      ```
   3. The old and new syntax cannot be mixed in the same switch statement.
3. **return type:** The return type of a switch expression is determined by the types of the values returned by its case expressions (or case blocks). Basically, the most specific super type of the return values is used.
   ```
   Integer x = 1;
   Object o = switch(x){
      case 1,2 -> "String";
      default -> 200;
   }; //semi-colon is required when assigning a switch expression to a variable        
   ```
Here, the types of the values returned are String and Integer. The most specific super type of these two is Object and so, the return type of the switch is Object.
4. A switch expression must return a value for all possible values of the switch variable i.e. it must be "exhaustive".
   ```
   Integer x = 1;
   Object o = switch(x){
      case 1,2 -> "String";
      default -> 200; //compilation error if this line is removed.
   };        
   ```
   The above will not compile without the default statement because the compiler figures out that no value will be returned when x is not 1 or 2.
5. **yield statement:** Unlike a case expression, a case block does not have a return value of its own. So, if you want to return a value from a case block, you must use the yield statement.
   ```
   Integer x = 1;
   Object o = switch(x){
      case 1,2 -> {
         System.out.println("in case block");
         yield "String"; //returning value using yield
      }
      default -> 200; //no yield here
   };
   ```

**Important points on the new switch syntax**
1. While the new syntax is useful for writing switch expressions, it can be used to write switch statements as well. For example:
```
Integer x = 2;
//the following is switch statement (not a switch expression) using new arrow syntax
switch(x){
   case 1,2 -> System.out.println("A"); //uses the new arrow syntax
   default -> System.out.println("C");//uses the new arrow syntax
} //no semi-colon required here 
```
2. **case statements and case blocks:** While using the arrow sytax to write a switch statement or a switch expression, each case label may be assiciated with a case statement or a case block.
```
Integer x = 2;
switch(x){
   case 1,2 -> { //case block start
      System.out.println("inside case block");
      System.out.println("A");
   } //case block end
   case 3,4 -> System.out.println("B"); //case statement
   default -> System.out.println("C"); //case statement
}
```
As you can see, a case block is a block of statements within { and }, while a case statement is a single statement written without enclosing { and }. Unlike the old switch statement, you can't associate multiple statements with a case statement outside of a case block.
3. **No "fall through":** The control does not fall through to the next case statement. i.e. The break statement is not required to terminate the switch expression evaluation.
4. **Usage of break:** It is possible but not necessary to use the break statement in a switch expression. For example:
```
Integer x = 2;
switch(x){
   case 1,2 -> { //case block
      System.out.println("A");
      break;//valid but redundant
   }
   case 3,4 -> System.out.println("B"); //will not be printed even if the break statement 
     //in the previous block is commented out
     //break; //error. break is not allowed outside a case block
     
   default -> System.out.println("C");
}
```
