package com.example.tp1_serie3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   private ListView listview;
    private ArrayList<achat> lst;
    private ArrayList<achat> nv_liste;
    private ArrayList<ArrayList<achat>> list_parent;
    private mon_adapter mon_adapter;
    TextView textAffiche;
    int posit;

  private Button button;
  private EditText editText,editText2;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview=findViewById(R.id.listview);
        button=findViewById(R.id.button);
        editText=findViewById(R.id.editText);
        editText2=findViewById(R.id.editText2);
        textAffiche=findViewById(R.id.textAffiche);
        this.registerForContextMenu(this.textAffiche);
        button.setOnClickListener(this);
        list_parent=new ArrayList<ArrayList<achat>>();
        posit=0;
        afficher_liste();

        lst=new ArrayList<achat>();
        lst.add(new achat("kg farine",10));
        lst.add(new achat("huile l",10));
        lst.add(new achat("Tomates kg ",4));
        lst.add(new achat("levures",10));
        lst.add(new achat("Eau l ",10));
        lst.add(new achat("Extrait de vanille",1));
        lst.add(new achat("poivre noir g",100));
        lst.add(new achat("Olives noir g",200));
        list_parent.add(lst);
        /*
        lst.add("10kg farine");
        lst.add("10L huile");
        lst.add("4kg Tomates");
        lst.add("10 levures");
        lst.add("10L Eau");
        lst.add("1 Extrait de vanille");
        lst.add("100 g poivre noir");
        lst.add("200 g Olives noir ");

         */
         mon_adapter = new mon_adapter(this,lst);
        listview.setAdapter(mon_adapter);





    }
    public boolean onCreateOptionsMenu(Menu menu){
        this.getMenuInflater().inflate(R.menu.mon_menu,menu);



        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){

        int id_item=item.getItemId();
        if (id_item==R.id.item1){
            //creation_de la nv liste
            nv_liste=new ArrayList<achat>();
            if (list_parent.size()>= 3){
                afficher_message("tu ne peut pas cree plus de 3 liste ");

            }
            else {
                list_parent.add(nv_liste);
                mon_adapter = new mon_adapter(this, get_liste_cournt(list_parent.size() - 1));
                afficher_liste();
                listview.setAdapter(mon_adapter);

                afficher_message("votre creation de la nouvel liste est bien fini");
            }
        }
        if (id_item==R.id.item2){

            final AlertDialog.Builder my_builder_supp =new  AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View view_modif = inflater.inflate(R.layout.vider_la_liste,null);
            my_builder_supp.setView(view_modif);

            my_builder_supp.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    get_liste_cournt(list_parent.size()-1).clear();
                    mon_adapter = new mon_adapter(MainActivity.this,get_liste_cournt(list_parent.size()-1));
                    listview.setAdapter(mon_adapter);

                    afficher_message("la superission est bien fait");
                    dialog.dismiss();

                }
            });
            my_builder_supp.setNegativeButton("non", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    afficher_message("votre annulation est bien fait");
                    dialog.dismiss();
                }
            });
            my_builder_supp.show();


        }
        if (id_item==R.id.item3){
            AlertDialog.Builder my_dilog=new AlertDialog.Builder(this);
            my_dilog.setMessage("Achats app devloper pur vous");
            my_dilog.setTitle("A propose");
            my_dilog.setNeutralButton("oki",null);
            my_dilog.show();

        }


        return  true;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        //question4
        if (!(editText2.getText().toString().isEmpty()) &&!(editText.getText().toString().isEmpty())){
       /*
        lst.add(editText2.getText().toString()+":"+editText.getText().toString());

        */
       get_liste_cournt(list_parent.size()-1).add(new achat(editText.getText().toString(),Double.valueOf(editText2.getText().toString())));
        editText.setText("");
        editText2.setText("");


       mon_adapter mon_adapter = new mon_adapter(this,get_liste_cournt(list_parent.size()-1));
        listview.setAdapter(mon_adapter);


        }
        else {
            Toast.makeText(this,"entre les deux a la fois ",Toast.LENGTH_LONG).show();
        }



    }
    public void afficher_liste(){
        textAffiche.setText("LIST "+posit);
    }
    public ArrayList<achat> get_liste_cournt(int position){
        this.posit=position;

        if (position>=0 && position<=list_parent.size()-1){


            return list_parent.get(position);
        }
        return null;
    }
    public void afficher_message(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }


    /**
     * Called when a context menu for the {@code view} is about to be shown.
     * Unlike {@link #onCreateOptionsMenu(Menu)}, this will be called every
     * time the context menu is about to be shown and should be populated for

     * this can be found in the {@code menuInfo})).
     * <p>
     * Use {@link #onContextItemSelected(MenuItem)} to know when an
     * item has been selected.
     * <p>
     * It is not safe to hold onto the context menu after this method returns.
     *
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        this.getMenuInflater().inflate(R.menu.my_context_menu,menu);
    }
    public boolean onContextItemSelected(MenuItem item) {
        int item_cournt = item.getItemId();
        if (item_cournt == R.id.item1) {
            if (get_liste_cournt(0) == null) {
                afficher_message("cette liste n'existe pas deja vous devais cree le");
            } else {
                mon_adapter = new mon_adapter(this, get_liste_cournt(0));
                afficher_liste();
                listview.setAdapter(mon_adapter);
                afficher_message("vous etre dans la liste 1 maintenant ");
            }
        }
        if (item_cournt == R.id.item2) {
            if (get_liste_cournt(1) == null) {
                afficher_message("cette liste n'existe pas deja vous devais cree le");
            } else {
                mon_adapter = new mon_adapter(this, get_liste_cournt(1));
                afficher_liste();
                listview.setAdapter(mon_adapter);
                afficher_message("vous etre dans la liste 2 maintenant ");
            }
        }
        if (item_cournt == R.id.item3) {
            if (get_liste_cournt(2) == null) {
                afficher_message("cette liste n'existe pas deja vous devais cree le");
            } else {
                mon_adapter = new mon_adapter(this, get_liste_cournt(2));
                afficher_liste();
                listview.setAdapter(mon_adapter);
                afficher_message("vous etre dans la liste 3 maintenant ");
            }
        }
        return true;


    }

}
