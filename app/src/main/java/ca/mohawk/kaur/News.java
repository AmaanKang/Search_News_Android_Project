package ca.mohawk.kaur;
import java.util.ArrayList;

/**
 * News class represents the whole set of data that is fetched from the WEB API
 */
public class News {
    public String status;
    public int total_hits;
    public int page;
    public int total_pages;
    public int page_size;
    public ArrayList<Articles> articles;
    public UserInput user_input;
}
