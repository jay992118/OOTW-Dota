package List;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Backpaint.BackgroundPanel;
import Enemy.Enemy;
import Tower.*;

public class TowerSelector implements Runnable {
    TowerFactory archerTowerFactory = new ArcherTowerFactory();
    Tower archerTower = archerTowerFactory.createTower();
    TowerFactory lightningTowerFactory = new LightningTowerFactory();
    Tower lightningTower = lightningTowerFactory.createTower();
    TowerFactory flameTowerFactory = new FlameTowerFactory();
    Tower flameTower = flameTowerFactory.createTower();
  
    final int AddCancelButW = 50;
    final int AddCancelButH = 50;
    final int[] Originalx = {300, 600, 900, 1200, 150, 450, 750, 1050};
    final int[] Originaly = {280, 280, 280, 280, 520, 520, 520, 520};
    private boolean shouldDrawCircle = false;
    private int circleX, circleY;
    private int circleRadius;
    private BackgroundPanel backgroundPanel;

    private ArrayList<Enemy> enemy;
    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;
    private Thread gameThread;

    public void Background() {

        JFrame frame = new JFrame("關卡一");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 900);
        backgroundPanel = new BackgroundPanel("./res/TowerDefenceGame_Map.jpg");
        backgroundPanel.setLayout(null);
        frame.setContentPane(backgroundPanel);
        frame.setLocationRelativeTo(null);

        //+號按鈕
        JButton[] imageAddButton = getAddCanceljButton("./res/button/Add.png");
        //-號按鈕
        JButton[] imageCancelButton = getAddCanceljButton("./res/button/Cancel.png");
        //箭塔選項
        JButton[] imageArcherChooseButton = getTowerChoosejButton("./res/ArcherTower.png", 0);
        //雷電塔選項
        JButton[] imageLightningChooseButton = getTowerChoosejButton("./res/LightningTower.png", 1);
        //可樂塔選項
        JButton[] imageColaChooseButton = getTowerChoosejButton("./res/ColaTower.png", 2);
        //箭塔生成
        JButton[] imageArcherButton = getTowerjButton("./res/ArcherTower.png");
        //雷電塔生成
        JButton[] imageLightningButton = getTowerjButton("./res/LightningTower.png");
        //可樂塔生成
        JButton[] imageColaButton = getTowerjButton("./res/ColaTower.png");
        //賣出按鈕
        JButton[] imageSellButton = getSellEscapejButton("./res/button/Gold.png");
        //取消按鈕
        JButton[] imageEscapeButton = getSellEscapejButton("./res/button/Escape.png");
        //升級按鈕
        JButton[] imageUpgradeButton = getSellEscapejButton("./res/button/Upgrade.png");

        for (int i = 0; i < Originalx.length; i++) {
            backgroundPanel.add(imageAddButton[i]);
        }

        for (int i = 0; i < imageAddButton.length; i++) {
            final int index = i;
            imageAddButton[i].setActionCommand("change" + i);
            imageAddButton[i].addActionListener(e -> {
                String actionCommand = e.getActionCommand();
                if (actionCommand.equals("change" + index)) {
                    backgroundPanel.add(imageArcherChooseButton[index]);
                    backgroundPanel.add(imageLightningChooseButton[index]);
                    backgroundPanel.add(imageColaChooseButton[index]);
                    backgroundPanel.add(imageCancelButton[index]);
                    backgroundPanel.remove(imageAddButton[index]);
                    backgroundPanel.revalidate();
                    backgroundPanel.repaint();
                }
            });
        }

        for (int i = 0; i < imageCancelButton.length; i++) {
            final int index = i;
            imageCancelButton[i].setActionCommand("change" + i);
            imageCancelButton[i].addActionListener(e -> {
                String actionCommand = e.getActionCommand();
                if (actionCommand.equals("change" + index)) {
                    backgroundPanel.add(imageAddButton[index]);
                    backgroundPanel.remove(imageArcherChooseButton[index]);
                    backgroundPanel.remove(imageLightningChooseButton[index]);
                    backgroundPanel.remove(imageColaChooseButton[index]);
                    backgroundPanel.remove(imageCancelButton[index]);
                    backgroundPanel.revalidate();
                    backgroundPanel.repaint();
                }
            });
        }

