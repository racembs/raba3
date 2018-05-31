package com.tonikamitv.loginregister;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by RBS on 28-Jan-18.
 */

public class AnnonceListAdapter extends BaseAdapter {
private Context context;
    private List<Annonce> annonces;

    public AnnonceListAdapter(Context context, List<Annonce> annonces) {
        this.context = context;
        this.annonces = annonces;
    }

    @Override
    public int getCount() {
        return annonces.size();
    }

    @Override
    public Object getItem(int i) {
        return annonces.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=View.inflate(context,R.layout.item_annonce,null);
        TextView titre=(TextView)v.findViewById(R.id.titreAnn);
        TextView desc=(TextView)v.findViewById(R.id.descriptionAnn);
        TextView nbrmanquant=(TextView)v.findViewById(R.id.nbrmanquanttxt);
        TextView id_annonce=(TextView)v.findViewById(R.id.idAnnonceItem);
        TextView id_env=(TextView)v.findViewById(R.id.idAnnonceItemOwner);

        titre.setText(annonces.get(i).getTitre());
        desc.setText(annonces.get(i).getDescription());
        id_annonce.setText(String.valueOf(annonces.get(i).getId()));
        id_env.setText(String.valueOf(annonces.get(i).getUser().getId()));
        if(annonces.get(i).getNbrmanquant().equals("0")){
            nbrmanquant.setText("complet");
        }
        else if(annonces.get(i).getNbrmanquant().equals("1")){
            nbrmanquant.setText("un Joueur manquant");
        }
            else{
            nbrmanquant.setText(annonces.get(i).getNbrmanquant()+ " Joueurs manquants ");
        }


        v.setTag(R.id.titreAnn,annonces.get(i).getTitre());
        v.setTag(R.id.descriptionAnn,annonces.get(i).getDescription());
        v.setTag(R.id.nbrmanqaunt,annonces.get(i).getNbrmanquant());
        v.setTag(R.id.idAnnonceItem,annonces.get(i).getId());
        v.setTag(R.id.idAnnonceItemOwner,annonces.get(i).getUser().getId());





        return v;
    }
}
