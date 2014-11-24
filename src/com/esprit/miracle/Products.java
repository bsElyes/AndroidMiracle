package com.esprit.miracle;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.esprit.adapter.ProduitAdapter;
import com.esprit.entities.Produit;
import com.esprit.utils.HelperHttp;

public class Products extends Fragment {
	ListView listProduitsList;
	GridView listProduitsGrid;
	List<Produit> produits= new ArrayList<Produit>();
	ProduitAdapter produitAdapter;
	String url="http://192.168.1.2:80/scripts/produits.php?id=1";
	String categorie="1";
	
	
	public Products() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_produits, container , false);
		
		listProduitsList=(ListView ) getActivity().findViewById(R.id.lv_produitsList);
		listProduitsGrid=(GridView) getActivity().findViewById(R.id.lv_produitsGrid);
		
		Log.d("Hello","i'm Here Before Asyc");
		new AsycGetProducts().execute();
		Log.d("Hello","i'm Here After Asyc");
		
		return v;
	}
	
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		for(int i =0 ; i <5 ; i++){
			Produit p=new Produit();
			p.setLibelle(",najnnkkj : "+i);
			p.setPrix(550);
			p.setImagePath("5544554 : "+i);
			produits.add(p);
		}
		produitAdapter=new ProduitAdapter(getActivity(), R.layout.produit_detail_row, produits);
		listProduitsList.setVisibility(View.VISIBLE);
		listProduitsList.setAdapter(produitAdapter);
		//listProduitsGrid.setAdapter(produitAdapter);
		onConfigurationChanged(getResources().getConfiguration());
		
		super.onStart();
	}
	
	
	void parseJsonProduit (List<Produit> list,String json){
		try {
			JSONArray array=new JSONArray(json);
			
			for(int i = 0 ;i<array.length();i++){
				JSONObject j=array.getJSONObject(i);
				Produit p = new Produit();
				
				//p.setDispo(j.optBoolean("Disponible"));
				Log.d("Disponible",j.optBoolean("Disponible")+"");
				p.setImagePath(j.optString("ImagePath"));
				p.setLibelle(j.optString("LibelleProduit"));
				p.setPrix(j.optDouble("PrixProduit"));
				//p.setQuantite(j.optInt("QuantiteProduit"));
				
				list.add(p);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.d("JSON ",e.getMessage());
		}
	}
	
	class AsycGetProducts extends AsyncTask<String, Void, String>{
		ProgressDialog barProgressDialog = new ProgressDialog(getActivity());
		
		@Override
		protected void onPreExecute() {
			//TODO Show Progress Bar
			barProgressDialog.setTitle("Loading...");
			barProgressDialog.setMessage("Please Wait...");
			barProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			barProgressDialog.show();
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
//			String jsonProduit=HelperHttp.getJSONResponseFromURL(url);
//			System.out.println("fffff"+jsonProduit);
//			parseJsonProduit(produits, jsonProduit);
			
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			
			produitAdapter=new ProduitAdapter(getActivity(), R.layout.produit_detail_row, produits);
			
			listProduitsList.setAdapter(produitAdapter);
			//listProduitsGrid.setAdapter(produitAdapter);
			listProduitsList.setVisibility(View.VISIBLE);
			barProgressDialog.dismiss();
			


			super.onPostExecute(result);
		}
		
	}
	
}
