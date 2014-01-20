package com.geon.alumno;

import java.util.ArrayList;

import com.geon.InspectoresAndroid.R;
import com.geon.alumno.AdaptadorAnotacionInterna.ViewHolder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdaptadorAnotacionLibro extends BaseAdapter {

	private Context contexto;
	//private ArrayList<AnotacionInterna> dataOriginal;
	private ArrayList<AnotacionLibro> data;

	
	
	protected LinearLayout.LayoutParams mContactPicLayoutParams;

	public AdaptadorAnotacionLibro(Context applicationContext,
			ArrayList<AnotacionLibro> sistemas) {
		this.data = sistemas;
		this.contexto = applicationContext;
		//this.dataOriginal=sistemas;
	}
	 
   
    
    

	public int getCount() {

		return data.size();
	}

	
	public AnotacionLibro getItem(int position) {

		return data.get(position);
	}


	public long getItemId(int position) {

		return position;
	}


	@SuppressLint("DefaultLocale")
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		View vista = convertView;
		if (convertView == null || !(vista.getTag() instanceof ViewHolder)) {
			LayoutInflater inflater = (LayoutInflater) contexto
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			
			
			vista = inflater.inflate(R.layout.list_item_anotacion_libro, parent, false); 

			holder = new ViewHolder();
			holder.fecha = (TextView) vista.findViewById(R.id.fecha_item_libro);
			holder.mensaje = (TextView) vista.findViewById(R.id.registro_item_libro);
			
			
			vista.setTag(holder);
		} else {
			holder = (ViewHolder) vista.getTag();
		}
		
		String fecha = data.get(position).getDia() + " " + data.get(position).getMes().toUpperCase();;
		int r = data.get(position).getRed();
		int g = data.get(position).getGreen();
		int b = data.get(position).getBlue();
		holder.fecha.setText(fecha);
		holder.mensaje.setText(data.get(position).getMensaje());
		holder.fecha.setBackgroundColor(Color.rgb(r, g, b));

		return vista;
	}
	
	
	

	public class ViewHolder {
		TextView fecha;
		TextView mensaje;
		
		//LoadImageAlumno task;
		int position;
	}
	

}