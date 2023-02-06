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

### Aliens
- Let me introduce the enemies: aliens. I have no idea what they are doing in Koç University but here we are.
- All of the aliens appear randomly in the buildings every 10 seconds and the type of the appearing aliens are again selected randomly.
- There are three types of aliens:
1. Shooter Alien
1. Blind Alien 
2. Time-wasting Alien

### Shooter Alien
- This type of alien is blue -cyan actually.

![Adsız tasarım](https://user-images.githubusercontent.com/66200657/217061155-7281bdf9-aa7d-483a-a652-167dea81d6a6.png)


### Blind Alien
- This type of alien is pink -magenta actually.

![Adsız tasarım-3](https://user-images.githubusercontent.com/66200657/217061168-0880600c-bd63-4804-87c2-39095d96da71.png)

### Time-wasting Alien
- This type of alien is green -yes, just green.

![Adsız tasarım-6](https://user-images.githubusercontent.com/66200657/217061587-47ec07de-20a1-412a-91a2-4890f7ddf471.png)
