# Everything

# Sommaire

- [Description](#description)
- [Installation](#installation)
- [Compilation](#compilation)
- [Liste des commandes](#liste-des-commandes)
- [Rythme de sortie des releases](#rythme-de-sortie-des-releases)
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
| Commande      | Argument                                                                  | Explication                                                                                                 |
|---------------|---------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| `/infos`      | `<Rien>`                                                                  | Affiche les infos du serveur                                                                                |
| `/backup`     | `<Rien>`                                                                  | Permet de sauvegarder les maps du serveur                                                                   |
| `/tpa`        | `<player>`                                                                | Envoie une demande de TP                                                                                    |
| `/tpaccept`   | `<Rien>`                                                                  | Accepte une demande de TP                                                                                   |
| `/sethome`    | `<nom>`                                                                   | Pose un point de téléport (home)                                                                            |
| `/home`       | `<nom>`                                                                   | Téléporte le joueur à un home                                                                               |
| `/delhome`    | `<nom>`                                                                   | Supprime un home                                                                                            |
| `/world`      | `<create/delete/tp>` `<normal/void>` `optional:[--time <enable/disable>]` | Gestion des mondes et téléportation dans les mondes                                                         |
| `/ec`         | `<Rien>`                                                                  | Accède à l'Ender Chest du joueur                                                                            |
| `/invsee`     | `<player>`                                                                | Affiche le contenue de l'inventaire d'un joueur                                                             |
| `/night`      | `y/n`                                                                     | Confirme le passage de la nuit si un joueur décide d'aller dormir (dans Minecraft bien sûr ici on dors pas) |    


# Rythme de sortie des releases

Les releases sont des packages dits stables. En revanche le dépôt git peut comporter des bugs.

Les releases sortent quand tout fonctionne ! (Logique)

Je pourrais automatiser ça avec GitHub Actions mais cela serait un peu bête si mon code n'est pas fonctionnel. (Bien qu'il permet de le vérifier, mais je préfère le faire manuellement.)

# Contribuer

Si vous souhaitez contribuer au code de mon plugin, vous pouvez !

De même si vous souhaitez que je rajoute une feature faites le savoir :p
