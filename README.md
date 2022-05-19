# Devoir 2022

Structure de départ pour le devoir sur le jeu *Brise-glace*.

Si vous le souhaitez, vous pouvez changer l'organisation ou supprimer les fichiers présents initialement dans ce dépôt.
Les seuls fichiers que vous devez impérativement conserver sont les suivants :

- le fichier `.gitignore` dans la racine du projet
- l'interface `IChallenger.java`
- la classe `MyChallenger.java`
- la classe `Client.java` (utile pour la partie 3)
- la classe `Message.java` (utile pour la partie 3)

## Partie 2

### Consignes

1. implémenter dans `MyChallenger` toutes les fonctions de l'interface `IChallenger`, à l'exception de la fonction `bestMove`, qui ne sera à écrire que pour la troisième partie;

2. écrire une courte description (un ou deux paragraphes, ci-dessous) de l'algorithme utilisé pour `possibleMoves`.

### Description de l'algorithme utilisé pour déterminer les coups possibles

L'algorythme utilisé pour déterminer les coups possibles est le suivant :
- Pour chaque bateax du joueur demande:
  - On liste les icebergs qui sont collés
  - Si il n'y a pas d'icebergs, on fait, en largeur, tout les chemins possible, pas par pas, jusqu'à ce qu'on arrive à un iceberg ou à une limite de 9 pas.
  - Tous les chemins sont exploré pour avoir la liste des choix possible.

## Partie 3



Nous avons gardé la structure de base, pour lancer notre IA :

```bash
# Build du projet:
gradle build

cp build/libs/TP3_TomM_LeandreB_Icebreaker.jar $turnamentPath

# Dans le dossier tournoi:
java -cp $jarName::commons-cli-1.4.jar iialib.games.contest.Client -p 4536 -s localhost -c games.icebreaker.MyChallenger

# Il est aussi possible de lancer notre implemantation avec une IA random :
java -cp $jarName::commons-cli-1.4.jar iialib.games.contest.Client -p 4536 -s localhost -c games.icebreaker.RandomChallenger
```