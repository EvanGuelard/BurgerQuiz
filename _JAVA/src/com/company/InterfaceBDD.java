package com.company;

import com.sun.deploy.security.WIExplorerUntrustedCertStore;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Created by Yohann on 06/05/2015.
 */

public class InterfaceBDD extends JFrame implements ActionListener{

    private JButton but_creer_categorie, but_modifier_categorie, but_supprimer_categorie, but_esc, but_ajout_question, but_modif_question, but_supprimer_question;

    private JButton but_ajout_theme, but_modif_theme, but_supprimer_theme;
    private int test = 0;
    private JMenuBar menu;
    private JMenu fichier, apropos, aide, deconnecter, informations;
    private JMenuItem apropos2, quitter, util, nbrCategorie;
    private GestionBDD bdd;
    private ArrayList<Integer> list_idQuestion;
    private ArrayList<Integer> list_idTheme;
    private ArrayList<Integer> list_idReponse;
    ListSelectionListener listSelectionCategorie;
    private JPanel panQuestion,panCategorie, panTheme;
    private JLabel Expli;
    private int appuieBtn;



    private JList list, list_question, list_reponse, list_theme, listTheme;

    private JLabel nomLabel, jlQuestion, jlTheme;

    private Box bCategorie;
    private String ValeurCate;

