
import java.util.Random;

public class MyThread extends Thread
{
	private int nbTirages;
	private int tiragesDansLeDisque;
	
	public MyThread(int nbTirages) {
		this.nbTirages = nbTirages;
		this.tiragesDansLeDisque = 0;
	}
	public int getNbTirages() {
		return nbTirages;
	}
	public int getTiragesDansLeDisque() {
		return tiragesDansLeDisque;
	}
	
	public void run() 
	{
		double x, y;
		Random r = new Random();
		for (int i = 0; i < nbTirages; i++) 
		{
			x = r.nextDouble();
			y = r.nextDouble();
			if (x * x + y * y <= 1) tiragesDansLeDisque++ ;
		}
	}
}
