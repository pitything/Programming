import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class TestDate {
    public static void main(String[] args) throws ParseException {
        long l = 1111119112112l;
        Date date = new Date(l);
        System.out.println(date);
        System.out.println(date.getTime());

        /** Date 格式转换 */
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateStr = dateFormat.format(date);
        System.out.println(dateStr);
        System.out.println(dateFormat.parse(dateStr));


        /** Calendar 类的使用 */
        Calendar calendar = Calendar.getInstance();
        System.out.println("当前时间：" + calendar.getTime());
        calendar.set(Calendar.YEAR, 2020);
        System.out.println("设置为2020年：" + calendar.getTime());
        calendar.add(Calendar.YEAR, 1);
        System.out.println("一年后：" + calendar.getTime());
        calendar.add(Calendar.DATE, 1);
        System.out.println("一天后：" + calendar.getTime());
        calendar.add(Calendar.MONDAY, 1);
        System.out.println("一月后：" + calendar.getTime());
        System.out.println(calendar.get(Calendar.DATE));
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));

        /** Date 和 Calendar 转换*/
        Date date1 = new Date(l);
        Calendar calendar1 = Calendar.getInstance();
        Date date2 = calendar1.getTime();
        calendar1.setTime(date1);

        /** LocalDate的使用 */
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);
        System.out.println(localDateTime.getMonth());
        System.out.println(localDateTime.getMonthValue());
        System.out.println("后一天为：" + localDateTime.plusDays(1));
        System.out.println("后一月为：" + localDateTime.plusMonths(1));
        System.out.println("后一年为：" + localDateTime.plusYears(1));
        System.out.println("后一 纳秒 为：" + localDateTime.plusNanos(1));
        System.out.println(localDateTime);

        /** Instant 类的使用 */
        System.out.println("：" + Instant.now());
        System.out.println("1970至今的毫秒数：" + Instant.now().toEpochMilli());



        /** DateTimeFormat 格式化 */
        LocalDateTime date22 = LocalDateTime.now();
        //1.使用常量创建DateTimeFormatter
        DateTimeFormatter dtf1 = DateTimeFormatter.ISO_DATE_TIME;
        System.out.println("使用常量创建DateTimeFormatter："+dtf1.format(date22));
        //2.使用MEDIUM类型风格的DateTimeFormatter
        DateTimeFormatter dtf2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        System.out.println("使用MEDIUM类型风格的DateTimeFormatter："+dtf2.format(date22));
        //3.根据模式字符串来创建DateTimeFormatter格式器
        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
        System.out.println("根据模式字符串来创建DateTimeFormatter格式器："+date22.format(dtf3));
        System.out.println("根据模式字符串来创建DateTimeFormatter格式器："+dtf3.format(date22));

        //定义两种日期格式的字符串
        String str1 = "2020-05-20 12:36:34";
        String str2 = "2020年05月20日 12点36分34秒";
        //定义解析所用的格式器
        DateTimeFormatter dtf11 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dtf22 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH点mm分ss秒");
        //使用DateTimeFormatter的parse()方法执行解析
        LocalDateTime ldt1 = LocalDateTime.parse(str1, dtf11);
        LocalDateTime ldt2 = LocalDateTime.parse(str2, dtf22);
        //输出
        System.out.println(ldt1);
        System.out.println(ldt2);
        System.out.println("--------------------------------------------------------------------");


        LocalDateTime localDateTime1 = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        System.out.println(dateTimeFormatter.format(localDateTime1));
        System.out.println(dateTimeFormatter1.format(localDateTime1));
        System.out.println(dateTimeFormatter2.format(localDateTime1));

        //Duration:用于计算两个“时间”间隔，以秒和纳秒为基准
        LocalTime localTime1 = LocalTime.now();
        LocalTime localTime2 = LocalTime.of(15, 23, 32);
        //between():静态方法，返回Duration对象，表示两个时间的间隔
        Duration duration = Duration.between(localTime2, localTime1);
        System.out.println(duration);
        System.out.println(duration.getSeconds());
        System.out.println(duration.getNano());
        LocalDateTime localDateTime2 = LocalDateTime.of(2016, 6, 12, 15, 23, 32);
        LocalDateTime localDateTime3 = LocalDateTime.of(2017, 6, 12, 15, 23, 32);
        Duration duration1 = Duration.between(localDateTime3, localDateTime2);
        System.out.println(duration1.toDays());

        //Period:用于计算两个“日期”间隔，以年、月、日衡量
        LocalDate localDate2 = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2028, 3, 18);
        Period period = Period.between(localDate2, localDate1);
        System.out.println(period);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        Period period1 = period.withYears(2);
        System.out.println(period1);

        /** TemporalAdjuster:时间校正器 */
        // 获取当前日期的下一个周日是哪天？
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.next(DayOfWeek.SUNDAY);
        LocalDateTime aaa = LocalDateTime.now().with(temporalAdjuster);
        System.out.println(aaa);
        // 获取下一个工作日是哪天？
        LocalDate bbb = LocalDate.now().with(temporal -> {
            LocalDate date3 = (LocalDate) temporal;
            if (date3.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                return date3.plusDays(3);
            } else if (date3.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                return date3.plusDays(2);
            } else {
                return date3.plusDays(1);
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

        /** Comparator 比较器 */
        Integer[] ddd = new Integer[]{1,2,3,3,1};
        Arrays.sort(ddd, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o1 ? 1 : (o1 == o2 ? 0 : -1);
            }
        });
        Arrays.sort(ddd, (o1, o2) -> o1 > o1 ? 1 : (o1 == o2 ? 0 : -1));
    }
}

class TestComparable implements Comparable{

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
