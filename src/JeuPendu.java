import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class JeuPendu{
	
	private ArrayList<String> dictionnaire;
	
	private char[] motSecret;
	private char[] motJoueur;
	private char inputLetter;

	private ArrayList<Character> lettresUtilisées;
	
	private boolean[] alphabet;

	private int nbreMot;
	private int nbreSucces;
	private int nbreSuccesBut;
	private int nbreEssais;
	private int nbreErreurs;

	Scanner inputZone = new Scanner(System.in); 
    String inputString;
    	
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
	
	public void choixMotSecret(ArrayList<String> dictionnaire, int nbreMot) {
		String motSecret;
		Random alea = new Random();
		int numeroMot = alea.nextInt(this.nbreMot);
		motSecret=this.dictionnaire.get(numeroMot);
		
		char[] lettres = new char[motSecret.length()]; 
		char[] lettresObfusquees = new char[motSecret.length()];
		
		for (int i=0; i<motSecret.length(); i++){
			lettres[i] = motSecret.charAt(i) ;
			lettresObfusquees[i] = '_';
		}	
		
		this.motSecret = lettres;
		this.motJoueur = lettresObfusquees;
	}
	
	public void resetPartie() {
		this.nbreSucces=0;
		this.nbreEssais=0;
		this.nbreErreurs=0;
		this.lettresUtilisées=new ArrayList<Character>();
		this.alphabet = new boolean[26];
		for(int i = 0; i<26; i++) {
			this.alphabet[i]=false;
		}
		this.nbreSuccesBut=this.setSucces();
	}
	
	public int setSucces() {
		int but = 0;
		ArrayList<Character> lettresUniques=new ArrayList<Character>();
		boolean ok;
		
		for(int i=0; i<this.motSecret.length; i++) {
			ok = true;
			for(int j=0; j<lettresUniques.size(); j++) {
				if (this.motSecret[i] == lettresUniques.get(j))
					 ok=false;				
			}
			lettresUniques.add(this.motSecret[i]);
			if(ok==true)
				but++;
		}		
		return but;
	}
	
	public void afficherTableau(char[] tableau) {
		for(int i = 0; i < tableau.length; i++) {
			System.out.print(tableau[i]);
		}
		System.out.println("");
	}
	
	public boolean verifDejaUtilise(int lettre) {
		if(this.alphabet[lettre]==true)
			return true;
		else
			return false;
	}
	
	public boolean testLettre(char lettre) {
		boolean reponse=false;
		
		for(int i=0; i<this.motSecret.length; i++) {
			if(lettre==motSecret[i]) {
				reponse=true;
				this.motJoueur[i]=lettre;
			}
		}
		
		return reponse;
	}
	
	public int characterToInt(char character) {	
		int characterToInt = -1;
		
		switch (character)
        {
            case 'A':
                characterToInt = 0;
                break;
            case 'B':
                characterToInt = 1;
                break;
            case 'C':
                characterToInt = 2;
                break;
            case 'D':
                characterToInt = 3;
                break;
            case 'E':
                characterToInt = 4;
                break;
            case 'F':
                characterToInt = 5;
                break;
            case 'G':
                characterToInt = 6;
                break;
            case 'H':
                characterToInt = 7;
                break;
            case 'I':
                characterToInt = 8;
                break;
            case 'J':
                characterToInt = 9;
                break;
            case 'K':
                characterToInt = 10;
                break;
            case 'L':
                characterToInt = 11;
                break;
            case 'M':
                characterToInt = 12;
                break;
            case 'N':
                characterToInt = 13;
                break;
            case 'O':
                characterToInt = 14;
                break;
            case 'P':
                characterToInt = 15;
                break;
            case 'Q':
                characterToInt = 16;
                break;
            case 'R':
                characterToInt = 17;
                break;
            case 'S':
                characterToInt = 18;
                break;
            case 'T':
                characterToInt = 19;
                break;
            case 'U':
                characterToInt = 20;
                break;
            case 'V':
                characterToInt = 21;
                break;
            case 'W':
                characterToInt = 22;
                break;
            case 'X':
                characterToInt = 23;
                break;
            case 'Y':
                characterToInt = 24;
                break;
            case 'Z':
                characterToInt = 25;
                break;
            default:
                System.out.println("Call Contains a bad character. Try again. \n");
                return characterToInt;
        }
		
		return characterToInt;
	}
	
	public char intToCharacter (int number) {	
		char intToCharacter = '_';
		
		switch (number)
        {
            case 0:
                intToCharacter = 'A';
                break;
            case 1:
                intToCharacter = 'B';
                break;
            case 2:
                intToCharacter = 'C';
                break;
            case 3:
                intToCharacter = 'D';
                break;
            case 4:
                intToCharacter = 'E';
                break;
            case 5:
                intToCharacter = 'F';
                break;
            case 6:
                intToCharacter = 'G';
                break;
            case 7:
                intToCharacter = 'H';
                break;
            case 8:
                intToCharacter = 'I';
                break;
            case 9:
                intToCharacter = 'J';
                break;
            case 10:
                intToCharacter = 'K';
                break;
            case 11:
                intToCharacter = 'L';
                break;
            case 12:
                intToCharacter = 'M';
                break;
            case 13:
                intToCharacter = 'N';
                break;
            case 14:
                intToCharacter = 'O';
                break;
            case 15:
                intToCharacter = 'P';
                break;
            case 16:
                intToCharacter = 'Q';
                break;
            case 17:
                intToCharacter = 'R';
                break;
            case 18:
                intToCharacter = 'S';
                break;
            case 19:
                intToCharacter = 'T';
                break;
            case 20:
                intToCharacter = 'U';
                break;
            case 21:
                intToCharacter = 'V';
                break;
            case 22:
                intToCharacter = 'W';
                break;
            case 23:
                intToCharacter = 'X';
                break;
            case 24:
                intToCharacter = 'Y';
                break;
            case 25:
                intToCharacter = 'Z';
                break;
            default:
                System.out.println("Call Contains a bad character. Try again. \n");
                return intToCharacter;
        }
		
		return intToCharacter;
	}
	
	public static void main(String[] args) throws IOException {
		JeuPendu partie = new JeuPendu();
		partie.choixMotSecret(partie.dictionnaire, partie.nbreMot);
		partie.resetPartie();	
		
		while(partie.nbreSucces!=partie.nbreSuccesBut) {
			System.out.print("Mot à trouver : ("+partie.motJoueur.length+") ");
			partie.afficherTableau(partie.motJoueur);
			System.out.println("");
			System.out.println("Nombre de bonnes lettres : "+partie.nbreSucces+", nombre d'erreurs : "+partie.nbreErreurs+", nombre d'essais : "+partie.nbreEssais);
			System.out.println("Lettres déjà utilisées : "+partie.lettresUtilisées.toString());
			System.out.println("Entrez une lettre :");
	    	partie.inputString = partie.inputZone.nextLine();
	    	partie.inputString = partie.inputString.toUpperCase();
	    	partie.inputLetter = partie.inputString.charAt(0);
	    	
	    	int tmpLetterInt = partie.characterToInt(partie.inputLetter);
	    	
	    	if(partie.verifDejaUtilise(tmpLetterInt)==true)
	    		System.out.println("Lettre "+partie.inputLetter+" déjà utilisée, essayez une autre lettre.");
	    	else {
	    		partie.lettresUtilisées.add(partie.inputLetter);
	    		partie.alphabet[tmpLetterInt]=true;
	    		
	    		if(partie.testLettre(partie.inputLetter)==true)
	    			partie.nbreSucces++;
	    		else
	    			partie.nbreErreurs++;
	    		
		    	partie.nbreEssais++;
	    	}
	    }
		System.out.print("Bravo tu as trouvé le mot ");
		partie.afficherTableau(partie.motJoueur);
		partie.inputZone.close();
	}
}
