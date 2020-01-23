import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random; 

public class JeuPendu{
	
	private ArrayList<String> dictionnaire;
	private int nbreMot;
	private String motSecret;
	
	public JeuPendu() throws IOException {
		this.dictionnaire=creationDictionnaire();
		this.nbreMot=dictionnaire.size();
	}
	
	public ArrayList<String> creationDictionnaire() throws IOException {
		BufferedReader fichier_dictionnaire = new BufferedReader(new FileReader("file/dictionnaire.txt"));
        String ligne;
        nbreMot=0;
        ArrayList<String> dictionnaire = new ArrayList<String>();
        
        while ((ligne = fichier_dictionnaire.readLine()) != null) {
           nbreMot=nbreMot+1;
           dictionnaire.add(fichier_dictionnaire.readLine());
        }
        
        fichier_dictionnaire.close();
        return dictionnaire;
	}
	
	public String choixMotSecret(ArrayList<String> dictionnaire, int nbreMot) {
		String motSecret;
		Random alea = new Random();
		int numeroMot = alea.nextInt(this.nbreMot);
		motSecret=this.dictionnaire.get(numeroMot);
		return motSecret;		
	}
	
	public static void main(String[] args) throws IOException {
		JeuPendu partie = new JeuPendu();
		partie.motSecret = partie.choixMotSecret(partie.dictionnaire, partie.nbreMot);
		System.out.println(partie.motSecret);
	}
}
