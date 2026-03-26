package style;

import javafx.scene.paint.Color;

public class Style {
    public int blurRadius;
    public float alpha;
    public int borderRadius;
    public String backgroundColor;
    public Shadow shadow;
    public FontStyle font;

    public Style() {
    }

    public Style(int blurRadius, float alpha, int borderRadius, String backgroundColor, Shadow shadow) {
        this.blurRadius = blurRadius;
        this.alpha = alpha;
        this.borderRadius = borderRadius;
        this.backgroundColor = backgroundColor;
        this.shadow = shadow;
    }

    // getters and setters.
    // 1. Getters.
    public int getBlurRadius() {
        return blurRadius;
    }

    public float getAlpha() {
        return alpha;
    }

    public int getBorderRadius() {
        return borderRadius;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public Shadow getShadow() {
        return shadow;
    }
    // 2. Setters.

    public void setBlurRadius(int blurRadius) {
        this.blurRadius = blurRadius;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setShadow(Shadow shadow) {
        this.shadow = shadow;
    }
}
