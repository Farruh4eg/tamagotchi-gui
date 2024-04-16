public class Tamagotchi implements ITamagotchi {
    private int hunger, thirst, happiness;
    private String nickname, imagePath, playOnePath, playTwoPath, playThreePath;

    public String getPlayOnePath() {
        return playOnePath;
    }

    public String getPlayTwoPath() {
        return playTwoPath;
    }

    public String getPlayThreePath() {
        return playThreePath;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        if (this.hunger > 100) {
            this.hunger = 100;
        } else {
            this.hunger = hunger;
        }

    }

    public int getThirst() {
        return thirst;
    }

    public void setThirst(int thirst) {
        if (this.thirst > 100) {
            this.thirst = 100;
        } else {
            this.thirst = thirst;
        }
    }

    public int getHappiness() {

        return happiness;
    }

    public void setHappiness(int happiness) {
        if (this.happiness > 100) {
            this.happiness = 100;
        } else {
            this.happiness = happiness;
        }
    }

    public String getNickname() {
        return nickname;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Tamagotchi(String nickname, String imagePath, String playOnePath, String playTwoPath, String playThreePath) {
        this.nickname = nickname;
        this.imagePath = imagePath;
        this.playOnePath = playOnePath;
        this.playTwoPath = playTwoPath;
        this.playThreePath = playThreePath;
        hunger = thirst = happiness = 50;
    }

    public void feed(Food food) {
        this.hunger += food.getNutrition();
        this.thirst += food.getWater();
    }
}