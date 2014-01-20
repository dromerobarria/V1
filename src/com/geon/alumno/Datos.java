package com.geon.alumno;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Bundle;


import com.geon.InspectoresAndroid.R;

public class Datos extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.datos_alumno,
				container, false);
		return view;
		
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		TextView nombre_alumno = ((TextView) getActivity().findViewById(R.id.nombre_alumno));

		TextView nombre_curso = ((TextView) getActivity().findViewById(R.id.nombre_curso));

		//ImageView avatar_alumno = ((ImageView) findViewById(R.id.rut));

		//Bundle extra = getActivity().getIntent().getExtras();
		nombre_alumno.setText(("Juan Perez"));
		nombre_curso.setText(("1 MEDIO A"));
		//avatar_alumno.setText(extra.getString(DatosJsonAlumno.TAG_PHONE_MOBILE));
	}


}
