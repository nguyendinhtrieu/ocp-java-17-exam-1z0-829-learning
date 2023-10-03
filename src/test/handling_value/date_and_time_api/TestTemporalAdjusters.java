package src.test.handling_value.date_and_time_api;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.IntStream;

public class TestTemporalAdjusters {
    public static void main(String[] args) throws Exception {
        System.out.println(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY).adjustInto(LocalDate.now()));
        System.out.println(TemporalAdjusters.lastDayOfMonth().adjustInto(LocalDate.of(2023, 2, 2)));

        IntStream.rangeClosed(2000, 2030)
                .mapToObj(x -> x + ": " + TemporalAdjusters.lastDayOfMonth().adjustInto(LocalDate.of(x, 2, 2)).toString())
                .forEach(System.out::println);

        System.out.println(LocalDate.now().atStartOfDay());
        System.out.println(LocalDate.now().atTime(23, 59, 59));
        System.out.println(Duration.between(LocalDate.now().atStartOfDay(), LocalDate.now().atTime(23, 59, 59)));
        System.out.println(Period.between(LocalDate.now(), LocalDate.of(2024, 1, 1)));


        Duration between;
        do {
            between = Duration.between(LocalDateTime.now(), LocalDate.of(2024, 1, 1).atStartOfDay());
            long hour = between.toHours();
            System.out.println((hour / 24) + " days, " + (hour % 24) + " hours, " + between.toMinutesPart() + " minutes, " + between.toSecondsPart() + " seconds left");
            Thread.sleep(1000);
            Runtime.getRuntime().exec("clear");
        } while (!between.isNegative());
    }
}
