package ca.mohawk.kaur;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * NewsActivity runs after the user clicks over a single ListView item on MainActivity and it displays the information about a single
 * news Article
 */
public class NewsActivity extends AppCompatActivity {
    private String TAG = "==NewsActivity==";
    private static Activity currentActivity;

    /**
     * onCreate method is the first method to run under the activity and it receives the news article passed through intent
     * to display that on screen.
     * @param savedInstanceState Bundle saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        currentActivity = this;
        Button goBack = findViewById(R.id.back_btn);
        goBack.setOnClickListener(this::goBack);
        Articles article = (Articles) getIntent().getSerializableExtra("article");
        Log.d(TAG,article.title);
        TextView titleText = findViewById(R.id.title_text);
        titleText.setText(article.title);
        if(article.media != null){
            Log.d(TAG,article.media);
            ImageAsyncTask imageTask = new ImageAsyncTask();
            imageTask.execute(article.media);
        }
        if(article.published_date == null){
            article.published_date = "";
        }
        if(article.author == null){
            article.author = "";
        }
        if(article.summary == null){
            article.summary = "";
        }
        Log.d(TAG,article.published_date);
        TextView authorDate = findViewById(R.id.date);
        String text = article.author+" "+article.published_date;
        authorDate.setText(text);
        Log.d(TAG,article.summary);
        TextView summary = findViewById(R.id.description);
        summary.setText(article.summary);
    }
    /**
     * @return the current activity
     */
    public static Activity getCurrentActivity(){
        return currentActivity;
    }

    /**
     * This method takes the user back to MainActivity
     * @param v Button View
     */
    public void goBack(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}