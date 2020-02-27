import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ReglePendu {
	
	private ArrayList<String> dictionnaire;
	
	private char[] motSecret;
	private char[] motJoueur;

	private ArrayList<Character> lettresUtilisées;
	
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
	
	//On choisit un mot aléatoire dans le dictionnaire en générant un nombre aléatoire lié aux nombres de mots dans le dictionnaire
	public void choixMotSecret(ArrayList<String> dictionnaire, int nbreMot) {
		String motSecret;
		Random alea = new Random();
		int numeroMot = alea.nextInt(this.getNbreMot());
		motSecret=this.getDictionnaire().get(numeroMot);
		
		//On transforme la String retournée en tableau de caractères
		//Le premier tableau sera le mot secret en clair
		//Le deucième mot sera le mot obfusqué pour le joueur
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
		this.setLettresUtilisées(new ArrayList<Character>());
		this.setAlphabet(new boolean[26]);
		for(int i = 0; i<26; i++) {
			this.getAlphabet()[i]=false;
		}
		//Définition du nombre de points à atteindre pour gagner, il correspond aux nombres de lettres indépendantes dans le mot secret
		this.setNbreSuccesBut(this.setSucces());
	}
	
	public int setSucces() {
		int but = 0;
		ArrayList<Character> lettresUniques=new ArrayList<Character>();
		boolean ok;
		
		for(int i=0; i<this.getMotSecret().length; i++) {
			//Par défaut on présume que la lettre est unique
			ok = true;
			//On parcourt l'ArrayList de lettres qu'on va peupler au fur et à mesure
			for(int j=0; j<lettresUniques.size(); j++) {
				//Si la lettre est déjà présente dans le tableau, ça veut dire qu'elle est déjà passée dans la vérification
				//Elle est donc déjà présente dans le mot, on passe l'unicité à false
				if (this.getMotSecret()[i] == lettresUniques.get(j))
					 ok=false;				
			}
			//On ajoute la lettre testée à l'ArrayList
			lettresUniques.add(this.getMotSecret()[i]);
			
			//Si la lettre n'a pas été trouvé dans l'ArrayList, on incrémente le but
			if(ok==true)
				but++;
		}		
		return but;
	}
	
	//On utilise des tableaux de caractères pour le mot secret et le mot du joueur, on a donc besoin d'une fonction pour afficher ces tableaux
	public void afficherTableau(char[] tableau) {
		for(int i = 0; i < tableau.length; i++) {
			System.out.print(tableau[i]);
		}
		System.out.println("");
	}
	
	//On utilise le tableau de boolean pour savoir si la lettre a déjà été utilisée
	public boolean verifDejaUtilise(int lettre) {
		if(this.getAlphabet()[lettre]==true)
			return true;
		else
			return false;
	}
	
	//On vérifie que l'input de l'utilisateur est bien un caractère valide non déjà utilisé
	public boolean verifierInput(String input, ReglePendu partie) {
		boolean validation = false;
		char lettre;
		//On vérifie que l'input n'est pas vide
		if(input.length()>0) { 			
    		//On récupère le premier caractère que l'on va transformer en int
    		lettre = input.charAt(0);
	    	int tmpLetterInt = partie.characterToInt(lettre);	    	
	    	//Vérification que l'entrée est bien dans la range [A-Z]
	    	if(tmpLetterInt!=-1) {
	    		//On regarde dans l'alphabet si la lettre a déjà été utilisée
	    		if(partie.verifDejaUtilise(tmpLetterInt)==false)
	    			validation=true;
			    else {
			    	System.out.println("Lettre "+lettre+" déjà utilisée, essayez une autre lettre.");
			    }	    		
	    	} else {
	    		System.out.println("Merci de renseigner un caractère valide.");
	    	}
    	} else {
    		System.out.println("Merci de renseigner un caractère.");
    	}		
		return validation;
	}
	
	//On test la lettre pour savoir si elle est dans le mot
	public boolean testLettre(char lettre) {
		//Par défaut la lettre n'est pas dans le mot
		boolean reponse=false;
		
		//On partcour le mot secret avec la lettre choisie
		for(int i=0; i<this.getMotSecret().length; i++) {
			//Si on trouve la lettre, la réponse passe à true et on remplace le '_' par la lettre dans le mot du joueur au même emplacement
			if(lettre==getMotSecret()[i]) {
				reponse=true;
				this.getMotJoueur()[i]=lettre;
			}
		}
		
		return reponse;
	}
	
	//Méthode qui permet de convertir la lettre en int
	//Cela nous permet de pouvoir consulter rapidement notre alphabet
	//Exemple : alphabet[0] = le boolean lié à la lettre 'A'
	public int characterToInt(char character) {	
		//Définition par défaut de la réponse sur une valeur impossible
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
            	//Si aucun case ne correspond alors la lettre n'est pas un caractère [A-Z], retour -1
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

	public ArrayList<Character> getLettresUtilisées() {
		return lettresUtilisées;
	}

	public void setLettresUtilisées(ArrayList<Character> lettresUtilisées) {
		this.lettresUtilisées = lettresUtilisées;
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
