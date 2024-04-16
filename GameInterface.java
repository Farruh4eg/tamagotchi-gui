import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameInterface extends JFrame {
    public static Tamagotchi tamagotchi;
    private static Boolean initialized = false, playOnDeath = false;
    private static JProgressBar hungerLevel, thirstLevel, happinessLevel;
    private List<Food> availableFood;

    public GameInterface(Tamagotchi tamagotchi) {
        super("Тамагочи");
        availableFood = new ArrayList<>();
        availableFood.add(new Drink("Молоко", 30, "./static/milk.jpg"));
        availableFood.add(new Drink("Вода", 30, "./static/water.jpg"));
        availableFood.add(new SolidFood("Стейк", 50, "./static/steak.jpg"));
        availableFood.add(new SolidFood("Яблоко", 20, "./static/apple.jpg"));

        GameInterface.tamagotchi = tamagotchi;
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton playButton = new JButton("Поиграть с " + tamagotchi.getNickname());
        JButton foodButton = new JButton("Еда");
        JButton miniGameButton = new JButton("Мини-игры");

        // stats panel (located in right panel)
        JPanel statsPanel = new JPanel();

        // hunger level panel
        JPanel hungerPanel = new JPanel();

        JLabel hungerLabel = new JLabel();
        ImageIcon hungerIcon = new ImageIcon(
                new ImageIcon("./static/hungerIcon.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        hungerLabel.setIcon(hungerIcon);

        hungerLevel = new JProgressBar();
        hungerLevel.setStringPainted(true);
        hungerLevel.setMinimum(0);
        hungerLevel.setMaximum(100);
        hungerLevel.setValue(tamagotchi.getHunger());

        hungerPanel.add(hungerLabel);
        hungerPanel.add(hungerLevel);

        // thirst level panel
        JPanel thirstPanel = new JPanel();

        JLabel thirstLabel = new JLabel();
        ImageIcon thirstIcon = new ImageIcon(
                new ImageIcon("./static/thirstIcon.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        thirstLabel.setIcon(thirstIcon);

        thirstLevel = new JProgressBar();
        thirstLevel.setStringPainted(true);
        thirstLevel.setMinimum(0);
        thirstLevel.setMaximum(100);
        thirstLevel.setValue(tamagotchi.getThirst());

        thirstPanel.add(thirstLabel);
        thirstPanel.add(thirstLevel);

        // happiness level panel
        JPanel happinessPanel = new JPanel();

        JLabel happinessLabel = new JLabel();
        ImageIcon happinessIcon = new ImageIcon(
                new ImageIcon("./static/happinessIcon.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        happinessLabel.setIcon(happinessIcon);

        happinessLevel = new JProgressBar();
        happinessLevel.setStringPainted(true);
        happinessLevel.setMinimum(0);
        happinessLevel.setMaximum(100);
        happinessLevel.setValue(tamagotchi.getHappiness());

        happinessPanel.add(happinessLabel);
        happinessPanel.add(happinessLevel);

        // all stats panel
        statsPanel.add(new JLabel("Показатели") {
            {
                setFont(new Font("Arial", Font.BOLD, 16));
            }
        });
        statsPanel.add(hungerPanel);
        statsPanel.add(thirstPanel);
        statsPanel.add(happinessPanel);
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setAlignmentX(CENTER_ALIGNMENT);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 100, 100));

        // buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 0));
        buttonsPanel.add(playButton);
        buttonsPanel.add(foodButton);
        buttonsPanel.add(miniGameButton);

        // right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));

        rightPanel.add(statsPanel);
        rightPanel.add(buttonsPanel);

        // pet image panel
        JPanel petPanel = new JPanel();

        JLabel petImageLabel = new JLabel();
        ImageIcon petImage = new ImageIcon(
                new ImageIcon(tamagotchi.getImagePath()).getImage().getScaledInstance(800, 720,
                        Image.SCALE_AREA_AVERAGING));
        petImageLabel.setIcon(petImage);

        petPanel.add(petImageLabel);

        // whole game panel
        JPanel gamePanel = new JPanel(new BorderLayout());

        gamePanel.add(petPanel, BorderLayout.CENTER);
        gamePanel.add(rightPanel, BorderLayout.EAST);

        addListeners(playButton, foodButton, miniGameButton);

        setContentPane(gamePanel);
        setSize(1280, 720);
        setResizable(false);
        setVisible(true);
    }

    public static void updateStats(int hunger, int thirst, int happiness) {
        if (hunger <= 0 || thirst <= 0 || happiness <= 0) {
            death();
        }
        GameInterface.tamagotchi.setHunger(hunger);
        GameInterface.tamagotchi.setThirst(thirst);
        GameInterface.tamagotchi.setHappiness((hunger + thirst + tamagotchi.getHappiness()) / 3);
        updateProgressBars();
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static void setInitialized(Boolean initialized) {
        GameInterface.initialized = initialized;
    }

    private static void updateProgressBars() {
        int hungerLevel = tamagotchi.getHunger(), thirstLevel = tamagotchi.getThirst();
        int happinessLevel = (hungerLevel + thirstLevel + GameInterface.tamagotchi.getHappiness()) / 3;
        GameInterface.hungerLevel.setValue(hungerLevel);
        GameInterface.thirstLevel.setValue(thirstLevel);
        GameInterface.happinessLevel.setValue(happinessLevel);
    }

    private static void death() {
        if (!playOnDeath) {
            new Play("./static/death.gif", GameInterface.tamagotchi, true);
            playOnDeath = true;
        }
    }

    public static void setTamagotchi(Tamagotchi tamagotchi) {
        GameInterface.tamagotchi = tamagotchi;
    }

    public static void setHungerLevel(JProgressBar hungerLevel) {
        GameInterface.hungerLevel = hungerLevel;
    }

    public static void setThirstLevel(JProgressBar thirstLevel) {
        GameInterface.thirstLevel = thirstLevel;
    }

    public static void setHappinessLevel(JProgressBar happinessLevel) {
        GameInterface.happinessLevel = happinessLevel;
    }

    private void addListeners(JButton playButton, JButton foodButton, JButton miniGameButton) {
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Random random = new Random();
                switch (random.nextInt(3) + 1) {
                    case 1:
                        new Play(GameInterface.tamagotchi.getPlayOnePath(), GameInterface.tamagotchi, false);
                        break;
                    case 2:
                        new Play(GameInterface.tamagotchi.getPlayTwoPath(), GameInterface.tamagotchi, false);
                        break;
                    case 3:
                        new Play(GameInterface.tamagotchi.getPlayThreePath(), GameInterface.tamagotchi, false);
                        break;
                    default:
                        break;
                }
            }
        });

        foodButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new FoodTable(availableFood);
            }
        });

        miniGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MiniGame(tamagotchi);
            }
        });
    }
}
