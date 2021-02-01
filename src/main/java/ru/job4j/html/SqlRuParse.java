package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.Parse;
import ru.job4j.grabber.ParseDate;
import ru.job4j.grabber.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SqlRuParse implements Parse {

    @Override
    public List<Post> list(String link) {
        List<Post> posts = new ArrayList<>();
        Document doc = getDocument(link);
        Elements row = doc.select(".postslisttopic");
        for (Element td : row) {
            Element href = td.child(0);
            posts.add(detail(href.attr("href")));
        }
        return posts;
    }

    @Override
    public Post detail(String link) {
        Document doc = getDocument(link);
        Element jData = doc.select(".msgTable").first();
        Element jDescription = jData.select(".msgBody").last();
        Element jAuthor = jData.select(".msgBody").first();
        Elements jDate = jData.select(".msgFooter");
        Date date = ParseDate.getDateFromRusFormat(Arrays.stream(jDate.text().split(" "))
                .limit(4).collect(Collectors.joining(" ")), "d MMM yy HH:mm");
        String description = jDescription.text();
        String author = jAuthor.text().split(" ")[0];
        return new Post(description, author, link, date);
    }

    public Document getDocument(String link) {
        try {
            return Jsoup.connect(link).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i <= 5; i++) {
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
            Elements date = doc.select(".altCol");
        }
    }
}
