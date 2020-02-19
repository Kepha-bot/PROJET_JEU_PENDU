import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class JeuPendu{
	
	private ArrayList<String> dictionnaire;
	private int nbreMot;
	private String motSecret;
	private int nbreEssais;
	private int nbreErreurs;

	Scanner inputZone = new Scanner(System.in); 
    String inputLetter;
	
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
	
	public static void main(String[] args) throws IOException {
		JeuPendu partie = new JeuPendu();
		partie.motSecret = partie.choixMotSecret(partie.dictionnaire, partie.nbreMot);
		Scanner inputZone = new Scanner(System.in); 
	    System.out.println("Enter letter :");
	    partie.inputLetter = partie.inputZone.nextLine();
	    System.out.println("Letter is: " + partie.characterToInt(partie.inputLetter.charAt(0)));
	}
}
