import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Play extends JDialog {
    private Image gifImage;

    public Play(String gifPath, Tamagotchi tamagotchi, boolean isDeath) {
        super();
        setModal(true);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        gifImage = toolkit.createImage(gifPath);

        JPanel canvasPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (gifImage != null) {
                    g.drawImage(gifImage, 0, 0, 600, 400, this);
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(600, 400);
            }
        };

        getContentPane().add(canvasPanel);
        pack();
        setResizable(false);

        if (!isDeath) {
            tamagotchi.setHappiness(tamagotchi.getHappiness() + 35);
        } else {
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        }

        setVisible(true);
    }
}
