// -*- coding: utf-8 -*-

import java.lang.Math; 

public class Calcul_PiSurQuatre{
    public static void main(String[] args) {
        int nombreDeTirages = 1_000_000 ;
        int tiragesDansLeDisque = 0 ;
        double x, y, resultat ;
      
        /* Pour modifier éventuellement le nombre de tirage à chaque exécution */
        if (args.length>0) {
            try { nombreDeTirages = Integer.parseInt(args[0]); } 
            catch(NumberFormatException nfe) { 
                System.err.println 
                    ("Usage : java Calcul_PiSurQuatre <nb de tirages>"); 
                System.err.println(nfe.getMessage()); 
                System.exit(1); 
            }
        }

        System.out.println("Nombre de tirages: " + nombreDeTirages) ;
		final long startTime = System.nanoTime();
		final long endTime;

        /* Début du calcul */
        for (int i = 0; i < nombreDeTirages; i++) {
            x = Math.random() ;
            y = Math.random() ;
            if (x * x + y * y <= 1) tiragesDansLeDisque++ ;
        }
        
        /* Fin du calcul */
        resultat = (double) tiragesDansLeDisque / nombreDeTirages ;
        System.out.println("Estimation de Pi/4: " + resultat) ;
        System.out.println("Pourcentage d'erreur: "
                           + 100 * Math.abs(resultat-Math.PI/4)/(Math.PI/4)
                           + " %");
        
        /* Affichage de la durée du calcul */
		endTime = System.nanoTime();
		final long duree = (endTime - startTime) / 1_000_000 ;
		System.out.println("Durée du calcul: " + (long) duree + " ms.");
    }
}

/* Avec un million de tirages, l'erreur doit être en général < 0.1 %
  $ make
  javac *.java
  $ java Calcul_PiSurQuatre
  Nombre de tirages: 1000000
  Estimation de Pi/4: 0.785013
  Pourcentage d'erreur: 0.049040526881574516 %
  Durée du calcul: 53 ms.
  $ java Calcul_PiSurQuatre
  Nombre de tirages: 1000000
  Estimation de Pi/4: 0.786132
  Pourcentage d'erreur: 0.09343497817430192 %
  Durée du calcul: 52 ms.
  $ java Calcul_PiSurQuatre
  Nombre de tirages: 1000000
  Estimation de Pi/4: 0.785176
  Pourcentage d'erreur: 0.028286722302388278 %
  Durée du calcul: 59 ms.
  $ java Calcul_PiSurQuatre
  Nombre de tirages: 1000000
  Estimation de Pi/4: 0.785571
  Pourcentage d'erreur: 0.022006239714655006 %
  Durée du calcul: 56 ms.
  $ java Calcul_PiSurQuatre
  Nombre de tirages: 1000000
  Estimation de Pi/4: 0.785324
  Pourcentage d'erreur: 0.009442777040303161 %
  Durée du calcul: 55 ms.
  $ java Calcul_PiSurQuatre
  Nombre de tirages: 1000000
  Estimation de Pi/4: 0.785596
  Pourcentage d'erreur: 0.025189338576485493 %
  Durée du calcul: 53 ms.
  $ java Calcul_PiSurQuatre
  Nombre de tirages: 1000000
  Estimation de Pi/4: 0.785309
  Pourcentage d'erreur: 0.01135263635740428 %
  Durée du calcul: 51 ms.
  $ java Calcul_PiSurQuatre
  Nombre de tirages: 1000000
  Estimation de Pi/4: 0.785345
  Pourcentage d'erreur: 0.006768973996367247 %
  Durée du calcul: 53 ms.
*/
