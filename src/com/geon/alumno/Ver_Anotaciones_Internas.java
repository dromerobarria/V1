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

public class Ver_Anotaciones_Internas  extends Fragment{
	
	AdaptadorAnotacionInterna adapter;
	private ProgressBar pDialog;
	private LinearLayout progresLayout;
	private JSONArray contacts = null;
	
	private ArrayList<AnotacionInterna> Anotaciones;
	
	private ListView listaAnotaciones;
	SharedPreferences sharedpreferences;
	String url_final;
	ProgressDialog myPd_ring;
	private GetContacts getAnotaciones;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.anotaciones_internas,
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
		
		url_final = getString(R.string.url_anotaciones_internas)+"?BCT5140U="+sharedpreferences.getString("nombre_colegio","")+"&T034K3NOX="+
				sharedpreferences.getString("codigo_login","")+"&PEST1400O="+rut;	
		
		
		myPd_ring  = ProgressDialog.show(getActivity(), "Cargando Anotaciones internas",
			    "Espere por favor...", true);
		myPd_ring.show();
		
		RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
		
		Anotaciones = new ArrayList<AnotacionInterna>();

		listaAnotaciones = (ListView) getActivity().findViewById(R.id.listaAnotacionesInternas);
	
		
		listaAnotaciones.setOnItemClickListener(new OnItemClickListener() {

	
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				// getting values from selected ListItem

				
/*
				String name = ((TextView) view.findViewById(R.id.name))
						.getText().toString();

				String cost = ((TextView) view.findViewById(R.id.curso))
						.getText().toString();
				String description = ((TextView) view.findViewById(R.id.rut))
						.getText().toString();
				// Starting single contact activity
				
			
				FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
	    		FragmentTransaction ft = fragmentManager.beginTransaction();
	    		Fragment ventana = new Ver_Alumno();
	    		ft.replace(R.id.principal, ventana,"TAG_PRINCIPAL");
	    		ft.addToBackStack(null);
	    		ft.commit();
	    		
				
				
				Intent in = new Intent(getActivity().getApplicationContext(),
						MainActivityAlumno.class);
				in.putExtra(ContactUtils.TAG_NAME, name);
				in.putExtra(ContactUtils.TAG_CURSO, cost);
				in.putExtra(ContactUtils.TAG_ID_RUT, description);
				in.putExtra(ContactUtils.URL_PHOTO, ((Alumno) (listaContactos
						.getAdapter().getItem(arg2))).getUrl());
				startActivity(in);
				getActivity().finish();*/
				String fecha = ((TextView) view.findViewById(R.id.fecha_item))
						.getText().toString();

				String mensaje = ((TextView) view.findViewById(R.id.registro_item))
						.getText().toString();
				
			//	Toast.makeText(getActivity().getBaseContext(), ContactUtils.TAG_ANOTACION, Toast.LENGTH_SHORT).show();
				 final Dialog dialog = new Dialog(getActivity());
	                // Include dialog.xml file
	                dialog.setContentView(R.layout.detalle_anotacion_interna);
	                // Set dialog title
	                dialog.setTitle("Detalle anotación interna");
	 
	                // set values for custom dialog components - text, image and button
	                TextView fecha_detalle = (TextView) dialog.findViewById(R.id.fecha_anotacion_detalle);
	                TextView mensaje_detalle = (TextView) dialog.findViewById(R.id.mensaje_anotacion_detalle);
	                TextView registrador_detalle = (TextView) dialog.findViewById(R.id.registrador_anotacion_detalle);
	                fecha_detalle.setText(fecha);
	                //fecha_detalle.setBackgroundColor(rgb(AdapterView.ge));
	                mensaje_detalle.setText(mensaje);
	                String regi = ((AnotacionInterna)(listaAnotaciones).getAdapter().getItem(arg2)).getAnotador();
	                int red = ((AnotacionInterna)(listaAnotaciones).getAdapter().getItem(arg2)).getRed();
	                int blue = ((AnotacionInterna)(listaAnotaciones).getAdapter().getItem(arg2)).getBlue();
	                int green = ((AnotacionInterna)(listaAnotaciones).getAdapter().getItem(arg2)).getGreen();
	                fecha_detalle.setBackgroundColor(Color.rgb(red,green,blue));
	                registrador_detalle.setText("Por: "+regi);
	                
	                dialog.show();
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
			 getAnotaciones = new GetContacts();
			 getAnotaciones.execute();
			 myPd_ring.dismiss();
			
		
		
	}
	
	public void parceJsonResponse(JSONObject jsonStr) {
		try {
			

			// Getting JSON Array node
			contacts = jsonStr.getJSONArray(ContactUtils.TAG_ANOTACION_INTERNAS);

			// looping through All Contacts
			for (int i = 0; i < contacts.length(); i++) {
				JSONObject c = contacts.getJSONObject(i);

				int id = Integer.parseInt(c.getString(ContactUtils.TAG_ID_ANOTACION));
				String anotacion = c.getString(ContactUtils.TAG_ANOTACION);
				String mes = c.getString(ContactUtils.TAG_MES);
				String dia = c.getString(ContactUtils.TAG_DIA);
				String anotador = c.getString(ContactUtils.TAG_REGISTRADOR);
				int red = Integer.parseInt(c.getString(ContactUtils.TAG_RED));
				int blue = Integer.parseInt(c.getString(ContactUtils.TAG_BLUE));
				int green = Integer.parseInt(c.getString(ContactUtils.TAG_GREEN));
				AnotacionInterna anotacion_obj = new AnotacionInterna(anotacion, mes, dia, id, anotador,red,blue,green);
				
				
				// adding contact to contact list
				Anotaciones.add(anotacion_obj);
				

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
			adapter = new AdaptadorAnotacionInterna(
					getActivity().getApplicationContext(), Anotaciones);

			listaAnotaciones.setAdapter(adapter);
			myPd_ring.dismiss();
			
		}

	}
	
	public void parceJsonResponse(String jsonStr) {
		try {
			JSONObject jsonObj = new JSONObject(jsonStr);

			// Getting JSON Array node
			contacts = jsonObj.getJSONArray(ContactUtils.TAG_ANOTACION_INTERNAS);

			// looping through All Contacts
			for (int i = 0; i < contacts.length(); i++) {
				JSONObject c = contacts.getJSONObject(i);

				int id = Integer.parseInt(c.getString(ContactUtils.TAG_ID_ANOTACION));
				String anotacion = c.getString(ContactUtils.TAG_ANOTACION);
				String mes = c.getString(ContactUtils.TAG_MES);
				String dia = c.getString(ContactUtils.TAG_DIA);
				String anotador = c.getString(ContactUtils.TAG_REGISTRADOR);
				int red = Integer.parseInt(c.getString(ContactUtils.TAG_RED));
				int blue = Integer.parseInt(c.getString(ContactUtils.TAG_BLUE));
				int green = Integer.parseInt(c.getString(ContactUtils.TAG_GREEN));
				AnotacionInterna anotacion_obj = new AnotacionInterna(anotacion, mes, dia, id, anotador,red,blue,green);
				
				
				// adding contact to contact list
				Anotaciones.add(anotacion_obj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void doBack() {
		myPd_ring.dismiss();
	//	activa = false;
		// TODO Auto-generated method stub
		if(getAnotaciones.getStatus()==Status.FINISHED){
			
			
		}
		
		if(getAnotaciones.getStatus()==Status.RUNNING){
			
			getAnotaciones.cancel(true);
			
		}
		
		if(getAnotaciones.getStatus()==Status.PENDING){
			
			getAnotaciones.cancel(true);
		}
		
		
	}
}