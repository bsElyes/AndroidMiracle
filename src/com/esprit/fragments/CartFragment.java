package com.esprit.fragments;

import java.util.ArrayList;
import java.util.List;

import com.esprit.adapter.ShoppingCartAdapter;
import com.esprit.entities.Produit;
import com.esprit.miracle.R;
import com.esprit.utils.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class CartFragment extends Fragment {
	ListView listItemCart;
	List<Produit> produitsCart=new ArrayList<Produit>();

	
	public CartFragment(List<Produit>list) {

		produitsCart=list;
	}
	
	public CartFragment() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View v = inflater.inflate(R.layout.fragment_shoppingcart, container,false);
		listItemCart = (ListView)v.findViewById(R.id.lv_shopping);
		ShoppingCartAdapter adapter=new ShoppingCartAdapter(getActivity(), R.layout.shopping_detail_row, produitsCart);
		adapter.notifyDataSetChanged();
		listItemCart.setAdapter(adapter);
		return v;
	}
}
