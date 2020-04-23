package com.itesm.hw4_a01633894;

import  androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Policy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<LatLng> listPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        listPoints=new ArrayList<>();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                if(listPoints.size()==2){
                    listPoints.clear();
                    mMap.clear();
                }
                listPoints.add(latLng);
                MarkerOptions markerOptions=new MarkerOptions();
                markerOptions.position(latLng);

                if(listPoints.size()==1){
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                }else{
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW ));
                }
                mMap.addMarker(markerOptions);

                if(listPoints.size()==2){
                    String url=getRequestUrl(listPoints.get(0),listPoints.get(1));
                    TaskRequestDirections taskRequestDirections=new TaskRequestDirections();
                    taskRequestDirections.execute(url);
                }
            }
        });

    }

    private String getRequestUrl(LatLng inicio, LatLng fin) {
        String str_inicio="inicio="+inicio.latitude+","+inicio.longitude;
        String str_fin="fin="+fin.latitude+","+fin.longitude;
        String sensor="sensor-false";
        String modo="Mode=driving";
        String param=str_inicio+"&"+str_fin+"&"+sensor+"&"+modo;
        String salida="json";
        String url="https://maps.googleapis.com/maps/api/directions/" + salida + "?" + param + "&key=" + getString(R.string.google_maps_key);
        return url;
    }

    private String requestDirection(String reUrl)throws IOException {
        String respuesta="";
        InputStream inputStream=null;
        HttpURLConnection httpURLConnection=null;
        try {
            URL url=new URL(reUrl);
            httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            inputStream=httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer=new StringBuffer();
            String linea="";
            while ((linea=bufferedReader.readLine())!=null){
                stringBuffer.append(linea);
            }
            respuesta=stringBuffer.toString();
            bufferedReader.close();
            inputStreamReader.close();

        }catch (Exception e){
            System.out.println("Error");
        }finally {
            if(inputStream!=null){
                inputStream.close();
            }
            httpURLConnection.disconnect();
        }
        return respuesta;
    }

     public class TaskRequestDirections extends AsyncTask<String,Void,String>{

         @Override
         protected String doInBackground(String... strings) {
             String respuesta="";
             try {
                 respuesta=requestDirection(strings[0]);
             }catch (Exception e){
                 System.out.println("Error");
             }
             return respuesta;
         }

         @Override
         protected void onPostExecute(String s) {
             super.onPostExecute(s);
             TaskParser taskParser=new TaskParser();
             taskParser.execute(s);
         }
     }

     public class TaskParser extends AsyncTask<String,Void,List<List<HashMap<String,String>>>>{

         @Override
         protected List<List<HashMap<String, String>>> doInBackground(String... strings) {
             JSONObject jsonObject=null;
             List<List<HashMap<String ,String>>> rutas=null;
             try {
                 jsonObject=new JSONObject(strings[0]);
                 DirectionsJSONParser directionsJSONParser=new DirectionsJSONParser();
                 rutas=directionsJSONParser.parse(jsonObject);
             }catch (JSONException e){
                 System.out.println("Error");
             }
             return rutas;

         }

         @Override
         protected void onPostExecute(List<List<HashMap<String, String>>> lista) {
             ArrayList puntos=null;

             PolylineOptions polylineOptions=null;

             for(List<HashMap<String, String>> path:lista){
                 puntos=new ArrayList();
                 polylineOptions=new PolylineOptions();

                 for (HashMap<String, String> punto:path){
                    double lat=Double.parseDouble(punto.get("lat"));
                    double lon=Double.parseDouble(punto.get("lon"));

                    puntos.add(new LatLng(lat,lon));
                 }
                polylineOptions.addAll(puntos);
                  polylineOptions.width(15);
                  polylineOptions.color(Color.RED);
                  polylineOptions.geodesic(true);

             }
             if(polylineOptions!=null){
                 mMap.addPolyline(polylineOptions);
             }else{
                 Toast.makeText(getApplicationContext(),"No se encontró la ruta",Toast.LENGTH_SHORT).show();
             }
         }
     }

}
