package guru.springframework;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MyTests {

    @Test
    void testCollection() {
        List listOne = new ArrayList(Arrays.asList(1,2,3,4,5));
        List listTwo = new ArrayList(Arrays.asList(1,2,3,4,6));

        listTwo.retainAll( listOne );
        System.out.println( listTwo );

        Object collect = listOne.stream()
                .filter(element -> listTwo.contains(element))
                .collect(Collectors.toList());

        System.out.println(collect);
    }

    @Test
    void testTimeZone() throws ParseException {
        String strTime = "2022-09-05T06:43:51.180442300Z";
        Date parse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(strTime);
        Instant now = Instant.now();
        now.toString();

        String createdDate = "2022-09-05T12:10:30.424063900Z";
        Instant parse1 = Instant.parse(createdDate);
        System.out.println("parse instatn: " + parse1.toString());

        System.out.println(parse.toString());
    }

    @Test
    void addDay(){
        Instant plus = Instant.now().plus(1, ChronoUnit.DAYS);
        Date from = Date.from(plus);

        Clock defaultClock = Clock.systemDefaultZone();
        Instant instant = Instant.now(defaultClock);

        System.out.println("Data: " + plus);
        System.out.println("From: " + from);
    }

    @Test
    void testDate(){
        Clock utcClock = Clock.systemUTC();
        Clock defaultClock = Clock.systemDefaultZone();
        Clock offsetClock = Clock.offset(Clock.systemUTC(), Duration.ofHours(-5));

        ZoneId denverTimeZone = ZoneId.of("America/Denver");
        ZoneId newYorkTimeZone = ZoneId.of("America/New_York");
        ZoneId chicagoTimeZone = ZoneId.of("America/Chicago");
        ZoneId losAngelesTimeZone = ZoneId.of("America/Los_Angeles");

        Instant instant = Instant.now(defaultClock);
        Instant instant2 = Instant.now(utcClock);
        Instant instant3 = Instant.now(offsetClock);

        ZonedDateTime zonedDateTime = instant.atZone(denverTimeZone);

        System.out.println(instant);
        System.out.println(instant2);
        System.out.println(instant3.plus(Duration.ofSeconds(90)));
        System.out.println(instant3.atZone(newYorkTimeZone));
        System.out.println(instant3.atZone(chicagoTimeZone));
        System.out.println(instant3.atZone(denverTimeZone));
        System.out.println(instant3.atZone(losAngelesTimeZone));
    }

    @Test
    void testDuration(){

        Duration lockoutPeriod = Duration.ofHours(24);
        System.out.println(lockoutPeriod.getSeconds());
        System.out.println(lockoutPeriod.toMinutes());
    }

    @Test
    void testOffsetDateTime(){
        OffsetDateTime now = OffsetDateTime.now();
        System.out.println("NOW: " + now.toString());

        OffsetDateTime offsetDateTime = now.withOffsetSameLocal(ZoneOffset.UTC);
        System.out.println("Offset: " + offsetDateTime.toString());

        Date date = new Date();
        OffsetDateTime offsetDateTime1 = OffsetDateTime.of(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), ZoneOffset.UTC);
        System.out.println("Test2: " + offsetDateTime1.toString());
    }

}
