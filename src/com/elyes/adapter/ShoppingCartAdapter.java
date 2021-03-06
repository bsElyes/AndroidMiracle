package com.elyes.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.elyes.entities.Produit;
import com.elyes.fragments.CartFragment;
import com.elyes.fragments.ProductsFragment;
import com.elyes.utils.DatabaseHelper;
import com.elyes.utils.ImageLoader;
import com.elyes.miracle.R;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.widgets.Dialog;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.readystatesoftware.viewbadger.BadgeView;


public class ShoppingCartAdapter extends ArrayAdapter<Produit>{
	
	int layoutResourceId;
	ImageLoader imageLoader;
	Context context;
	List<Produit>produits=new ArrayList<Produit>();
	BadgeView badge2;
	DatabaseHelper dbHelper;
	RuntimeExceptionDao<Produit, Integer> prodDao;
	public ShoppingCartAdapter(Context context, int layoutResourceId
			, List<Produit> produits) {
		super(context, layoutResourceId, produits);
		
		
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.produits = produits;
		imageLoader = new ImageLoader(context.getApplicationContext());
		
	}
	
	
@Override
public int getPosition(Produit item) {
    return super.getPosition(item);
}

@Override
public Produit getItem(int position) {
    return produits.get(position);
}

@Override
public int getCount() {
    return produits.size();
}

@Override
public long getItemId(int position) {
    return super.getItemId(position);
}


	
	@SuppressLint("SdCardPath")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(layoutResourceId, parent, false);
			final int pos=position;
			ImageView produitImg = (ImageView) row.findViewById(R.id.img_produit_s);
			TextView produitLibelle = (TextView) row.findViewById(R.id.tv_produit_name_s);
			TextView produitPrix = (TextView) row.findViewById(R.id.tv_produit_prix_s);
			Button produitDel=(Button) row.findViewById(R.id.btn_delete);
			
			produitDel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try{
						dbHelper=OpenHelperManager.getHelper(getContext(), DatabaseHelper.class);
						prodDao = dbHelper.getProdRuntimeExceptionDao();
						Dialog dialog = new Dialog(getContext(),"Delete From Cart", "Do You want really Delete The Product "+produits.get(pos).getLibelle());
						dialog.setOnAcceptButtonClickListener( new OnClickListener() {							
							@Override
							public void onClick(View v) {
								prodDao.deleteById(produits.get(pos).getId());
								Toast.makeText(getContext(),produits.get(pos).getLibelle()+" is Deleted Succesfully", 2000).show();
								notifyDataSetChanged();
							
							
							}
						});
						// Set cancel click listenner
						dialog.setOnCancelButtonClickListener(new OnClickListener() {							
							@Override
							public void onClick(View v) {
								Toast.makeText(getContext(), "Yabta", 5000).show();
								
							}
						});
						dialog.show();

					}catch(Exception e){
						Toast.makeText(getContext(),"Error !", 2000).show();
						List<Produit>listProd=prodDao.queryForAll();
						Log.d("erreur","ERROR TO ADD"+listProd.toString());
					}
				}
			});
		
		Produit p = produits.get(position);
		System.out.println(p.getId()+"  "+p.getLibelle()+ " *** "+p.getImagePath());
//		if(position==1){
//			badge2 = new BadgeView(getContext(), holder.produitLibelle);
//	    	badge2.setText("New!");
//	    	badge2.setTextColor(Color.BLUE);
//	    	badge2.setBadgeBackgroundColor(Color.YELLOW);
//	    	badge2.setTextSize(12);
//	    	badge2.show();
//		}
		imageLoader.DisplayImage(ProductsFragment.ipServer+p.getImagePath(), produitImg);
		produitLibelle.setText(p.getLibelle());
		produitPrix.setText(p.getPrix()+" DT");
		
		return row;
	}

	
}
