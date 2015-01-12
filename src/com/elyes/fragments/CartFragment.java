package com.elyes.fragments;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.elyes.adapter.ShoppingCartAdapter;
import com.elyes.entities.Produit;
import com.elyes.miracle.MainMenu;
import com.elyes.utils.DatabaseHelper;
import com.elyes.utils.JSONParser;
import com.elyes.utils.JSONParserSend;
import com.elyes.miracle.R;
import com.gc.materialdesign.views.ButtonRectangle;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class CartFragment extends Fragment {
	ListView listItemCart;
	public  List<Produit> produitsCart=null;
	String urlRes= ProductsFragment.ipServer+"/scripts/addRes.php";
	private ProgressDialog pDialog;
	JSONParserSend jsonParser = new JSONParserSend();
	private static final String TAG_SUCCESS = "success";

	
	public CartFragment(List<Produit>list) {
		produitsCart=list;
	}
	
	public CartFragment() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_shoppingcart, container,false);
		listItemCart = (ListView)v.findViewById(R.id.lv_shopping);
		ShoppingCartAdapter adapter=new ShoppingCartAdapter(getActivity(), R.layout.shopping_detail_row, produitsCart);
		adapter.notifyDataSetChanged();
		listItemCart.setAdapter(adapter);
		ButtonRectangle process = (ButtonRectangle) v.findViewById(R.id.btn_checkout);
		process.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(produitsCart.size()==0){
					Toast.makeText(getActivity(), "List est Vide", 4000).show();
				}
				else{
					new AddReservation(produitsCart).execute();

				}
			}
		});
		return v;
	}
	
	public class AddReservation extends AsyncTask<String, String,String>{
		List<Produit> listP=null;
		DatabaseHelper dbHelper;
		RuntimeExceptionDao<Produit, Integer> prodDao;
		ListView listShop;
		public AddReservation(List<Produit> list) {
			listP=list;
			dbHelper=OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
			prodDao = dbHelper.getProdRuntimeExceptionDao();
		}	
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Add Reservation");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		
		@Override
		protected String doInBackground(String... params) {
			
			if(listP.size()==0){
				
			for(int i=0;i<listP.size();i++){
			prodDao.delete(listP);
			List<NameValuePair> val = new ArrayList<NameValuePair>();
			val.add(new BasicNameValuePair("idClient", MainMenu.ID_USER+""));
			val.add(new BasicNameValuePair("idProduit", listP.get(i).getId()+""));

			JSONObject json = jsonParser.makeHttpRequest(urlRes,
					"POST", val);
			
			Log.d("Create Response", json.toString());

			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Ajoute Reservation","TERMINER AVEC SUCCES !");
				} else {
					Log.d("ERREUR","ERREUR d'ajoute à la base de donnée");
				}
			} catch (JSONException e) {
				Log.d("RAISON",e.getMessage());
			}
			}
			}else{
				Toast.makeText(getActivity(), "List Est Vide !", 2000).show();
			}
			
			return null;
		}
		
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();

		}
		
		
		
	}
}
