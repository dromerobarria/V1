package com.geon.docente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.geon.InspectoresAndroid.R;
import com.squareup.picasso.Picasso;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class AdaptadorDocente extends BaseAdapter {
	private static final int IMAGE_HEIGHT = 40;
	private static final int IMAGE_WIDTH = 40;
	private Context contexto;
	private ArrayList<docente> dataOriginal;
	private ArrayList<docente> data;
	 private Filter alumnofiltro;
	
	
	
	protected LinearLayout.LayoutParams mContactPicLayoutParams;

	public AdaptadorDocente(Context applicationContext,
			ArrayList<docente> sistemas) {
		this.data = sistemas;
		this.contexto = applicationContext;
		this.dataOriginal=sistemas;
	}
	
	 /*
     * We create our filter        
     */
    
    public Filter getFilter() {
            if (alumnofiltro == null)
            	alumnofiltro = new DocenteFiltro();
            
            return alumnofiltro;
    }

    public void resetData() {
        data = dataOriginal;
}

    
    
    private class DocenteFiltro extends Filter {

        
        
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                // We implement here the filter logic
                if (constraint == null || constraint.length() == 0) {
                        // No filter implemented we return all the list
                        results.values = dataOriginal;
                        results.count = dataOriginal.size();
                }
                else {
                        // We perform filtering operation
                        List<docente> nPlanetList = new ArrayList<docente>();
                        
                        for (docente p : data) {
                                if (p.getNombre().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                                        nPlanetList.add(p);
                        }
                        
                        results.values = nPlanetList;
                        results.count = nPlanetList.size();

                }
                return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                        FilterResults results) {
                
                // Now we have to inform the adapter about the new list filtered
                if (results.count == 0)
                        notifyDataSetInvalidated();
                else {
                        data = (ArrayList<docente>) results.values;
                        notifyDataSetChanged();
                }
                
        }
        
}
    

	@Override
	public int getCount() {

		return data.size();
	}

	@Override
	public docente getItem(int position) {

		return data.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		View vista = convertView;
		if (convertView == null || !(vista.getTag() instanceof ViewHolder)) {
			LayoutInflater inflater = (LayoutInflater) contexto
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			
			
			vista = inflater.inflate(R.layout.list_item_docente, parent, false); 

			holder = new ViewHolder();
			holder.nombre = (TextView) vista.findViewById(R.id.name);
			holder.area = (TextView) vista.findViewById(R.id.area);
			holder.rut = (TextView) vista.findViewById(R.id.rut);
			holder.avatar = (ImageView) vista.findViewById(R.id.avatar_docente);
			vista.setTag(holder);
		} else {
			holder = (ViewHolder) vista.getTag();
		}
		
		int rutAlumno = Integer.valueOf(data.get(position).getRut());
	//	Log.d("adapterDocente", data.get(position).getUrl());
		
	    Picasso.with(contexto)
	            .load(data.get(position).getUrl())
	            .placeholder(R.drawable.ic_launcher2)
	            .error(android.R.drawable.star_big_on)
	            .resize(IMAGE_HEIGHT, IMAGE_WIDTH).centerCrop()
	            .into(holder.avatar);                 
	  
		holder.nombre.setText(data.get(position).getNombre());
		holder.area.setText(data.get(position).getArea());
		holder.rut.setText(data.get(position).getRut());

		return vista;
	}
	
	
	

	public class ViewHolder {
		TextView nombre;
		TextView rut;
		TextView area;
		ImageView avatar;
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
