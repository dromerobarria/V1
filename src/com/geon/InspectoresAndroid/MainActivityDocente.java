package com.geon.InspectoresAndroid;
import com.geon.docente.*;
import com.geon.login.login;
import com.squareup.picasso.Picasso;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.FragmentTabHost;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.AppOpsManager.OnOpChangedListener;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivityDocente extends FragmentActivity implements
ActionBar.TabListener {

	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	SharedPreferences sharedpreferences;
	public static final String name = "userKey";
	public static final String MyPREFERENCES = "GEON";
	private OnBackPress OnBackPress;

	// variable menus
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private ActionBar actionbar;
	private String mTitle = "";
	
	// fin menu

	public interface OnBackPress {
		public void doBack();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main_activity_docente);

		mTitle = getString(R.string.nombre_colegio);
		getActionBar().setTitle(mTitle);

		Resources res = getResources();
		Bitmap bMap = BitmapFactory.decodeResource(res, R.drawable.arriba);
		BitmapDrawable actionBarBackground = new BitmapDrawable(res, bMap);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(actionBarBackground);

		// Getting reference to the DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerList = (ListView) findViewById(R.id.drawer_list);

		sharedpreferences = getSharedPreferences(login.MyPREFERENCES,
				Context.MODE_PRIVATE);
		
		View header = getLayoutInflater().inflate(R.layout.menu_principal, null); 
		mDrawerList.addHeaderView(header);
		TextView nom = (TextView)findViewById(R.id.textView1);
		TextView mail = (TextView)findViewById(R.id.textView2);
		nom.setText(sharedpreferences.getString("nombre_usuario", "nombre_usuario"));
		mail.setText(sharedpreferences.getString("email_usuario", "email_usuario"));
		ImageView avatar = (ImageView)findViewById(R.id.imageView1);
		String urlPhoto = ContactUtils.URL_PHOTO + sharedpreferences.getString("rut_usuario", "rut_usuario");
		 Picasso.with(this)
            .load(urlPhoto)
            .placeholder(R.drawable.man)
            .resize(40, 40).centerCrop()
            .into(avatar); 

		// Getting reference to the ActionBarDrawerToggle
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when drawer is closed */
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);

				invalidateOptionsMenu();

			}

			/** Called when a drawer is opened */
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle("Menu Principal");

				invalidateOptionsMenu();
			}

		};

		// Setting DrawerToggle on DrawerLayout
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// Creating an ArrayAdapter to add items to the listview mDrawerList
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getBaseContext(), R.layout.deawer_list_item, getResources()
						.getStringArray(R.array.menus));

		// Setting the adapter on mDrawerList
		mDrawerList.setAdapter(adapter);

		// Enabling Home button
		getActionBar().setHomeButtonEnabled(true);

		// Enabling Up navigation
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// Setting item click listener for the listview mDrawerList
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// Getting an array of rivers
				String[] menuItems = getResources().getStringArray(
						R.array.menus);

				// Currently selected river
				mTitle = menuItems[position - 1];

				Log.d("Menu Item: ", "> " + position);

				Intent in = null;
				in = new Intent(getApplicationContext(), MainActivity.class);

				ContactUtils.TAB_VENTANA = "" + position;

				// in.putExtra(ContactUtils.TAB_VENTANA, position);

				startActivity(in);
				finish();

				// Closing the drawer
				mDrawerLayout.closeDrawer(mDrawerList);

			}
		});

		// creamos menu tab

		actionbar = getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// create new tabs and set up the titles of the tabs
	
		ActionBar.Tab AlAnotaaciones = actionbar.newTab().setText(
				getString(R.string.al_anotacion));	
		ActionBar.Tab AlHorario = actionbar.newTab().setText(
				getString(R.string.al_horario));
		ActionBar.Tab AlFicha = actionbar.newTab().setText(
				getString(R.string.al_ficha));

		
		

	
	

		
		AlAnotaaciones.setTabListener(this);
	
		AlHorario.setTabListener(this);
		AlFicha.setTabListener(this);

		// add the tabs to the action bar

		actionbar.addTab(AlAnotaaciones);
		
		actionbar.addTab(AlHorario);
		actionbar.addTab(AlFicha);
		
		
		

	}

	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		Toast.makeText(getApplicationContext(), "Selected!" + item.getTitle(),
				Toast.LENGTH_SHORT).show();

		return super.onOptionsItemSelected(item);
	}

	/** Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the drawer is open, hide action items related to the content view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		if (OnBackPress != null) {

			OnBackPress.doBack();

		} else {
			// super.onBackPressed();

		}

	}

	public void setOnBackPressListener(OnBackPress listen) {
		this.OnBackPress = listen;

	}

	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	
		
	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub

		Fragment fragment =  new Ver_Docente();
		Bundle arguments = new Bundle();
		switch (tab.getPosition()) {
		case 0:
			fragment = new Ver_Docente();
			getSupportFragmentManager().beginTransaction()
	        .replace(R.id.fragment_container, fragment).commit();
			break;
		case 1:
			fragment = new Ver_Horario();
			 
		       
		      //  arguments.putBoolean("shouldYouCreateAChildFragment", false);
		       
				
		        fragment.setArguments(arguments);
		        getSupportFragmentManager().beginTransaction().replace(R.id.content_docente, fragment).commit();
			
			break;
		case 2:
			fragment = new Ver_Ficha_Docente();
		
			 	
			 
		      //  arguments.putBoolean("shouldYouCreateAChildFragment", false);
		        
		        fragment.setArguments(arguments);
		        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
			
			break;

		default:
			break;
		}
		
	    
		
	    
	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

}

// TabListenr class for managing user interaction with the ActionBar tabs. The
// application context is passed in pass it in constructor, needed for the
// toast.

