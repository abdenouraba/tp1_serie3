package com.example.tp1_serie3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class mon_adapter extends ArrayAdapter<achat> {
     Activity context;
     ArrayList<achat> my_list;
     ImageButton btn_supp,btn_edit;
      TextView textView,text;

    /**
     * Constructor
     *
     * @param context  The current context.

     */
    public mon_adapter(@NonNull Context context, ArrayList<achat> my_list) {

        super(context, R.layout.layout_achat,my_list);
        this.context= (Activity) context;
        this.my_list=my_list;

    }

    public View getView(final int position, View convert, ViewGroup parent)
    {

        LayoutInflater inflate = context.getLayoutInflater();
         convert=inflate.inflate(R.layout.layout_achat,null);


        /*
      convert=  LayoutInflater.from(getContext()).inflate(R.layout.layout_achat,parent,false);

         */

          textView=convert.findViewById(R.id.textview);
         text=convert.findViewById(R.id.text2);
        textView.setText(my_list.get(position).getAchat().toString());
        text.setText(String.valueOf(my_list.get(position).getquantite()));
        btn_supp=convert.findViewById(R.id.imageButton);
        btn_edit=convert.findViewById(R.id.imageButton2);
        Log.i("erreur","on est avent le onclik");
        btn_supp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("erreur2","ca va ca marche onclilk");
                my_list.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context,"votre produit achats a etais bien supprimer ",Toast.LENGTH_LONG).show();
            }
        });
        Log.i("erreur3","on est avent le onclik2");
        btn_edit.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                edit_mon_item(position);


            }
        });
        return convert;
    }
    public void edit_mon_item( final int position){

        final EditText edit_achat,edit_quantite;
         AlertDialog.Builder my_Builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = context.getLayoutInflater();
        View view_modif = inflater.inflate(R.layout.layout_edit,null);


        my_Builder.setView(view_modif);
        edit_achat=view_modif.findViewById(R.id.achat_nv);
        edit_quantite=view_modif.findViewById(R.id.quantite_nv);
        my_Builder.setNegativeButton("annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        my_Builder.setPositiveButton("Modifier ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (edit_achat.getText().toString().isEmpty() && edit_quantite.getText().toString().isEmpty()) {
                    Toast.makeText(context,"doit remplier au moins une modification",Toast.LENGTH_LONG).show();
                }
                else {
                    if (!edit_achat.getText().toString().isEmpty()){
                        double qt=my_list.get(position).getquantite();
                        my_list.set(position,new achat(edit_achat.getText().toString(),qt));

                    }
                    if (!edit_quantite.getText().toString().isEmpty()){
                        String achat=my_list.get(position).getAchat();
                        my_list.set(position,new achat(achat,Double.valueOf(edit_quantite.getText().toString())));

                    }
                notifyDataSetChanged();
                    Toast.makeText(context,"votre modification  est bien fait",Toast.LENGTH_LONG).show();

                    dialog.dismiss();
                }

            }
        });


my_Builder.show();




    }






}
