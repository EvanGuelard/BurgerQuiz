package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JTextField addressBDD = new JTextField("127.0.0.1");

        JTextField nomBDD = new JTextField("burger");
        JTextField utilisateur = new JTextField("root");
        JPasswordField motDePasse = new JPasswordField();
        int choix = JOptionPane.showOptionDialog(null,
                new Object[] {"Adresse de la BDD",addressBDD,"Nom de la BDD",nomBDD,"Utilisateur :", utilisateur, "Mot de passe :", motDePasse},
                "Connexion",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,  null, null);

        GestionBDD bdd = new GestionBDD(addressBDD.getText(),nomBDD.getText(),utilisateur.getText(),motDePasse.getText());
        switch (choix) {
            case 0 : if (utilisateur.getText().equalsIgnoreCase("root") && motDePasse.getText().equals(""))
                JOptionPane.showMessageDialog(null, "Vous êtes connecté");
            else
                JOptionPane.showMessageDialog(null,
                        new String[] {"Utilisateur inconnu", "ou mot de passe incorrecte"},
                        "Connexion en mode privée refusée",
                        JOptionPane.ERROR_MESSAGE);
                break;
            default : JOptionPane.showMessageDialog(null, "Non connecté...", "ATTENTION", JOptionPane.ERROR_MESSAGE); break;
        }

        InterfaceBDD fenetre= new InterfaceBDD(bdd);

    }
}
