package src.test.handling_value.date_and_time_api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestLocalDatePrint {
    public static void main(String[] args) {
        LocalDate d1 = LocalDate.parse("2022-02-05", DateTimeFormatter.ISO_DATE);
        LocalDate d2 = LocalDate.of(2022, 2, 5);
        LocalDate d3 = LocalDate.now();
        System.out.println(d1); //2022-02-05
        System.out.println(d2); //2022-02-05
        System.out.println(d3); //2022-02-05
    }
}
