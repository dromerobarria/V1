package com.geon.InspectoresAndroid;

import java.util.ArrayList;

import com.geon.alumno.search_alumno;
import com.geon.cursos.search_curso;
import com.geon.docente.search_docente;
import com.geon.mensajes.ver_mensajes;
import com.geon.InspectoresAndroid.principal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory; 
import android.widget.AdapterView; 
import android.widget.AdapterView.OnItemClickListener;



public class principal extends Fragment {
	
	GridView gridView; 
	ArrayList<ItemGridPrincipal> gridArray = new ArrayList<ItemGridPrincipal>(); 
	CustomGridViewAdapter customGridAdapter;

	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.principal,
				container, false);
		return view; //REVISAR
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		//set grid view item
		 gridView = (GridView) getActivity().findViewById(R.id.gridViewPrincipal);
		gridView.setAdapter(null);
		
		 
        Bitmap alumnosIcono = BitmapFactory.decodeResource(this.getResources(), R.drawable.squaregris);
        Bitmap docenteIcono = BitmapFactory.decodeResource(this.getResources(), R.drawable.squarerojo);
        Bitmap cursosIcono = BitmapFactory.decodeResource(this.getResources(), R.drawable.squareverde);
        Bitmap mensajesIcono = BitmapFactory.decodeResource(this.getResources(), R.drawable.squareazul);
        Bitmap calendarioIcono = BitmapFactory.decodeResource(this.getResources(), R.drawable.squareamarillo);
        Bitmap recordatoriosIcono = BitmapFactory.decodeResource(this.getResources(), R.drawable.squareceleste);
        
        gridArray.clear();
        gridArray.add(new ItemGridPrincipal(alumnosIcono,"Alumnos"));
        gridArray.add(new ItemGridPrincipal(docenteIcono,"Docentes"));
        gridArray.add(new ItemGridPrincipal(cursosIcono,"Cursos"));
        gridArray.add(new ItemGridPrincipal(mensajesIcono,"Mensajes"));
        gridArray.add(new ItemGridPrincipal(calendarioIcono,"Calendario"));
        gridArray.add(new ItemGridPrincipal(recordatoriosIcono,"Recordatorios"));
        
        
       
    	  
      
    	   customGridAdapter = new CustomGridViewAdapter(getActivity(), R.layout.itemgrid_principal, gridArray);
           gridView.setAdapter(customGridAdapter);
       
      
           int count = gridView.getAdapter().getCount();
           Log.d("Count",""+count);
        
       
        
		
        
       
     
     
        
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
               // Toast.makeText(HelloGridView.this, "" + position, Toast.LENGTH_SHORT).show();
            	FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
               
                switch (position) {
				case 0:
				  search_alumno newFragment = new search_alumno();
				  transaction.replace(R.id.principal, newFragment);
			      break;

				case 1:
					  search_docente newFragment2 = new search_docente();
					  transaction.replace(R.id.principal, newFragment2);
				      break;
				case 2:
					  search_curso newFragment3 = new search_curso();
					  transaction.replace(R.id.principal, newFragment3);
				      break;
				case 3:
					  ver_mensajes newFragment4 = new ver_mensajes();
					  transaction.replace(R.id.principal, newFragment4);
				      break;
				      
				default:
					break;
				}
                
             
                
     	          
   	            
   	            
   	            transaction.addToBackStack(null);
   	            transaction.commit();
                
                
            }
        });
		

	}
	
}
	
	 
         
         

	
