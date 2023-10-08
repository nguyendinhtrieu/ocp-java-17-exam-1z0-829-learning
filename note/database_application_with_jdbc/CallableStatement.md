Besides regular SQL queries, the JDBC API can be used to invoke stored procedures as well. For example, let us say you have the following stored procedure:
```
CREATE PROCEDURE my_storedprocname(NAME IN VARCHAR, DEPID IN INT, AMOUNT OUT INT) as 
BEGIN
...valid sql code here
END
```
It can be used as follows:

## 1. Create a CallableStatement
```
CallableStatement stmt = conn.prepareCall("{CALL my_storedprocname(?, ?, ?)}");
(OR if the procedure returns a value (in which case, it is called "stored function"):
CallableStatement stmt = conn.prepareCall("{? = CALL my_storedprocname(?, ?, ?)}");
```

## 2. Set parameter values
CallableStatement inherits from PreparedStatement, therefore, you can use various setXXX methods to set the input values
```
stmt.setString(1,"johndoe");
stmt.setObject(2, 20, java.sql.Types.INTEGER);//using the setObject method with the "type" argument
```

Note that indexing of parameters starts with 1. So, the index of the first question mark in the query is always 1. Thus, if your query starts with a ?=, the index of the first input parameter would be at 2 because the first question mark is for the return value, which is nothing but an OUT parameter.

Note that the third argument to PreparedStatement's setObject can be an int value (the ones defined in java.sql.Types class) or a java.sql.JDBCType enum value (such as JDBCType.VARCHAR) because setObject is overloaded to accept either one.

In addition to the above, a CallableStatement also allows you to set a parameter by name aka "parameter marker" like this:

```
stmt.setInt("NAME","johndoe"); //stmt.setInt("name","johnd");
//parameter marker is case insensitive 
stmt.setObject("DEPTID", 20, java.sql.Types.INTEGER);
```

- NOTE 1: Setting parameter values by name is not possible with PreparedStatement.
- NOTE 2: The order of setting the parameters is not important. You can set them in any order before executing the query. As long as your parameter index (or the parameter name) is correct, the value will be set for the correct input parameter. 
- NOTE 3: Although the JDBC specification explicitly mentions that the columns names of a ResultSet are case insensitive, it does not specify anything about parameters markers. For the purpose of the exam, assume that they are case insensitive as well. In the exam, you may see questions where the case of the parameter markers in the stored procedure is different from case of the parameter markers used in the Java code. Ignore this difference. 
- NOTE 4: You cannot mix the index and parameter markers in the same query. That is, you can either set parameters using the index or using the parameter markers but not both.
  
## 3. Register OUT (and INOUT) parameters
To use the out parameter (assuming that the third parameter is an OUT parameter in this stored procedure), you must first "register" it like this:
```
stmt.registerOutParameter(3, java.sql.Types.INTEGER);
// OR
stmt.registerOutParameter("AMOUNT", java.sql.Types.INTEGER);
```
## 4. Retrieve the result
The procedure can be executed and the result can be retrieved as follows:
```
boolean hasResults = stmt.execute(); //check if the stored proc has returned one or more ResultSets
while (hasResults) {
  ResultSet rs = stmt.getResultSet(); 
  // process result set 
  hasResults = stmt.getMoreResults();
}
int outvalue = stmt.getInt(3);//retrieve out value
```

If a stored procedure returns a result as well (besides returning zero or more ResultSets), you can retrieve the result by registering an OUT parameter for the return value. For example, let us say my_storedproc2 expects one input parameter, one output parameter, and returns a value. You can invoke it like this:
```
CallableStatement stmt = conn.prepareCall("{?= CALL my_storedproc2(?, ?)}");//observe ?= at the beginning 
stmt.registerOutParameter(1, JDBCType.VARCHAR); //registering an out parameter for the return value, indexing starts with 1 
stmt.setInt(2, 100); //setting the input parameter at index 2
stmt.registerOutParameter(3, JDBCType.VARCHAR); //registering the out parameter at index 3 
stmt.execute(); String resultvalue = stmt.getString(1);//getting the return value 
String outvalue = stmt.getString(3);//getting the out parameter
```