package com.geon.alumno;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.geon.InspectoresAndroid.R;



public class AdaptadorAnotacionInterna extends BaseAdapter {
	
	private Context contexto;
	//private ArrayList<AnotacionInterna> dataOriginal;
	private ArrayList<AnotacionInterna> data;

	
	
	protected LinearLayout.LayoutParams mContactPicLayoutParams;

	public AdaptadorAnotacionInterna(Context applicationContext,
			ArrayList<AnotacionInterna> sistemas) {
		this.data = sistemas;
		this.contexto = applicationContext;
		//this.dataOriginal=sistemas;
	}
	 
   
    
    

	public int getCount() {

		return data.size();
	}

	
	public AnotacionInterna getItem(int position) {

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

			
			
			vista = inflater.inflate(R.layout.list_item_anotacion, parent, false); 

			holder = new ViewHolder();
			holder.fecha = (TextView) vista.findViewById(R.id.fecha_item);
			holder.mensaje = (TextView) vista.findViewById(R.id.registro_item);
			
			
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
	
	/*private class LoadImageAlumno extends AsyncTask<String , Void, Integer> {

		
		private ViewHolder mViewHolder;
		private int mPosition;
		private String contenedorUrl;

		public LoadImageAlumno(ViewHolder viewHolder, int position) {
			mViewHolder = viewHolder;
			mPosition = position;
		}
		
		@Override
		protected Integer doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
		protected void onPostExecute(Integer contactId) {
			if (mViewHolder.position == mPosition) {
				mCachedContactIds.put(contenedorUrl, contactId);

				Picasso.with(contexto)
						.load(mCachedContactIds.get(contenedorUrl))
						.placeholder(R.drawable.ic_launcher)
						.into(mViewHolder.avatar);
			}
		}
	}*/
}
