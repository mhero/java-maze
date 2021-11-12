## Java maze game

#### Docker: ####

```bash
docker-compose up -d
docker attach java-maze_app_1
```
	

#### Build: ####

- Install maven
- Run in terminal command:
	

```bash
mvn package
```

#### Run: ####
	
```bash
java -jar target\mazer-jar-with-dependencies.jar
```

#### Includes: ####

- Maze generator
- Game menu
- Storing/loading game
- Trivia topic when enemies encounter
- Auto restart after winning

Instructions:

- Start the program
- Main menu shows, start a new game using 1, load previous game using 2
- You'll be sent to secondary menu, you can navigate the maze using that
- To save, return to manin menu using 5, and save it using 3
- Once you encounter an enemy "X" you'll be prompt with a question

![alt text](https://i.imgur.com/MfA7r4Q.png)



