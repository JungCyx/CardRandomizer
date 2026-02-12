# Card Randomizer 

A modern **Java Swing desktop application** that simulates a full 52-card deck.

The application allows users to shuffle the deck, deal cards one at a time, reset the deck instantly, and track live statistics  all inside a clean, casino-style interface.

---

## Overview

Card Randomizer is a graphical desktop application built using **Java Swing**. It visually displays all 52 playing cards in a grid layout and provides interactive controls for managing the deck.

The interface includes:

- A full deck grid display  
- A dealt card preview section  
- Live deck statistics  
- Button controls and keyboard shortcuts  

---

## Features

- **Full 52-Card Grid** (4 × 13 layout)
- **Shuffle Deck** using built-in randomization
- **Deal Cards** one at a time
- **Reset Deck** to original state
- **Live Statistics**
  - Cards Remaining
  - Cards Dealt
- **Keyboard Shortcuts**
- Styled buttons with hover effects
- Scrollable card panel
- Dealt card preview display

---

## Controls

### Keyboard Shortcuts

| Key      | Action         |
|----------|---------------|
| `Space`  | Shuffle deck  |
| `D`      | Deal a card   |
| `R`      | Reset deck    |

### On-Screen Buttons

- **Shuffle (Space)**
- **Deal Card (D)**
- **Reset (R)**

---


Screenshot:

<img width="1371" height="796" alt="Card Randomizer" src="https://github.com/user-attachments/assets/9cf77ceb-6a15-4b67-849c-79791cff38f4" />


## Requirements

- **Java JDK 8 or higher**
- Recommended: **JDK 17 or newer**

Compatible with:
- Windows  
- macOS  
- Linux  

---

## How to Run

### Option 1 — Command Line

Navigate to the folder containing `CardRandomizer.java`:

javac CardRandomizer.java
java CardRandomizer

### Option 2 — VS Code (similiar steps for other IDE's)

1. Install a Java JDK (17+ recommended).
2. Install the Java Extension Pack in VS Code.
3. Open the project folder.
4. Run `CardRandomizer.java` using the Run button.

## Notes

- The application relies on relative image paths.
- Ensure you run the program from the project root directory.
- All deck logic is handled in-memory during runtime
