package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SqlRuParse {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i <= 5; i++) {
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
            Elements date = doc.select(".altCol");
        }
    }
}
