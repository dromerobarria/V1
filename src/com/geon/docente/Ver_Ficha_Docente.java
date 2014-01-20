package com.geon.docente;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.AsyncTask.Status;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.geon.InspectoresAndroid.R;
import com.geon.docente.ContactUtils;
import com.geon.comunicaciones.ServiceHandle;
import com.geon.login.login;
import com.squareup.picasso.Picasso;

public class Ver_Ficha_Docente extends Fragment {
	ProgressDialog myPd_ring;
	String url_final;
	private GetDatos getDatos;
	

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
	String rut2 = (extra.getString(ContactUtils.TAG_ID_RUT));
	
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

	
	nombre.setText(extra.getString(ContactUtils.TAG_NAME));
	rut.setText(rut2);
	area.setText(extra.getString(ContactUtils.TAG_AREA));	
	urlAvatar = extra.getString(ContactUtils.URL_PHOTO);
	mail.setText(extra.getString(ContactUtils.TAG_EMAIL));
	
	
	
	SharedPreferences sharedpreferences =  getActivity().getSharedPreferences(login.MyPREFERENCES,
			Context.MODE_PRIVATE);
	
	url_final = getString(R.string.url_detalle_docente)+"?BCT5140U="+sharedpreferences.getString("nombre_colegio","")+"&T034K3NOX="+
			sharedpreferences.getString("codigo_login","")+"&PEST1400O="+rut2;	
	
	
	Picasso.with(getActivity().getApplicationContext()).load(urlAvatar).fit().into(imgAvatar);
	//super.onActivityCreated(savedInstanceState);
	
	getDatos = new GetDatos();
	getDatos.execute();

}



private class GetDatos extends AsyncTask<Void, Void, Void> {

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
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
		   //parceJsonResponse(jsonStr);
		} else {
			Log.e("ServiceHandler", "Couldn't get any data from the url");
		}

		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
	//	progresLayout.setVisibility(View.GONE);
		myPd_ring.dismiss();
		
		
	}



public void parceJsonResponse(String jsonStr) {
	try {
		JSONObject jsonObj = new JSONObject(jsonStr);
	} catch (JSONException e) {
		e.printStackTrace();
	}
}


}}





