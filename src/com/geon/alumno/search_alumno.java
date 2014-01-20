package com.geon.alumno;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;

import com.geon.InspectoresAndroid.MainActivity;
import com.geon.InspectoresAndroid.MainActivityAlumno;
import com.geon.InspectoresAndroid.MainActivityPrincipal.OnBackPress;
import com.geon.InspectoresAndroid.R;
import com.geon.InspectoresAndroid.principal;
import com.geon.comunicaciones.ServiceHandle;
import com.geon.login.login;

public class search_alumno extends Fragment implements OnBackPress{
	
	
	ProgressDialog myPd_ring;
	private LinearLayout progresLayout;
	private JSONArray contacts = null;
	private ArrayList<Alumno> contactList;
	private ListView listaContactos;
	private static boolean TAG_CACHE = true;
	private GetContacts getAlumnos;
	private boolean activa = true;
	EditText inputSearch;
	AdaptadorAlumno adapter;
	SharedPreferences sharedpreferences;
	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	String url_final;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.search_alumno,
				container, false);
		return view;
		
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		sharedpreferences = getActivity().getSharedPreferences(login.MyPREFERENCES,
				Context.MODE_PRIVATE);
		
		url_final = getString(R.string.url_alumnos_externo)+"?BCT5140U="+sharedpreferences.getString("nombre_colegio","")+"&T034K3NOX="+
				sharedpreferences.getString("codigo_login","");
		// Creating service handler class instance
	
		//nameValuePairs.add(new BasicNameValuePair("PEST1400O", usuario));
		//nameValuePairs.add(new BasicNameValuePair("A15T8TPC", clave));
		
		nameValuePairs.add(new BasicNameValuePair("BCT5140U", sharedpreferences.getString("nombre_colegio","")));
		nameValuePairs.add(new BasicNameValuePair("T034K3NOX", sharedpreferences.getString("codigo_login","")));
		inputSearch = (EditText)getActivity().findViewById(R.id.inputSearch);
		
		myPd_ring  = ProgressDialog.show(getActivity(), "Cargando Alumnos",
			    "Espere por favor...", true);
		myPd_ring.show();
		
		
		RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
		
		contactList = new ArrayList<Alumno>();

		listaContactos = (ListView) getActivity().findViewById(R.id.listado_alumno);
		//progresLayout = (LinearLayout) findViewById(R.id.layoutProgress);

		listaContactos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				// getting values from selected ListItem

				

				String name = ((TextView) view.findViewById(R.id.name))
						.getText().toString();

				String cost = ((TextView) view.findViewById(R.id.curso))
						.getText().toString();
				String description = ((TextView) view.findViewById(R.id.rut))
						.getText().toString();
				// Starting single contact activity
				
			
				/*FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
	    		FragmentTransaction ft = fragmentManager.beginTransaction();
	    		Fragment ventana = new Ver_Alumno();
	    		ft.replace(R.id.principal, ventana,"TAG_PRINCIPAL");
	    		ft.addToBackStack(null);
	    		ft.commit();
	    		*/
				
				
				Intent in = new Intent(getActivity().getApplicationContext(),
						MainActivityAlumno.class);
				in.putExtra(ContactUtils.TAG_NAME, name);
				in.putExtra(ContactUtils.TAG_CURSO, cost);
				in.putExtra(ContactUtils.TAG_ID_RUT, description);
				in.putExtra(ContactUtils.URL_PHOTO, ((Alumno) (listaContactos
						.getAdapter().getItem(arg2))).getUrl());
				startActivity(in);
				getActivity().finish();
				
				

			}
		});
		
		if (TAG_CACHE) {
			
			
			//sh.makeServiceCall(getString(R.string.url_alumnos_externo), ServiceHandle.GET,
				//	nameValuePairs);
			
			//progresLayout.setVisibility(View.VISIBLE);
			JsonObjectRequest jsObjRequest = new JsonObjectRequest(
					Request.Method.GET, url_final, null,
					new Response.Listener<JSONObject>() {

						@Override
						public void onResponse(JSONObject response) {
							// TODO Auto-generated method stub
							// txtDisplay.setText("Response => "+response.toString());
							parceJsonResponse(response);
							//progresLayout.setVisibility(View.GONE);
							//getActivity().setProgressBarIndeterminateVisibility(false);
							if(activa==true){
							
							try {
								adapter = new AdaptadorAlumno(
										getActivity().getApplicationContext(), contactList);
								listaContactos.setAdapter(adapter);
								/**
						         * Enabling Search Filter
						         * */
						        inputSearch.addTextChangedListener(new TextWatcher() {
									
									@Override
						            public void onTextChanged(CharSequence s, int start, int before, int count) {
						                System.out.println("Text ["+s+"] - Start ["+start+"] - Before ["+before+"] - Count ["+count+"]");
						                if (count < before) {
						                        // We're deleting char so we need to reset the adapter data
						                	adapter.resetData();
						                }
						                        
						                adapter.getFilter().filter(s.toString());
						                
						        }
									
									
						        
						        @Override
						        public void beforeTextChanged(CharSequence s, int start, int count,
						                        int after) {
						                
						        }
						        
						        @Override
						        public void afterTextChanged(Editable s) {
						        }
						});
						        inputSearch.setOnKeyListener(new View.OnKeyListener() {
						        	
						            @Override
						            public boolean onKey(View view, int i, KeyEvent keyEvent) {
						                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
						                        (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER))
						                {
						                	
						                }
						                // Returning false allows other listeners to react to the press.
						                return false;
						            }

									
						        });
								myPd_ring.dismiss();
							} catch (Exception e) {
								// TODO: handle exception
							}	
								
							}
						}
					}, new Response.ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError error) {
							// TODO Auto-generated method stub

						}
					});

			queue.add(jsObjRequest);

			
		} else {
			 getAlumnos = new GetContacts();
			getAlumnos.execute();
			
		}

		
		
	}
	
	public void parceJsonResponse(JSONObject jsonStr) {
		try {
			

			// Getting JSON Array node
			contacts = jsonStr.getJSONArray(ContactUtils.TAG_CONTACTS);

			// looping through All Contacts
			for (int i = 0; i < contacts.length(); i++) {
				JSONObject c = contacts.getJSONObject(i);

				String id = c.getString(ContactUtils.TAG_ID_RUT);
				String name = c.getString(ContactUtils.TAG_NAME);
				String nombreLargo = c
						.getString(ContactUtils.TAG_CURSO);
				String urlPhoto = ContactUtils.URL_PHOTO + id;
				//Log.d("url", urlPhoto);

				// String address =
				// c.getString(ContactUtils.TAG_ADDRESS);
				// String gender = c.getString(ContactUtils.TAG_GENDER);

				

				Alumno contact = new Alumno(urlPhoto, name, id,
						nombreLargo);

				// adding contact to contact list
				contactList.add(contact);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			//progresLayout.setVisibility(View.VISIBLE);
			
			
			myPd_ring.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			
			
		//	nameValuePairs.add(new BasicNameValuePair("codIdem", codigo));

			ServiceHandle sh = new ServiceHandle();
			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url_final, ServiceHandle.GET);
			Log.d("Response: ", "> " + jsonStr);
			
		

			if (jsonStr != null) {
				parceJsonResponse(jsonStr);
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			progresLayout.setVisibility(View.GONE);
			myPd_ring.dismiss();
			getActivity().setProgressBarIndeterminateVisibility(false);
			adapter = new AdaptadorAlumno(
					getActivity().getApplicationContext(), contactList);

			listaContactos.setAdapter(adapter);
			
		}

	}
	
	public void parceJsonResponse(String jsonStr) {
		try {
			JSONObject jsonObj = new JSONObject(jsonStr);

			// Getting JSON Array node
			contacts = jsonObj.getJSONArray(ContactUtils.TAG_CONTACTS);

			// looping through All Contacts
			for (int i = 0; i < contacts.length(); i++) {
				JSONObject c = contacts.getJSONObject(i);

				String id = c.getString(ContactUtils.TAG_ID_RUT);
				String name = c.getString(ContactUtils.TAG_NAME);
				String nombreLargo = c
						.getString(ContactUtils.TAG_CURSO);
				String urlPhoto = ContactUtils.URL_PHOTO + id;
				Log.d("url", urlPhoto);

				// String address =
				// c.getString(ContactUtils.TAG_ADDRESS);
				// String gender = c.getString(ContactUtils.TAG_GENDER);

				

				Alumno contact = new Alumno(urlPhoto, name, id,
						nombreLargo);

				// adding contact to contact list
				contactList.add(contact);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doBack() {
		myPd_ring.dismiss();
		activa = false;
		// TODO Auto-generated method stub
		if(getAlumnos.getStatus()==Status.FINISHED){
			
			
		}
		
		if(getAlumnos.getStatus()==Status.RUNNING){
			
			getAlumnos.cancel(true);
			
		}
		
		if(getAlumnos.getStatus()==Status.PENDING){
			
			getAlumnos.cancel(true);
		}
		
		
	}
}