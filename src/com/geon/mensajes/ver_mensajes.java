package com.geon.mensajes;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.AsyncTask.Status;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.geon.InspectoresAndroid.MainActivityAlumno;
import com.geon.InspectoresAndroid.R;
import com.geon.InspectoresAndroid.MainActivityPrincipal.OnBackPress;

import com.geon.alumno.AdaptadorAnotacionInterna;
import com.geon.alumno.Alumno;
import com.geon.alumno.AnotacionInterna;
import com.geon.alumno.ContactUtils;

import com.geon.comunicaciones.ServiceHandle;
import com.geon.docente.AdaptadorDocente;
import com.geon.login.login;

public class ver_mensajes  extends Fragment implements OnBackPress{
	
	AdaptadorMensajes adapter;
	private GetMensajes getMensaje;
	ProgressDialog myPd_ring;
	private JSONArray contacts = null;
	private ArrayList<Mensaje> contactList;
	private ListView listaMensajes;
	private static boolean TAG_CACHE = true;
	private boolean activa = true;
	String url_final;
	SharedPreferences sharedpreferences;
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.ver_mensajes,
				container, false);
		return view;
  }
	
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Bundle extra = getActivity().getIntent().getExtras();
		sharedpreferences = getActivity().getSharedPreferences(login.MyPREFERENCES,
				Context.MODE_PRIVATE);


	
		//https://www.geon.cl/appmovil/email.php?PEST1400O=9318731&BCT5140U=hsse&T034K3NOX=f7f61fdfe35aa40bd7b1079e2678a0f4fcb68347
		
		url_final = getString(R.string.url_mensajes)+"?BCT5140U="+sharedpreferences.getString("nombre_colegio","")+"&T034K3NOX="+
				sharedpreferences.getString("codigo_login","")+"&PEST1400O="+sharedpreferences.getString("rut_usuario","");
		
		
		myPd_ring  = ProgressDialog.show(getActivity(), "Cargando Mensajes",
			    "Espere por favor...", true);
		myPd_ring.show();
		
		RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
		
		contactList = new ArrayList<Mensaje>();

		listaMensajes = (ListView) getActivity().findViewById(R.id.listado_mensajes);
		
		
		listaMensajes.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				// getting values from selected ListItem


				String name = ((TextView) view.findViewById(R.id.mensaje_name))
						.getText().toString();

				String para = ((TextView) view.findViewById(R.id.mensaje_rut))
						.getText().toString();
				
				
				Bundle parametro = new Bundle();
				parametro.putString("para",para);
				FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
	    		FragmentTransaction ft = fragmentManager.beginTransaction();
	    		Fragment ventana = new Nuevo_Mensaje();
	    		ventana.setArguments(parametro);
	    		ft.replace(R.id.principal, ventana,"TAG_PRINCIPAL");
	    		//ft.addToBackStack(null);
	    		ft.commit();
	    		
				
				

			}
		});
		
		
		 getMensaje = new GetMensajes();
		 getMensaje.execute();
		 myPd_ring.dismiss();
		
	}

	

	private class GetMensajes extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			myPd_ring.show();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			ServiceHandle sh = new ServiceHandle();
			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url_final, ServiceHandle.GET);
			Log.d("Response Email: ", "> " + jsonStr);
			
		

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
			getActivity().setProgressBarIndeterminateVisibility(false);
			adapter = new AdaptadorMensajes(
					getActivity().getApplicationContext(), contactList);

			listaMensajes.setAdapter(adapter);
			myPd_ring.dismiss();			
		}

		
		public void parceJsonResponse(String jsonStr) {
			try {
				JSONObject jsonObj = new JSONObject(jsonStr);

				// Getting JSON Array node
				contacts = jsonObj.getJSONArray("email");

				// looping through All Contacts
				for (int i = 0; i < contacts.length(); i++) {
					JSONObject c = contacts.getJSONObject(i);
					int id = Integer.parseInt(c.getString(ContactUtils.TAG_ID_ANOTACION));
					String nombre = c.getString("Nombre");
					String asunto = c.getString("asunto");
					String rut_ = c.getString("id");
					String urlPhoto = ContactUtils.URL_PHOTO + rut_;
					
					
					Mensaje obj = new Mensaje(urlPhoto, rut_, nombre, asunto);
					contactList.add(obj); 
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	
	@Override
	public void doBack() {
		// TODO Auto-generated method stub
		if(getMensaje.getStatus()==Status.FINISHED){
			
		}
		
		if(getMensaje.getStatus()==Status.RUNNING){
			
			getMensaje.cancel(true);
			
		}
		
		if(getMensaje.getStatus()==Status.PENDING){
			
			getMensaje.cancel(true);
		}
		
		
	}

}
