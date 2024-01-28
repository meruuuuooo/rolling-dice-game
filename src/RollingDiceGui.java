import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class RollingDiceGui extends JFrame {
    static boolean isRolling = false;
    public RollingDiceGui() {
        super("Random Dice Game!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        addGuiComponents();
    }

    private void addGuiComponents() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(Color.CYAN);

        JLabel bannerImg = ImgService.loadImage("resources/banner.png");
        bannerImg.setBounds(35, 25, 416, 121);
        jPanel.add(bannerImg);

        JLabel diceOneImg = ImgService.loadImage("resources/redDice1.png");
        diceOneImg.setBounds(55, 200, 128, 128);
        jPanel.add(diceOneImg);

        JLabel diceTwoImg = ImgService.loadImage("resources/redDice1.png");
        diceTwoImg.setBounds(300, 200, 128, 128);
        jPanel.add(diceTwoImg);

        Random rand = new Random();
        JButton rollBtn = new JButton();
        rollBtn.setBounds(35, 390, 416, 50);
        rollBtn.setBorder(BorderFactory.createEmptyBorder());
        rollBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rollBtn.setBackground(Color.BLUE);
        rollBtn.setFocusable(false);
        rollBtn.setIcon(new ImageIcon("src/resources/dice-cube-outline.png"));
        rollBtn.setFont(new Font("Dialog", Font.BOLD, 25));
        rollBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollBtn.setEnabled(false);
                Sound.playRolledDiceClip();
                long startTime = System.currentTimeMillis();
                Thread rollThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long endTime = System.currentTimeMillis();
                        try {
                            while ((endTime - startTime)/1000F < 3) {
                                int diceOne = rand.nextInt(1, 7);
                                int diceTwo = rand.nextInt(1, 7);

                                ImgService.updateImage(diceOneImg, "resources/redDice" + diceOne + ".png");
                                ImgService.updateImage(diceTwoImg, "resources/redDice" + diceTwo + ".png");

                                repaint();
                                revalidate();

                                Thread.sleep(60);
                                endTime = System.currentTimeMillis();
                            }
                            rollBtn.setEnabled(true);
                        }catch (InterruptedException e) {
                            System.out.println("Threading Error: " + e);
                        }
                    }
                });
                rollThread.start();
            }
        });
        jPanel.add(rollBtn);

        this.getContentPane().add(jPanel);
    }
}
