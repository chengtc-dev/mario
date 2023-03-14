package ui;

import db.Database;
import main.Game;
import util.ImagesLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Main.WINDOW_HEIGHT;
import static main.Main.WINDOW_WIDTH;

public abstract class UserInterface extends JPanel {
    protected static final int SCALE = 3;

    protected CardLayout cardLayout;
    protected JPanel cards;
    protected JButton playButton;
    protected JButton loginButton;
    protected JButton registerButton;
    protected JButton rankButton;
    protected JLabel nameLabel;
    protected JLabel accountLabel;
    protected JLabel passwordLabel;
    protected JTextField accountField;
    protected JTextField nameField;
    protected JPasswordField passwordField;
    protected JTable rankTable;
    protected BufferedImage titleImage;
    protected BufferedImage mapImage;
    protected Game game;
    protected Database marioDatabase;
    protected Font marioFont;
    protected String account;
    protected String password;
    protected String name;

    private final ImagesLoader imagesLoader;


    public UserInterface(ImagesLoader imagesLoader, JPanel cards, Database marioDatabase, Font marioFont) {
        this.imagesLoader = imagesLoader;
        this.marioDatabase = marioDatabase;
        this.marioFont = marioFont.deriveFont(8f);
        this.cards = cards;
        cardLayout = (CardLayout) cards.getLayout();

        setImage();
        setLayout(null);
    }

    private void setImage() {
        titleImage = imagesLoader.getImage("title");
        mapImage = imagesLoader.getImage("map")
                .getSubimage(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    protected void initialCommonComponent() {
        accountLabel = new JLabel("account");
        accountLabel.setFont(marioFont);
        accountLabel.setBounds(200, 450, 150, 30);

        accountField = new JTextField();
        accountField.setFont(marioFont);
        accountField.setBounds(284, 450, 200, 30);

        passwordLabel = new JLabel("password");
        passwordLabel.setFont(marioFont);
        passwordLabel.setBounds(200, 500, 150, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(284, 500, 200, 30);

        add(accountLabel);
        add(accountField);
        add(passwordLabel);
        add(passwordField);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = titleImage.getWidth() * SCALE;
        int height = titleImage.getHeight() * SCALE;

        g.drawImage(mapImage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);
        g.drawImage(titleImage, (WINDOW_WIDTH - width) / 2, (WINDOW_HEIGHT - height) / 5,
                width, height, null);
    }

    protected void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
