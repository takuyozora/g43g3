package calcul;

import communication.Client;

public class Programme extends Thread  {
	
	double[][] parametres;
	int tempo; // en ms
	Client client = new Client();
	
	boolean mustStop = false;
	boolean isRunning = false;
	boolean hasError = false;
	
	public Programme(double[][] parametres, int tempo){
		this.parametres = parametres;
		this.tempo = tempo;
	}
	
	public void run(){
		this.isRunning = true;
		int i = 0;
		while(mustStop != true){
			if(this.client.envoiParm(this.parametres[i][0],this.parametres[i][1],this.parametres[i][2]) == false){
				this.hasError = true;
			}
			i++;
			if(i>=this.parametres.length){
				break;
			}
			try {
				Thread.sleep(this.tempo);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		this.isRunning = false;
	}
	
	
	

}
