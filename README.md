# Everything

# Sommaire

- [Description](#description)
- [Installation](#installation)
- [Compilation](#compilation)
- [Liste des commandes](#liste-des-commandes)
- [Contribuer](#contribuer)

# Description

Ce dépôt est présent pour mon plugin sur PaperMC, Everything.

Ce plugin est censé rassembler tout ce dont j'ai besoin dans un serveur Minecraft que je peux faire. (Par exemple, je ne vais pas recoder WorldEdit :3)

J'ai eu l'idée de faire ce plugin suite à la création d'un serveur Minecraft vanilla en 1.20.4 avec des amis. Et puisque j'en avais marre de tout le temps faire de la configuration pour que le serveur fonctionne, j'ai décidé de faire ce plugin qui plus tard va me faciliter la vie !

# Installation

Rien de plus simple, il suffit juste de glisser le `.jar` dans le dossier plugin du serveur, attention il faut utiliser PaperMC (Fork de Spigot qui est un fork de Bukkit)

# Compilation

Pour pouvoir compiler le plugin, il vous faut avoir un environnement Java, soit vous utilisez Eclipse, soit vous utilisez Idea ou encore VSCode. Personnellement, j'utilise Idea.

# Liste des commandes
| Commande    | Argument                                                                   | Explication                                               |
|-------------|----------------------------------------------------------------------------|-----------------------------------------------------------|
| `/infos`    | `<Rien>`                                                                   | Affiche les infos du serveur                              |
| `/backup`   | `<Rien>`                                                                   | Permet de sauvegarder les maps du serveur                 |
| `/tpa`      | `<player>`                                                                 | Envoie une demande de TP                                  |
| `/tpaccept` | `<Rien>`                                                                   | Accepte une demande de TP                                 |
| `/sethome`  | `<nom>`                                                                    | Pose un point de téléport (home)                          |
| `/home`     | `<nom>`                                                                    | Téléporte le joueur à un home                             |
| `/delhome`  | `<nom>`                                                                    | Supprime un home                                          |
| `/world`    | `<create/delete/tp>` `<normal/void>` `optional:[--time <enable/disable>]`  | Gestion des mondes et téléportation dans les mondes       |
| `/ec`       | `<Rien>`                                                                   | Accède à l'Ender Chest du joueur                          |
| `/invsee`   | `<player>`                                                                 | Affiche le contenue de l'inventaire d'un joueur           |


Info (27/12/2023):

Sur ce tableau sont présentes uniquement les commandes qui sont sur le package en latest "stable".

Je fonctionne de cette manière :

1) Je fais plusieurs implémentations
2) Quand je commence à en avoir pas mal je fais un nouveau `.jar`
3) Je fais une nouvelle release sur GitHub.

Je préfère faire ça que de surcharger les releases, bien que pour l'instant il risque d'y en avoir beaucoup.

# Contribuer

Si vous souhaitez contribuer au code de mon plugin, vous pouvez !

De même si vous souhaitez que je rajoute une feature faites le savoir :p


