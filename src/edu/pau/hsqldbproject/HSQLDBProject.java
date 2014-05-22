package edu.pau.hsqldbproject;

public class HSQLDBProject {

    public static void main(String[] args) 
    {
        Pencere pncr = new Pencere();
        pncr.setBounds(0,0,480,410);
        pncr.setVisible(true);
        pncr.setResizable(false); // Ekran boyutu değiştirilemiyor.
    }
}