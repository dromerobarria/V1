package com.geon.docente;


import com.geon.InspectoresAndroid.MainActivityDocente;
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


public class ficha extends Fragment {
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		
		FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        fm.beginTransaction();
        Fragment fragTwo = new Ver_Ficha();
        Bundle arguments = new Bundle();
       
        
        arguments.putBoolean("shouldYouCreateAChildFragment", false);
    
        fragTwo.setArguments(arguments);
       ft.add(R.id.content_ficha, fragTwo);
        ft.commit();
        return inflater.inflate(R.layout.ficha_docente_p, container, false);
    }

	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		
		
		super.onActivityCreated(savedInstanceState);
	}
	   
	



}
