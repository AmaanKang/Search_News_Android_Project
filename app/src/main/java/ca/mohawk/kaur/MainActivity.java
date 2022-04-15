package ca.mohawk.kaur;
/**
 * I, Amandeep Kaur, 000822179 certify that this material is my original work. No
 * other person's work has been used without due acknowledgement.
 *
 * Youtube link:
 * https://youtu.be/5bfvqNQdvQQ
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Main Activity is the first activity that runs on the launch of application.
 */
public class MainActivity extends AppCompatActivity {
    private String TAG = "==MainActivity==";
    private static Activity currentActivity;
    public final String SW_STATE = "STATE";
    public final String SW_STATE1 = "STATE1";
    public final String SP_File = "shared_pref.dat";
    private TopFragment top;

    /**
     * onCreate method is the first method to run under the activity and it replaces the top and bottom Frame layouts of activity
     * with two different fragments.
     * @param savedInstanceState Bundle saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentActivity = this;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ArticleFragment articles = new ArticleFragment();
        top = new TopFragment();
        ft.replace(R.id.top_layout,top);
        ft.replace(R.id.frame_layout,articles);
        ft.commit();
    }

    /**
     *
     * @return the current activity
     */
    public static Activity getCurrentActivity(){
        return currentActivity;
    }

    /**
     * this method runs right before the activity comes to foreground.
     */
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = this.getSharedPreferences(SP_File, Context.MODE_PRIVATE);
        String q = sharedPreferences.getString(SW_STATE,"");
        int lang = sharedPreferences.getInt(SW_STATE1,0);
        top.getSearchTerm().setText(q);
        top.getSpinner().setSelection(lang);
        top.onSearchBtnClick(top.getBtnView());
    }

    /**
     * this method runs right before the user leaves the activity.
     */
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = this.getSharedPreferences(SP_File,Context.MODE_PRIVATE);
        String q = top.getSearchTerm().getText().toString();
        int lang = top.getSpinner().getSelectedItemPosition();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SW_STATE,q);
        editor.putInt(SW_STATE1,lang);
        editor.apply();
    }
}