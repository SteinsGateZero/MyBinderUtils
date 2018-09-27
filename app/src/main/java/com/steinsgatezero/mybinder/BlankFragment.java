package com.steinsgatezero.mybinder;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.steinsgatezero.coolbinder.IntentKey;
import com.steinsgatezero.coolbinder.IntentType;
import com.steinsgatezero.coolbinderutils.IntentBinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    TextView textView;

    @IntentKey(value = "keyinfo3", intentType = IntentType.FRAGMENT)
    TestInfo info;


    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        textView = view.findViewById(R.id.frag_tv);
        IntentBinder.inject(this);
        textView.setText(info.toString());
        return view;
    }

}
