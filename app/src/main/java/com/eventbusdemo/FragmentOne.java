package com.eventbusdemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.greenrobot.event.EventBus;

/**
 * Created by admin on 2/20/2016.
 */
public class FragmentOne extends Fragment {

    View rootVIew;
    EditText et1;
    TextView tv1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootVIew = inflater.inflate(R.layout.layout_fragment_one, container, false);

        Button btnNextFragment = (Button) rootVIew.findViewById(R.id.btnnextfragment);
        et1 = (EditText) rootVIew.findViewById(R.id.et1);
        tv1 = (TextView) rootVIew.findViewById(R.id.tv1);

        btnNextFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container, new FragmentTwo());
                transaction.commit();

                EventBus.getDefault().postSticky(new DataModel(et1.getText().toString()));

            }
        });

        return rootVIew;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(!EventBus.getDefault().isRegistered(this)){

            EventBus.getDefault().registerSticky(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(DataModel model){

        tv1.setText("Received Data : " + model.getData());

    }
}
