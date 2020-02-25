import java.io.IOException;
import java.util.Scanner;

public class JeuPendu{
	
	private static String OS = System.getProperty("os.name").toLowerCase();	

	public static void CLS() throws InterruptedException, IOException {
		switch(OS) {
			case "win" :
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			case "nux" :
				System.out.print("\033[H\033[2J");  
			    System.out.flush();  
		}
	}

	public static void OSValidator() {					
		if (OS.indexOf("win") >= 0) {
			OS="win";
		}
		if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0) {
			OS="nux";
		}
	}

	static Scanner inputZone = new Scanner(System.in); 
    static String inputString;
	private static char inputLetter;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		boolean jouer = true;
		ReglePendu partie = new ReglePendu();
		JeuPendu.OSValidator();
		
		while(jouer==true) {
			partie.choixMotSecret(partie.getDictionnaire(), partie.getNbreMot());
			partie.resetPartie();	
			
			while(1!=0) {		    	
				JeuPendu.CLS();
		        
				System.out.print("Mot à trouver : ("+partie.getMotJoueur().length+") ");
				partie.afficherTableau(partie.getMotJoueur());
				System.out.println("");
				System.out.println("Nombre de bonnes lettres : "+partie.getNbreSucces()+", nombre d'erreurs : "+partie.getNbreErreurs()+", nombre d'essais : "+partie.getNbreEssais());
				System.out.println("Lettres déjà utilisées : "+partie.getLettresUtilisées().toString());
				System.out.println("Entrez une lettre :");
				JeuPendu.inputString = JeuPendu.inputZone.nextLine();
				JeuPendu.inputString = JeuPendu.inputString.toUpperCase();
		    	
		    	if(JeuPendu.inputString.length()>0) {
		    		JeuPendu.inputLetter = JeuPendu.inputString.charAt(0);
			    	int tmpLetterInt = partie.characterToInt(JeuPendu.inputLetter);
			    	if(tmpLetterInt!=-1) {
				    	if(partie.verifDejaUtilise(tmpLetterInt)==true)
				    		System.out.println("Lettre "+JeuPendu.inputLetter+" déjà utilisée, essayez une autre lettre.");
				    	else {
				    		partie.getLettresUtilisées().add(JeuPendu.inputLetter);
				    		partie.getAlphabet()[tmpLetterInt]=true;
				    		
				    		if(partie.testLettre(JeuPendu.inputLetter)==true)
				    			partie.setNbreSucces(partie.getNbreSucces() + 1);
				    		else
				    			partie.setNbreErreurs(partie.getNbreErreurs() + 1);
				    		
					    	partie.setNbreEssais(partie.getNbreEssais() + 1);
				    	}
			    	} else {
			    		System.out.println("Merci de renseigner un caractère valide.");
			    	}
		    	} else {
		    		System.out.println("Merci de renseigner un caractère.");
		    	}
		    	
		    	if(partie.getNbreErreurs()==9) {
		    		System.out.println("Trop d'erreurs, vous avez perdu !");
		    		System.out.print("Le mot à trouver était ");
					partie.afficherTableau(partie.getMotSecret());
					break;
		    	}
		    	
		    	if(partie.getNbreSucces()==partie.getNbreSuccesBut()) {
		    		System.out.print("Bravo vous avez trouvé le mot : ");
					partie.afficherTableau(partie.getMotJoueur());
					break;
		    	}
		    }
			System.out.println("Si vous voulez quittez, entrez \"N\" dans la console (sinon le jeu se relancera) : ");
			JeuPendu.inputString = JeuPendu.inputZone.nextLine();
			JeuPendu.inputString = JeuPendu.inputString.toUpperCase();
	    	if(JeuPendu.inputString.length()>0) {
	    		JeuPendu.inputLetter = JeuPendu.inputString.charAt(0);
		    	if(JeuPendu.inputLetter=='N') {
		    		jouer=false;
		    	}
	    	}
		}
		System.out.println("Merci d'avoir joué !");
		JeuPendu.inputZone.close();
	}
}
