import java.util.TimerTask;

public class GameController extends TimerTask {
  Tamagotchi tamagotchi;

  @Override
  public void run() {
    if (GameInterface.isInitialized()) {
      int hungerLevel = tamagotchi.getHunger(), thirstLevel = tamagotchi.getThirst();
      int happinessLevel = (hungerLevel + thirstLevel + tamagotchi.getHappiness()) / 3;
      GameInterface.updateStats(hungerLevel - 1, thirstLevel - 2, happinessLevel);
    }
  }

  public GameController(Tamagotchi tamagotchi) {
    this.tamagotchi = tamagotchi;
  }
}