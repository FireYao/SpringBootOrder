package com.simpletour.java8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class Java8DateTest {

    @Test
    public void name1() throws Exception {
        Instant is = Instant.now();
        System.out.println(is);
        System.out.println(new Date(is.toEpochMilli()));

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        System.out.println(LocalTime.now());

        System.out.println("test");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String format = dateTimeFormatter.format(ldt);
        System.out.println(format);
    }

    @Test
    public void name2() throws Exception {

        LocalDate ld1 = LocalDate.of(2017, 12, 10);
        LocalDate ld2 = LocalDate.of(2016, 4, 4);

        Period period = Period.between(ld2, ld1);

        System.out.println("相差" + period.getYears() + "年" + period.getMonths() + "月" + period.getDays() + "天");

    }


    /**
     * LocalDate
     *
     * @throws Exception
     */
    @Test
    public void name3() throws Exception {
        //Current Date
        LocalDate today = LocalDate.now();
        System.out.println("Current Date=" + today);

        LocalDate firstDay_2014 = LocalDate.of(2014, Month.JANUARY, 1);
        System.out.println("Specific Date=" + firstDay_2014);

        LocalDate todayChina = LocalDate.now(ZoneId.of("Asia/Shanghai"));
        System.out.println("Current Date in CTT=" + todayChina);

        LocalDate dateFromBase = LocalDate.ofEpochDay(100000);
        System.out.println("100000th day from base date= " + dateFromBase);

        LocalDate hundredDay2014 = LocalDate.ofYearDay(2017, 100);
        System.out.println("100th day of 2014=" + hundredDay2014);

    }

    /**
     * LocalTime
     *
     * @throws Exception
     */
    @Test
    public void name4() throws Exception {
        LocalTime time = LocalTime.now();
        System.out.println("Current Time=" + time);

        LocalTime specificTime = LocalTime.of(12, 20, 25, 40);
        System.out.println("Specific Time of Day=" + specificTime);

        LocalTime invalidTime = LocalTime.of(23, 20);
        System.out.println(invalidTime);

        LocalTime timeKolkata = LocalTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println("Current Time in CTT=" + timeKolkata);

        LocalTime specificSecondTime = LocalTime.ofSecondOfDay(60);
        System.out.println("10000th second time= " + specificSecondTime);

    }


    /**
     * LocalDateTime
     *
     * @throws Exception
     */
    @Test
    public void name5() throws Exception {

        LocalDateTime today = LocalDateTime.now();
        System.out.println("Current DateTime=" + today);

        LocalDateTime specificDate = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30, 12);
        System.out.println("Specific Date=" + specificDate);

        LocalDateTime todayKolkata = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println("Current Date in CTT=" + todayKolkata);

        LocalDateTime dateFromBase = LocalDateTime.ofEpochSecond(10000, 0, ZoneOffset.UTC);
        System.out.println("10000th second time from 01/01/1970= " + dateFromBase);
    }

    /**
     * Instant
     *
     * @throws Exception
     */
    @Test
    public void name6() throws Exception {

        //Current timestamp
        Instant timestamp = Instant.now();
        System.out.println("Current Timestamp = " + timestamp);

        //Instant from timestamp
        Instant specificTime = Instant.ofEpochMilli(timestamp.toEpochMilli());
        System.out.println("Specific Time = " + specificTime);

        //Duration example
        Duration thirtyDay = Duration.ofDays(30);
        System.out.println(thirtyDay);

    }


    /**
     * LocalDate API
     *
     * @throws Exception
     */
    @Test
    public void name7() throws Exception {
        LocalDate today = LocalDate.now();
//        LocalDate today = LocalDate.of(2016,5,20);
        System.out.println("Current Date=" + today);

        System.out.println(today.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        System.out.println(today.getYear() + " : " + (today.isLeapYear() ? "是闰年" : "不是闰年"));

        System.out.println("10 days after today will be " + today.plusDays(10));
        System.out.println("3 weeks after today will be " + today.plusWeeks(3));
        System.out.println("20 months after today will be " + today.plusMonths(20));
        System.out.println("10 days before today will be " + today.minusDays(10));
        System.out.println("3 weeks before today will be " + today.minusWeeks(3));
        System.out.println("20 months before today will be " + today.minusMonths(20));

        System.out.println("First date of this month= " + today.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDate lastDayOfYear = today.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("Last date of this year= " + lastDayOfYear);

        Period period = today.until(lastDayOfYear);
        System.out.println("Period Format= " + period);
        System.out.println("Months remaining in the year= " + period.getMonths());

    }
}
