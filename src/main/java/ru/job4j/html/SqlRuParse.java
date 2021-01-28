package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Pattern;


public class SqlRuParse {
   public static final Pattern DATE_PATTERN = Pattern.compile(".*, \\d{2}:\\d{2}");
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements row = doc.select(".postslisttopic");
        Elements date = doc.select(".altCol");
        for (int i = 1; i < date.size(); i += 2) {
            System.out.println(date.get(i).text());
        }
    }
}
