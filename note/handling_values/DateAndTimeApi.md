Java 8 introduced a new package java.time to deal with dates. The old classes such as java.util.Date are not recommended anymore.

#### Briefly:
java.time Package: This is the base package of new Java Date Time API. All the commonly used classes such as LocalDate, LocalTime, LocalDateTime, Instant, Period, Duration are part of this package. All of these classes are immutable and thread safe.

`java.time.format` Package: This package contains classes used for formatting and parsing date time objects such as java.time.format.DateTimeFormatter.

(The following two are not important for the exam.)

`java.time.zone` Package: This package contains classes for supporting different time zones and their rules.

`java.time.chrono` Package: This package defines generic APIs for non ISO calendar systems. We can extend AbstractChronology class to create our own calendar system.

`java.time.temporal` Package: This package contains temporal objects and we can use it for find out specific date or time related to date/time object. For example, we can use these to find out the first or last day of the month. You can identify these methods easily because they always have format “withXXX”.

-----------------------------------------------------------------------------

Here are some points that you should keep in mind about the new Date/Time classes introduced in Java 8 -

1. They are in package java.time and they have no relation at all to the old java.util.Date and java.sql.Date.  
2. java.time.temporal.TemporalAccessor is the base interface that is implemented by LocalDate, LocalTime, and LocalDateTime concrete classes. This interface defines read-only access to temporal objects, such as a date, time, offset or some combination of these, which are represented by the interface TemporalField. 
3. LocalDate, LocalTime, and LocalDateTime classes do not have any parent/child relationship among themselves. As their names imply, LocalDate contains just the date information and no time information, LocalTime contains only time and no date, while LocalDateTime contains date as well as time. None of them contains zone information. For that, you can use ZonedDateTime.  

These classes are immutable and have no public constructors. You create objects of these classes using their static factory methods such as of(...) and from(TemporalAccessor ).  For example, LocalDate ld = LocalDate.of(2015, Month.JANUARY, 1); or LocalDate ld = LocalDate.from(anotherDate); or LocalDateTime ldt = LocalDateTime.of(2015, Month.JANUARY, 1, 21, 10); //9.10 PM  

Since you can't modify them once created, if you want to create new object with some changes to the original, you can use the instance method named with(...). For example, LocalDate sunday = ld.with(java.time.temporal.TemporalAdjusters.next(DayOfWeek.SUNDAY));  
4. Formatting of date objects into String and parsing of Strings into date objects is done by java.time.format.DateTimeFormatter class. This class provides public static references to readymade DateTimeFormatter objects through the fields named ISO_DATE, ISO_LOCAL_DATE, ISO_LOCAL_DATE_TIME, etc.  For example -
```
LocalDate d1 = LocalDate.parse("2015-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
```
The parameter type and return type of the methods of DateTimeFormatter class is the base interface TemporalAccessor instead of concrete classes such as LocalDate or LocalDateTime. So you shouldn't directly cast the returned values to concrete classes like this -
```
LocalDate d2
        = (LocalDate) DateTimeFormatter.ISO_LOCAL_DATE.parse("2015-01-01"); //will compile    
        //but may or may not throw a ClassCastException at runtime.
```
You should do like this -
```
LocalDate d2
        = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse("2015-01-01"));
```
5. Besides dates, java.time package also provides Period and Duration classes. Period is used for quantity or amount of time in terms of years, months and days, while Duration is used for quantity or amount of time in terms of hour, minute, and seconds.

Durations and periods differ in their treatment of daylight savings time when added to ZonedDateTime. A Duration will add an exact number of seconds, thus a duration of one day is always exactly 24 hours. By contrast, a Period will add a conceptual day, trying to maintain the local time.

For example, consider adding a period of one day and a duration of one day to 18:00 on the evening before a daylight savings gap. The Period will add the conceptual day and result in a ZonedDateTime at 18:00 the following day. By contrast, the Duration will add exactly 24 hours, resulting in a ZonedDateTime at 19:00 the following day (assuming a one hour DST gap).

--------------------------------------------------------------------------------
You need to remember that Instant doesn't represent date. It represents an instantaneous point on the time-line in nanoseconds and is used to record event time-stamps in the application. You cannot create an instance of Instant using new Instance(). You create an instance using static methods Instant.now() or Instant.now(Clock ).

Instant.now() returns the current instant from the system clock (also known as machine time), which is always in UTC. UTC may not necessarily be same as the local time. For example, if the local time is ahead of UTC by two hours and if local time is 3 PM, then UTC time must be 1PM (3 - 2). Therefore, the Instant.now() will create an Instant representing 2022-01-20T13:00:00.001Z. (Assuming the date is Jan 20th 2022).

-----------------------------------------------------------------------------------
This is actually a simple question to answer if you know the following basic rules -
1. Instant  is a point on Java time line. This timeline start from  from the first second of January 1, 1970 (1970-01-01T00:00:00Z) also called the EPOCH. Note that there is no time zone here. You may think of it as "Java Time Zone" and it matches with GMT or UTC time zone. That means, 1PM in Java time zone will be same as 1 PM in GMT or UTC time zone.
2. Once created, an Instant cannot be modified. Its methods such as plus and minus return a new Instant object.
3. Instant works with time (instead of dates), so you can use Duration instance to create new Instants.
4. LocalDateTime is a time in a given time zone. (But remember that an instance of LocalDateTime itself does not store the zone information!). You can, therefore, use an Instant and a time zone to create a LocalDateTime object.
5. Whenever you convert an Instant to a LocalDateTime using a time zone, just add or substract the GMT offset of the time zone i.e. if the time zone is GMT+2, add 2 hours and if the time zone is GMT-2, substract two hours. For example, you can yourself this question - if it is 1PM (for example) here in London, which is in GMT, then what would be the time in New York (for example), which is in GMT-4 (or 5, depending on whether the date lies when Day Light Savings time is on or not). The answer would be 1PM - 4 i.e. 9AM. 
