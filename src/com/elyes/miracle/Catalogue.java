package com.elyes.miracle;


import com.elyes.fragments.ProductsFragment;
import com.elyes.miracle.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

public class Catalogue extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	
	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catalogue);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		Fragment fragment =null;
	    FragmentManager fragmentManager = getFragmentManager();
	    switch(position) {
	        case 0:
	            fragment = new ProductsFragment(1);
	            break;
	        case 1:
	        	fragment = new ProductsFragment(2);
	            break;
	        case 2:
	        	fragment = new ProductsFragment(3);
	            break;
	        case 3:
	        	fragment = new ProductsFragment(4);
	            break;
	        case 4:
	        	fragment = new ProductsFragment(5);
	            break;
	        case 5:
	        	fragment = new ProductsFragment(6);
	            break;
	        case 6:
	        	fragment = new ProductsFragment(7);
	            break;
	    }
	    fragmentManager.beginTransaction()
	        .replace(R.id.container, fragment)
	        .commit();
		}
	
	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		case 4:
			mTitle = getString(R.string.title_section3);
			break;
		case 5:
			mTitle = getString(R.string.title_section3);
			break;
		case 6:
			mTitle = getString(R.string.title_section3);
			break;
		case 7:
			mTitle = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			
			getMenuInflater().inflate(R.menu.catalogue, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	

}
