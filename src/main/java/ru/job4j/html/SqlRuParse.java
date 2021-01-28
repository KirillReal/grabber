package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class SqlRuParse {
    private static final DateFormatSymbols rusDateFormatSymbols = new DateFormatSymbols() {
        @Override
        public String[] getMonths() {
            return new String[]{"янв", "фев", "мар", "апр", "май", "июн",
                    "июл", "авг", "сен", "окт", "ноя", "дек"};
        }
    };

    public static Date today() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Date yesterday() {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public static Date getDateFromRusFormat(String dateStr, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, rusDateFormatSymbols);
        dateStr = dateStr.replace(",", "");
        try {
            if (dateStr.contains("сегодня")) {
                return today();
            } else if (dateStr.contains("вчера")) {
                return yesterday();
            }
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i <= 5; i++) {
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
            Elements date = doc.select(".altCol");
            for (int k = 7; k < date.size(); k += 2) {
                System.out.println(getDateFromRusFormat(
                        date.get(k).text(), "d MMM yy HH:mm"));
            }
        }
    }
}
