package question2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Stack;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.ListIterator;

public class JPanelListe2 extends JPanel implements ActionListener, ItemListener {

    private JPanel cmd = new JPanel();
    private JLabel afficheur = new JLabel();
    private JTextField saisie = new JTextField();

    private JPanel panelBoutons = new JPanel();
    private JButton boutonRechercher = new JButton("rechercher");
    private JButton boutonRetirer = new JButton("retirer");

    private CheckboxGroup mode = new CheckboxGroup();
    private Checkbox ordreCroissant = new Checkbox("croissant", mode, false);
    private Checkbox ordreDecroissant = new Checkbox("décroissant", mode, false);

    private JButton boutonOccurrences = new JButton("occurrence");

    private JButton boutonAnnuler = new JButton("annuler");

    private TextArea texte = new TextArea();

    private List<String> liste;
    private Map<String, Integer> occurrences;
    
    private Originator<List<String>> originator = new Originator<List<String>>();
    private careTaker_Pile<List<String>> careTaker = new careTaker_Pile<List<String>>();

    

    public JPanelListe2(List<String> liste, Map<String, Integer> occurrences) {
        this.liste = liste;
        this.occurrences = occurrences;

        cmd.setLayout(new GridLayout(3, 1));

        cmd.add(afficheur);
        cmd.add(saisie);

        panelBoutons.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBoutons.add(boutonRechercher);
        panelBoutons.add(boutonRetirer);
        panelBoutons.add(new JLabel("tri du texte :"));
        panelBoutons.add(ordreCroissant);
        panelBoutons.add(ordreDecroissant);
        panelBoutons.add(boutonOccurrences);
        panelBoutons.add(boutonAnnuler);
        cmd.add(panelBoutons);


        if(liste!=null && occurrences!=null){
            afficheur.setText(liste.getClass().getName() + " et "+ occurrences.getClass().getName());
            texte.setText(liste.toString());
        }else{
            texte.setText("la classe Chapitre2CoreJava semble incomplète");
        }

        setLayout(new BorderLayout());

        add(cmd, "North");
        add(texte, "Center");

        
        
        
        
        boutonRechercher.addActionListener(this);
        boutonRetirer.addActionListener(this);
        boutonOccurrences.addActionListener(this);
        ordreCroissant.addItemListener(this);
        ordreDecroissant.addItemListener(this);
        boutonAnnuler.addActionListener(this);
        
        
        
        originator.setState(new LinkedList<String>(liste));
        // à compléter;

    }

    public void actionPerformed(ActionEvent ae) {
        try {
            boolean res = false;
            if (ae.getSource() == boutonRechercher || ae.getSource() == saisie) {
                res = liste.contains(saisie.getText());
                Integer occur = occurrences.get(saisie.getText());
                afficheur.setText("résultat de la recherche de : "+ saisie.getText() + " -->  " + res);
            } else if (ae.getSource() == boutonRetirer) {
                          
                res = retirerDeLaListeTousLesElementsCommencantPar(saisie.getText());
                    
                afficheur.setText("résultat du retrait de tous les éléments commençant par -->  "+ saisie.getText() + " : " + res);
            } else if (ae.getSource() == boutonOccurrences) {
                Integer occur = occurrences.get(saisie.getText());
                if (occur != null)
                    afficheur.setText(" -->  " + occur + " occurrence(s)");
                else
                    afficheur.setText(" -->  ??? ");
            } else if (ae.getSource() == boutonAnnuler){
                
                try{
                originator.setState(careTaker.get().getState());
                liste = new LinkedList<String>(originator.getState());
                occurrences = Chapitre2CoreJava2.occurrencesDesMots(liste);
                }
                catch(Exception ex){
                }
                
                
                
            }
            texte.setText(liste.toString());

        } catch (Exception e) {
            afficheur.setText(e.toString());
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        careTaker.add(originator.saveStateToMemento());
        
        
        if (ie.getSource() == ordreCroissant)
            Collections.sort(liste);
        else if (ie.getSource() == ordreDecroissant)
            Collections.sort(liste,new sortDecroissant());// à compléter
        
        originator.setState(new LinkedList<String>(liste));
        
        texte.setText(liste.toString());
    }

    private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe) {
        boolean resultat = false;
        // à compléter
        // à compléter
        // à compléter
        if(prefixe != null){
            ListIterator < String > listIterator = liste.listIterator();
            while(listIterator.hasNext()){
                String temp = listIterator.next();
                if (temp.length() >= prefixe.length() && temp.substring(0,prefixe.length()).equals(prefixe)){
                    listIterator.remove();
                    resultat = true;
                    occurrences.put(temp,occurrences.get(temp)-1);
                }
            }
            
        }
        if(resultat){
            careTaker.add(originator.saveStateToMemento());
            originator.setState(new LinkedList<String>(liste));
                
        }
        return resultat;
    }
    private class sortDecroissant implements Comparator<String>{
        
        public int compare(String a, String b){
            return b.compareTo(a);
        }
        
    }
    
    
    private class Memento<T>{
        private T state;
        public Memento(T state){
            this.state = state;
        }
        
        public T getState(){
            return this.state;
        }
        
    }
    
    private class careTaker_Pile<T>{
        private Stack<Memento<T>> mementoList;
        
        careTaker_Pile(){
            mementoList = new Stack<Memento<T>>();
        }
        
        public void add(Memento<T> state){
          mementoList.add(state);
       }
       
       //Comme On utilise Une pile la methode get retire le dernier �l�ment ajout� � la pile
        public Memento<T> get(){
            if (mementoList.isEmpty())
                return null;
          return mementoList.pop();
       }
       
    }
    
    private class Originator<T> {
       private T state;
    
       public void setState(T state){
          this.state = state;
       }
    
       public T getState(){
          return state;
       }
    
       public Memento<T> saveStateToMemento(){
          return new Memento(state);
       }
    
       public void getStateFromMemento(Memento<T> memento){
          state = memento.getState();
       }
    }
    

}