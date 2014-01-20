package com.geon.alumno;


import com.geon.InspectoresAndroid.R;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Ver_Alumno extends Fragment {
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		
		FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        fm.beginTransaction();
        Fragment fragTwo = new Ver_Anotaciones_Internas();
        Bundle arguments = new Bundle();
       
        
        arguments.putBoolean("shouldYouCreateAChildFragment", false);
    
        fragTwo.setArguments(arguments);
        ft.add(R.id.content_alumno, fragTwo);
        ft.commit();
        return inflater.inflate(R.layout.ver_alumno, container, false);
    }

	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TextView name = ((TextView) getActivity().findViewById(R.id.nombre_alumno));

		TextView cost = ((TextView) getActivity().findViewById(R.id.nombre_curso));

	//	TextView description = ((TextView) getActivity().findViewById(R.id.rut));
		ImageView imgAvatar =  (ImageView) getActivity().findViewById(R.id.avatar_alumno);
		
		String urlAvatar ;

		Bundle extra = getActivity().getIntent().getExtras();
		name.setText(extra.getString(ContactUtils.TAG_NAME));
		cost.setText(extra.getString(ContactUtils.TAG_CURSO));		
		urlAvatar = extra.getString(ContactUtils.URL_PHOTO);
		
		
		Picasso.with(getActivity().getApplicationContext()).load(urlAvatar).fit().into(imgAvatar);
		super.onActivityCreated(savedInstanceState);
	}
	   
	

}