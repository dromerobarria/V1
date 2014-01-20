package com.geon.docente;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.geon.InspectoresAndroid.R;
import com.geon.comunicaciones.Encriptador;
import com.geon.comunicaciones.ServiceHandle;
import com.geon.docente.ContactUtils;
import com.geon.login.login;
import com.squareup.picasso.Picasso;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Ver_Ficha extends Fragment{
	ProgressDialog myPd_ring;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.ficha_docente,
				container, false);
		return view;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		
		
		Bundle extra = getActivity().getIntent().getExtras();
		
		myPd_ring  = ProgressDialog.show(getActivity(), "Cargando Ficha",
			    "Espere por favor...", true);
		myPd_ring.show();
		

		// Creating service handler class instance
		TextView nombre = ((TextView) getActivity().findViewById(R.id.nombre_docente));

		TextView rut = ((TextView) getActivity().findViewById(R.id.rut_docente));
		TextView mail = ((TextView) getActivity().findViewById(R.id.email_docente));
		TextView area = ((TextView) getActivity().findViewById(R.id.area));
		TextView titulo = ((TextView) getActivity().findViewById(R.id.titulo));
		TextView ciclo = ((TextView) getActivity().findViewById(R.id.ciclo));
		TextView celular = ((TextView) getActivity().findViewById(R.id.celular_docente));
		TextView telefono = ((TextView) getActivity().findViewById(R.id.telefono_docente));
		TextView cargo = ((TextView) getActivity().findViewById(R.id.cargo));

	//	TextView description = ((TextView) getActivity().findViewById(R.id.rut));
		ImageView imgAvatar =  (ImageView) getActivity().findViewById(R.id.avatar_docente);
		
		String urlAvatar ;

	
		nombre.setText("Elizabeth Oredenes");
		//rut.setText(extra.getInt(ContactUtils.TAG_ID_RUT));
		area.setText("pastoral");		
	//	urlAvatar = extra.getString(ContactUtils.URL_PHOTO);
		//mail.setText(extra.getString(ContactUtils.TAG_EMAIL));
		
		
		
		SharedPreferences sharedpreferences =  getActivity().getSharedPreferences(login.MyPREFERENCES,
				Context.MODE_PRIVATE);
		
		String url_final = getString(R.string.url_detalle_docente)+"?BCT5140U="+sharedpreferences.getString("nombre_colegio","")+"&T034K3NOX="+
				sharedpreferences.getString("codigo_login","")+"&PEST1400O="+"11722399";	
		
		String url2 = "https://www.geon.cl/inspectores/do_detalles.php?BCT5140U=hsse&T034K3NOX=f7f61fdfe35aa40bd7b1079e2678a0f4fcb68347&PEST1400O=11722399";
		
		//Picasso.with(getActivity().getApplicationContext()).load(urlAvatar).fit().into(imgAvatar);
		//super.onActivityCreated(savedInstanceState);

		ServiceHandle sh2 = new ServiceHandle();
		// Making a request to url and getting response
		//String jsonSTR2 = sh2.makeServiceCall(url2, ServiceHandle.GET);
		//String jsonStr = sh.makeServiceCall(url_final, ServiceHandle.GET);
		//Log.d("Response: ", "> " + jsonSTR2);
/*
		if (jsonStr != null) {
			try {
				JSONObject jsonObj = new JSONObject(jsonStr);

				// Getting JSON Array node
				JSONArray arreglo = jsonObj.getJSONArray(ContactUtils.TAG_DETALLES);

				// looping through All Contacts
				for (int i = 0; i < arreglo.length(); i++) {
					JSONObject c = arreglo.getJSONObject(i);

					/*cargo.setText(c.getString(ContactUtils.TAG_CARGO));
					ciclo.setText(c.getString(ContactUtils.TAG_CICLO));
					titulo.setText(c.getString(ContactUtils.TAG_TITULO));
					celular.setText(c.getString(ContactUtils.TAG_CELULAR));
					telefono.setText(c.getString(ContactUtils.TAG_TELEFONO));*/
					
					 //myPd_ring.dismiss();
						/*
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}*/
	}
}


