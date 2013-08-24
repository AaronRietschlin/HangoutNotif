package com.asa.notif.hangouts.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;


public class AsaBaseFragment extends Fragment {

	protected AsaBaseActivity mActivity;
	protected View mLoadingLayout;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = (AsaBaseActivity) activity;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// TODO - Add this in!
//		mLoadingLayout = view.findViewById(R.id.loading);
	}
}
