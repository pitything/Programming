import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class TestDate {
    public static void main(String[] args) throws ParseException {
        /** java.lang.System类 */
        System.out.println("-----------java.lang.System类-----------");
        System.out.println(System.currentTimeMillis());

        /** java.util.Date类 */
        System.out.println("-----------java.lang.Date类-----------");
        Date date1 = new Date(); // 当前时间
        System.out.println(date1);
        System.out.println(date1.getTime()); // 返回1970-01-01到当前的ms数

        /** java.text.SimpleDateFormat类 */
        System.out.println("-----------java.text.SimpleDateFormat类-----------");
        Date date2 = new Date(); // 当前时间
        String dateStr = "2022-07-01";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 转化格式
        System.out.println(simpleDateFormat.format(date2));// 2022-07-04
        System.out.println(simpleDateFormat.parse(dateStr)); // 返回 Fri Jul 01 00:00:00 CST 2022

        /** java.util.Calendar类 */
        System.out.println("-----------java.util.Calendar类-----------");
        Calendar calendar = Calendar.getInstance(); // 2022-07-04
        System.out.println(calendar.get(Calendar.YEAR)); // 年：2022
        System.out.println(calendar.get(Calendar.MONTH));// 月：6；1月是0，以此类推
        System.out.println(calendar.get(Calendar.DATE)); // 日：4
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));// 一年的第几天：185
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH)); // 一月的第几天，同DATE：4
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));// 一周的第几天：2：周日为1，以此类推
        calendar.set(2022, 7, 5, 12, 12, 12);
        calendar.set(Calendar.YEAR, 2020); // 设置为2020年
        Date date3 = calendar.getTime();
        calendar.setTime(new Date(1656921026218L));
        System.out.println(calendar.getTime()); // Mon Jul 04 15:50:26 CST 2022
        calendar.add(Calendar.HOUR, 2); // 当前时间加2小时后：Mon Jul 04 17:50:26 CST 2022
        System.out.println(calendar.getTime());
        calendar.add(Calendar.MONTH, -2); // 两个月前：Wed May 04 17:50:26 CST 2022
        System.out.println(calendar.getTime());

        /** LocalDate的使用 */
        System.out.println("-----------LocalDate的使用-----------");
        /** 创建时间日期对象 */
        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = LocalDate.of(2020, 7, 4);
        LocalTime localTime1 = LocalTime.now();
        LocalTime localTime2 = LocalTime.of(15, 44, 12, 12);
        LocalDateTime localDateTime1 = LocalDateTime.now();
        LocalDateTime localDateTime2 = LocalDateTime.of(2020, 7, 4, 15, 44, 12, 12);

        /** 获取年月日时分秒 */
        System.out.println(localDateTime1.getYear());
        System.out.println(localDateTime1.getMonth());
        System.out.println(localDateTime1.getMonthValue());
        System.out.println(localDateTime1.getDayOfMonth());
        System.out.println(localDateTime1.getDayOfYear());
        System.out.println(localDateTime1.getDayOfWeek());
        System.out.println(localDateTime1.getHour());
        System.out.println(localDateTime1.getMinute());
        System.out.println(localDateTime1.getSecond());
        System.out.println(localDateTime1.getNano());

        /** 设置指定的年月日时分秒 */
        System.out.println(localDateTime1.withYear(2020));
        System.out.println(localDateTime1.withMonth(8));
        System.out.println(localDateTime1.withDayOfMonth(9));
        System.out.println(localDateTime1.withHour(16));
        System.out.println(localDateTime1.withMinute(22));
        System.out.println(localDateTime1.withSecond(23));
        System.out.println(localDateTime1.withNano(12));

        /** 当前时间日期加减 */
        System.out.println(localDateTime1.plusYears(1));
        System.out.println(localDateTime1.plusMonths(1));
        System.out.println(localDateTime1.plusWeeks(1));
        System.out.println(localDateTime1.plusDays(1));
        System.out.println(localDateTime1.minusYears(1));
        System.out.println(localDateTime1.minusMonths(1));
        System.out.println(localDateTime1.minusWeeks(1));
        System.out.println(localDateTime1.minusDays(1));

        /** Instant 类的使用 */
        System.out.println("-----------Instant的使用-----------");
        Instant now = Instant.now();
        System.out.println(now); // 当前时间点：2022-07-04T09:30:23.524Z
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));// 偏移8小时：格林尼治 到北京时间
        System.out.println(offsetDateTime); // 当前时间点：2022-07-04T17:30:23.524+08:00
        System.out.println(Instant.ofEpochMilli(1656926415516L)); // 根据时间戳创建时间点：2022-07-04T09:20:15.516Z
        System.out.println(now.toEpochMilli()); // 1970至今的毫秒数：1656926826993

        /** DateTimeFormat 格式化 */
        System.out.println("-----------DateTimeFormat 格式化-----------");
        LocalDateTime localDateTime = LocalDateTime.now();
        String str11 = "2022-07-04T20:21:52.493";
        //1.预定义的标准格式
        System.out.println(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime)); // 2022-07-04T20:26:49.758
        System.out.println(DateTimeFormatter.ISO_LOCAL_DATE.format(localDateTime)); // 2022-07-04
        System.out.println(DateTimeFormatter.ISO_LOCAL_TIME.format(localDateTime)); // 20:26:49.758
        System.out.println(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(str11)); // 2022-07-04T20:21:52.493
        //2.本地化相关的格式
        DateTimeFormatter dtf21 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG); // 2022年7月4日 下午08时31分10秒
        DateTimeFormatter dtf22 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT); // 22-7-4 下午8:31
        DateTimeFormatter dtf23 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);// 2022-7-4 20:31:10
        System.out.println(dtf21.format(localDateTime));
        System.out.println(dtf22.format(localDateTime));
        System.out.println(dtf23.format(localDateTime));
        System.out.println(dtf23.parse("2022-7-4 20:28:55"));
        //3.自定义的格式。
        String str1 = "2020-05-20 12:36:34";
        String str2 = "2020年05月20日 12点36分34秒";
        DateTimeFormatter dtf31 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dtf32 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH点mm分ss秒");
        System.out.println(localDateTime.format(dtf31)); // 2022-07-04 20:31:10
        System.out.println(dtf31.format(localDateTime)); // 2022-07-04 20:31:10
        System.out.println(LocalDateTime.parse(str1, dtf31)); // 2020-05-20T12:36:34
        System.out.println(LocalDateTime.parse(str2, dtf32)); // 2020-05-20T12:36:34

        /** Duration:用于计算两个“时间”间隔，以秒和纳秒为基准 */
        System.out.println("-----------Duration-----------");
        LocalDateTime localDateTime3 = LocalDateTime.of(2016, 6, 12, 15, 23, 32);
        LocalDateTime localDateTime4 = LocalDateTime.of(2017, 6, 12, 15, 23, 32);
        //between():静态方法，返回Duration对象，表示两个时间的间隔
        Duration duration = Duration.between(localDateTime3, localDateTime4);
        System.out.println(duration); // PT8760H
        System.out.println(duration.toDays()); // 365
        System.out.println(duration.toHours()); // 8760
        System.out.println(duration.toMinutes()); // 525600
        System.out.println(duration.getSeconds()); // 31536000
        System.out.println(duration.toMillis()); // 31536000000
        System.out.println(duration.toNanos()); // 31536000000000000
        System.out.println(duration.getNano()); // 0

        /** Period:用于计算两个“日期”间隔，以年、月、日衡量 */
        System.out.println("-----------Period-----------");
        LocalDate localDate5 = LocalDate.of(2016, 6, 12);
        LocalDate localDate6 = LocalDate.of(2017, 7, 22);
        Period period = Period.between(localDate5, localDate6);
        System.out.println(period); // P1Y1M10D
        System.out.println(period.getYears()); // 1
        System.out.println(period.getMonths()); // 1
        System.out.println(period.getDays()); // 10
        System.out.println(period.toTotalMonths()); // 13

        /** TemporalAdjuster:时间校正器 */
        // 获取当前日期的下一个周日是哪天？
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.next(DayOfWeek.SUNDAY);
        LocalDateTime aaa = LocalDateTime.now().with(temporalAdjuster);
        System.out.println(aaa);
        // 获取下一个工作日是哪天？
        LocalDate bbb = LocalDate.now().with(temporal -> {
            LocalDate date9 = (LocalDate) temporal;
            if (date9.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                return date9.plusDays(3);
            } else if (date9.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                return date9.plusDays(2);
            } else {
                return date9.plusDays(1);
            } });
        System.out.println("下一个工作日是：" + bbb);

        TestComparable testComparable = new TestComparable();
        TestComparable testComparable1 = new TestComparable();
        System.out.println(testComparable.compareTo(testComparable1));
        System.out.println(testComparable.equals(testComparable1));
        LocalDate localDate3 = LocalDate.now();
        LocalDate localDate4 = LocalDate.now();
        System.out.println(localDate3.compareTo(localDate4));
        System.out.println(localDate3.equals(localDate4));
    }
}


