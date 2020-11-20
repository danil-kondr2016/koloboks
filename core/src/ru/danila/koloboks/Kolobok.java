package ru.danila.koloboks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Disposable;

public class Kolobok implements Disposable {
    final String TEXTURE_NAME = "kolobok.png";

    float x = 0, y = 0;
    int img_width, img_height;
    protected float dx, dy;

    float da, angle = 0;

    Texture img;

    int hitCount = 0;
    boolean isAlive = true;

    Kolobok(float x, float y, float dx, float dy, int w, int h) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;

        this.img_width = w;
        this.img_height = h;

        this.da = MathUtils.random(-3f, 3f);

        img = new Texture(TEXTURE_NAME);
    }

    public void move() {
        this.x += this.dx;
        this.y += this.dy;
        if ((this.x >= KoloboksGame.SCR_WIDTH-img_width) || (this.x <= 0))
            this.dx *= -1;
        if ((this.y >= KoloboksGame.SCR_HEIGHT-img_height) || (this.y <= 0))
            this.dy *= -1;
        this.angle += this.da;
    }

    public boolean isHit(float x, float y) {
        return (x >= this.x && x <= this.x+this.img_width && y >= this.y && y <= this.y+this.img_height);
    }

    public void draw(SpriteBatch batch) {
        if (isAlive)
        batch.draw(img, this.x, this.y, this.img_width/2f, this.img_height/2f,
                this.img_width, this.img_height, 1, 1, this.angle, 0, 0, this.img.getWidth(), this.img.getHeight(),
                false, false);
    }

    @Override
    public void dispose() {
        img.dispose();
    }
}
