package com.geon.login;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.geon.InspectoresAndroid.MainActivity;
import com.geon.InspectoresAndroid.MainActivityPrincipal;
import com.geon.InspectoresAndroid.R;
import com.geon.comunicaciones.Encriptador;
import com.geon.comunicaciones.ServiceHandle;

public class login extends FragmentActivity {

	

	// Create a new HttpClient and Post Header
	HttpClient httpclient = new DefaultHttpClient();
	public static int NO_OPTIONS = 0;
	private String SHAHash;
	private EditText userName, passWord;
	private String username, passwd;
	private String colegio;
	private String codigo ;
	private String token = "";
	String url_login;

	ProgressDialog myPd_ring;
	SharedPreferences sharedpreferences;

	public static final String name = "userKey";
	public static final String MyPREFERENCES = "GEON";

	@Override
	protected void onCreate(Bundle arg0) {
		url_login = getString(R.string.url_login_externo);
		// TODO Auto-generated method stub
		setContentView(R.layout.login);

		sharedpreferences = getSharedPreferences(MyPREFERENCES,
				Context.MODE_PRIVATE);

		if (sharedpreferences.contains(name)) {
			Intent i = new Intent(getBaseContext(), MainActivityPrincipal.class);

			startActivity(i);
			finish();
		}

		final Button button = (Button) findViewById(R.id.btn_entrar);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Perform action on click

				// TODO Auto-generated method stub

				

				InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

				new GetLogin().execute();
				// Envio datos para inicio session

			}

		});

		super.onCreate(arg0);
	}

	private class GetLogin extends AsyncTask<Void, Void, Void> {

		String perfil;
		String nombres;
		String email;
		String usuario;
		String clave;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog

			myPd_ring =new ProgressDialog(login.this);
			myPd_ring.setMessage("Espere por favor..");
			myPd_ring.setTitle("Validando");
			myPd_ring.setCancelable(false);
			myPd_ring.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance

			Encriptador encripta = new Encriptador();

			userName = (EditText) findViewById(R.id.text_user);
			passWord = (EditText) findViewById(R.id.text_pass);
			
			usuario = userName.getText().toString();
			clave = encripta.GeneraSHAHash(
					encripta.GeneraMD5Hash(passWord.getText().toString()))
					.toLowerCase();

			passwd = encripta.GeneraMD5Hash(passWord.getText().toString());
			
			colegio = getString(R.string.colegio);
			codigo = encripta.GeneraSHAHash(
					encripta.GeneraMD5Hash("geonappmovil")).toLowerCase();
			token = getGcmPreferences(getApplicationContext()).getString("registration_id", "0");
			
			
			

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
			nameValuePairs.add(new BasicNameValuePair("PEST1400O", usuario));
			nameValuePairs.add(new BasicNameValuePair("A15T8TPC", clave));
			nameValuePairs.add(new BasicNameValuePair("BCT5140U", colegio));
			nameValuePairs.add(new BasicNameValuePair("T034K3NOX", token));
			nameValuePairs.add(new BasicNameValuePair("codIdem", codigo));
			nameValuePairs.add(new BasicNameValuePair("DEVICE", "ANDROID"));

			ServiceHandle sh = new ServiceHandle();
			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url_login, ServiceHandle.GET,
					nameValuePairs);
			Log.d("Response: ", "> " + jsonStr);

			if (jsonStr != null) {
				try {
					JSONObject jsonObj = new JSONObject(jsonStr);

					// Getting JSON Array node
					JSONArray arreglo = jsonObj.getJSONArray("login");

					// looping through All Contacts
					for (int i = 0; i < arreglo.length(); i++) {
						JSONObject c = arreglo.getJSONObject(i);

						perfil = c.getString("perfil");
						nombres = c.getString("Nombre");
						email = c.getString("email");
						Log.d("Perfil : ", "> " + perfil);

					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			myPd_ring.dismiss();

			if (perfil.compareTo("NoDocente") == 0) {

				Editor editor = sharedpreferences.edit();
				String u = username;
				editor.putString(name, u);
				editor.putString("nombre_colegio", colegio);
				editor.putString("token", token);
				editor.putString("codigo_login", codigo);
				editor.putString("nombre_usuario",nombres);
				editor.putString("email_usuario",email);
				editor.putString("rut_usuario",usuario);
				editor.putString("pass_usuario",passwd);
				editor.commit();

				Intent i = new Intent(getBaseContext(),
						MainActivityPrincipal.class);
				startActivity(i);
				finish();

			} else {
				Toast.makeText(getApplicationContext(), perfil,
						Toast.LENGTH_SHORT).show();

			}
		}

	}
	
	 private SharedPreferences getGcmPreferences(Context context) {

	        return getSharedPreferences(MainActivity.class.getSimpleName(),
	                Context.MODE_PRIVATE);
	    }

}
