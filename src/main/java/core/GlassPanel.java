package core;

import style.Style;

import javax.swing.*;
import java.awt.*;

public class GlassPanel extends JComponent {
    private Style style;

    public GlassPanel(Style s) {
        this.style = s;
        setOpaque(false);
    }

    public void setStyle(Style s) {
        this.style = s;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        float alpha = Math.max(0f, Math.min(style.alpha, 1f)); // Validación básica.

        // fondo translucido.
        if (style.backgroundColor != null) {
            Color bg = Color.decode(style.backgroundColor);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.setColor(bg);
            g2.fillRoundRect(0, 0, w, h, style.blurRadius, style.borderRadius);
        }
        g2.dispose();
    }
}
