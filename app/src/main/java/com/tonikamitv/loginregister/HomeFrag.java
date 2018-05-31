package com.tonikamitv.loginregister;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by RBS on 25-Jan-18.
 */

public class HomeFrag extends Fragment {
    private ListView annonces;
    private AnnonceListAdapter adapter;
    private List<Annonce> annonceList;
    private List<Annonce> invannonceList;
    static User user;
     boolean etatB=false;
    boolean notif=false;
    NotificationManager nmanager;
    static int id=1;
     String usernameNotif="";
    JSONArray jsonArray;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View myview=inflater.inflate(R.layout.home_frag, container,false);
        DbConnection db=new DbConnection(getActivity().getApplicationContext());

        ArrayList <User> users=db.getAll();
        user=users.get(0);
        annonces=(ListView) myview.findViewById(R.id.listview_annonce);
        annonceList=new ArrayList<>();


       /* annonces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // Toast.makeText(myview,"home",Toast.LENGTH_LONG).show();
            }
        });*/



        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsonResponse = new JSONArray(response);
                    for(int i=0; i < jsonResponse.length(); i++) {
                        JSONObject jsonobject = jsonResponse.getJSONObject(i);
                        int id       = jsonobject.getInt("id");
                        String title    = jsonobject.getString("titre");
                        String description  = jsonobject.getString("description");
                        String nbrmanquant  = jsonobject.getString("nbrmanquant");
                        User user=new User();
                        user.setId(Integer.valueOf(jsonobject.getString("id_user")));
                        annonceList.add(new Annonce(id,title,description,nbrmanquant,user));

                    }


                    Collections.reverse(annonceList);





                    adapter=new AnnonceListAdapter(getActivity().getApplicationContext(),annonceList);
                    annonces.setAdapter(adapter);
                    annonces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getActivity(), AnnonceClicked.class);

                            intent.putExtra("titre",view.getTag(R.id.titreAnn).toString());
                            intent.putExtra("description",view.getTag(R.id.descriptionAnn).toString());
                            intent.putExtra("nbrmanquant",view.getTag(R.id.nbrmanqaunt).toString());
                            intent.putExtra("id_rec",view.getTag(R.id.idAnnonceItemOwner).toString());
                            intent.putExtra("id_annonce",view.getTag(R.id.idAnnonceItem).toString());

                            getActivity().startActivity(intent);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };




        getAnnonceRequest annonceRequest = new getAnnonceRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(annonceRequest);


        final  Response.Listener<String> SetEtatresponseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    for(int i=0; i < jsonResponse.length(); i++) {
                        JSONObject jsonobject = jsonResponse.getJSONObject(i);






                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


        final  Response.Listener<String> UserresponseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    for(int i=0; i < jsonResponse.length(); i++) {
                        JSONObject jsonobject = jsonResponse.getJSONObject(i);



                        usernameNotif = jsonobject.getString("username");

                        notif=true;


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {

                            Response.Listener<String> responseListener = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray jsonResponse = new JSONArray(response);
                                        for(int i=0; i < jsonResponse.length(); i++) {
                                            JSONObject jsonobject = jsonResponse.getJSONObject(i);

                                            int id_env = jsonobject.getInt("id_env");
                                            int id_annonce = jsonobject.getInt("id_annonce");
                                            int id_rec = jsonobject.getInt("id_rec");
                                            int etat = jsonobject.getInt("etat");
                                            if(id_rec==user.getId()){




                                                LoginRequest.LOGIN_REQUEST_URL = "http://atra7.000webhostapp.com/getUserById.php";
                                                LoginRequest loginRequest = new LoginRequest(String.valueOf(id_env), UserresponseListener);
                                                RequestQueue queue = Volley.newRequestQueue(getActivity());
                                                queue.add(loginRequest);



                                                if(notif){

                                                NotificationCompat.Builder nbuild =new NotificationCompat.Builder(getActivity());
                                                nbuild.setContentTitle("Demande");
                                                nbuild.setContentText("demande envoyée par "+usernameNotif);
                                                nbuild.setSmallIcon(R.drawable.ic_menu_camera);
                                                nbuild.setWhen(System.currentTimeMillis());
                                                Intent resultIntent = new Intent(getActivity(), HomeActivity.class);
                                                android.support.v4.app.TaskStackBuilder stackBuilder = android.support.v4.app.TaskStackBuilder.create(getActivity());

                                                stackBuilder.addParentStack(HomeActivity.class);


                                                stackBuilder.addNextIntent(resultIntent);
                                                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                                                nbuild.setContentIntent(resultPendingIntent);
                                                nbuild.setAutoCancel(true);
                                                nmanager=(NotificationManager)getActivity().getSystemService(getActivity().getApplicationContext().NOTIFICATION_SERVICE);
                                                nmanager.notify(id,nbuild.build());
                                                id++;
                                                    etatB=true;

                                            }
                                            if(etatB){


                                                DemandeRequest.REQUEST_URL="http://atra7.000webhostapp.com/setEtatDemande.php";
                                                DemandeRequest EtatRequest = new DemandeRequest(String.valueOf(id_env),String.valueOf(id_rec),String.valueOf(id_annonce),SetEtatresponseListener);
                                                RequestQueue Etatqueue = Volley.newRequestQueue(getActivity());
                                                Etatqueue.add(EtatRequest);
                                            }
                                           }


                                        }


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };

                            DemandeRequest.REQUEST_URL="http://atra7.000webhostapp.com/getDemandeOwner.php";
                            DemandeRequest ajoutAnnRequest = new DemandeRequest(String.valueOf(HomeFrag.user.getId()),responseListener,"owner");
                            RequestQueue queue = Volley.newRequestQueue(getActivity());
                            queue.add(ajoutAnnRequest);



                        } catch (Exception e) {
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 10000, 10000);

        return myview;
    }
}
