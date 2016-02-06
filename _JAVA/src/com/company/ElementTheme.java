package com.company;

/**
 * Created by Yohann on 26/05/2015.
 */
public class ElementTheme {

    private int id_theme;
    private String Theme1, Theme2, Nom_Categorie;

    public ElementTheme(int id_theme, String Theme1, String Theme2, String Nom_Categorie){
        this.id_theme = id_theme;
        this.Theme1 = Theme1;
        this.Theme2 = Theme2;
        this.Nom_Categorie = Nom_Categorie;
    }

    public int getId_theme(){
        return id_theme;
    }

    public String getTheme1(){
        return Theme1;
    }

    public String getTheme2(){
        return Theme2;
    }

    public String getNom_Categorie(){
        return Nom_Categorie;
    }

    public String toString(){
        String tmp = new String();
        tmp = ( Theme1 + " ou " + Theme2 + " ou les deux.");
        return tmp;
    }




}