    public InterfaceBDD(GestionBDD bddtmp){
        super("Editeur de question du Burger Quiz by Yoh et Vavan");
        this.setIconImage(new ImageIcon("C:\\Users\\Yohann\\IdeaProjects\\BurgerQuizz\\src\\com\\company\\burger.png").getImage());
        this.bdd = bddtmp;

        this.creerInterface();
        this.creerBarMenu();
        this.bdd.listerCategorie();
        ArrayList<String> tmp = bdd.getList_categorie();
        list.setListData(tmp.toArray());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.pack();
        this.setVisible(true);
    }
    private void creerInterface(){

        //Categorie
        this.bCategorie = Box.createVerticalBox();
        this.panCategorie = new JPanel();
        panCategorie.setBackground(Color.white);
        panCategorie.setPreferredSize(new Dimension(300, 300));
        panCategorie.setBorder(BorderFactory.createTitledBorder("Catégorie"));
        nomLabel = new JLabel();
        this.list = new JList();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        int nbr = bdd.nombreCategorie();

        if(nbr >= 2){
            panCategorie.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(61,181,115)), "Categorie", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), new Color(61,181,115)));
        }else{
            panCategorie.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red), "Categorie", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), Color.red));

        }


        JPanel panBouton = new JPanel();
        panBouton.setBackground(Color.white);

        this.but_creer_categorie = new JButton("Ajouter");
        this.but_modifier_categorie = new JButton("Modifier");
        this.but_supprimer_categorie = new JButton("Supprimer");
        panBouton.add(but_creer_categorie);
        panBouton.add(but_modifier_categorie);
        panBouton.add(but_supprimer_categorie);

        but_creer_categorie.addActionListener(this);
        but_modifier_categorie.addActionListener(this);
        but_supprimer_categorie.addActionListener(this);
        JScrollPane jsp_cat = new JScrollPane(list);
        jsp_cat.setPreferredSize(new Dimension(200,200));

        panCategorie.add(nomLabel);
        panCategorie.add(jsp_cat);
        panCategorie.add(panBouton);

        //Theme
        this.panTheme = new JPanel();
        panTheme.setBackground(Color.white);
        panTheme.setPreferredSize(new Dimension(300, 300));
        panTheme.setBorder(BorderFactory.createTitledBorder("Theme"));
        panTheme.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,  new Color(85,85,85)), "Theme", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), new Color(85,85,85)));


        list_idTheme = new ArrayList<Integer>();

        this.jlTheme = new JLabel("Theme");
        this.list_theme = new JList();
        list_theme.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // this.listTheme = new JList();

        JPanel panBoutonTheme = new JPanel();
        panBoutonTheme.setBackground(Color.white);


        this.but_ajout_theme = new JButton("Ajouter ");
        this.but_modif_theme = new JButton("Modifier ");
        this.but_supprimer_theme = new JButton("Supprimer ");

        panBoutonTheme.add(but_ajout_theme);
        panBoutonTheme.add(but_modif_theme);
        panBoutonTheme.add(but_supprimer_theme);

        //JScrollPane jsp_theme = new JScrollPane(listTheme);
        JScrollPane jsp_theme = new JScrollPane(list_theme);

        jsp_theme.setPreferredSize(new Dimension(200, 200));

       // panTheme.add(listTheme);
        panTheme.add(jsp_theme);
       /* panTheme.add(but_ajout_theme);
        panTheme.add(but_modif_theme);
        panTheme.add(but_supprimer_theme);*/
        panTheme.add(panBoutonTheme);
        but_ajout_theme.addActionListener(this);
        but_modif_theme.addActionListener(this);
        but_supprimer_theme.addActionListener(this);


        //Question
        list_idQuestion = new ArrayList<Integer>();
        this.panQuestion = new JPanel();
        panQuestion.setBackground(Color.white);
        panQuestion.setPreferredSize(new Dimension(300, 300));
        panQuestion.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,  new Color(85,85,85)), "Question", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), new Color(85,85,85)));


        //   panQuestion.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.red));

        this.jlQuestion = new JLabel("Question");
        this.list_question = new JList();
        list_question.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);



        JPanel panBoutonQuestion = new JPanel();
        panBoutonQuestion.setBackground(Color.white);


        this.but_ajout_question = new JButton("Ajouter");
        this.but_modif_question = new JButton("Modifier");
        this.but_supprimer_question = new JButton("Supprimer");
        panBoutonQuestion.add(but_ajout_question);
        panBoutonQuestion.add(but_modif_question);
        panBoutonQuestion.add(but_supprimer_question);

        JScrollPane jsp_question = new JScrollPane(list_question);
        jsp_question.setPreferredSize(new Dimension(200, 200));

        panQuestion.add(jsp_question);

      /*  panQuestion.add(but_ajout_question);
        panQuestion.add(but_modif_question);
        panQuestion.add(but_supprimer_question);*/
        panQuestion.add(panBoutonQuestion);
        but_ajout_question.addActionListener(this);
        but_modif_question.addActionListener(this);
        but_supprimer_question.addActionListener(this);

        //REPONSE && EXPLICATION
        JPanel panRep = new JPanel();
        panRep.setBackground(Color.white);
        panRep.setPreferredSize(new Dimension(300, 75));
        panRep.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(85, 85, 85)), "Réponses", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), new Color(85, 85, 85)));

        this.list_reponse = new JList();
        list_idReponse = new ArrayList<Integer>();


        panRep.add(list_reponse);

        final JPanel panExp = new JPanel();
        panExp.setBackground(Color.white);
        panExp.setPreferredSize(new Dimension(300, 225));
        panExp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(85, 85, 85)), "Explications", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), new Color(85, 85, 85)));
        this.Expli = new JLabel();
        panExp.add(Expli);

        JPanel panRepExp = new JPanel();
        panRepExp.setLayout(new BoxLayout(panRepExp,BoxLayout.Y_AXIS));

        panRepExp.add(panRep);
        panRepExp.add(panExp);



        JPanel content = new JPanel();
        JLabel score = new JLabel("Il manque des question");
        score.setForeground(Color.red);

        content.setBackground(Color.white);
        content.add(bCategorie);
        content.add(panCategorie);
        content.add(panTheme);
        content.add(panQuestion);
        content.add(panRepExp);

        JLabel image = new JLabel(new ImageIcon("C:/Users/Yohann/IdeaProjects/BurgerQuizz/src/com/company/banniere.png"));
