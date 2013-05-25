/**
 * 
 */
package communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author olivier
 *
 */
public class Client {
	
	/* Informations sur le réseau */
	final private String IP = "192.168.1.254"; // Replace by the IP address of your WiFi E-Block
	final private int PORT = 5000; 
	private InetAddress address;
	private DatagramSocket UDPSocket;
	
	final public double VITESSE_MAX = 200;
	
	public Client(){
		
		//Initialize network
   		try {
   			this.address = InetAddress.getByName(this.IP);
   			System.out.println("Adresse:"+address+" , Port:"+PORT);
//   			UDPSocket = new DatagramSocket(PORT, address);
   			this.UDPSocket = new DatagramSocket();
   			
  		} catch (IOException e) {
  			System.out.println("Impossible de se connecter : "+e);
     		//Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
     	}
		
	}
	
	private void envoiCmd(int adresse, int commande, int variable) throws IOException{
		try{
			byte[] data = new byte[4];
			data[0]=(byte)adresse;
			data[1]=(byte)commande;
			data[2]=(byte)variable;	//Data
			data[3]=(byte)((data[0]+data[1]+data[2])&127); //checksum
			// Sending packet
			DatagramPacket packet = new DatagramPacket(data, 4, this.address, this.PORT);
			UDPSocket.send(packet);
			//Toast.makeText(this, R.string.sentText, Toast.LENGTH_SHORT).show();
		}catch (IOException e){
			throw e;
		}
    }
	
	public boolean envoiParm(double vitesse,double phi, double theta){
		// Envoie les paramètres au lanceur
		try {
			this.envoiCmd(128, 0, (byte)((double)(vitesse/this.VITESSE_MAX)*127)); // Moteur 1
			this.envoiCmd(128, 4, (byte)((double)(vitesse/this.VITESSE_MAX)*127)); // Moteur 2
			double angleD = (phi * 180 / 3.14)%180;
			angleD = 10; // (byte)(angleD*255/180)
			this.envoiCmd(100, 0, 10);
			// this.envoiCmd(***,***,theta);
			return true;
		}catch (SocketException e) {
			System.out.println("[ERR] Impossible d'envoyer les paramètres au lanceur : "+e);
		}catch (Exception e) {
			System.out.println("[ERR] Impossible d'envoyer les paramètres au lanceur : "+e);
		}
		return false;
	}
	
	public static void main(String[] args){
		Client c = new Client();
		c.envoiParm(100,2,0);
		c.envoiParm(120,0.4,0);
		c.envoiParm(80,1.5,0);
	}

}
