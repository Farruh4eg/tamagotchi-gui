import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Menu extends JFrame {
    public static String petName = null, selectedImage = null, selectedPlayOne = null, selectedPlayTwo = null,
            selectedPlayThree = null;
    private FileFilter gifFilter = new FileNameExtensionFilter("GIF-файлы", "gif");

    FileFilter fileFilter = new FileNameExtensionFilter("Изображения", ImageIO.getReaderFileSuffixes());
    JTextField nameTextField, imgPathTextField, playOneTextField, playTwoTextField, playThreeTextField;
    JLabel nameLabel, imgPathLabel, playOneLabel, playTwoLabel, playThreeLabel;
    int width = 800, height = 600;
    JFileChooser fileChooser;
    JButton chooseImageButton, choosePlayOneButton, choosePlayTwoButton, choosePlayThreeButton, confirmButton;

    public Menu() {
        super("Тамагочи");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        nameLabel = new JLabel("Имя");

        nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(400, 30));

        JPanel textFieldPanel = new JPanel();
        textFieldPanel.add(nameLabel);
        textFieldPanel.add(Box.createHorizontalStrut(10));
        textFieldPanel.add(nameTextField);
        textFieldPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        chooseImageButton = new JButton("Выбрать изображение");
        imgPathLabel = new JLabel("Фото питомца");
        imgPathTextField = new JTextField();
        imgPathTextField.setEditable(false);
        imgPathTextField.setPreferredSize(new Dimension(400, 30));

        JPanel chooseImagePanel = new JPanel();
        chooseImagePanel.add(imgPathLabel);
        chooseImagePanel.add(Box.createHorizontalStrut(10));
        chooseImagePanel.add(imgPathTextField);
        chooseImagePanel.add(chooseImageButton);
        chooseImagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // all plays panel
        JPanel playsPanel = new JPanel();
        playsPanel.setLayout(new BoxLayout(playsPanel, BoxLayout.Y_AXIS));
        playsPanel.add(new JLabel("Анимации") {
            {
                setFont(new Font("Arial", Font.BOLD, 20));
            }
        });
        playsPanel.add(Box.createHorizontalStrut(0));

        // play one panel
        playOneLabel = new JLabel("Анимация игры 1");
        playOneTextField = new JTextField();
        playOneTextField.setEditable(false);
        playOneTextField.setPreferredSize(new Dimension(400, 30));

        choosePlayOneButton = new JButton("Выбрать анимацию");

        JPanel playOnePanel = new JPanel();
        playOnePanel.add(playOneLabel);
        playOnePanel.add(Box.createHorizontalStrut(10));
        playOnePanel.add(playOneTextField);
        playOnePanel.add(choosePlayOneButton);
        playOnePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // play two panel
        playTwoLabel = new JLabel("Анимация игры 2");
        playTwoTextField = new JTextField();
        playTwoTextField.setEditable(false);
        playTwoTextField.setPreferredSize(new Dimension(400, 30));

        choosePlayTwoButton = new JButton("Выбрать анимацию");

        JPanel playTwoPanel = new JPanel();
        playTwoPanel.add(playTwoLabel);
        playTwoPanel.add(Box.createHorizontalStrut(10));
        playTwoPanel.add(playTwoTextField);
        playTwoPanel.add(choosePlayTwoButton);
        playTwoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // play three panel
        playThreeLabel = new JLabel("Анимация игры 3");
        playThreeTextField = new JTextField();
        playThreeTextField.setEditable(false);
        playThreeTextField.setPreferredSize(new Dimension(400, 30));

        choosePlayThreeButton = new JButton("Выбрать анимацию");

        JPanel playThreePanel = new JPanel();
        playThreePanel.add(playThreeLabel);
        playThreePanel.add(Box.createHorizontalStrut(10));
        playThreePanel.add(playThreeTextField);
        playThreePanel.add(choosePlayThreeButton);
        playThreePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add all three panels to plays panel

        playsPanel.add(playOnePanel);
        playsPanel.add(playTwoPanel);
        playsPanel.add(playThreePanel);

        confirmButton = new JButton("Сохранить");

        JPanel confirmButtonPanel = new JPanel();
        confirmButtonPanel.add(confirmButton);
        confirmButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        addListeners();

        JPanel contents = new JPanel();
        contents.add(Box.createHorizontalStrut(50));
        contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));
        contents.add(textFieldPanel);
        contents.add(chooseImagePanel);
        // contents.add(Box.createVerticalStrut(20));
        contents.add(playsPanel);
        contents.add(confirmButtonPanel);
        setContentPane(contents);

        setSize(800, 600);
        setResizable(false);
        setVisible(true);
    }

    private void addListeners() {
        chooseImageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                fileChooser.setDialogTitle("Выбор изображения");
                fileChooser.setFileFilter(fileFilter);
                fileFilter = new FileNameExtensionFilter("Изображения", ImageIO.getReaderFileSuffixes());
                int result = fileChooser.showOpenDialog(Menu.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedImage = fileChooser.getSelectedFile().getAbsolutePath();
                    imgPathTextField.setText(selectedImage);
                }
            }
        });

        choosePlayOneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                fileChooser.setDialogTitle("Выбор анимации 1");
                fileChooser.setFileFilter(gifFilter);
                int result = fileChooser.showOpenDialog(Menu.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedPlayOne = fileChooser.getSelectedFile().getAbsolutePath();
                    playOneTextField.setText(selectedPlayOne);
                }
            }
        });

        choosePlayTwoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                fileChooser.setDialogTitle("Выбор анимации 2");
                fileChooser.setFileFilter(gifFilter);
                int result = fileChooser.showOpenDialog(Menu.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedPlayTwo = fileChooser.getSelectedFile().getAbsolutePath();
                    playTwoTextField.setText(selectedPlayTwo);
                }
            }
        });

        choosePlayThreeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                fileChooser.setDialogTitle("Выбор анимации 3");
                fileChooser.setFileFilter(gifFilter);
                int result = fileChooser.showOpenDialog(Menu.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedPlayThree = fileChooser.getSelectedFile().getAbsolutePath();
                    playThreeTextField.setText(selectedPlayThree);
                }
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                petName = nameTextField.getText();
                if (isAllowedStart()) {
                    loadGameInterface();
                }
            }
        });
    }

    private Boolean isAllowedStart() {
        if (petName == null || selectedImage == null || selectedPlayOne == null || selectedPlayTwo == null
                || selectedPlayThree == null) {
            return false;
        }

        return true;
    }

    private void loadGameInterface() {
        setVisible(false);
        dispose();
        Tamagotchi tamagotchi = new Tamagotchi(petName, selectedImage, selectedPlayOne, selectedPlayTwo,
                selectedPlayThree);
        new GameInterface(tamagotchi);
        GameInterface.setInitialized(true);
        new Timer().schedule(new GameController(tamagotchi), 0, 15000);
    }
}
