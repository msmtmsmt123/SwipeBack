package info.papdt.swipeback.ui.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import info.papdt.swipeback.R;
import info.papdt.swipeback.ui.app.PerActivityFragment;
import info.papdt.swipeback.ui.app.PerAppFragment;
import static info.papdt.swipeback.ui.utils.UiUtility.*;

public class GlobalActivity extends ActionBarActivity
{
	private Toolbar mToolbar;
	private String mExtraPass;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base);
		
		mToolbar = $(this, R.id.toolbar);
		ViewCompat.setElevation(mToolbar, 15.0f);
		
		setSupportActionBar(mToolbar);
		
		Intent i = getIntent();
		
		Class<? extends Fragment> clazz = null;
		String extra = i.getStringExtra("fragment");
		mExtraPass = i.getStringExtra("pass");
		
		switch (extra != null ? extra : "") {
			case "perapp":
				clazz = PerAppFragment.class;
				break;
			case "peract":
				clazz = PerActivityFragment.class;
				break;
			default:
				clazz = PerAppFragment.class;
		}
		
		try {
			getFragmentManager().beginTransaction().replace(R.id.container, clazz.newInstance()).commit();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	Toolbar getToolbar() {
		return mToolbar;
	}
	
	String getExtraPass() {
		return mExtraPass;
	}
}
