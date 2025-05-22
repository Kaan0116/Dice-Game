# Dice Game - BBM104 Spring 2024 Quiz 1

This project implements a simple object-oriented dice game in Java, developed as part of the BBM104 Introduction to Programming Laboratory II course at Hacettepe University (Spring 2024). The goal is to apply concepts of abstraction and encapsulation while building a console-based game simulation.

## 🎮 Game Summary

- Each player rolls two dice during their turn.
- If both dice are NOT 1, the sum is added to the player's score.
- If exactly one die is 1, nothing happens.
- If both dice are 1, the player is eliminated and their score resets to 0.
- Players can skip their turn with a "0-0" input.
- The game continues until only one player remains, who becomes the winner.

## 📁 File Structure

The `input/` folder contains 3 sample inputs and their corresponding expected outputs:

input/
├── i1.txt # Sample input 1
├── i2.txt # Sample input 2
├── i3.txt # Sample input 3
├── o1.txt # Expected output 1
├── o2.txt # Expected output 2
└── o3.txt # Expected output 3



Source files (e.g., `DiceGame.java`) should be placed inside a `src/` folder if modularized, or in the root directory if using a single file.

## ▶️ How to Compile & Run (Java 8u401)

```bash
javac8 DiceGame.java
java8 DiceGame input/i1.txt output.txt
