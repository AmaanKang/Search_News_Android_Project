package ca.mohawk.kaur;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Articles class represents the data present under a single article fetched from the internet and it is used by News Class as a data type
 * for articles field.
 */
public class Articles implements Serializable {
    public String title;
    public String author;
    public String published_date;
    public String published_date_precision;
    public String link;
    public String clean_url;
    public String summary;
    public String rights;
    public int rank;
    public String topic;
    public String country;
    public String language;
    public ArrayList<String> authors;
    public String media;
    public boolean is_opinion;
    public String twitter_account;
    public double _score;
    public String _id;
}
