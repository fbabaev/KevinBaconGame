# KevinBaconGame
### Authors: Firdavskhon Babaev, Dhanush Balaji  
### Dartmouth CS 10, Winter 2024

---

## Project Overview

The **Kevin Bacon Game** is a tool that lets you explore the connections between actors based on the movies they’ve appeared in together. The game is centered around finding the shortest path, or "Bacon number," between any actor and a chosen center, like Kevin Bacon. You can also change the center to any other actor and see how others connect to them.

## Features

### 1. Find Bacon Numbers
   - Calculate the shortest path between any actor and the current center of the universe.
   - See the movies that link these actors together.

### 2. Change the Center
   - You can set any actor as the center of the universe to explore how other actors are connected to them.

### 3. Actor Network Analysis
   - Find actors who are not connected to the center.
   - List the best or worst centers based on how well-connected they are.
   - Look at actors by the number of movies they’ve appeared in with other actors.

## How to Use

This program is run from the command line. Here are the main commands you can use:

### Commands

- `u <name>`: Set `<name>` as the new center of the universe.
  
- `p <name>`: Find the shortest path from `<name>` to the current center of the universe.

- `c <#>`: List the top or bottom `<#>` centers of the universe by how well they are connected to others.

- `d <low> <high>`: Show actors who have a number of co-stars between `<low>` and `<high>`.

- `i`: List actors who aren’t connected to the current center.

- `s <low> <high>`: Show actors with a connection path between `<low>` and `<high>` steps.

- `q`: Quit the program.

### Example

To find the Bacon number for Diane Keaton:

```bash
p Diane Keaton

