package com.company;

import com.sun.deploy.panel.RadioPropertyGroup;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;

/**
 *  @Author Yohann on 04/06/2015.
 *
 */
public class questionJDialog extends JDialog implements ActionListener{

    private JButton btnVal, btnCancel, btnValModif;
    private JTextField jtfQuestion;
    private JTextArea jtaExplication;
    private JPanel panQuestion, panReponse, panExplication;
    private JRadioButton Theme1, Theme2, LesDeux;
    private int Reponse;
    private GestionBDD bdd;
    private int idtheme, idquestion;
    private int modif;

    public questionJDialog(GestionBDD bdd, int idTheme, String question, int rep, int idQuestion ) {
        this(bdd, idTheme);
        System.out.println("DANS LE MODIFICATION");
        this.Reponse=rep;
        this.jtfQuestion.setText(question);
        if (rep == 0){
            this.LesDeux.setSelected(true);

        }else if (rep == 1){
            this.Theme1.setSelected(true);
        }else{
            this.Theme2.setSelected(true);
        }
        this.modif = 1;
        System.out.println("MODIFICATION");
        System.out.println(Reponse);
        this.idquestion = idQuestion;



    }

    public questionJDialog(GestionBDD bdd, int idTheme ){
        super(new JFrame(), "Ajouter", true);
        this.setSize(new Dimension(900, 400));
        this.setLocationRelativeTo(null);
        this.bdd = bdd;
        this.idtheme = idTheme;
        this.Reponse = -1; // On donne une valeur par défault car sinon il y a un problème quand on veut ajouter plusieurs catégories
        this.modif = 0;
        /******************************/
        /* PANEL AJOUTER UNE QUESTION */
        /*****************************/

        this.panQuestion = new JPanel();
        panQuestion.setBackground(Color.white);
       // panQuestion.setBorder(BorderFactory.createTitledBorder("Ajouter une question"));
        panQuestion.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(85, 85, 85)), "Ajouter une question", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), new Color(85, 85, 85)));
        panQuestion.setPreferredSize(new Dimension(300,75));
        jtfQuestion = new JTextField(20);
        panQuestion.add(jtfQuestion);


        /******************************/
        /* PANEL AJOUTER UNE REPONSE */
        /*****************************/
        this.panReponse = new JPanel();
        panReponse.setBackground(Color.white);
        panReponse.setBorder(BorderFactory.createTitledBorder("Ajouter une réponse"));
        panReponse.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(85, 85, 85)), "Ajouter une réponse", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), new Color(85, 85, 85)));
        panReponse.setPreferredSize(new Dimension(300, 75));

        ButtonGroup group = new ButtonGroup();
        this.Theme1 = new JRadioButton(bdd.Theme1(idtheme));
        this.Theme1.addActionListener(this);

        this.Theme2 = new JRadioButton(bdd.Theme2(idtheme));
        this.Theme2.addActionListener(this);

        this.LesDeux = new JRadioButton("Les deux");
        this.LesDeux.addActionListener(this);

        group.add(Theme1);
        group.add(Theme2);
        group.add(LesDeux);
        JPanel panBtnRep = new JPanel();
        panBtnRep.setBackground(Color.white);
        panBtnRep.add(Theme1);
        panBtnRep.add(Theme2);
        panBtnRep.add(LesDeux);
        Theme1.setBackground(Color.white);
        Theme2.setBackground(Color.white);
        LesDeux.setBackground(Color.white);

        panReponse.add(panBtnRep);

        /*********************************/
        /* PANEL AJOUTER UNE EXPLICATION */
        /*********************************/
        this.panExplication = new JPanel();
        panExplication.setBackground(Color.white);
        //panExplication.setBorder(BorderFactory.createTitledBorder("Ajouter une explication (facultatif) :"));
        panExplication.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(85, 85, 85)), "Ajouter une explication (facultatif) :", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), new Color(85, 85, 85)));

        this.jtaExplication = new JTextArea(5,50);
       // jtaExplication.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(85, 85, 85)), "Zone de saisie :", TitledBorder.LEFT, TitledBorder.TOP, new Font("arial", Font.PLAIN, 14), new Color(85, 85, 85)));
        JScrollPane jspExplication = new JScrollPane();
        jspExplication.add(jtaExplication);
        panExplication.add(jtaExplication);


        /*********************************/
        /* PANEL BOUTONS                 */
        /*********************************/

        JPanel panBtn = new JPanel();
        this.btnVal = new JButton("Valider");
        this.btnCancel = new JButton("Annuler");
        panBtn.add(btnVal);
        panBtn.add(btnCancel);
        this.btnVal.addActionListener(this);
        this.btnCancel.addActionListener(this);
        panBtn.setBackground(Color.white);





        JPanel content = new JPanel();
        content.add(panQuestion);
        content.add(panReponse);
        content.setBackground(Color.white);

        this.getContentPane().setBackground(Color.white);

        this.getContentPane().add(content, BorderLayout.NORTH);
        this.getContentPane().add(panExplication,BorderLayout.CENTER);
        this.getContentPane().add(panBtn, BorderLayout.SOUTH);


        setVisible(true) ;

       // this.pack();

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVal){

            if ((Reponse ==0 || Reponse ==1  || Reponse ==2) && !jtfQuestion.getText().isEmpty()){
                System.out.println("ça fonctionne,apero");
                if (modif == 0){
                    bdd.createQuestion(jtfQuestion.getText(), Reponse, idtheme, jtaExplication.getText());

                }else{
                    bdd.modifQuestion(jtfQuestion.getText(), idquestion, jtaExplication.getText(),Reponse);
                    System.out.println("INTITU :"+jtfQuestion.getText()+"IDQUESTION:"+idquestion+"REPONSE :"+Reponse);
                }
                setVisible(false);

            }else{
                JOptionPane.showMessageDialog(null,"Il faut remplir le champ question ainsi que clicker sur un bouton pour la réponse","Erreur",JOptionPane.ERROR_MESSAGE);
            }

        }
        if (e.getSource() == Theme1){
            System.out.println("ESSAI RADIO BTN THEME1");
            this.Reponse = 1;

        }
        if (e.getSource() == Theme2){
            System.out.println("ESSAI RADIO BTNTHEME2");
            this.Reponse = 2;

        }if (e.getSource() == LesDeux){
            System.out.println("ESSAI RADIO BTN LES DEUX");
            this.Reponse = 0;

        }

        if (e.getSource() == btnCancel){
            this.setVisible(false);
        }
    }
}
