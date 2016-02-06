package com.company;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Yohann on 07/05/2015.
 */
public class GestionBDD {

    private ArrayList<String> list_categorie;
    private ArrayList<String> list_theme;
    private ArrayList<String> list_question;
    private ArrayList<String> list_reponse;
    private ArrayList<ElementTheme> listTheme;
    private ArrayList<Integer> list_idQuestion;
    private ArrayList<Integer> list_idTheme;
    private ArrayList<Integer> list_idReponse;
    private Connection con;
    private PreparedStatement pst;
    private Statement st;
    private ResultSet rs;


    private String driver, url, user, passwd;

    public GestionBDD(String adresse, String nomBdd, String utilisateur, String mdp) {

        this.list_categorie = new ArrayList<String>();
        this.list_theme = new ArrayList<String>();
        this.list_question = new ArrayList<String>();
        this.list_reponse = new ArrayList<String>();
        this.list_idQuestion = new ArrayList<Integer>();
        this.list_idTheme = new ArrayList<Integer>();
        this.list_idReponse = new ArrayList<Integer>();


        this.driver = ("com.mysql.jdbc.Driver");
        this.url = ("jdbc:mysql://"+adresse+"/"+nomBdd);
        this.user = (utilisateur);
        this.passwd = (mdp);
        try {
            connexion();

        }catch (Exception e1){
            JOptionPane.showMessageDialog(null, e1,"Errer",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

    }

    public void connexion() throws Exception{
        this.con = null;
        this.st = null;
        this.rs = null;
        try{
            con = DriverManager.getConnection(url, user, passwd);
        }catch (SQLException e){
            throw new Exception(e);
        }

    }

    public void listerCategorie() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM categorie");

            list_categorie.clear();

            while (rs.next()) {
                String nom = new String(rs.getString(1));
                list_categorie.add(nom);

            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (InstantiationException e) {

            e.printStackTrace();
        } catch (IllegalAccessException e) {

            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void listerTheme(String categorie){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);
            pst = con.prepareStatement("SELECT * FROM `theme` WHERE Nom_Categorie = (?) ;");
            pst.setString(1, categorie);
            rs = pst.executeQuery();


            list_theme.clear();
            list_idTheme.clear();
            while (rs.next()) {


                int id = rs.getInt(1);
                list_idTheme.add(id);
                String Theme1 = new String(rs.getString(2));
                String Theme2 = new String(rs.getString(3));
                //   ElementTheme tmp = new ElementTheme(id,Theme1,Theme2,Nom_cat );


                String tmp = new String(Theme1 + " - " + Theme2);

                list_theme.add(tmp);
            }

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
            }


        }


    }


    public void ajouterCategorie(String ajout) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);
            pst = con.prepareStatement("INSERT INTO categorie(Nom_Categorie) VALUES (?)");
            pst.setString(1, ajout);
            pst.executeUpdate();

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage(), "Vous ne pouvez rajoueter une catégorie déjà existante ! ", JOptionPane.ERROR_MESSAGE);

        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
            }


        }
    }


    public void modifierCategorie( String motAmodif, String modification) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);
            pst = con.prepareStatement("INSERT INTO categorie(Nom_Categorie) VALUES (?)");
            pst.setString(1, "tmp");
            pst.executeUpdate();
            pst = con.prepareStatement("SELECT `Id_Theme` FROM `Theme` WHERE Nom_Categorie = (?) ;");
            pst.setString(1, motAmodif);
            rs = pst.executeQuery();

            while (rs.next()){

                modiferTheme("tmp",rs.getInt(1));
            }

            pst = con.prepareStatement("UPDATE  categorie SET Nom_Categorie = (?) WHERE `categorie`.`Nom_Categorie` = (?) ;");
            pst.setString(1, modification);
            pst.setString(2, motAmodif);


            pst.executeUpdate();
            pst = con.prepareStatement("SELECT `Id_Theme` FROM `Theme` WHERE Nom_Categorie = (?) ;");
            pst.setString(1, "tmp");
            rs = pst.executeQuery();

            while (rs.next()){

                modiferTheme(modification, rs.getInt(1));
            }
            pst = con.prepareStatement("DELETE FROM categorie WHERE Nom_Categorie = (?) ;");
            pst.setString(1, "tmp");
            pst.executeUpdate();


        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
            }


        }
    }

    public void supprimerCategorie(String motAdelete) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);
            pst = con.prepareStatement("SELECT `Id_Theme` FROM `Theme` WHERE Nom_Categorie = (?) ;");
            pst.setString(1, motAdelete);
            rs = pst.executeQuery();
            list_question.clear();

            while (rs.next()){
                supprimerTheme(rs.getInt(1));
            }



            pst = con.prepareStatement("DELETE FROM categorie WHERE Nom_Categorie = (?) ;");
            pst.setString(1, motAdelete);
            pst.executeUpdate();

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
            }


        }
    }


    public ArrayList<String> getList_categorie() {
        return this.list_categorie;
    }
    public ArrayList<Integer> getList_idTheme() {
        return this.list_idTheme;
    }


    public void supprimerTheme(int id_theme){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);
            pst = con.prepareStatement("SELECT `Id_Question` FROM `question` WHERE Id_Theme = (?) ;");
            pst.setInt(1, id_theme);
            rs = pst.executeQuery();
            list_question.clear();

            while (rs.next()){
                deleteQuestion(rs.getInt(1));
            }
            pst = con.prepareStatement("DELETE FROM theme WHERE Id_Theme = (?) ;");
            pst.setInt(1, id_theme);
            pst.executeUpdate();


        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
            }


        }
    }
    public ArrayList<ElementTheme> getListTh(){
        return this.listTheme;
    }

    public void createTheme(String cate, String Theme1, String Theme2){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);
            pst = con.prepareStatement("INSERT INTO `theme` (`Id_Theme`, `Theme1`, `Theme2`, `Nom_Categorie`) VALUES (NULL, (?), (?), (?));");
            pst.setString(1, Theme1);
            pst.setString(2,Theme2);
            pst.setString(3, cate);


            pst.executeUpdate();

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
            }


        }


    }

    public void modiferTheme(String modif, int id_theme){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {


            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);
            pst = con.prepareStatement("UPDATE `theme` SET `Nom_Categorie` = (?) WHERE `theme`.`Id_Theme` = (?); ");
            pst.setString(1, modif);

            pst.setInt(2, id_theme);
            pst.executeUpdate();




        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
            }


        }
    }

    public void modifTheme(String theme1, int id_theme, String theme2){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {


            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);
            pst = con.prepareStatement("UPDATE `theme` SET `Theme1`=(?),`Theme2`=(?) WHERE `Id_Theme`=(?) ; ");
            pst.setString(1, theme1);
            pst.setString(2, theme2);

            pst.setInt(3, id_theme);
            pst.executeUpdate();




        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
            }


        }
    }

    public ArrayList<String> getList_theme() {
        return this.list_theme;
    }

    public void listerQuestion(int id_theme) {
        if(id_theme == 0){
            list_question.clear();
        }else {
            Connection con = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            try {

                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(url, user, passwd);


                pst = con.prepareStatement("SELECT * FROM `question` WHERE Id_Theme = (?) ;");
                pst.setInt(1, id_theme);
                rs = pst.executeQuery();


                list_question.clear();
                list_idQuestion.clear();
                while (rs.next()) {
                    String tmp = new String(rs.getString(1));
                    int rep = rs.getInt(2);
                    // String expli = new String(rs.getString(3));
                    int id = rs.getInt(4);
                    list_idQuestion.add(id);


                    // int id = rs.getInt(1);

                    // String Theme1 = new String(rs.getString(2));
                    // String Theme2 = new String(rs.getString(3));
                    //String tmp = Theme1 + Theme2;
                    list_question.add(tmp);

                }


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pst != null) pst.close();
                    if (con != null) con.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public ArrayList<Integer> getList_idQuestion(){return this.list_idQuestion;}

    public void createQuestion(String intitu_question, int reponse, int id_theme, String explication){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);
            pst = con.prepareStatement("INSERT INTO `question` (`Intitule_Question`, `Reponse`, `Explication`, `Id_Theme`) VALUES ((?),(?), (?), (?));");
            pst.setString(1, intitu_question);
            pst.setInt(2, reponse);
            pst.setString(3, explication);
            pst.setInt(4, id_theme);


            pst.executeUpdate();

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
            }


        }

    }

    public void modifQuestion(String intitu_question, int id_question, String expli, int reponse){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);
            pst = con.prepareStatement("UPDATE `question` SET `Intitule_Question` = (?), `Reponse` = (?), `Explication` = (?) WHERE `question`.`Id_Question` = (?); ");
            pst.setString(1, intitu_question);
            pst.setInt(2, reponse);
            pst.setString(3, expli);
            pst.setInt(4,id_question);



            pst.executeUpdate();

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
            }


        }

    }

    public void deleteQuestion(int id_question){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);
            pst = con.prepareStatement("DELETE FROM `question` WHERE `question`.`Id_Question` = (?)");
            pst.setInt(1, id_question);

            pst.executeUpdate();

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
            }


        }


    }

    public ArrayList<String> getList_question() {
        return this.list_question;
    }

    public void modifReponse(int id_question, int reponse){

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);
            pst = con.prepareStatement("UPDATE `question` SET `Reponse` = (?) WHERE `question`.`Id_Question` = (?); ");
            pst.setInt(1, reponse);
            pst.setInt(2, id_question);



            pst.executeUpdate();

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
            }


        }


    }

    public void listerReponse(int id_question, int id_theme){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);


            pst = con.prepareStatement("SELECT theme.Theme1, theme.Theme2, question.Reponse FROM theme, question WHERE Id_Question = (?) AND theme.Id_Theme = (?)  ;");
            pst.setInt(1,id_question );
            pst.setInt(2,id_theme);
            rs = pst.executeQuery();


            list_reponse.clear();
            list_idReponse.clear();
            while (rs.next()) {
                String Theme1 = new String(rs.getString(1));
                String Theme2 = new String(rs.getString(2));

                int tmp = rs.getInt(3);
                list_idReponse.add(tmp);

                if(tmp == 0){
                    list_reponse.add("Les deux");
                }
                if (tmp == 1){

                    list_reponse.add(Theme1);
                }
                if (tmp == 2){
                    list_reponse.add(Theme2);
                }




            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public ArrayList<String> getList_reponse() {
        return this.list_reponse;
    }
    public ArrayList<Integer> getList_idReponse(){return this.list_idReponse;}

    public String Theme1(int id_theme){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String tmp = new String();

        try {

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);


            pst = con.prepareStatement("SELECT Theme1 FROM theme WHERE Id_Theme = (?)  ;");
            pst.setInt(1,id_theme);
            rs = pst.executeQuery();


            while (rs.next()) {
                tmp = rs.getString(1);


            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tmp;

    }
    public String Theme2(int id_theme){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String tmp = new String();

        try {

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);


            pst = con.prepareStatement("SELECT Theme2 FROM theme WHERE Id_Theme = (?)  ;");
            pst.setInt(1,id_theme);
            rs = pst.executeQuery();


            while (rs.next()) {
                tmp = rs.getString(1);


            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tmp;

    }

    public int Reponse(int id_question){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int tmp = 0;

        try {

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);


            pst = con.prepareStatement("SELECT `Reponse` FROM `question` WHERE `Id_Question` = (?)   ;");
            pst.setInt(1,id_question);
            rs = pst.executeQuery();


            while (rs.next()) {
                tmp = rs.getInt(1);
                return tmp;


            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tmp;
    }

    public int nombreCategorie(){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int tmp = 0;
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);
            pst = con.prepareStatement("SELECT count(*) FROM `categorie`  ;");
            rs = pst.executeQuery();
            while (rs.next()) {
                tmp = rs.getInt(1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tmp;
    }
    public int nombreTheme(String valCat){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int tmp = 0;
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);
            pst = con.prepareStatement("SELECT count(*) FROM `theme` Where `Nom_Categorie` = (?) ;");
            pst.setString(1, valCat);
            rs = pst.executeQuery();
            while (rs.next()) {
                tmp = rs.getInt(1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tmp;
    }
    public int nombreQuestion(int idtheme){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int tmp = 0;
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);
            pst = con.prepareStatement("SELECT count(*) FROM `question` WHERE `Id_Theme` = (?) ;");
            pst.setInt(1, idtheme);
            rs = pst.executeQuery();
            while (rs.next()) {
                tmp = rs.getInt(1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tmp;
    }

    public String Explication(int idQuestion){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String explication = new String();
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, passwd);
            pst = con.prepareStatement("SELECT `Explication` FROM `question` WHERE `Id_Question` = (?) ;");
            pst.setInt(1, idQuestion);
            rs = pst.executeQuery();
            while (rs.next()) {
                explication = rs.getString(1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return explication;
    }





}
