package com.asa.notif.hangouts;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.asa.notif.hangouts.ui.FragmentMain;
import com.asa.notif.hangouts.ui.base.AsaBaseActivity;

public class MainActivity extends AsaBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container_view);

        if (savedInstanceState == null) {
            addMainFragment();
        } else {
            Fragment frag = mFragmentManager.findFragmentByTag(FragmentMain.TAG);
            if (frag != null) {
                mFragmentManager.beginTransaction().attach(frag).commit();
            } else {
                addMainFragment();
            }
        }

    }

    private void addMainFragment() {
        addFragment(FragmentMain.newInstance(), FragmentMain.TAG, false, false);
    }

}
