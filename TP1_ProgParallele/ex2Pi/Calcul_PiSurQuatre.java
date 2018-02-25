
import java.lang.Math;


public class Calcul_PiSurQuatre
{
	public static void main(String[] args) 
	{
		int nombreDeTirages = 100000000;
		double resultat = 0; // init à 0
		if (args.length>0) {
			try { nombreDeTirages = Integer.parseInt(args[0]); }
			catch(NumberFormatException nfe) {
				System.err.println("Usage : java Calcul_PiSurQuatre <nb de tirages>");
				System.err.println(nfe.getMessage());
				System.exit(1);
			}
		}
		
		System.out.println("Nombre de tirages: " + nombreDeTirages);
		final long startTime = System.nanoTime();
		final long endTime;
		
		// Threads work
		int nbThreads = 10;
		MyThread[] thread = new MyThread[nbThreads];
		for (int i = 0; i < nbThreads; i++)
		{
			thread[i] = new MyThread(nombreDeTirages/nbThreads);
			thread[i].start();
		}
		for (int j = 0; j < nbThreads; j++)
		{
			try{
				thread[j].join();
			}
			catch (Exception e) {System.out.println(e.getMessage());}
		}
		// Calcul du résultat
		for (MyThread th : thread)
		{
			resultat += th.getTiragesDansLeDisque();
		}
		resultat = (double) resultat / nombreDeTirages;
		System.out.println("Estimation de Pi/4: " + resultat) ;
		System.out.println("Pourcentage d'erreur: " 
		+ 100 * Math.abs(resultat-Math.PI/4)/(Math.PI/4) 
		+ " %");
		endTime = System.nanoTime();
		final long duree = (endTime - startTime) / 1000000 ;
		System.out.println("Durée du calcul: " + (long) duree + " ms.");
	}
}
