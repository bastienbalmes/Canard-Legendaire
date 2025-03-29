# Canard Battle - Modélisation et Choix Techniques

## Questionnements sur la modélisation

### 1. Quelles classes pourraient être abstraites ?
La classe `Canard` pourrait être abstraite, vu qu'on a plusieurs types de canards qui héritent de cette classe. Elle aurait des méthodes communes à tous les canards, mais certaines actions spécifiques seraient définies dans les classes filles

### 2. Quels comportements communs pourraient être définis dans une interface ?
Des comportements comme `attaquer()` ou `activerCapaciteSpeciale()` pourraient être définis dans une interface. Chaque type de canard pourrait implémenter cette interface de manière spécifique, en ayant une structure commune

### 3. Comment représenter un changement de statut (par exemple, brûlé ou paralysé) dans la modélisation ?
Les statuts comme "brûlé" ou "paralysé" sont représentés par un `enum` (`TypeStatus`). Cet enum permet de gérer facilement les effets de statut et facilite l'ajout de nouveaux statuts

### 4. Quels seraient les avantages d’utiliser une classe ou une interface supplémentaire pour gérer les capacités spéciales ?
Une classe ou interface supplémentaire pour les capacités spéciales permettrait de mieux séparer la logique des différentes capacités. Cela rendrait le code plus lisible et plus facile à maintenir, car chaque capacité spéciale serais implémenter indépendamment des autres

### 5. Quels défis sont associés à l’extensibilité du modèle pour ajouter de nouveaux types de canards ou de nouvelles capacités ?
Le principal défi est de s’assurer que les nouveaux types de canards ou capacités n'entrent pas en conflit avec les existants. L'utilisation d'interfaces et de classes abstraites facilite l'ajout de nouveaux éléments. De plus, il faut s'assurer que les interactions entre types de canards restent équilibrées

---

## Modifications et Réflexion Conceptuelle

### 1. Canard abstrait
J'ai choisi de rendre la classe `Canard` abstraite, car tous les canards partagent certaines propriétés, mais chaque type de canard a des comportements spécifiques. Cette approche évite la duplication du code et facilite l'ajout de nouveaux types de canards

### 2. Enum pour les statuts
Pour gérer les statuts comme "brûlé", "paralysé", ou "gelé", j'ai utilisé un `enum` (`TypeStatus`). Cela permet une meilleure organisation et facilite l'ajout de nouveaux statuts

### 3. ArrayList pour stocker la durée des statuts
J'ai choisi une `ArrayList<Integer>` pour stocker la durée de chaque statut, ce qui permet de gérer facilement plusieurs statuts en même temps. Les indices de la liste sont liés aux indices de l'`enum`

---

## Description des choix techniques

### 1. Effets de statut
- **Modifications apportées :**
  - Utilisation d'un `enum` pour les statuts
  - Stockage des durées des statuts dans une `ArrayList<Integer>`
- **Pourquoi ce choix ?**
  - L'`enum` permet d'éviter des erreurs avec les String par exemple, et facilite l'ajout de nouveaux statuts
  - L'`ArrayList` permet une gestion simple de la durée des statuts

### 2. Points d'Énergie (PE)
- **Modifications apportées :**
  - Ajout d'une variable `pe` pour suivre l'énergie de chaque canard
  - Vérification des PE avant chaque action pour éviter des attaques sans énergie
- **Pourquoi ce choix ?**
  - Une varaible private dans l'objet canard suffit pour gérer se comportement

### 3. Attaques Critiques
- **Modifications apportées :**
  - Ajout d'une probabilité de 10% pour un coup critique (dégâts x2)
  - Gestion via un nombre aléatoire généré à chaque attaque

### 4. Gestion des Statuts et Attaques avec Effets
- **Modifications apportées :**
  - Gestion des effets de statut directement dans les méthodes d'attaque et de capacité spéciale
  - Mise à jour automatique des statuts à chaque tour
- **Pourquoi ce choix ?**
  - Facilite l'ajout de nouveaux effets
  - Permet de gérer facilement la durée des statut

---

## Diagramme UML des classes
