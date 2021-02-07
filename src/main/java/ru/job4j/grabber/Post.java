package ru.job4j.grabber;

import java.util.Date;

public class Post {
    private int id;
    private String description;
    private String link;
    private Date date;
    private String nameVac;

    public Post(String nameVac, String description, String link, Date date) {
        this.description = description;
        this.link = link;
        this.date = date;
        this.nameVac = nameVac;
    }

    public Post(int id, String nameVac, String description, String link, Date date) {
        this.id = id;
        this.description = description;
        this.link = link;
        this.date = date;
        this.nameVac = nameVac;
    }

   public int getId() {
        return id;
   }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public Date getDate() {
        return date;
    }

    public String getNameVac() {
        return nameVac;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNameVac(String nameVac) {
        this.nameVac = nameVac;
    }

    @Override
    public String toString() {
        return "Post{"
                + "description='" + description + '\''
                + ", link='" + link + '\''
                + ", date=" + date
                + ", nameVac='" + nameVac + '\''
                + '}';
    }

}


