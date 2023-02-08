# escape-game

This repository includes an updated version of our COMP 302 Term Project for Fall 2022. 

Group Members (credits to them :)
- İrem Karaca
- Birkan Çelik
- Mete Erdoğan
- Arda Gürsul
- Begüm Yalçın

We did the game all together. After that, I changed some little details and prepared this README. I hope you enjoy!

## About Game
This is a classical escape game. The aim is to find keys in all buildings. Once you find the key, you need to walk towards the door of the building. After passing the door, you will reach the next building. In order to win this game, finding all keys and passing through all doors are needed. The game takes place at Koç University -the university I am studying in. 


## Gameplay

### Login Screen
- First of all you need to login the game. 
- If you don't have an account, don't worry! You can create one with the sign up button.

<img width="1112" alt="Screen Shot 2023-02-06 at 20 18 59" src="https://user-images.githubusercontent.com/66200657/217040023-6797ae85-7f62-4ffb-bc1e-9f02af97c130.png">

## Load Game
- You can load the game from local or cloud! Yeah, we saved your progress in both your local machine and our cloud if you exit the game with the corresponding button. Btw we used MongoDB for database.
- Is this your first time? Welcome, my friend!!! You can start playing with clicking the Start New Game button.

<img width="1112" alt="Screen Shot 2023-02-06 at 20 24 44" src="https://user-images.githubusercontent.com/66200657/217041374-9a7532f0-6296-42ef-aba5-819b5e7104cd.png">

## Building Mode
- Meh, this might be the most boring part of the game. 
- You need to click inside the grids to put objects. The type of objects are random. And all of them are some classroom stuff since the game is in our university.
- Every building has its own amount of needed objects. You are not able to build other buildings if the current object amount is not enough.
- If you want to delete an object, you can click it again.
- Try putting objects in a way that the door become unreachable :) or putting them in a way that forming a closed area. (Don't try, we did dfs to prevent these kind of things)

<img width="1112" alt="Screen Shot 2023-02-06 at 20 39 26" src="https://user-images.githubusercontent.com/66200657/217044851-3bcb95af-d8f9-486a-9a4f-4db757a754d3.png">


## Running Mode
- The player walks around using the arrow keys. The avatar is Bambi -this was our group name and our initials.
- You need to go next to an object and click on it to search for the key. Once you find the key, a key will be appeared for a second and the door will be opened.
- There is a time limit also. The time is limited by 5 seconds for each object presented in the building.

### Aliens
- Let me introduce the enemies: aliens. I have no idea what they are doing in Koç University but here we are.
- All aliens appear randomly in the buildings every 10 seconds and the type of the appearing aliens are again selected randomly.
- There are three types of aliens:
1. Shooter Alien
1. Blind Alien 
2. Time-wasting Alien

#### Shooter Alien
- This type of alien is blue -cyan actually.

![shooter_alien_img](https://user-images.githubusercontent.com/66200657/217061155-7281bdf9-aa7d-483a-a652-167dea81d6a6.png)

- It appears in a random location in the building and shoots a bullet every second.
- The shoot range is marked with red color. Once you are inside the red area, you are shot every second. However, you are safe at the outside of the red area.
- If you are shot, you will lose a life.

<img width="1292" alt="Screen Shot 2023-02-06 at 19 52 05" src="https://user-images.githubusercontent.com/66200657/217528993-6afdbe82-3ea1-4eda-8c88-288a892a2361.png">


#### Blind Alien
- This type of alien is pink -magenta actually.

![blind_alien_img](https://user-images.githubusercontent.com/66200657/217061168-0880600c-bd63-4804-87c2-39095d96da71.png)

- It walks around randomly.
- To be killed by blind alien, you should be next to it.
- Once you are killed, you will lose a life.


#### Time-wasting Alien
- This type of alien is green -yes, just green.

![timewasting_alien_img](https://user-images.githubusercontent.com/66200657/217061587-47ec07de-20a1-412a-91a2-4890f7ddf471.png)

- It does not kill the player, but it changes the location of the key randomly.
- If less than 30% of the total time remains when this alien appears, the alien will conclude that the player is not in a good situation, and player might lose the game. So, it will change the location of the key only once and disappear.
- If more than 70% of the total time remains, the alien will conclude that the player is doing well. So it will make the situation challenging by changing the location of the key every 3 seconds.
- If the remaining time is between 30% - 70%, it will get confused and indecisive, so it will stay in the place in which it appears, then disappear after 2 seconds without doing anything.


### Power-ups
- Don't worry! There is also power-ups that can help you.
- To collect them, you need to click on the power-up -your location doesn't matter.
- Once you collect, you can see them on the inventory -right side of the game screen.

#### Extra Time
<img src="https://user-images.githubusercontent.com/66200657/217525124-1fef9752-c2aa-4b38-86b2-5ec781079067.png" alt= "time_powerup_img" width="100" height="100">   
- When the user collects an extra time power-up extra 5 seconds are added to the timer.


#### Hint

 
<img src="https://user-images.githubusercontent.com/66200657/217525792-023d73a8-c223-4dc0-bf06-4e3480db4df9.png" alt= "hint_powerup_img" width="100" height="100">

- This power-up gives a hint about the location of the key.
- To use it, hit the H button on the keyboard.
- It will be shown that, the key is inside the yellow area.

<img width="1292" alt="Screen Shot 2023-02-08 at 15 17 18" src="https://user-images.githubusercontent.com/66200657/217528714-0a2348bb-08ac-4cb5-a5e8-e54e7645100c.png">

#### Protection Vest
<img src="https://user-images.githubusercontent.com/66200657/217525966-183066b6-687c-4a44-b00a-6e67b53efe1e.png" alt= "vest_powerup_img" width="100" height="100">

- This power-up protects you from being shot by the shooter alien.
- To use it, hit the P button on the keyboard.
- When you wear it, its protection lasts for 20 seconds.

#### Plastic Bottle
<img src="https://user-images.githubusercontent.com/66200657/217526052-2bf80e0d-31c7-4c63-aa2f-4e0ba6edd528.png" alt= "bottle_powerup_img" width="100" height="100">

- This power up is used to fool the blind alien. 
- To use it,  first hit the B button and then one of the following buttons A, D, W, or X to decide on the bottle’s direction (A: west, D: east, W: north, X:south).
- Once you use it, the blind alien will walk to the direction you chose until the alien hits somewhere.

#### Extra Life

<img src="https://user-images.githubusercontent.com/66200657/217526109-52837d60-6cc3-4d5e-ae04-1a07557364d0.png" alt= "life_powerup_img" width="100" height="100">

- This power-up adds one extra life to the player’s lives.
