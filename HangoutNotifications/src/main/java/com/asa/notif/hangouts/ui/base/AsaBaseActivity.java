package com.asa.notif.hangouts.ui.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.asa.notif.hangouts.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class AsaBaseActivity extends FragmentActivity {

	protected FragmentManager mFragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mFragmentManager = getSupportFragmentManager();
	}

	/**
	 * Sets the animation on the FragmentTransaction. If nothing is set, then it
	 * defaults to the animations that are included in the library. Recommended
	 * that you just use those.
	 * 
	 * @param ft
	 * @return
	 */
	private FragmentTransaction setAnimation(FragmentTransaction ft) {
		ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
		return ft;
	}

	/**
	 * Removes a fragment from the Activity.
	 * 
	 * @param fragmentToRemove
	 */
	public void removeFragment(Fragment fragmentToRemove) {
		FragmentTransaction ft = mFragmentManager.beginTransaction();
		setAnimation(ft);
		ft.remove(fragmentToRemove);
		ft.commit();
	}

	/**
	 * Replaces the current fragment with the fragment passed in.
	 * 
	 * @param newFragment
	 *            The fragment to display.
	 * @param addToBackStack
	 *            Tells whether or not to add this transaction to the back
	 *            stack.
	 */
	public void replaceFragment(Fragment newFragment, boolean addToBackStack) {
		replaceFragment(newFragment, null, addToBackStack, true);
	}

	/**
	 * Replaces the current fragment with the fragment passed in.
	 * 
	 * @param newFragment
	 *            The fragment to display.
	 * @param tag
	 *            The tag of the new fragment.
	 * @param addToBackStack
	 *            Tells whether or not to add this transaction to the back
	 *            stack.
	 */
	public void replaceFragment(Fragment newFragment, String tag, boolean addToBackStack) {
		replaceFragment(newFragment, tag, addToBackStack, true);
	}

	public void replaceFragment(Fragment newFragment, String tag, boolean addToBackStack, boolean animate) {
		FragmentTransaction ft = mFragmentManager.beginTransaction();
		if (animate)
			setAnimation(ft);
		ft.replace(R.id.fragment_container, newFragment, tag);
		if (addToBackStack)
			ft.addToBackStack(null);
		ft.commit();
	}

	/**
	 * Adds a fragment.
	 * 
	 * @param newFragment
	 *            The fragment to add
	 * @param addToBackStack
	 *            Tells whether or not to add this transaction to the back
	 *            stack.
	 */
	public void addFragment(Fragment newFragment, boolean addToBackStack) {
		addFragment(newFragment, null, addToBackStack, true);
	}

	/**
	 * Adds a fragment.
	 * 
	 * @param newFragment
	 *            The fragment to add
	 * @param tag
	 *            The tag of the fragment added.
	 * @param addToBackStack
	 *            Tells whether or not to add this transaction to the back
	 *            stack.
	 */
	public void addFragment(Fragment newFragment, String tag, boolean addToBackStack) {
		addFragment(newFragment, tag, addToBackStack, true);
	}

	public void addFragment(Fragment newFragment, String tag, boolean addToBackStack, boolean animate) {
		FragmentTransaction ft = mFragmentManager.beginTransaction();
		if (animate)
			setAnimation(ft);
		ft.add(R.id.fragment_container, newFragment, tag);
		if (addToBackStack)
			ft.addToBackStack(null);
		ft.commit();
	}

	public void popBackStack() {
		mFragmentManager.popBackStack();
	}

}
