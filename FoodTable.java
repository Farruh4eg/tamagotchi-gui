import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoodTable {
  public FoodTable(List<Food> availableFood) {
    JFrame frame = new JFrame();
    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    JPanel columnsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 120, 10));
    columnsPanel.add(new JLabel("Фото"));
    columnsPanel.add(new JLabel("Название"));
    columnsPanel.add(new JLabel("Сытность"));
    columnsPanel.add(new JLabel("Жидкость"));
    columnsPanel.add(new JLabel("Действие"));

    JScrollPane scrollPane = new JScrollPane();
    frame.add(columnsPanel);

    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

    for (Food food : availableFood) {
      JPanel currentFoodPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 150, 10));

      JLabel iconLabel = new JLabel();
      ImageIcon foodIcon = new ImageIcon(
          new ImageIcon(food.getImagePath()).getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING));
      iconLabel.setIcon(foodIcon);

      currentFoodPanel.add(iconLabel);
      currentFoodPanel.add(new JLabel(food.getName()));
      currentFoodPanel.add(new JLabel(String.valueOf(food.getNutrition())));
      currentFoodPanel.add(new JLabel(String.valueOf(food.getWater())));

      JButton consumeButton = new JButton("Употребить");
      consumeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          GameInterface.tamagotchi.feed(food);
        };
      });
      currentFoodPanel.add(consumeButton);

      contentPanel.add(currentFoodPanel);
    }

    scrollPane.setViewportView(contentPanel);
    frame.add(scrollPane);

    frame.setSize(1280, 720);
    frame.setResizable(false);
    frame.setVisible(true);
  }
}
