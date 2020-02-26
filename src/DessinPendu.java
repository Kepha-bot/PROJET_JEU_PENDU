public class DessinPendu {	
	//Affichage du dessin selon le nombre d'erreurs commises par le joueur
	public static void afficherPendu(int erreurs) {
		switch (erreurs) {
		case 0:
			System.out.println(" _______     ");
			System.out.println("|       |    ");
			System.out.println("|            ");
			System.out.println("|            ");
			System.out.println("|            ");
			System.out.println("|            ");
			System.out.println("|________    ");
			System.out.println("");
			break;
		case 1:
			System.out.println(" _______     ");
			System.out.println("|       |    ");
			System.out.println("|       O    ");
			System.out.println("|            ");
			System.out.println("|            ");
			System.out.println("|            ");
			System.out.println("|________    ");
			System.out.println("");
			break;
		case 2:
			System.out.println(" _______     ");
			System.out.println("|       |    ");
			System.out.println("|       O    ");
			System.out.println("|       |    ");
			System.out.println("|            ");
			System.out.println("|            ");
			System.out.println("|________    ");
			System.out.println("");
			break;
		case 3:
			System.out.println(" _______     ");
			System.out.println("|       |    ");
			System.out.println("|       O    ");
			System.out.println("|      /|    ");
			System.out.println("|            ");
			System.out.println("|            ");
			System.out.println("|________    ");
			System.out.println("");
			break;
		case 4:
			System.out.println(" _______     ");
			System.out.println("|       |    ");
			System.out.println("|       O    ");
			System.out.println("|      /|\\  ");
			System.out.println("|            ");
			System.out.println("|            ");
			System.out.println("|________    ");
			System.out.println("");
			break;
		case 5:
			System.out.println(" _______     ");
			System.out.println("|       |    ");
			System.out.println("|       O    ");
			System.out.println("|      /|\\  ");
			System.out.println("|      /     ");
			System.out.println("|            ");
			System.out.println("|________    ");
			System.out.println("");
			break;
		case 6:
			System.out.println(" _______     ");
			System.out.println("|       |    ");
			System.out.println("|       O    ");
			System.out.println("|      /|\\  ");
			System.out.println("|      / \\  ");
			System.out.println("|            ");
			System.out.println("|________    ");
			System.out.println("");
			break;
		}
	}
}