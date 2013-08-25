package com.asa.notif.hangouts.ui;

import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.asa.notif.hangouts.AppData;
import com.asa.notif.hangouts.R;
import com.asa.notif.hangouts.ui.base.AsaBaseFragment;

/**
 * Created by Aaron on 8/23/13.
 */
public class FragmentMain extends AsaBaseFragment implements View.OnClickListener {
    public static final String TAG = "FragmentMain";
    private Button mBtnAllowAccess;
    private Button mBtnSelectRingtone;

    private RingtoneManager mRingtoneManager;

    private Uri mSelectedUri;

    public static FragmentMain newInstance() {
        FragmentMain frag = new FragmentMain();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        mBtnAllowAccess = (Button) v.findViewById(R.id.main_btn_allow_access);
        mBtnSelectRingtone = (Button) v.findViewById(R.id.main_btn_select_ringtone);

        mBtnAllowAccess.setOnClickListener(this);
        mBtnSelectRingtone.setOnClickListener(this);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            Bundle extras = data.getExtras();
            Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            Log.d(TAG, "Test");
        }
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        switch (vId) {
            case R.id.main_btn_allow_access:
                startActivity(new Intent(AppData.ACTION_NOTIFICATION_LISTENER_SETTINGS));
                break;
            case R.id.main_btn_select_ringtone:
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, true);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Yo, Select Your Ringtone...Bitch");
                startActivityForResult(intent, AppData.ForResult.RINGTONE_SELECTOR);
                break;
        }
    }

    private void buildRingtoneManagerIfNeeded(){
        if(mRingtoneManager == null && mActivity != null){
            mRingtoneManager = new RingtoneManager(mActivity.getApplicationContext());
        }
    }
}
