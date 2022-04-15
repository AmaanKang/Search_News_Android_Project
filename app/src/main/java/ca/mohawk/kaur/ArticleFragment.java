package ca.mohawk.kaur;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * This Fragment holds the list of articles.
 */
public class ArticleFragment extends Fragment {
    /**
     * Fragment Constructor
     */
    public ArticleFragment() {
    }

    /**
     * @param inflater layout inflater
     * @param container View group
     * @param savedInstanceState Bundle state
     * @return an inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article, container, false);
    }
}