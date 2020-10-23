package ru.danila.koloboks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class Kolobok implements Disposable {
    final String TEXTURE_NAME = "kolobok.png";
    public static final int IMG_WIDTH = 16;
    public static final int IMG_HEIGHT = 16;

    float x = 0, y = 0;
    private float dx, dy;
    Texture img;

    Kolobok(float x, float y, float dx, float dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;

        img = new Texture(TEXTURE_NAME);
    }

    public void move() {
        this.x += this.dx;
        this.y += this.dy;
        if ((this.x >= Screen.SCR_WIDTH-IMG_WIDTH) || (this.x <= 0))
            this.dx *= -1;
        if ((this.y >= Screen.SCR_HEIGHT-IMG_HEIGHT) || (this.y <= 0))
            this.dy *= -1;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(img, x, y, IMG_WIDTH, IMG_HEIGHT);
    }

    @Override
    public void dispose() {
        img.dispose();
    }
}
