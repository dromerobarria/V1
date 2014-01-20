package com.geon.mensajes;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.geon.InspectoresAndroid.R;
import com.squareup.picasso.Picasso;



public class AdaptadorMensajes extends BaseAdapter {
	
	private Context contexto;
	private static final int IMAGE_HEIGHT = 40;
	private static final int IMAGE_WIDTH = 40;
	//private ArrayList<AnotacionInterna> dataOriginal;
	private ArrayList<Mensaje> data;

	
	
	protected LinearLayout.LayoutParams mContactPicLayoutParams;

	public AdaptadorMensajes(Context applicationContext,
			ArrayList<Mensaje> sistemas) {
		this.data = sistemas;
		this.contexto = applicationContext;
		//this.dataOriginal=sistemas;
	}
	 
   
    
    

	public int getCount() {

		return data.size();
	}

	
	public Mensaje getItem(int position) {

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

			
			
			vista = inflater.inflate(R.layout.list_item_mensajes, parent, false); 

			holder = new ViewHolder();
			holder.nombre = (TextView) vista.findViewById(R.id.mensaje_name);
			holder.asunto = (TextView) vista.findViewById(R.id.mensaje_asunto);
			holder.avatar = (ImageView) vista.findViewById(R.id.avatar_de);
			holder.rut = (TextView) vista.findViewById(R.id.mensaje_rut);
			vista.setTag(holder);
		} else {
			holder = (ViewHolder) vista.getTag();
		}
		
	
		int rutAlumno = Integer.valueOf(data.get(position).getRut());
		
		
	    Picasso.with(contexto)
	            .load(data.get(position).getUrl())
	            .placeholder(R.drawable.ic_launcher)
	            .resize(IMAGE_HEIGHT, IMAGE_WIDTH).centerCrop()
	            .into(holder.avatar);                 

		holder.nombre.setText(data.get(position).getNombre());
		holder.rut.setText(data.get(position).getRut());
		holder.asunto.setText(data.get(position).getAsunto());
	
		
		return vista;
	}
	
	
	

	public class ViewHolder {
		TextView nombre;
		TextView rut;
		TextView asunto;
		ImageView avatar;
		//LoadImageAlumno task;
		int position;
	}
	
	
}
