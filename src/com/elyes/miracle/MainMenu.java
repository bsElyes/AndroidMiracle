package com.elyes.miracle;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import uk.me.lewisdeane.lnavigationdrawer.NavigationItem;
import uk.me.lewisdeane.lnavigationdrawer.NavigationListView;
import uk.me.lewisdeane.lnavigationdrawer.NavigationListView.NavigationItemClickListener;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.elyes.entities.Produit;
import com.elyes.fragments.AboutFragment;
import com.elyes.fragments.CartFragment;
import com.elyes.fragments.MyCardFragment;
import com.elyes.fragments.ProductsFragment;
import com.elyes.fragments.SliderFragment;
import com.elyes.utils.DatabaseHelper;
import com.elyes.utils.GMapV2Direction;
import com.elyes.utils.GPSTracker;
import com.esprit.miracle.R;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;



@SuppressLint("ResourceAsColor")
public class MainMenu extends Activity {
	public final static int ID_USER=2000;

    private DrawerLayout mDrawerLayout;
    private NavigationListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;
    private boolean drawerArrowColor;
    
    //DB
	DatabaseHelper dbHelper;
	RuntimeExceptionDao<Produit, Integer> prodDao;
    //MAP
    private GoogleMap mMap;
    private MapFragment mMapFragment;
    private GPSTracker mGPS ;
    public GMapV2Direction md;
    private static boolean showMap=true;
    LatLng fromPosition =null;
	LatLng toPosition = new LatLng(36.8498327,10.2203717);
	
	//List Produit Cart
	List<Produit>list = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        
        FragmentManager fm= getFragmentManager();
        Fragment fragment =new SliderFragment();
        
        if(fragment!=null){
		 	fm.beginTransaction()
	        .replace(R.id.containerV2, fragment)
	        .commit();
	       
		}
        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (NavigationListView) findViewById(R.id.navdrawer);
        
        mMapFragment = ((MapFragment)getFragmentManager().findFragmentById(R.id.mapV2));
        mMap = mMapFragment.getMap();     
		mMapFragment.getView().setVisibility(View.INVISIBLE);
		MapDrawer(mMap);
		dbHelper=OpenHelperManager.getHelper(this, DatabaseHelper.class);
		prodDao=dbHelper.getProdRuntimeExceptionDao();
		
		drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };
        
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
            drawerArrow, R.string.drawer_open,
            R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        