        for (int i = 0; i < imageArcherChooseButton.length; i++) {
            final int index = i;
            imageArcherChooseButton[i].setActionCommand("change" + i);
            imageArcherChooseButton[i].addActionListener(e -> {
                String actionCommand = e.getActionCommand();
                if (actionCommand.equals("change" + index)) {
                    backgroundPanel.add(imageArcherButton[index]);
                    backgroundPanel.remove(imageArcherChooseButton[index]);
                    backgroundPanel.remove(imageLightningChooseButton[index]);
                    backgroundPanel.remove(imageColaChooseButton[index]);
                    backgroundPanel.remove(imageCancelButton[index]);
                    backgroundPanel.revalidate();
                    backgroundPanel.repaint();
                }
            });
        }

        for (int i = 0; i < imageLightningChooseButton.length; i++) {
            final int index = i;
            imageLightningChooseButton[i].setActionCommand("change" + i);
            imageLightningChooseButton[i].addActionListener(e -> {
                String actionCommand = e.getActionCommand();
                if (actionCommand.equals("change" + index)) {
                    backgroundPanel.add(imageLightningButton[index]);
                    backgroundPanel.remove(imageArcherChooseButton[index]);
                    backgroundPanel.remove(imageLightningChooseButton[index]);
                    backgroundPanel.remove(imageColaChooseButton[index]);
                    backgroundPanel.remove(imageCancelButton[index]);
                    backgroundPanel.revalidate();
                    backgroundPanel.repaint();
                }
            });
        }

        for (int i = 0; i < imageColaChooseButton.length; i++) {
            final int index = i;
            imageColaChooseButton[i].setActionCommand("change" + i);
            imageColaChooseButton[i].addActionListener(e -> {
                String actionCommand = e.getActionCommand();
                if (actionCommand.equals("change" + index)) {
                    backgroundPanel.add(imageColaButton[index]);
                    backgroundPanel.remove(imageArcherChooseButton[index]);
                    backgroundPanel.remove(imageLightningChooseButton[index]);
                    backgroundPanel.remove(imageColaChooseButton[index]);
                    backgroundPanel.remove(imageCancelButton[index]);
                    backgroundPanel.revalidate();
                    backgroundPanel.repaint();
                }
            });
        }

        for (int i = 0; i < imageArcherButton.length; i++) {
            final int index = i;
            imageArcherButton[i].setActionCommand("change" + i);
            imageArcherButton[i].addActionListener(e -> {
                String actionCommand = e.getActionCommand();
                if (actionCommand.equals("change" + index)) {
                    shouldDrawCircle = true;
                    circleX = Originalx[index];
                    circleY = Originaly[index];
                    circleRadius = 300;
                    backgroundPanel.setCircle(circleX, circleY, circleRadius, shouldDrawCircle);
                    backgroundPanel.add(imageEscapeButton[index]);
                    backgroundPanel.add(imageSellButton[index]);
                    backgroundPanel.add(imageUpgradeButton[index]);
                    backgroundPanel.revalidate();
                    backgroundPanel.repaint();
                }
            });
        }

        for (int i = 0; i < imageLightningButton.length; i++) {
            final int index = i;
            imageLightningButton[i].setActionCommand("change" + i);
            imageLightningButton[i].addActionListener(e -> {
                String actionCommand = e.getActionCommand();
                if (actionCommand.equals("change" + index)) {
                    shouldDrawCircle = true;
                    circleX = Originalx[index];
                    circleY = Originaly[index];
                    circleRadius = 250;
                    backgroundPanel.setCircle(circleX, circleY, circleRadius, shouldDrawCircle);
                    backgroundPanel.add(imageEscapeButton[index]);
                    backgroundPanel.add(imageSellButton[index]);
                    backgroundPanel.add(imageUpgradeButton[index]);
                    backgroundPanel.revalidate();
                    backgroundPanel.repaint();
                }
            });
        }

