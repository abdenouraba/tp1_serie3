package com.example.tp1_serie3;

public class achat {
    private String achat;
    private double quantite;
    public achat(String achat,double quantite){
        this.achat=achat;
        this.quantite=quantite;
        }
        public String getAchat(){return achat;}
        public double getquantite(){return quantite;}
        public void set_Achat(String achat){this.achat=achat;}
        public void set_quantite(double quantite){this.quantite=quantite;}
}
