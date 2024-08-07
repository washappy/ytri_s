package com.github.washappy.screen;

import com.github.washappy.Music;
import com.github.washappy.enums.Screens;
import com.github.washappy.listener.KeyListener;
import com.github.washappy.screen.recources.IntroPanelResources;
import com.github.washappy.tetris.Game;
import com.github.washappy.tetris.Player;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static com.github.washappy.Ingredient.SCREEN_HEIGHT;
import static com.github.washappy.Ingredient.SCREEN_WIDTH;

public class Screen2 extends JFrame {


    //기본 배경 자료 가져오기
    private Image screenImage;
    private Graphics screenGraphic;
    private final IntroPanelResources recources = new IntroPanelResources();

    public static final Music introMusic = new Music("introMusic.mp3",true);

    private Image logoImage = recources.logo;
    private final JLabel menuBar = new JLabel(recources.menuBarIcon);

    private Image boardImage = null;
    private Image holdImage = null;
    private Image holdMinoImage = null;
    private Image nextImage = null;
    private Image[] nextMinoImages = new Image[5];

    //버튼에 객체 할당
    private JButton exitButton = new JButton(recources.exitButtonBasic);
    private Image[][] fieldImages = new Image[10][40];

    private int mouseX;
    private int mouseY;


    private Graphics2D graphics2D;
    public static Game game = new Game();
    public static Player NOWPLAYER;


    public Screen2() {

        // 메인 프레임 생성
        setUndecorated(true);
        setTitle("Ytri_S");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);

        addKeyListener(new KeyListener());



        //exit 버튼 생성
        exitButton.setBounds(1245, 0, 30, 30);
        setPaintSetting(exitButton);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(recources.exitButtonEntered);
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(recources.exitButtonBasic);
                exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                playButtonClick();
                try {
                    Thread.sleep(235);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
        add(exitButton);

        //메뉴바 생성
        menuBar.setBounds(0, 0, 1280, 30);
        menuBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        menuBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - mouseX, y - mouseY);
            }
        });
        add(menuBar);

        revalidate();
        repaint();
    }


    private void setPaintSetting(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
    }

    private void playButtonOn() {
        Music buttonEnteredMusic = new Music("buttonOn.mp3", false);
        buttonEnteredMusic.start();
    }

    private void playButtonClick() {
        Music buttonPressedMusic = new Music("buttonClick.mp3", false);
        buttonPressedMusic.start();
    }

    @Override
    public void paint(Graphics g) {
        screenImage = createImage(SCREEN_WIDTH, SCREEN_HEIGHT);
        screenGraphic = screenImage.getGraphics();
        this.graphics2D = (Graphics2D) screenGraphic;
        screenDraw(graphics2D);
        g.drawImage(screenImage, 0, 0, null);
    }

    public void screenDraw(Graphics2D g) {
        g.drawImage(recources.introBackground, 0, 0, null);
        if(Navigator.INSTANCE.getCurrentScreen()==Screens.INTRO) {
            g.drawImage(logoImage, 20, 10, null);
        }
        g = Navigator.INSTANCE.getCurrentPanel().screenDraw(g);

        paintComponents(g);
        this.repaint();
    }
}