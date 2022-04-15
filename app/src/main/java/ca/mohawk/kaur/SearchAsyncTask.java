package ca.mohawk.kaur;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import com.google.gson.Gson;

/**
 * SearchAsyncTask is called by MainActivity to search for a list of articles and display that list in MainActivity.
 */
public class SearchAsyncTask extends AsyncTask<String, Void, String> {
    private String TAG = "==SearchAsyncTask==";
    private ArrayList<Articles> articles;

    /**
     * Searches for news article in background by using the url and makes call to WEB API
     * @param urls web api urls
     * @return the fetched data in form of StringBuilder
     */
    @Override
    protected String doInBackground(String... urls) {
        StringBuilder fetchedData = new StringBuilder();
        try {
            URL url = new URL(urls[0]);
            String line = null;
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("X-RapidAPI-Host", "free-news.p.rapidapi.com");
            conn.setRequestProperty("X-RapidAPI-Key", "0d008786a5mshf2a5fe3e71f3ff2p1de90bjsnc26e55a56641");
            int statusCode = conn.getResponseCode();
            Log.d(TAG, "Status Code: " + statusCode);
            if (statusCode == 200) {
                InputStream inputStream = new BufferedInputStream(
                        conn.getInputStream());
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream,
                                "UTF-8"));
                while ((line = bufferedReader.readLine()) != null) {
                    fetchedData.append(line);
                }
                Log.d(TAG, "Data received = " + fetchedData);
            }
        } catch (IOException ex) {
            Log.d(TAG, "Caught Exception: " + ex);
        }
        return fetchedData.toString();
    }

    /**
     * After the data is fetched, onPostExecute uses that data and gets the articles out of it to display in ListView.
     * @param result the fetched data in form of StringBuilder
     */
    @Override
    protected void onPostExecute(String result) {
        News newsList = null;
        ListView lv = MainActivity.getCurrentActivity().findViewById(R.id.article_list);
        ArrayList<String> titles = new ArrayList<>();
        if (result == null) {
            Log.d(TAG, "No results");
        } else {
            Gson gson = new Gson();
            try {
                newsList = gson.fromJson(result, News.class);
                articles = newsList.articles;
                for (Articles article:articles) {
                    titles.add(article.title);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.getCurrentActivity(), android.R.layout.simple_list_item_1,titles);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(this::onItemClick);
            } catch (NullPointerException e) {
                Log.d(TAG, e.toString());
                titles.add("No results found");
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.getCurrentActivity(), android.R.layout.simple_list_item_1,titles);
                lv.setAdapter(adapter);
            }
        }
    }

    /**
     * When an item is clicked in ListView, then onItemClick passes the clicked article to NewsActivity by using intent and
     * starts NewsActivity.
     * @param parent Adapter View
     * @param v item View
     * @param position position of the article in the ListView
     * @param id id of the clicked article
     */
    public void onItemClick(AdapterView parent, View v, int position, long id) {
        String name = ((TextView) v).getText().toString();
        Articles selectedArticle = null;
        for (Articles article:articles) {
            if(article.title.equals(name)){
                selectedArticle = article;
            }
        }
        Intent intent = new Intent(MainActivity.getCurrentActivity(), NewsActivity.class);
        intent.putExtra("article", selectedArticle);
        MainActivity.getCurrentActivity().startActivity(intent);
    }
}
