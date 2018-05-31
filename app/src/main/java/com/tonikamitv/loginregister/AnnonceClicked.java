package com.tonikamitv.loginregister;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AnnonceClicked extends AppCompatActivity {
    static int id=1;
    NotificationManager nmanager;
      boolean sent=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_annonce_clicked);

        final TextView titre = (TextView) findViewById(R.id.titreClicked);
        final TextView description = (TextView) findViewById(R.id.descriptionlicked);
        final TextView nbrmanquant = (TextView) findViewById(R.id.nbrmanquantclicked);

        final Button envoyer = (Button) findViewById(R.id.envoyerDemande);
        final Button annuler = (Button) findViewById(R.id.annulerDemande);
        annuler.setVisibility(View.GONE);

        final Intent  intent = getIntent();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsonResponse = new JSONArray(response);
                    for(int i=0; i < jsonResponse.length(); i++) {
                        JSONObject jsonobject = jsonResponse.getJSONObject(i);

                        int id_env = jsonobject.getInt("id_env");
                        int id_annonce = jsonobject.getInt("id_annonce");
                        if(id_env==HomeFrag.user.getId()&&Integer.valueOf(intent.getStringExtra("id_annonce"))==id_annonce){
                            envoyer.setVisibility(View.GONE);
                            annuler.setVisibility(View.VISIBLE);
                        }


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        DemandeRequest.REQUEST_URL="http://atra7.000webhostapp.com/getDemande.php";
        DemandeRequest ajoutAnnRequest = new DemandeRequest(String.valueOf(HomeFrag.user.getId()),responseListener);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(ajoutAnnRequest);

        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Demande envoyée",Toast.LENGTH_SHORT).show();
                envoyer.setVisibility(View.GONE);
                annuler.setVisibility(View.VISIBLE);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(getApplicationContext(),"Demande envoyée",Toast.LENGTH_LONG).show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                                builder.setMessage("Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                DemandeRequest.REQUEST_URL="http://atra7.000webhostapp.com/ajoutDemande.php";
                DemandeRequest ajoutAnnRequest = new DemandeRequest(String.valueOf(HomeFrag.user.getId()),String.valueOf(intent.getStringExtra("id_rec")),String.valueOf(intent.getStringExtra("id_annonce")),responseListener);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(ajoutAnnRequest);


         /*      NotificationCompat.Builder nbuild =new NotificationCompat.Builder(getBaseContext());
                nbuild.setContentTitle("Demande");
                nbuild.setContentText("demande envoyée par rbs");
                nbuild.setSmallIcon(R.drawable.ic_menu_camera);
                nbuild.setWhen(System.currentTimeMillis());
                Intent resultIntent = new Intent(AnnonceClicked.this, HomeActivity.class);
                android.support.v4.app.TaskStackBuilder stackBuilder = android.support.v4.app.TaskStackBuilder.create(AnnonceClicked.this);

                stackBuilder.addParentStack(HomeActivity.class);


                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                nbuild.setContentIntent(resultPendingIntent);
                nbuild.setAutoCancel(true);
                nmanager=(NotificationManager)getSystemService(getBaseContext().NOTIFICATION_SERVICE);
                nmanager.notify(id,nbuild.build());
                id++;

*/


            }
        });
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Demande annulée",Toast.LENGTH_SHORT).show();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                DemandeRequest.REQUEST_URL="http://atra7.000webhostapp.com/deleteDemande.php";
                DemandeRequest ajoutAnnRequest = new DemandeRequest(String.valueOf(HomeFrag.user.getId()),String.valueOf(intent.getStringExtra("id_rec")),String.valueOf(intent.getStringExtra("id_annonce")),responseListener);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(ajoutAnnRequest);
                annuler.setVisibility(View.GONE);
                envoyer.setVisibility(View.VISIBLE);

            }
        });

        String titre_txt = intent.getStringExtra("titre");
        String desc = intent.getStringExtra("description");
        String nbrmanqunt = intent.getStringExtra("nbrmanquant");
        if(nbrmanqunt.equals("0")){
            nbrmanquant.setText("complet");
        }
        else if(nbrmanqunt.equals("1")){
            nbrmanquant.setText("un Joueur manquant");
        }
        else{
            nbrmanquant.setText(nbrmanqunt+ " Joueurs manquants ");
        }
        titre.setText(titre_txt);
        description.setText(desc);



    }
}
