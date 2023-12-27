# Everything

Ce dépôt est présent pour mon plugin sur PaperMC, Everything.

Ce plugin est censé rassemblé tout ce dont j'ai besoin dans un serveur Minecraft que je peux faire. (Par exemple, je ne vais pas recoder WorldEdit :3)

J'ai eu l'idée de faire ce plugin suite à la création d'un serveur Minecraft vanilla en 1.20.4 avec des amis. Et puisque j'en avais marre de tout le temps faire de la configuration pour que le serveur fonctionne, j'ai décidé de faire ce plugin qui plus tard vas me facilité la vie !

# Installation

Rien de plus simple, il suffit juste de glisser le .jar dans le dossier plugin du serveur, attention il faut utiliser PaperMC (Fork de Spigot qui est un fork de Bukkit)

# Compilation

Pour pouvoir compiler le plugin, il vous faut avoir un environnement Java, sois vous utilisez Eclipse, sois vous utilisez Idea ou encore VSCode. Personnellement j'utilise Idea.

# Liste des commandes
| Commande       | Argument               | Explication                                   |
|----------------|------------------------|-----------------------------------------------|
| `/ping`        | `<Rien>`               | Pong!                                         |
| `/infos`       | `<Rien>`               | Affiche les infos du serveur                  |
| `/backup`      | `<Rien>`               | Permet de sauvegarder les maps du serveur     |
| `/tpa`         | `<player>`             | Envoie une demande de TP                     |
| `/tpaccept`    | `<Rien>`               | Accepte une demande de TP                    |
| `/sethome`     | `<nom>`                | Pose un point de téléport (home)              |
| `/home`        | `<nom>`                | Téléporte le joueur à un home                 |
| `/delhome`     | `<nom>`                | Supprime un home                              |
| `/world`       | `<create/delete/tp>` `<normal/void>` `[--time=<enable/disable>]` | Gestion des mondes et téléportation     |
| `/enderchest`  | `<Rien>`               | Accède à l'Ender Chest du joueur              |

Attention : 
Sur la commande /world, désactiver le timestamp ne marche pas, c'est une feature que je n'arrive pas à implémenter


# Contribuer

Si vous souhaitez contribuer au code de mon plugin, vous pouvez ! 
De même si vous souhaitez que je rajoute une feature faites le savoir :p