//image.setPreferredSize(new Dimension(300,300));
        JPanel panImage = new JPanel();
        panImage.setBackground(Color.white);

        panImage.add(image);

        /*JLabel titre = new JLabel("BURGER QUIZ BY YOH ET VAVAN");

        Font font = new Font("Arial",Font.BOLD,40);
        titre.setForeground(Color.blue);
        titre.setSize(200, 200);
        titre.setFont(font);
        titre.setHorizontalAlignment(JLabel.CENTER);*/
        this.getContentPane().add(content, BorderLayout.CENTER);
        this.getContentPane().add(score, BorderLayout.SOUTH);
        this.getContentPane().add(panImage, BorderLayout.NORTH);


        this.but_modifier_categorie.setEnabled(false);
        this.but_supprimer_categorie.setEnabled(false);
        this.but_ajout_theme.setEnabled(false);
        this.but_modif_theme.setEnabled(false);
        this.but_supprimer_theme.setEnabled(false);
        this.but_ajout_question.setEnabled(false);
        this.but_modif_question.setEnabled(false);
        this.but_supprimer_question.setEnabled(false);





        this.listSelectionCategorie = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    if (list.getSelectedIndex() == -1) {
                        System.out.println("APERO");
                        but_modifier_categorie.setEnabled(false);
                        but_supprimer_categorie.setEnabled(false);
                        but_ajout_theme.setEnabled(false);
                        mettreAjourTheme("");
                        mettreAjourQuestion(0);
                        mettreAjourReponse(0, 0);
                        Expli.setText("");
                        panQuestion.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(85, 85, 85)), "Question", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), new Color(85, 85, 85)));

                    }else {
                        mettreAjourQuestion(0);
                        mettreAjourReponse(0, 0);
                        Expli.setText("");
                        but_supprimer_categorie.setEnabled(true);
                        but_modifier_categorie.setEnabled(true);
                        but_ajout_theme.setEnabled(true);
                        System.out.println("re");
                        int nbr = bdd.nombreCategorie();

                        if(nbr >= 2){
                            panCategorie.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(61,181,115)), "Categorie", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), new Color(61,181,115)));
                        }else{
                            panCategorie.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red), "Categorie", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), Color.red));

                        }
                        ValeurCate= new String((String)list.getSelectedValue());
                        mettreAjourTheme(ValeurCate);
                        list_idTheme = bdd.getList_idTheme();
                        if (bdd.nombreTheme((String)list.getSelectedValue()) >= 2){
                            panTheme.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(61,181,115)),"Theme", TitledBorder.LEFT,TitledBorder.TOP, new Font("arial",Font.PLAIN,14), new Color(61,181,115) ));

                        }else{
                            panTheme.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.red),"Theme", TitledBorder.LEFT,TitledBorder.TOP, new Font("arial",Font.PLAIN,14), Color.red ));

                        }
                        panQuestion.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(85, 85, 85)), "Question", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), new Color(85, 85, 85)));


                    }
                }
            }
        };
        ListSelectionListener listSelectionTheme = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println("MAJ THEME");

                if (e.getValueIsAdjusting() == false) {
                    if (list_theme.getSelectedIndex() == -1) {
                        but_ajout_question.setEnabled(false);
                        but_modif_theme.setEnabled(false);
                        but_supprimer_theme.setEnabled(false);
                        but_modif_question.setEnabled(false);
                        but_supprimer_question.setEnabled(false);
                        Expli.setText("");
                    } else {
                        int idtheme= list_idTheme.get((int)list_theme.getSelectedIndex());
                        mettreAjourQuestion(idtheme);
                        //int idtheme= list_idTheme.get((int)list_theme.getSelectedIndex());

                        if (bdd.nombreQuestion(idtheme) >= 3){
                            panQuestion.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(61,181,115)), "Question", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), new Color(61,181,115)));

                        }else{
                            panQuestion.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red), "Question", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), Color.red));

                        }
                        but_ajout_question.setEnabled(true);
                        but_modif_theme.setEnabled(true);
                        but_supprimer_theme.setEnabled(true);
                        //mettreAjourQuestion(1);
                        list_idQuestion = bdd.getList_idQuestion();
                        // System.out.println(list_question.getSelectedValue());
                        System.out.println("Nom theme " + (String) list.getSelectedValue());
                        System.out.println("Valeur queje cherche :" + bdd.nombreTheme((String) list.getSelectedValue()));

                        mettreAjourQuestion(idtheme);
                        mettreAjourReponse(0, 0);

                    }
                }

            }
        };
        final ListSelectionListener listSelectionQuestion = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false){
                    if (list_question.getSelectedIndex() == -1){
                        but_modif_question.setEnabled(false);
                        but_supprimer_question.setEnabled(false);
                        Expli.setText("");

                    }else{
                        but_modif_question.setEnabled(true);
                        but_supprimer_question.setEnabled(true);
                        int idtheme= list_idTheme.get(list_theme.getSelectedIndex());
                        int id_question = list_idQuestion.get(list_question.getSelectedIndex());
                        mettreAjourReponse(id_question,idtheme);
                        Expli.setText(bdd.Explication(id_question));

                    }
                }
            }
        };

      /*  ListSelectionListener listSelectionReponse = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {


                if (e.getValueIsAdjusting() == false) {

                    if (list_question.getSelectedIndex() == -1) {

                    }else{

                        int id_question = list_idQuestion.get((int) list_question.getSelectedIndex());
                        Expli.setText(bdd.Explication(id_question));
                    }
                } else {
                }

            }
        };*/

        this.list.addListSelectionListener(listSelectionCategorie);
        list_theme.addListSelectionListener(listSelectionTheme);
        list_question.addListSelectionListener(listSelectionQuestion);
       // list_reponse.addListSelectionListener(listSelectionReponse);

        this.pack();
    }

    private void creerBarMenu(){

        this.menu = new JMenuBar();

        this.fichier = new JMenu("Fichier");
        this.apropos = new JMenu("A propos");
        this.aide = new JMenu("Aide");
        this.deconnecter = new JMenu("Déconnecter");
        this.quitter = new JMenuItem("Quitter");
        quitter.addActionListener(this);
        this.deconnecter.add(quitter);

        this.apropos2 = new JMenuItem("A propos de nous");
        apropos2.addActionListener(this);
        this.util = new JMenuItem("Comment utiliser l'interface");

        this.fichier.add(apropos2); // On initialise le menu
        this.aide.add(util);

        this.informations = new JMenu("Statistiques BDD");
        this.nbrCategorie = new JMenuItem("Nombre de catégories");
        JMenuItem nbrTheme = new JMenuItem("Nombre de Theme");
        JMenuItem nbrQuestion = new JMenuItem("Nombre de Question");
        nbrCategorie.addActionListener(this);
        nbrTheme.addActionListener(this);
        nbrQuestion.addActionListener(this);

        this.informations.add(nbrCategorie);
        this.informations.add(nbrTheme);
        this.informations.add(nbrQuestion);

        this.menu.add(fichier);
        this.menu.add(informations);
        this.menu.add(aide);
        this.menu.add(deconnecter);

        this.setJMenuBar(menu);

    }

    private void mettreAjour(){
        this.list.removeAll();
        bdd.listerCategorie();
        list.setListData(bdd.getList_categorie().toArray());
    }

    private void mettreAjourQuestion(int id_theme){
        bdd.listerQuestion(id_theme);
        list_question.removeAll();
        list_question.setListData(bdd.getList_question().toArray());
    }
    private void mettreAjourTheme(String valCat){
        bdd.listerTheme(valCat);
        list_theme.removeAll();
        list_theme.setListData(bdd.getList_theme().toArray());
    }
    private void mettreAjourReponse(int id_question, int id_reponse){
        bdd.listerReponse(id_question, id_reponse);
        list_reponse.removeAll();
        list_reponse.setListData(bdd.getList_reponse().toArray());

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == but_creer_categorie){

                String txt = JOptionPane.showInputDialog(null, "Saisir le nom de la nouvelle catégorie : ");
                if (txt != null) {
                    bdd.ajouterCategorie(txt);
                }
            int nbr = bdd.nombreCategorie();

            if(nbr >= 2){
                panCategorie.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(61,181,115)), "Categorie", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), new Color(61,181,115)));
            }else{
                panCategorie.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red), "Categorie", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), Color.red));

            }
            this.appuieBtn = 1;

            mettreAjour();

            mettreAjourQuestion(0);
            mettreAjourReponse(0, 0);

        }

        if(e.getSource() == but_supprimer_categorie){

            String tmp = (String)list.getSelectedValue();
            bdd.supprimerCategorie(tmp);
            int nbr = bdd.nombreCategorie();

          if(nbr >= 2){
                 panCategorie.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(61,181,115)), "Categorie", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), new Color(61,181,115)));
        }else{
            panCategorie.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red), "Categorie", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), Color.red));

        }
            mettreAjour();
            mettreAjourTheme(tmp);

        }

        if(e.getSource() == but_modifier_categorie){
            String tmp = (String)list.getSelectedValue();
            String txt = JOptionPane.showInputDialog(null, "Saisir la modification de la catégorie : ",tmp);
            if(txt != null){
                bdd.modifierCategorie(tmp, txt);
                mettreAjour();
            }
        }

        if(e.getSource() == but_modif_theme){
            int idtheme= list_idTheme.get((int)list_theme.getSelectedIndex());

            String tmp = (String)list.getSelectedValue();
            if(tmp == null){
                JOptionPane.showMessageDialog(null, "IL FAUT CLIC sur une categorie");

            }

                JTextField Theme1 = new JTextField(bdd.Theme1(idtheme));
                JTextField Theme2 = new JTextField(bdd.Theme2(idtheme));

                int choix = JOptionPane.showOptionDialog(null,
                        new Object[] {"Saisir Theme1 :", Theme1, "Saisir theme2 :", Theme2},
                        "Modification Theme",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,  null, null);
                if (choix == 0 && Theme1.getText() != null && !Theme2.getText().isEmpty()){
                    bdd.modifTheme(Theme1.getText(),idtheme,Theme2.getText());
                    System.out.println(Theme2.getText());
                    System.out.println("dans le if de modif");

                }else{
                    JOptionPane.showMessageDialog(null, "La modification n'a pus se faire car vous n'avez pas saisi de valeur dans un des deux champs.", "Erreur",JOptionPane.ERROR_MESSAGE);

                }
                mettreAjourTheme((String) list.getSelectedValue());
                mettreAjourQuestion(0);
                mettreAjourReponse(0, 0);
        }

        if (e.getSource() == but_supprimer_theme){
            int idtheme= list_idTheme.get((int)list_theme.getSelectedIndex());

            bdd.supprimerTheme(idtheme);
            mettreAjourTheme((String) list.getSelectedValue());
            mettreAjourQuestion(0);
            mettreAjourReponse(0,0);

        }

        if(e.getSource()== but_ajout_theme){
            String tmp = (String)list.getSelectedValue();
            if(tmp == null){
                JOptionPane.showMessageDialog(null, "IL FAUT CLIC sur une categorie");

            }

            int retour = 1;
            while( retour == 1){
                JTextField Theme1 = new JTextField();
                JTextField Theme2 = new JTextField();

                int choix = JOptionPane.showOptionDialog(null,
                        new Object[] {"Saisir Theme1 :", Theme1, "Saisir theme2 :", Theme2},
                        "Modification Theme",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,  null, null);
                bdd.createTheme(tmp, Theme1.getText(), Theme2.getText());
                    mettreAjourTheme(tmp);

                    retour = JOptionPane.CLOSED_OPTION;

                //}


            }
            mettreAjourTheme((String) list.getSelectedValue());
            mettreAjourQuestion(0);
            mettreAjourReponse(0,0);

        }


        if(e.getSource()==but_ajout_question){

            int idtheme= list_idTheme.get((int)list_theme.getSelectedIndex());

            questionJDialog test = new questionJDialog(bdd,idtheme);
            test.dispose();
            mettreAjourQuestion(idtheme);

            System.out.println("MAJ");
            mettreAjourReponse(0, 0);

        }
        if(e.getSource() == but_modif_question){
            int id_question = list_idQuestion.get((int) list_question.getSelectedIndex());
            int idtheme = list_idTheme.get((int) list_theme.getSelectedIndex());
            String valuequestion = new String((String)list_question.getSelectedValue());

            System.out.println("MODIF");

            questionJDialog modif = new questionJDialog(bdd,idtheme, valuequestion, bdd.Reponse(id_question), id_question);

            mettreAjourQuestion(idtheme);
            mettreAjourReponse(0, 0);

        }


        if (e.getSource() == but_supprimer_question){
            int id_question = list_idQuestion.get((int) list_question.getSelectedIndex());
            int idtheme = list_idTheme.get((int) list_theme.getSelectedIndex());

            bdd.deleteQuestion(id_question);
            mettreAjourQuestion(idtheme);
            mettreAjourReponse(0, 0);
        }

        if (e.getSource() == apropos2){
            System.out.println("TEST");
            ImageIcon img = new ImageIcon("C:\\Users\\Yohann\\IdeaProjects\\BurgerQuizz\\src\\com\\company\\about.png");
            JOptionPane.showMessageDialog(null, "", "", JOptionPane.ERROR_MESSAGE, img);
        }
        if(e.getSource() == quitter)System.exit(0);
        if (e.getSource() == nbrCategorie){
            int tmp = bdd.nombreCategorie();

            JOptionPane.showMessageDialog(null, "Il y a actuellement "+ tmp + " catégories dans la BDD");
        }

    }
}
