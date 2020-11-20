package ru.danila.koloboks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Mutant1 extends Kolobok {
    final String TEXTURE_NAME = "mutant.png";
    Texture img;

    Mutant1(float x, float y, float dx, float dy, int w, int h) {
        super(x, y, dx, dy, w, h);
        img = new Texture(TEXTURE_NAME);
    }

    public void move() {
        this.x += this.dx;
        this.y += this.dy;
        if ((this.x >= KoloboksGame.SCR_WIDTH-img_width) || (this.x <= 0))
            this.dx *= -1;
        if ((this.y >= KoloboksGame.SCR_HEIGHT-img_height) || (this.y <= 0))
            this.dy *= -1;
        this.angle -= this.da*2;
    }

    public void draw(SpriteBatch batch) {
        if (isAlive)
        batch.draw(img, this.x, this.y, this.img_width/2f, this.img_height/2f,
                this.img_width, this.img_height, 1, 1, this.angle, 0, 0, this.img.getWidth(), this.img.getHeight(),
                false, false);
    }
}
