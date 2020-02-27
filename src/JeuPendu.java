import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class JeuPendu{
	
	//Variable pour stocker l'OS de l'utilisateur
	private static String OS = System.getProperty("os.name").toLowerCase();	

	//Méthode permettant de nettoyer la console, dépendante de l'OS de l'utilisateur
	public static void CLS() throws InterruptedException, IOException {
		switch(OS) {
			case "win" :
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			case "nux" :
				System.out.print("\033[H\033[2J");  
			    System.out.flush();
			default :
				//On ne fait rien si l'OS utilisateur n'est pas Windows ou Linux
		}
	}

	//Méthode permettant de reformater la variable OS pour une utilisation plus facile
	public static void OSValidator() {					
		if (OS.indexOf("win") >= 0) {
			OS="win";
		}
		if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0) {
			OS="nux";
		}
	}

	//Définition des variables de lecture de la console
	static Scanner inputZone = new Scanner(System.in); 
    static String inputString;
	private static char inputLetter;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		//Intitialisation de la variable pour la boucle du jeu
		boolean jouer = true;
		
		//Initialisation de la partie et définition de l'OS
		ReglePendu partie = new ReglePendu();
		JeuPendu.OSValidator();
		
		//Boucle du jeu
		while(jouer==true) {
			//On choisit un mot dans le dictionnaire
			partie.choixMotSecret(partie.getDictionnaire(), partie.getNbreMot());
			//On reset les valeurs de la partie
			partie.resetPartie();
			
			//Boucle infinie pour renseigner les caractères
			//La partie se terminera quand un break sera atteint (trop d'erreurs ou but atteint)
			while(1!=0) {
				//On nettoie la console
				JeuPendu.CLS();
				//On dessine le pendu
		        DessinPendu.afficherPendu(partie.getNbreErreurs());
		        
		        //Affichage des informations joueurs et du mot obfusqué
				System.out.print("Mot à trouver : ("+partie.getMotJoueur().length+") ");
				partie.afficherTableau(partie.getMotJoueur());
				System.out.println("");
				System.out.println("Nombre de bonnes lettres : "+partie.getNbreSucces()+", nombre d'erreurs : "+partie.getNbreErreurs()+", nombre d'essais : "+partie.getNbreEssais());
				System.out.println("Lettres déjà utilisées : "+partie.getLettresUtilisées().toString());
		    	
				//Si 6 erreurs alors le joueur a perdu, affichage du mot qui était à trouver
		    	if(partie.getNbreErreurs()==6) {
		    		System.out.println("Trop d'erreurs, vous avez perdu !");
		    		System.out.print("Le mot à trouver était ");
					partie.afficherTableau(partie.getMotSecret());
					break;
		    	}
		    	
		    	//Si le joueur a réussi à atteindre le score objectif il gagne la partie, affichage du mot trouvé
		    	if(partie.getNbreSucces()==partie.getNbreSuccesBut()) {
		    		System.out.print("Bravo vous avez trouvé le mot : ");
					partie.afficherTableau(partie.getMotJoueur());
					break;
		    	}
		    	
		    	//On boucle la demande de saisie de caractère jusqu'à ce que l'utilisateur renseigne un caractère correcte
		    	do {
			    	//Prompt pour renseigner une lettre
					System.out.println("Entrez une lettre :");
					JeuPendu.inputString = JeuPendu.inputZone.nextLine();
					JeuPendu.inputString = JeuPendu.inputString.toUpperCase();					
		    	} while (partie.verifierInput(JeuPendu.inputString, partie)==false);
		    	
		    	JeuPendu.inputLetter = JeuPendu.inputString.charAt(0);
		    	int tmpLetterInt = partie.characterToInt(JeuPendu.inputLetter);		
			    
		    	//On ajoute la lettre à l'ArrayList des lettres utilisées et on set l'alphabet
			   	partie.getLettresUtilisées().add(JeuPendu.inputLetter);
			   	partie.getAlphabet()[tmpLetterInt]=true;
			   		
			   	//Implémentation du nombre de succès ou d'erreurs si la lettre choisie est bien dans le mot
			   	if(partie.testLettre(JeuPendu.inputLetter)==true)
			   		partie.setNbreSucces(partie.getNbreSucces() + 1);
			   	else
			   		partie.setNbreErreurs(partie.getNbreErreurs() + 1);
			   	
			   	partie.setNbreEssais(partie.getNbreEssais() + 1);
			}
			
			//Une fois que la partie est terminée le joueur peut quitter le jeu
			System.out.println("Si vous voulez quittez, entrez \"N\" dans la console (sinon le jeu se relancera) : ");
			JeuPendu.inputString = JeuPendu.inputZone.nextLine();
			JeuPendu.inputString = JeuPendu.inputString.toUpperCase();
			
			//Exécution des mêmes vérifications syntaxiques que précédemment
	    	if(JeuPendu.inputString.length()>0) {
	    		JeuPendu.inputLetter = JeuPendu.inputString.charAt(0);
		    	if(JeuPendu.inputLetter=='N') {
		    		jouer=false;
		    	}
	    	}
		}
		System.out.println("Merci d'avoir joué !");
		//Le jeu attend 3s pour que le joueur puisse lire le message de fin
		TimeUnit.SECONDS.sleep(3);
		JeuPendu.inputZone.close();
	}
}