        for (int i = 0; i < imageColaButton.length; i++) {
            final int index = i;
            imageColaButton[i].setActionCommand("change" + i);
            imageColaButton[i].addActionListener(e -> {
                String actionCommand = e.getActionCommand();
                if (actionCommand.equals("change" + index)) {
                    shouldDrawCircle = true;
                    circleX = Originalx[index];
                    circleY = Originaly[index];
                    circleRadius = 200;
                    backgroundPanel.setCircle(circleX, circleY, circleRadius, shouldDrawCircle);
                    backgroundPanel.add(imageEscapeButton[index]);
                    backgroundPanel.add(imageSellButton[index]);
                    backgroundPanel.add(imageUpgradeButton[index]);
                    backgroundPanel.revalidate();
                    backgroundPanel.repaint();
                }
            });
        }

        for (int i = 0; i < imageEscapeButton.length; i++) {
            final int index = i;
            imageEscapeButton[i].setActionCommand("change" + i);
            imageEscapeButton[i].addActionListener(e -> {
                String actionCommand = e.getActionCommand();
                if (actionCommand.equals("change" + index)) {
                    shouldDrawCircle = false;
                    backgroundPanel.setCircle(circleX, circleY, circleRadius, shouldDrawCircle);
                    backgroundPanel.remove(imageEscapeButton[index]);
                    backgroundPanel.remove(imageSellButton[index]);
                    backgroundPanel.remove(imageUpgradeButton[index]);
                    backgroundPanel.revalidate();
                    backgroundPanel.repaint();
                }
            });
        }

        for (int i = 0; i < imageSellButton.length; i++) {
            final int index = i;
            imageSellButton[i].setActionCommand("change" + i);
            imageSellButton[i].addActionListener(e -> {
                String actionCommand = e.getActionCommand();
                if (actionCommand.equals("change" + index)) {
                    shouldDrawCircle = false;
                    backgroundPanel.setCircle(circleX, circleY, circleRadius, shouldDrawCircle);
                    backgroundPanel.remove(imageEscapeButton[index]);
                    backgroundPanel.remove(imageSellButton[index]);
                    backgroundPanel.remove(imageUpgradeButton[index]);
                    backgroundPanel.remove(imageColaButton[index]);
                    backgroundPanel.remove(imageArcherButton[index]);
                    backgroundPanel.remove(imageLightningButton[index]);
                    backgroundPanel.add(imageAddButton[index]);
                    backgroundPanel.revalidate();
                    backgroundPanel.repaint();
                }
            });
        }

