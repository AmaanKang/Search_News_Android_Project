package ca.mohawk.kaur;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * TopFragment contains some user controls and is displayed in the top frame layout of MainActivity.
 */
public class TopFragment extends Fragment {
    private EditText searchTerm;
    private Spinner language;
    private View searchBtn;

    /**
     * Fragment constructor
     */
    public TopFragment() {
    }

    /**
     * onCreateView is the fist method to run when the fragment is created in any activity
     * @param inflater layout inflater
     * @param container View group
     * @param savedInstanceState Bundle state
     * @return an inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_top, container, false);
        searchTerm = fragView.findViewById(R.id.search_term);
        language = fragView.findViewById(R.id.lang_spinner);
        searchBtn = fragView.findViewById(R.id.search_btn);
        searchBtn.setOnClickListener(this::onSearchBtnClick);
        return fragView;
    }

    /**
     * onSearchBtnClick method runs when the search button is clicked and it calls SearchAsyncTask to search for the articles
     * by using url.
     * @param v
     */
    public void onSearchBtnClick(View v){
        String q = searchTerm.getText().toString();
        TextView msg = getActivity().findViewById(R.id.msg);
        if(q.equals("")){
            ListView lv = getActivity().findViewById(R.id.article_list);
            lv.setAdapter(null);
            msg.setText(R.string.msg);
        }else{
            msg.setText("");
            String lang = language.getSelectedItem().toString().toLowerCase();
            if(lang.equals("language")){
                lang = "en";
            }
            SearchAsyncTask search = new SearchAsyncTask();
            String url = "https://free-news.p.rapidapi.com/v1/search?q="+q+"&lang="+lang;
            search.execute(url);
        }

    }

    /**
     * @return EditText View
     */
    public EditText getSearchTerm(){
        return searchTerm;
    }

    /**
     * @return Spinner View
     */
    public Spinner getSpinner(){
        return language;
    }

    /**
     * @return Search Button View
     */
    public View getBtnView(){
        return searchBtn;
    }
}