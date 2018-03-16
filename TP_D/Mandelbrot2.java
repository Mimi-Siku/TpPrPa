// -*- coding: utf-8 -*-

import java.awt.Color;

//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ExecutorCompletionService;
//import java.util.concurrent.CompletionService;
import java.util.concurrent.*;

public class Mandelbrot2
{
    final static Color noir =  new Color(0, 0, 0);
    final static Color blanc =  new Color(255, 255, 255);
    final static int taille = 500;   // nombre de pixels par ligne (et par colonne)
    // Il y a donc taille*taille pixels blancs ou noirs à déterminer
    final static Picture image = new Picture(taille, taille);

    final static double xc = -.5 ;
    final static double yc = 0 ; // Le point (xc,yc) est le centre de l'image
    final static double region = 2;
    // La région du plan considérée est un carré de côté égal à 2.
    // Elle s'étend donc du point (xc - 1, yc - 1) au point (xc + 1, yc + 1)
    // c'est-à-dire du point (-1.5, -1) en bas à gauche au point (0.5, 1) en haut
    // à droite

    final static int max = 20_000; 
    // C'est le nombre maximum d'itérations pour déterminer la couleur d'un pixel
    
    public static void main(String[] args)  
    {
        final long startTime = System.nanoTime();
        final long endTime;
        
        ExecutorService executeur = Executors.newFixedThreadPool(4);
        CompletionService<Long> ecs = new ExecutorCompletionService<Long>(executeur);
        int i = 0;
        while (i < taille)
        {
        	ecs.submit(new TraceLigne(i));
        	i++;
        }

        for (int k = 0; k < taille; k++)
        {
        	try
        	{
        		ecs.take();
        	}
        	catch(InterruptedException e){}
        }
        
        endTime = System.nanoTime();
        final long duree = (endTime - startTime) / 1_000_000 ;
        System.out.println("Durée = " + duree + " ms.");
        image.show();
    }

    // La fonction colorierPixel(i,j) colorie le pixel (i,j) de l'image
    public static void colorierPixel(int i, int j) 
    {
        double a = xc - region/2 + region*i/taille;
        double b = yc - region/2 + region*j/taille;
        // Le pixel (i,j) correspond au point (a,b)
        if (mandelbrot(a, b, max)) image.set(i, j, noir);
        else image.set(i, j, blanc); 
    }

    // La fonction mandelbrot(a, b, max) détermine si le point (a,b) est noir
    public static boolean mandelbrot(double a, double b, int max) 
    {
        double x = 0;
        double y = 0;
        for (int t = 0; t < max; t++) 
        {
            if (x*x + y*y > 4.0) return false; // Le point (a,b) est blanc
            double nx = x*x - y*y + a;
            double ny = 2*x*y + b;
            x = nx;
            y = ny;
        }
        return true; // Le point (a,b) est noir
    }
    
    public static class TraceLigne implements Callable<Long>
    {
    	int line;

    	public TraceLigne(int line)
    	{
    		this.line = line;
    	}
    	
    	public Long call()
    	{
            for (int j = 0; j < Mandelbrot2.taille; j++) 
            {
            	Mandelbrot2.colorierPixel(line, j);
            }
            return (long)0;
    	}
    }
}


/* 
  $ javac Mandelbrot.java
  $ java Mandelbrot
  Durée = 17831 ms.
  $ make
  javac *.java 
  jar cvmf MANIFEST.MF Mandelbrot.jar *.class 
  ...
  $ java -jar Mandelbrot.jar
  Durée = 17869 ms.
*/
