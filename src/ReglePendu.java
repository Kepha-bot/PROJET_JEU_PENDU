import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ReglePendu {
	
	private ArrayList<String> dictionnaire;
	
	private char[] motSecret;
	private char[] motJoueur;

	private ArrayList<Character> lettresUtilis�es;
	
	private boolean[] alphabet;

	private int nbreMot;
	private int nbreSucces;
	private int nbreSuccesBut;
	private int nbreEssais;
	private int nbreErreurs;
	
	public ReglePendu() throws IOException {
		this.setDictionnaire(creationDictionnaire());
		this.setNbreMot(getDictionnaire().size());
	}

	//Initialisation du dictionnaire, on vient lire un fichier dictionnaire fourni pour peupler une ArrayList avec les mots
	public ArrayList<String> creationDictionnaire() throws IOException {
		BufferedReader fichier_dictionnaire = new BufferedReader(new FileReader("file/dictionnaire.txt"));
        @SuppressWarnings("unused")
		String ligne;
        setNbreMot(0);
        ArrayList<String> dictionnaire = new ArrayList<String>();
        
        while ((ligne = fichier_dictionnaire.readLine()) != null) {
           setNbreMot(getNbreMot()+1);
           dictionnaire.add(fichier_dictionnaire.readLine());
        }
        
        fichier_dictionnaire.close();
        return dictionnaire;
	}
	
	//On choisit un mot al�atoire dans le dictionnaire en g�n�rant un nombre al�atoire li� aux nombres de mots dans le dictionnaire
	public void choixMotSecret(ArrayList<String> dictionnaire, int nbreMot) {
		String motSecret;
		Random alea = new Random();
		int numeroMot = alea.nextInt(this.getNbreMot());
		motSecret=this.getDictionnaire().get(numeroMot);
		
		//On transforme la String retourn�e en tableau de caract�res
		//Le premier tableau sera le mot secret en clair
		//Le deuci�me mot sera le mot obfusqu� pour le joueur
		char[] lettres = new char[motSecret.length()]; 
		char[] lettresObfusquees = new char[motSecret.length()];
		
		for (int i=0; i<motSecret.length(); i++){
			lettres[i] = motSecret.charAt(i) ;
			lettresObfusquees[i] = '_';
		}	
		
		this.setMotSecret(lettres);
		this.setMotJoueur(lettresObfusquees);
	}
	
	public void resetPartie() {
		this.setNbreSucces(0);
		this.setNbreEssais(0);
		this.setNbreErreurs(0);
		this.setLettresUtilis�es(new ArrayList<Character>());
		this.setAlphabet(new boolean[26]);
		for(int i = 0; i<26; i++) {
			this.getAlphabet()[i]=false;
		}
		//D�finition du nombre de points � atteindre pour gagner, il correspond aux nombres de lettres ind�pendantes dans le mot secret
		this.setNbreSuccesBut(this.setSucces());
	}
	
	public int setSucces() {
		int but = 0;
		ArrayList<Character> lettresUniques=new ArrayList<Character>();
		boolean ok;
		
		for(int i=0; i<this.getMotSecret().length; i++) {
			//Par d�faut on pr�sume que la lettre est unique
			ok = true;
			//On parcourt l'ArrayList de lettres qu'on va peupler au fur et � mesure
			for(int j=0; j<lettresUniques.size(); j++) {
				//Si la lettre est d�j� pr�sente dans le tableau, �a veut dire qu'elle est d�j� pass�e dans la v�rification
				//Elle est donc d�j� pr�sente dans le mot, on passe l'unicit� � false
				if (this.getMotSecret()[i] == lettresUniques.get(j))
					 ok=false;				
			}
			//On ajoute la lettre test�e � l'ArrayList
			lettresUniques.add(this.getMotSecret()[i]);
			
			//Si la lettre n'a pas �t� trouv� dans l'ArrayList, on incr�mente le but
			if(ok==true)
				but++;
		}		
		return but;
	}
	
	//On utilise des tableaux de caract�res pour le mot secret et le mot du joueur, on a donc besoin d'une fonction pour afficher ces tableaux
	public void afficherTableau(char[] tableau) {
		for(int i = 0; i < tableau.length; i++) {
			System.out.print(tableau[i]);
		}
		System.out.println("");
	}
	
	//On utilise le tableau de boolean pour savoir si la lettre a d�j� �t� utilis�e
	public boolean verifDejaUtilise(int lettre) {
		if(this.getAlphabet()[lettre]==true)
			return true;
		else
			return false;
	}
	
	//On v�rifie que l'input de l'utilisateur est bien un caract�re valide non d�j� utilis�
	public boolean verifierInput(String input, ReglePendu partie) {
		boolean validation = false;
		char lettre;
		//On v�rifie que l'input n'est pas vide
		if(input.length()>0) { 			
    		//On r�cup�re le premier caract�re que l'on va transformer en int
    		lettre = input.charAt(0);
	    	int tmpLetterInt = partie.characterToInt(lettre);	    	
	    	//V�rification que l'entr�e est bien dans la range [A-Z]
	    	if(tmpLetterInt!=-1) {
	    		//On regarde dans l'alphabet si la lettre a d�j� �t� utilis�e
	    		if(partie.verifDejaUtilise(tmpLetterInt)==false)
	    			validation=true;
			    else {
			    	System.out.println("Lettre "+lettre+" d�j� utilis�e, essayez une autre lettre.");
			    }	    		
	    	} else {
	    		System.out.println("Merci de renseigner un caract�re valide.");
	    	}
    	} else {
    		System.out.println("Merci de renseigner un caract�re.");
    	}		
		return validation;
	}
	
	//On test la lettre pour savoir si elle est dans le mot
	public boolean testLettre(char lettre) {
		//Par d�faut la lettre n'est pas dans le mot
		boolean reponse=false;
		
		//On partcour le mot secret avec la lettre choisie
		for(int i=0; i<this.getMotSecret().length; i++) {
			//Si on trouve la lettre, la r�ponse passe � true et on remplace le '_' par la lettre dans le mot du joueur au m�me emplacement
			if(lettre==getMotSecret()[i]) {
				reponse=true;
				this.getMotJoueur()[i]=lettre;
			}
		}
		
		return reponse;
	}
	
	//M�thode qui permet de convertir la lettre en int
	//Cela nous permet de pouvoir consulter rapidement notre alphabet
	//Exemple : alphabet[0] = le boolean li� � la lettre 'A'
	public int characterToInt(char character) {	
		//D�finition par d�faut de la r�ponse sur une valeur impossible
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
            	//Si aucun case ne correspond alors la lettre n'est pas un caract�re [A-Z], retour -1
                return characterToInt;
        }
		
		return characterToInt;
	}

	public char[] getMotJoueur() {
		return motJoueur;
	}

	public void setMotJoueur(char[] motJoueur) {
		this.motJoueur = motJoueur;
	}

	public ArrayList<String> getDictionnaire() {
		return dictionnaire;
	}

	public void setDictionnaire(ArrayList<String> dictionnaire) {
		this.dictionnaire = dictionnaire;
	}

	public int getNbreSucces() {
		return nbreSucces;
	}

	public void setNbreSucces(int nbreSucces) {
		this.nbreSucces = nbreSucces;
	}

	public int getNbreMot() {
		return nbreMot;
	}

	public void setNbreMot(int nbreMot) {
		this.nbreMot = nbreMot;
	}

	public ArrayList<Character> getLettresUtilis�es() {
		return lettresUtilis�es;
	}

	public void setLettresUtilis�es(ArrayList<Character> lettresUtilis�es) {
		this.lettresUtilis�es = lettresUtilis�es;
	}

	public boolean[] getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(boolean[] alphabet) {
		this.alphabet = alphabet;
	}

	public int getNbreErreurs() {
		return nbreErreurs;
	}

	public void setNbreErreurs(int nbreErreurs) {
		this.nbreErreurs = nbreErreurs;
	}

	public int getNbreEssais() {
		return nbreEssais;
	}

	public void setNbreEssais(int nbreEssais) {
		this.nbreEssais = nbreEssais;
	}

	public char[] getMotSecret() {
		return motSecret;
	}

	public void setMotSecret(char[] motSecret) {
		this.motSecret = motSecret;
	}

	public int getNbreSuccesBut() {
		return nbreSuccesBut;
	}

	public void setNbreSuccesBut(int nbreSuccesBut) {
		this.nbreSuccesBut = nbreSuccesBut;
	}
}
