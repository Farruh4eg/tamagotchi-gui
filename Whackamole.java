import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Whackamole extends JFrame {
    public static JButton[][] buttons;
    public static int score = 0;
    private Timer moleTimer;
    public Whackamole() {
        setTitle("Счет: " + score);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1024, 768);
        setResizable(false);
        setLayout(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        initializeButtons();

        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        setVisible(true);

        moleTimer = new Timer();
        moleTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                reCreateMole(false);
            }
        }, 0, 500);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                disposeResources();
            }
        });
    }

    private void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();
        JButton buttonToPress = null;

        switch (keyCode) {
            case KeyEvent.VK_NUMPAD7:
                buttonToPress = buttons[0][0];
                break;
            case KeyEvent.VK_NUMPAD8:
                buttonToPress = buttons[0][1];
                break;
            case KeyEvent.VK_NUMPAD9:
                buttonToPress = buttons[0][2];
                break;
            case KeyEvent.VK_NUMPAD4:
                buttonToPress = buttons[1][0];
                break;
            case KeyEvent.VK_NUMPAD5:
                buttonToPress = buttons[1][1];
                break;
            case KeyEvent.VK_NUMPAD6:
                buttonToPress = buttons[1][2];
                break;
            case KeyEvent.VK_NUMPAD1:
                buttonToPress = buttons[2][0];
                break;
            case KeyEvent.VK_NUMPAD2:
                buttonToPress = buttons[2][1];
                break;
            case KeyEvent.VK_NUMPAD3:
                buttonToPress = buttons[2][2];
                break;
        }

        if (buttonToPress != null) {
            buttonToPress.doClick();
        }
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton();
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                button.addActionListener(new ButtonClickListener(i, j));
                buttons[i][j] = button;
                add(button);
            }
        }
    }

    private void createMole() {
        int randRow = new Random().nextInt(3);
        int randCol = new Random().nextInt(3);
        try {
            File moleFile = new File("static/whackamole/mole.png");
            Image moleImg = ImageIO.read(moleFile);
            buttons[randRow][randCol].setIcon(new ImageIcon(moleImg));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getIcon() != null) {
                buttons[row][col].setIcon(null);
                ++score;
                setTitle("Счет: " + score);
                createMole();
            } else {
                reCreateMole(true);
            }
        }
    }

    private void reCreateMole(boolean decreaseScore) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setIcon(null);
            }
        }

        if (decreaseScore) {
            --score;
            if (score < 0) {
                JOptionPane.showMessageDialog(this, "Игра окончена", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                disposeResources();
            }
            setTitle("Счет: " + score);
        }
        createMole();
    }

    private void disposeResources() {
        if (moleTimer != null) {
            moleTimer.cancel();
            moleTimer = null;
        }
        dispose();
    }
}
