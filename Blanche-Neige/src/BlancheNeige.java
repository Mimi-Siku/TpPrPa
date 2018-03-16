
public class BlancheNeige {
private volatile boolean libre = true;

public void requerir(){
	System.out.println(Thread.currentThread().getName()+"_veux a ressource");
}

public synchronized void acceder () throws InterruptedException{
	while(!libre)
		wait();
	libre = false;
}

public synchronized void relacher(){
	libre = true;
	notifyAll();
}
}
