# Social Media Simulation (Java)

## Overview  
This project simulates a simplified **social media ecosystem**, modeling interactions between **content creators** and **users**.  

- **Creators** (4 types: Gamer, Vlogger, Controversial Commentary, Reaction) produce different content (videos, live streams, pictures).  
- **Users** (3 types: Loyal, Hater, Casual) consume and react to content in unique ways.  
- The simulation runs with preset inputs (subscriber counts, posting frequency, actions) and produces statistics at the end.  

The goal is to explore **design patterns, concurrency, and system behavior** in a realistic, engaging simulation.  

---

## Key Features  
- **Observer Pattern** – Users (observers) get notified of creator uploads (observables) and react based on their type.  
- **Template Method Pattern** – Users follow a sequential “reaction flow” (view → like/dislike → share/unsubscribe) with customizable behavior per type.  
- **Proxy Pattern (Shadow Ban System)** – Banned creators can’t gain new subscribers until they post an apology.  
- **Singletons** –  
  - `BannedCreators`: tracks and manages banned creators.  
  - `Trending`: manages which creators are trending at a given time.  
- **Semaphores** – Used for the **Reaction Channel**, which can only post after the **Controversial Commentary Channel** uploads.  

---

## Class Hierarchy  
- **Creators** (`Units` superclass)  
  - Gamer 🎮 – Can host giveaways when trending.  
  - Vlogger 📹 – Travels and posts special vlogs.  
  - Controversial Commentary ⚡ – Risk of bans (shadow ban system).  
  - Reaction Channel 🔁 – Reacts to content from the Commentary channel.  
- **Users** (`User` superclass, implements Observer)  
  - Loyal User ❤️ – Likes most content, rarely unsubscribes.  
  - Hater 👎 – Always dislikes, spreads negativity.  
  - Casual User 🙂 – Neutral, may unsubscribe if offended.  

---

## Simulation Flow  
1. Input specifies creators, actions, and posting frequencies.  
2. Creators post content → Users get notified → Users react.  
3. Statistics are updated (likes, dislikes, subs gained/lost, bans, trending).  
4. At the end:  
   - Growth, bans, and trending history are printed.  
   - Results vary depending on test scenario.  

---

## Test Scenarios  
- **Test 1: Normal Run** – Balanced posting and growth across all creators.  
- **Test 2: Lazy Controversial Creator** – Reaction channel stops posting when there’s no content to react to.  
- **Test 3: Controversy Spike** – Controversial creator grows rapidly due to hate engagement.  

---

## Technologies  
- Java (Core OOP)  
- Multithreading (Semaphores)  
- Design Patterns (Observer, Template Method, Proxy, Singleton)  

---

## File Structure  
/src  
├── Units.java  
├── Gamer.java  
├── Vlogger.java  
├── ControversialCommentary.java  
├── ReactionChannel.java  
├── User.java  
├── LoyalUser.java  
├── HaterUser.java  
├── CasualUser.java  
├── BannedCreators.java  
├── Trending.java  
└── SimulationInput.java  

---

## Notes  
This project was created for practice in **object-oriented design and simulation modeling**.  
It focuses on **system interactions** and **patterns** rather than UI.  

