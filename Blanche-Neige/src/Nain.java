
public class Nain extends Thread {
private static Object Blanche_Neige = new Object();

void print(){
	System.out.println(Thread.currentThread().getName()+"_veux a ressource");
}
public static Object getBlanche_Neige() {
	return Blanche_Neige;
}
public static void setBlanche_Neige(Object blanche_Neige) {
	Blanche_Neige = blanche_Neige;
}
public void run(){
	synchronized(Blanche_Neige){
		try {
			Thread.sleep(1000);
			Blanche_Neige.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}
