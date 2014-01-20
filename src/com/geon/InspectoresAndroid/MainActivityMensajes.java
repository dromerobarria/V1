package com.geon.InspectoresAndroid;










import com.geon.alumno.ContactUtils;
import com.geon.alumno.search_alumno;
import com.geon.cursos.search_curso;
import com.geon.docente.search_docente;
import com.geon.login.login;
import com.squareup.picasso.Picasso;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;


import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityMensajes extends FragmentActivity{
 
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
    SharedPreferences sharedpreferences;
	 public static final String name = "userKey"; 
	 public static final String MyPREFERENCES = "GEON" ;
	 private OnBackPress OnBackPress;
	 
	 //variable menus
	 private DrawerLayout mDrawerLayout;
	 private ListView mDrawerList;
	 private ActionBarDrawerToggle mDrawerToggle;
     private String mTitle = "";
     
	 //fin menu
	 
     public interface OnBackPress{
    	 public void doBack();
    	 
    	 
     }
     
     
    @Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_principal);
      
       
       
        	//INICIALIZADO VENTANA PRINICPAL U OTRA
            Fragment ventana  = new principal();
			FragmentManager fragmentManager = getSupportFragmentManager();
    		FragmentTransaction ft = fragmentManager.beginTransaction();
    		String tabchange = (ContactUtils.TAB_VENTANA).toString();
    	
    		if(tabchange.equals("1")){ ventana = new principal(); Log.d("Ventana Principal", tabchange); }
    		if(tabchange.equals("2")){ ventana = new search_alumno(); Log.d("Ventana buscar Alumno", tabchange);}
    		if(tabchange.equals("3")){ ventana = new search_docente(); Log.d("Ventana buscar Docente", tabchange);}
    		if(tabchange.equals("4")){ ventana = new search_curso(); Log.d("Ventana buscar Curso", tabchange);}
    		
    		
    		ft.replace(R.id.principal, ventana,"TAG_PRINCIPAL");
    		ft.commit();
    		//FIN VIEW VENTANA
        
        
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
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), 
						R.layout.deawer_list_item, getResources().getStringArray(R.array.menus));

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
						String[] menuItems = getResources().getStringArray(R.array.menus);
					
						// Currently selected river
						mTitle = menuItems[position-1];

						Log.d("Menu Item: ", "> " + position);
						
						// Getting reference to the FragmentManager
						FragmentManager fragmentManager = getSupportFragmentManager();

						// Creating a fragment transaction
						FragmentTransaction ft = fragmentManager.beginTransaction();

						Fragment ventana = null;
						
						
						switch (position) {
						case 1:
							ventana = new principal();
							break;
						case 2:
							ventana = new search_alumno();
							break;
						case 3:
							ventana = new search_docente();
							break;
						case 4:
							ventana = new search_curso();
							break;
						case 9: 
							AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivityMensajes.this);
							 
					        // Setting Dialog Title
					        alertDialog.setTitle("Cerrar sesión?...");
					 
					        // Setting Dialog Message
					        alertDialog.setMessage("¿Esta seguro que desea cerrar sesión?");
					 
					        // Setting Icon to Dialog
					       // alertDialog.setIcon(R.drawable.delete);
					 
					        // Setting Positive "Yes" Button
					        alertDialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
					            public void onClick(DialogInterface dialog,int which) {
					 
					            // Write your code here to invoke YES event
					          //  Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
					            //	MainActivityPrincipal.this.finish();
					            	
					            //finish();
					            	getApplicationContext().getSharedPreferences("GEON", 0).edit().clear().commit();
					            	Intent i = new Intent(getBaseContext(),
					            			MainActivity.class);
					            	
					            
				            	startActivity(i);
				            	finish();
					            }
					        });
					 
					        // Setting Negative "NO" Button
					        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
					            public void onClick(DialogInterface dialog, int which) {
					            // Write your code here to invoke NO event
					           // Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
					            dialog.cancel();
					            }
					        });
					 
					        // Showing Alert Message
					        alertDialog.show();

						default:
							ventana = new principal();
							break;
						}
						
						
						
						
						
						// Adding a fragment to the fragment transaction
						ft.replace(R.id.principal, ventana,"TAG_PRINCIPAL");
						ft.addToBackStack(null);
						mDrawerList.setItemChecked(position, true);
						// Committing the transaction
						ft.commit();

						// Closing the drawer
						mDrawerLayout.closeDrawer(mDrawerList);

					}
				});
        
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
		
		if(OnBackPress!=null){
			
			OnBackPress.doBack();
			
		}else{
			//super.onBackPressed();
			
		}
		
		
		
	}
	public void setOnBackPressListener(OnBackPress listen){
			this.OnBackPress = listen;
		
	}
}
