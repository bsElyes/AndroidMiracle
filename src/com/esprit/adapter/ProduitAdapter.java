package com.esprit.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.esprit.entities.Produit;
import com.esprit.miracle.Products;
import com.esprit.miracle.R;
import com.esprit.utils.ImageLoader;


public class ProduitAdapter extends ArrayAdapter<Produit>{
	
	int layoutResourceId;
	ImageLoader imageLoader;
	Context context;
	List<Produit>produits=new ArrayList<Produit>();
	

	public ProduitAdapter(Context context, int layoutResourceId
			, List<Produit> produits) {
		super(context, layoutResourceId, produits);
		

		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.produits = produits;
		imageLoader = new ImageLoader(context.getApplicationContext());
		
		
	}

	
	@SuppressLint("SdCardPath")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ProduitHolder holder = null;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new ProduitHolder();
			holder.produitImg = (ImageView) row.findViewById(R.id.img_produit);
			holder.produitLibelle = (TextView) row.findViewById(R.id.tv_produit_name);
			holder.produitPrix = (TextView) row.findViewById(R.id.tv_produit_prix);
			row.setTag(holder);
		} else {
			holder = (ProduitHolder) row.getTag();
		}
		Produit p = produits.get(position);
		System.out.println(p.getLibelle()+ " *** "+p.getImagePath());
		imageLoader.DisplayImage(Products.ipServer+p.getImagePath(), holder.produitImg);
		holder.produitLibelle.setText(p.getLibelle());
		holder.produitPrix.setText(p.getPrix()+" DT");
		
		return row;
	}

	public class ProduitHolder {
		ImageView produitImg;
		TextView produitLibelle;
		TextView produitPrix;
	}
}
