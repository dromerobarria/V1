package com.geon.alumno;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.geon.InspectoresAndroid.MainActivityAlumno;
import com.geon.InspectoresAndroid.R;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.AsyncTask.Status;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


import com.geon.comunicaciones.*;
import com.geon.login.login;

public class Ver_Atraso  extends Fragment{
	
	AdaptadorAtraso adapter;
	
	private LinearLayout progresLayout;
	private JSONArray contacts = null;
	
	private ArrayList<Atraso> Atrasos;
	
	private ListView listaAtrasos;
	SharedPreferences sharedpreferences;
	String url_final;
	ProgressDialog myPd_ring;
	private GetContacts getAtrasos;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.atrasos,
				container, false);
		return view;
		
		
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
		


		Bundle extra = getActivity().getIntent().getExtras();
		String rut = (extra.getString(ContactUtils.TAG_ID_RUT));
		sharedpreferences = getActivity().getSharedPreferences(login.MyPREFERENCES,
				Context.MODE_PRIVATE);
		
		url_final = getString(R.string.url_atrasos)+"?BCT5140U="+sharedpreferences.getString("nombre_colegio","")+"&T034K3NOX="+
				sharedpreferences.getString("codigo_login","")+"&PEST1400O="+rut;	
		
		
		myPd_ring  = ProgressDialog.show(getActivity(), "Cargando Atrasos",
			    "Espere por favor...", true);
		myPd_ring.show();
		
		RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
		
		Atrasos = new ArrayList<Atraso>();

		listaAtrasos = (ListView) getActivity().findViewById(R.id.listaAtrasos);
	
		
		listaAtrasos.setOnItemClickListener(new OnItemClickListener() {

	
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				
				
				//Toast.makeText(getActivity().getBaseContext(), ContactUtils.TAG_ANOTACION, Toast.LENGTH_SHORT).show();
				
	                
			}
		});
		
		/*if (TAG_CACHE) {
			
			
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
							
						        
								myPd_ring.dismiss();
							
								
							}
						}
					}, new Response.ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError error) {
							// TODO Auto-generated method stub

						}
					});

			queue.add(jsObjRequest);

			
		} else {*/
			 getAtrasos = new GetContacts();
			 getAtrasos.execute();
			 myPd_ring.dismiss();
			
		
		
	}
	
	public void parceJsonResponse(JSONObject jsonStr) {
		try {
			

			// Getting JSON Array node
			contacts = jsonStr.getJSONArray(ContactUtils.TAG_ATRASO);

			// looping through All Contacts
			for (int i = 0; i < contacts.length(); i++) {
				JSONObject c = contacts.getJSONObject(i);

				int id = Integer.parseInt(c.getString(ContactUtils.TAG_ID_ATRASO));
				
				String mes = c.getString(ContactUtils.TAG_MES);
				String dia = c.getString(ContactUtils.TAG_DIA);
				String hora = c.getString(ContactUtils.TAG_HORA);
				int red = Integer.parseInt(c.getString(ContactUtils.TAG_RED));
				int blue = Integer.parseInt(c.getString(ContactUtils.TAG_BLUE));
				int green = Integer.parseInt(c.getString(ContactUtils.TAG_GREEN));
				Atraso atraso_obj = new Atraso(id,dia,mes,red,blue,green,hora);
				
				
				// adding contact to contact list
				Atrasos.add(atraso_obj);
				

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
		//	progresLayout.setVisibility(View.GONE);
			
			getActivity().setProgressBarIndeterminateVisibility(false);
			adapter = new AdaptadorAtraso(
					getActivity().getApplicationContext(), Atrasos);

			listaAtrasos.setAdapter(adapter);
			myPd_ring.dismiss();
			
		}

	}
	
	public void parceJsonResponse(String jsonStr) {
		try {
			JSONObject jsonObj = new JSONObject(jsonStr);

			// Getting JSON Array node
			contacts = jsonObj.getJSONArray(ContactUtils.TAG_ATRASO);

			// looping through All Contacts
			for (int i = 0; i < contacts.length(); i++) {
				JSONObject c = contacts.getJSONObject(i);

int id = Integer.parseInt(c.getString(ContactUtils.TAG_ID_ATRASO));
				
				String mes = c.getString(ContactUtils.TAG_MES);
				String dia = c.getString(ContactUtils.TAG_DIA);
				String hora = c.getString(ContactUtils.TAG_HORA);
				int red = Integer.parseInt(c.getString(ContactUtils.TAG_RED));
				int blue = Integer.parseInt(c.getString(ContactUtils.TAG_BLUE));
				int green = Integer.parseInt(c.getString(ContactUtils.TAG_GREEN));
				Atraso atraso_obj = new Atraso(id,dia,mes,red,blue,green,hora);
				
				
				// adding contact to contact list
				Atrasos.add(atraso_obj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void doBack() {
		myPd_ring.dismiss();
	//	activa = false;
		// TODO Auto-generated method stub
		if(getAtrasos.getStatus()==Status.FINISHED){
			
			
		}
		
		if(getAtrasos.getStatus()==Status.RUNNING){
			
			getAtrasos.cancel(true);
			
		}
		
		if(getAtrasos.getStatus()==Status.PENDING){
			
			getAtrasos.cancel(true);
		}
		
		
	}
}
