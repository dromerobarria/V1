package com.geon.cursos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.geon.InspectoresAndroid.R;
import com.squareup.picasso.Picasso;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class AdaptadorCurso extends BaseAdapter {

	private Context contexto;
	private ArrayList<Cursos> dataOriginal;
	private ArrayList<Cursos> data;
	 private Filter cursofiltro;
	
	
	
	protected LinearLayout.LayoutParams mContactPicLayoutParams;

	public AdaptadorCurso(Context applicationContext,
			ArrayList<Cursos> sistemas) {
		this.data = sistemas;
		this.contexto = applicationContext;
		this.dataOriginal=sistemas;
	}
	
	 /*
     * We create our filter        
     */
    
    public Filter getFilter() {
            if (cursofiltro == null)
            	cursofiltro = new DocenteFiltro();
            
            return cursofiltro;
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
                        List<Cursos> nPlanetList = new ArrayList<Cursos>();
                        
                        for (Cursos p : data) {
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
                        data = (ArrayList<Cursos>) results.values;
                        notifyDataSetChanged();
                }
                
        }
        
}
    

	@Override
	public int getCount() {

		return data.size();
	}

	@Override
	public Cursos getItem(int position) {

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

			
			
			vista = inflater.inflate(R.layout.list_item_curso, parent, false); 

			holder = new ViewHolder();
			holder.nombre = (TextView) vista.findViewById(R.id.name);
			holder.profesor = (TextView) vista.findViewById(R.id.profesor);
			
			vista.setTag(holder);
		} else {
			holder = (ViewHolder) vista.getTag();
		}
		
	              

		holder.nombre.setText(data.get(position).getNombre());
		holder.profesor.setText(data.get(position).getProfesor());
	//	holder.rut.setText(data.get(position).getRut());

		return vista;
	}
	
	
	

	public class ViewHolder {
		TextView nombre;
		TextView profesor;
		
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
