package ui;

import javax.swing.*;
import java.awt.*;

// A BackgroundImage class helps create the background
public class BackgroundImage extends JComponent {
    private Image image;

    // EFFECTS: construct the BackgroundImage
    public BackgroundImage(Image image) {
        this.image = image;
    }

    // MODIFIES: this
    // EFFECTS: create the background with properties
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
