package ru.danila.koloboks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class Kolobok implements Disposable {
    final String TEXTURE_NAME = "kolobok.png";
    float x = 0, y = 0, dx, dy;
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
    }

    public void draw(SpriteBatch batch) {
        batch.draw(img, x, y);
    }

    @Override
    public void dispose() {
        img.dispose();
    }
}
