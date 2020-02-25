import java.lang.*;
import java.net.URL;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.io.*; 
import java.awt.*; 
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver; 

//Rajouter dessin pendu 

public class DessinPendu extends JFrame implements ActionListener, WindowListener {
	
	
	int i = 0; //Initialisation du compteur 
	
	//Instanciation en dehors du public DessinPendu pour qu'il soit pris en compte dans toute la classe. 
	
	Container contenu = getContentPane();
	JTextArea dessinP = new JTextArea ();  
	
	public DessinPendu() {	//Declaration du constructeur pour l'interface graphique
		
		
		addWindowListener(this); 
		setTitle("Le jeu du pendu");   //modification du titre de la fenetre 
		
		
		//Creation des composants de l'interface 
		
			//Bouton OK
		
		JButton b1 = new JButton ("OK");
		b1.addActionListener(this); 
		
			//Image du pendu 
	
		ImageIcon image = new ImageIcon(new ImageIcon("images/etat11.jpg").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
		JLabel imagebis = new JLabel(); 
		imagebis.setIcon(image);
	

			//Texte contenant l'avertissement sur le nombre d'essaie restant 
		
		JTextArea avertissement = new JTextArea ("attention");
		
			//Ajout du bloc de texte pour l'utilisateur, pour rentrer la lettre choisie. 
		
		JTextField textField = new JTextField(); 
		textField.setColumns(10); 
		
			//Consigne pour indiquer à l'utilisateur ce qu'il doit faire 
		
		JLabel texte = new JLabel("Veuillez rentrer une lettre");
		
			//Ajout du bloc texte contenant les lettres déja utilisées par l'utilisateur 
		
		JTextArea lettreU = new JTextArea("Lettre(s) utilisée(s) : "); 
				
			//Ajout du bouton pour lancer une partie
		
		JButton start = new JButton("Lancer une partie"); 
		
		
		// Ajout des différents composants dans l'interface
		
		contenu.setLayout(new FlowLayout());
		contenu.add(start); 
		contenu.add(texte); 
		contenu.add(textField); 
		contenu.add(b1); 
		contenu.add(avertissement);
		contenu.add(imagebis); 
		contenu.add(dessinP);
		contenu.add(lettreU ); 
		
		
		//Rendre les blocs de textes non editable par l'utilisateur 
		
		dessinP.setEditable(false);
		lettreU.setEditable(false);
	}
	
	
		// Méthode pour afficher le dessin en fonction du nombre d'erreur 
	
	public void actionPerformed(ActionEvent e)
	{
		
		this.i++; // compteur d'erreur 
 
		if((e.getActionCommand()).equals("OK"))  //boucle si on clique sur le bouton 
		{
	
		    
		    if(i==0) {
		    	
		    	dessinP.setText( 
		    	 "      \n"+ 
		    	 "      \n"+ 
		    	 "      \n"+ 
		    	 "      \n" + 
		    	 "      \n" + 
		    	 "- - - "); 
		    	
		    	
		    }
			
			if(i==1)
			{
				dessinP.setText( 
				    	 "      \n"+ 
				    	 "      \n"+ 
				    	 "      \n"+ 
				    	 "      \n" + 
				    	 "|      \n" + 
				    	 "- - - "); 
				
			}
			
			if(i==2)
			{
				dessinP.setText( 
				    	 "      \n"+ 
				    	 "      \n"+ 
				    	 "      \n"+ 
				    	 "|      \n" + 
				    	 "|      \n" + 
				    	 "- - - "); 
		
				
			}
			if(i==3)
			{
				
				dessinP.setText( 
				    	 "      \n"+ 
				    	 "      \n"+ 
				    	 "|     \n"+ 
				    	 "|      \n" + 
				    	 "|      \n" + 
				    	 "- - - "); 
			
				
			}
			if(i==4)
			{
				dessinP.setText( 
				    	 "      \n"+ 
				    	 "|      \n"+ 
				    	 "|     \n"+ 
				    	 "|      \n" + 
				    	 "|      \n" + 
				    	 "- - - "); 
		
				
				
			}
			if(i==5)
			{
				
				dessinP.setText( 
				    	 "- - - \n"+ 
				    	 "|      \n"+ 
				    	 "|     \n"+ 
				    	 "|      \n" + 
				    	 "|      \n" + 
				    	 "- - - "); 
		
				
			}
			
			if(i==6)
			{
				dessinP.setText( 
				    	 "- - -  \n"+ 
				    	 "|     | \n"+ 
				    	 "|     \n"+ 
				    	 "|      \n" + 
				    	 "|      \n" + 
				    	 "- - - "); 
				
			}
			
			if(i==7)
			{
				dessinP.setText( 
				    	 "- - -  \n"+ 
				    	 "|     | \n"+ 
				    	 "|     o\n"+ 
				    	 "|      \n" + 
				    	 "|      \n" + 
				    	 "- - - "); 
				
				
			}
			if(i==8)
			{
				
				dessinP.setText( 
				    	 "- - -  \n"+ 
				    	 "|     | \n"+ 
				    	 "|     o\n"+ 
				    	 "|    -|- \n" + 
				    	 "|      \n" + 
				    	 "- - - ");
				
				
			}
			
			if(i==9)
			{
				
				dessinP.setText( 
				    	 "- - -  \n"+ 
				    	 "|     | \n"+ 
				    	 "|     o\n"+ 
				    	 "|    -|- \n" + 
				    	 "|    | | \n" + 
				    	 "- - - ");
			
				
			}
			
		}	
			
			
			
		}
	
	//main pour créer l'interface et la rendre visible 
	
	public static void main (String[]arg)
	{
		DessinPendu dp1 = new DessinPendu(); 	
		dp1.setVisible(true);  
		dp1.setSize(800,600); 
		dp1.setResizable(false);
		
		
	}


	//Permettre a l'utilisateur d'ouvrir, de fermer, de réduire et d'augmenter la fenetre 
	
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosing(WindowEvent e) {

		System.exit(0);
		
	}

	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		

	}

	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}