//        String[] values = new String[]{
//            "Acceuil",
//            "Catalogue",
//            "   Soins",
//            "   Maquillage",
//            "   Parfums Homme",
//            "   Parfums Femme",
//            "   Parfums Mixte",
//            "   Coffrets pour Homme",
//            "   Coffrets pour Femme",
//            "About"
//        };
        
        
        ArrayList<NavigationItem> listItem =new ArrayList<NavigationItem>();
        mDrawerList.addNavigationItem(new NavigationItem("Acceuil",R.drawable.dr_acceuil));
        mDrawerList.addNavigationItem(new NavigationItem("Categorie", R.drawable.dr_shop));
        mDrawerList.addNavigationItem(new NavigationItem("Soins",R.drawable.dr_1));
        mDrawerList.addNavigationItem(new NavigationItem("Maquillage",R.drawable.dr_2));
        mDrawerList.addNavigationItem(new NavigationItem("Parfums Homme",R.drawable.dr_3));
        mDrawerList.addNavigationItem(new NavigationItem("Parfums Femme",R.drawable.dr_4));
        mDrawerList.addNavigationItem(new NavigationItem("Parfums Mixte",R.drawable.dr_5));
        mDrawerList.addNavigationItem(new NavigationItem("Coffrets pour Homme",R.drawable.dr_6));
        mDrawerList.addNavigationItem(new NavigationItem("Coffrets pour Femme",R.drawable.dr_7));
        mDrawerList.addNavigationItem(new NavigationItem("Ma Carte", R.drawable.dr_fidelity));
        mDrawerList.addNavigationItem(new NavigationItem("About", R.drawable.dr_about));


        mDrawerList.setNavigationItemClickListener(new NavigationItemClickListener() {
			
			@Override
			public void onNavigationItemSelected(String item,
					ArrayList<NavigationItem> items, int position) {
			Intent intent;
	       	FragmentManager fm= getFragmentManager();
	        Fragment fragment =null;

				switch (position) {
						case 0:
							setTitle("Acceuil");
							fragment=new SliderFragment();
							break;
						case 1:
							setTitle("Categorie");
							Toast.makeText(getApplicationContext(),
									"Categorie", 5000).show();
							break;

						case 2:
							setTitle(R.string.title_section1);
							fragment = new ProductsFragment(1);
							break;
						case 3:
							setTitle(R.string.title_section2);
							fragment = new ProductsFragment(2);
							break;
						case 4:
							setTitle(R.string.title_section3);
							fragment = new ProductsFragment(3);
							break;
						case 5:
							setTitle(R.string.title_section4);
							fragment = new ProductsFragment(4);
							break;
						case 6:
							setTitle(R.string.title_section5);
							fragment = new ProductsFragment(5);
							break;
						case 7:
							setTitle(R.string.title_section6);
							fragment = new ProductsFragment(6);
							break;
						case 8:
							setTitle(R.string.title_section7);
							fragment = new ProductsFragment(7);
							break;
						case 9:
							fragment = new MyCardFragment();
							break;
						case 10:
							fragment=new AboutFragment();
							
							break;

						}
				if(fragment!=null){
				 	fm.beginTransaction()
			        .replace(R.id.containerV2, fragment)
			        .commit();
			        mDrawerLayout.closeDrawer(mDrawerList);			        
			        mMapFragment.getView().setVisibility(View.INVISIBLE);
				}
			}
			
		});
    }
   
    @Override
    public boolean onCreateOptionsMenu(Menu menu ) {
    	getMenuInflater().inflate(R.menu.magasins, menu);
    	
//    	RelativeLayout badgeLayout = (RelativeLayout) menu.findItem(R.id.action_shop).getActionView();
//        TextView tv = (TextView) badgeLayout.findViewById(R.id.actionbar_notifcation_textview);
//        tv.setText("12");
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }
        
        if(item.getItemId()==R.id.action_map){
        	if(showMap){                         
        		mMapFragment.getView().setVisibility(View.VISIBLE);
        		showMap=false;
        	}else{
        		mMapFragment.getView().setVisibility(View.INVISIBLE);
        		showMap=true;
        	}
        	
        }
        if(item.getItemId()==R.id.action_shop){
    		mMapFragment.getView().setVisibility(View.INVISIBLE);

        	FragmentManager fm= getFragmentManager();
    		list=prodDao.queryForAll();
    		int i=0;
    		for(Produit p : list){
    			Log.d("Produit "+i++,p.toString());
    		}
            Fragment fragment =new CartFragment(list);
        	fm.beginTransaction()
            .replace(R.id.containerV2, fragment)
            .commit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    
    public void MapDrawer(GoogleMap mMap){
    	mGPS=new GPSTracker(this);
    	
    	if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    	 md = new GMapV2Direction();
 		
 		mMap.setMyLocationEnabled(true);
 		LatLng fromPosition = new LatLng(36.8339127,10.1789832);		

 		//fromPosition= new LatLng(mGPS.getLatitude(),mGPS.getLongitude());
 		LatLng coordinates = new LatLng(36.8339127,10.1789832);		
 		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 16));
 		
 		mMap.addMarker(new MarkerOptions().position(fromPosition).title("Vous ete ici !"));
 		mMap.addMarker(new MarkerOptions().position(toPosition).title("Magasin Miracle"));
 		
 		Document doc = md.getDocument(fromPosition, toPosition, GMapV2Direction.MODE_DRIVING);
 		int duration = md.getDurationValue(doc);
 		String distance = md.getDistanceText(doc);
 		
 		String start_address = md.getStartAddress(doc);
 		String copy_right = md.getCopyRights(doc);
 		
 		
 		Log.d("Durée",duration+"");
 		Log.d("Distance :",distance+"");
 		Log.d("start_address",start_address+"");
 		Log.d("copy_right",copy_right+"");

 		ArrayList<LatLng> directionPoint = md.getDirection(doc);
 		PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED);
 		
 		for(int i = 0 ; i < directionPoint.size() ; i++) {			
 			rectLine.add(directionPoint.get(i));
 		}
 		
 		mMap.addPolyline(rectLine);
    }
}