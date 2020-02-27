# PROJET_JEU_PENDU
Création d'un jeu de pendu, version console, en java

## Pour lancer le jeu :
  - Sur Windows : Exécuter jeu.bat
  - Sur Linux/Unix : Exécuter jeu.sh
  - Pour tout autre OS, exécuter jeu.jar depuis un terminal
  
## Fonctionnement du jeu
La partie se lance toute seul au démarrage.

Vous devez deviner le mot en renseignant des lettres une par une. Vous pouvez rentrer les lettres en minuscules ou en majuscules. Si vous renseignez plusieurs lettres en même temps, seule la première lettre sera prise en compte.

Si vous faites trop d'erreurs, vous perdez la partie et le mot secret vous sera dévoilé.

Essayer une lettre déjà utilisée ne compte pas comme une erreur.

Si vous trouvez le mot secret vous gagnez la partie.

Une fois la partie terminée, vous pouvez lancer une nouvelle partie en répondant 'Y' ou 'N' au prompt affiché à l'écran.

## Affichage legacy
Le fichier DessinPendu.java dans /legacy est une ébauche d'interface graphique prévue initialement pour le jeu.

Cette interface n'a malheureusement pas été implémentée dans la version finale.

## Modification du dictionnaire
Si vous souhaitez changer la liste des mots possibles à trouver, vous pouvez modifier le fichier /file/dictionnaire.txt

Attention, le mot doit être en majuscule et sans espace blanc à la fin, un seul mot par ligne.
