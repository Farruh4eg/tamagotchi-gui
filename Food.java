public class Food {
    private int nutrition, water;
    private String imagePath, name;

    public int getWater() {
        return water;
    }

    public int getNutrition() {
        return nutrition;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Food(int nutrition, int water, String name, String imagePath) {
        this.nutrition = nutrition;
        this.water = water;
        this.name = name;
        this.imagePath = imagePath;
    }
}
