JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $<

CLASSES = Drink.class Food.class FoodTable.class GameController.class GameInterface.class ITamagotchi.class Main.class MakefileGenerator.class Menu.class MiniGame.class Play.class SolidFood.class Tamagotchi.class TicTacToe.class 

all: $(CLASSES)

clean:
	$(RM) *.class

build: all

