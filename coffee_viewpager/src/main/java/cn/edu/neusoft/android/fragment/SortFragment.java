package cn.edu.neusoft.android.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.neusoft.android.activity.R;

/**
 * Created by sevon on 2017/4/10.
 */

public class SortFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.sort_fragment, null);
    }
}
