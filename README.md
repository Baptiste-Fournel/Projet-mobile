# 📚 Quiz Multiplatform

## 🚀 Introduction
Bienvenue dans **Quiz Multiplatform**, une application interactive développée en **Kotlin Multiplatform** avec **Jetpack Compose**. 
Elle permet aux utilisateurs de tester leurs connaissances avec des **questions interactives**, un **système de vies**, un **timer dynamique**, et des **indices** pour aider les joueurs.

---

## 🎮 Fonctionnalités
✅ **Questions interactives** : Choisissez la bonne réponse parmi plusieurs propositions. </br>
✅ **Système de vies** : Vous démarrez avec 5 vies et pouvez retenter une question en cas d'erreur.</br>
✅ **Timer dynamique** : Chaque question doit être répondue en 10 secondes.</br>
✅ **Indices automatiques** : Un indice s'affiche après 5 secondes si aucune réponse n'est sélectionnée</br>
✅ **Progression visible** : Une barre de progression affiche l'avancement du quiz.</br>
✅ **Écran des scores** : À la fin du quiz, un écran résume votre performance avec un affichage coloré.</br>

---

## 📸 Aperçu de l'interface
🎯 **Écran d'accueil** :
- Présentation du quiz.
- Bouton de démarrage du jeu.

📝 **Écran de question** :
- Affichage de la question actuelle.
- Choix des réponses interactifs.
- Timer visible avec changement de couleur selon le temps restant.
- Affichage des vies sous forme d'icônes ❤️.
- Barre de progression.

🏆 **Écran des scores** :
- Affichage du score final.
- Progression du score sous forme de barre.
- Bouton pour rejouer le quiz.

---

## 🛠️ Technologies utilisées
- **Kotlin Multiplatform (KMP)** : Développement multiplateforme Android, iOS et Desktop.
- **Jetpack Compose** : UI réactive et moderne.
- **Ktor** : Requêtes HTTP et gestion des données distantes.
- **Serialization Kotlinx** : Gestion des fichiers JSON pour stocker les questions.
- **State Management** : `remember`, `mutableStateOf` pour la gestion d'état.

---

## 🏗️ Structure du projet
📂 **src/commonMain/kotlin/com/worldline/quiz/**
- `data/` → Gestion des données et questions du quiz.
- `screens/` → Composants UI du quiz.
- `QuizViewModel.kt` → Gestion des états du quiz.
- 
---

## 📝 Fonctionnement détaillé
### ⏳ **Gestion du Timer**
Chaque question démarre avec **10 secondes**.
- Si `timeLeft == 5`, **l'indice** de la question est affiché.
- Si `timeLeft == 0`, on passe automatiquement à la **question suivante**.
- Si `timeLeft == 0` sur **la dernière question**, on affiche l'**écran des scores**.

### ❤️ **Gestion des vies**
- Le joueur commence avec **5 vies**.
- Si une réponse est incorrecte, il **perd une vie** et peut retenter la question.
- Si **toutes les vies sont perdues**, il passe automatiquement à la **question suivante**.

### 🏆 **Affichage des scores**
À la fin du quiz, le score est affiché avec une **couleur dynamique** :
- 🟢 **Vert** si score > 80%
- 🟠 **Orange** si score entre 50% et 80%
- 🔴 **Rouge** si score < 50%

---

## 🔧 Installation et exécution
1️⃣ **Cloner le projet** :
```bash
git clone https://github.com/worldline/quiz-multiplatform.git
cd quiz-multiplatform
```

2️⃣ **Ouvrir avec Android Studio**
3️⃣ **Exécuter sur l'émulateur ou un appareil réel**

---

📌 **Merci d'utiliser Quiz Multiplatform ! Bonne chance et amusez-vous bien ! 🎉**
