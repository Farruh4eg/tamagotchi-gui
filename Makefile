JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $<

CLASSES = Main.class Food.class ITamagotchi.class GameInterface.class Tamagotchi.class Drink.class MiniGame.class SolidFood.class FoodTable.class MakefileGenerator.class Menu.class GameController.class Whackamole.class TicTacToe.class Play.class 

all: $(CLASSES)

clean:
	$(RM) *.class

build: all