        frame.setVisible(true);
        start();
        enemy = backgroundPanel.enemyTileManager.getEnemies();
    }

    //創建＋-符號
    private JButton[] getAddCanceljButton(String image) {
        JButton[] buttons = new JButton[Originalx.length];
        for (int i = 0; i < Originalx.length; i++) {
            ImageIcon imageOptionPath = new ImageIcon(image);
            Image originaloptionbut = imageOptionPath.getImage();
            Image scaledchosenImage = originaloptionbut.getScaledInstance(AddCancelButW, AddCancelButH, Image.SCALE_SMOOTH);
            ImageIcon scaledoptionIcon = new ImageIcon(scaledchosenImage);
            JButton imageoptionButton = new JButton(scaledoptionIcon);
            imageoptionButton.setFocusPainted(false);
            imageoptionButton.setOpaque(false);
            imageoptionButton.setContentAreaFilled(false);
            imageoptionButton.setBorderPainted(false);
            imageoptionButton.setBounds(Originalx[i], Originaly[i], AddCancelButW, AddCancelButH);
            buttons[i] = imageoptionButton;
        }
        return buttons;
    }

    //創建防禦塔選項
    private JButton[] getTowerChoosejButton(String image, int d) {
        JButton[] buttons = new JButton[Originalx.length];
        for (int i = 0; i < Originalx.length; i++) {
            ImageIcon chooseTower = new ImageIcon(image);
            Image originalchooseImage = chooseTower.getImage();
            Image scaledcloseImage = originalchooseImage.getScaledInstance(45, 80, Image.SCALE_AREA_AVERAGING);
            ImageIcon scaledcloseIcon = new ImageIcon(scaledcloseImage);
            JButton imageoptionButton = new JButton(scaledcloseIcon);
            imageoptionButton.setBounds((Originalx[i] - 65 + d * 67), (Originaly[i] - 80), 45, 80);
            imageoptionButton.setFocusPainted(false);
            imageoptionButton.setOpaque(false);
            imageoptionButton.setContentAreaFilled(false);
            imageoptionButton.setBorderPainted(false);
            buttons[i] = imageoptionButton;
        }
        return buttons;
    }

    //創建已生成的防禦塔按鈕
    private JButton[] getTowerjButton(String image) {
        JButton[] buttons = new JButton[Originalx.length];
        for (int i = 0; i < Originalx.length; i++) {
            ImageIcon chosenTower = new ImageIcon(image);
            Image originalcloseImage = chosenTower.getImage();
            Image scaledcloseImage = originalcloseImage.getScaledInstance(135, 240, Image.SCALE_AREA_AVERAGING);
            ImageIcon scaledcloseIcon = new ImageIcon(scaledcloseImage);
            JButton imageoptionButton = new JButton(scaledcloseIcon);
            imageoptionButton.setBounds(Originalx[i] - 42, Originaly[i] - 170, 135, 240);
            imageoptionButton.setFocusPainted(false);
            imageoptionButton.setOpaque(false);
            imageoptionButton.setContentAreaFilled(false);
            imageoptionButton.setBorderPainted(false);
            buttons[i] = imageoptionButton;
        }
        return buttons;
    }

    //創建X和賣出按鈕
    private JButton[] getSellEscapejButton(String image) {
        JButton[] buttons = new JButton[Originalx.length];
        for (int i = 0; i < Originalx.length; i++) {
            ImageIcon imageoptionbut = new ImageIcon(image);
            Image originaloptionbut = imageoptionbut.getImage();
            Image scaledchosenImage = originaloptionbut.getScaledInstance(AddCancelButW, AddCancelButH, Image.SCALE_AREA_AVERAGING);
            ImageIcon scaledoptionIcon = new ImageIcon(scaledchosenImage);
            JButton imageoptionButton = new JButton(scaledoptionIcon);
            switch (image) {
                case "./res/button/Gold.png":
                    imageoptionButton.setBounds(Originalx[i] - 60, Originaly[i] + 80, 50, 50);
                    break;
                case "./res/button/Upgrade.png":
                    imageoptionButton.setBounds(Originalx[i], Originaly[i] + 80, 50, 50);
                    break;
                case "./res/button/Escape.png":
                    imageoptionButton.setBounds(Originalx[i] + 60, Originaly[i] + 80, 50, 50);
                    break;
            }
                imageoptionButton.setFocusPainted(false);
                imageoptionButton.setOpaque(false);
                imageoptionButton.setContentAreaFilled(false);
                imageoptionButton.setBorderPainted(false);
                buttons[i] = imageoptionButton;
            }
        return buttons;
    }


    private void updatesEnemy() {
        backgroundPanel.enemyTileManager.update();
    }

    private void start(){
        gameThread = new Thread(this) {
        };
        gameThread.start();
    }

    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;

        long now;

        while (true) {
            now = System.nanoTime();

            // Render
            if (now - lastFrame >= timePerFrame) {
                backgroundPanel.repaint();
                lastFrame = now;
                frames++;
            }
            // Update
            if (now - lastUpdate >= timePerUpdate) {
                updatesEnemy();
                lastUpdate = now;
                updates++;
            }
            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();

                for(Enemy e:enemy){
                    System.out.println(e.getX());
                    System.out.println(e.getHealth());
                    if(e.getX() > 1500){
                        System.out.println("HP-1");
                    }
                }
            }
        }
    }
}