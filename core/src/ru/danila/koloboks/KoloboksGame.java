package ru.danila.koloboks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class KoloboksGame extends ApplicationAdapter {
	public static final int SCR_WIDTH = 1280;
	public static final int SCR_HEIGHT = 720;

	OrthographicCamera camera;
	SpriteBatch batch;
	Texture bg;
	Texture imgExplosion;

	Sound sndExplosion, sndShot;

	BitmapFont font;

	Kolobok[] koloboks = new Kolobok[25];
	Mutant1[] mutant1s = new Mutant1[15];

	int score = 0;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
		batch = new SpriteBatch();
		for (int i = 0; i < koloboks.length; i++) {
			int img_size = MathUtils.random(32,64);
			koloboks[i] = new Kolobok(
					MathUtils.random(SCR_WIDTH - img_size),
					MathUtils.random(SCR_HEIGHT - img_size),
					MathUtils.random(-2f, 2f),
					MathUtils.random(-2f, 2f),
					img_size, img_size
			);
		}
		for (int i = 0; i < mutant1s.length; i++) {
			int img_size = MathUtils.random(24,48);
			mutant1s[i] = new Mutant1(
					MathUtils.random(SCR_WIDTH - img_size),
					MathUtils.random(SCR_HEIGHT - img_size),
					MathUtils.random(-2f, 2f),
					MathUtils.random(-2f, 2f),
					img_size, img_size
			);
		}

		sndExplosion = Gdx.audio.newSound(Gdx.files.internal("explode.wav"));
		sndShot = Gdx.audio.newSound(Gdx.files.internal("shot.wav"));
		bg = new Texture("stars2.png");
		imgExplosion = new Texture("explosion.png");

		FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(
				Gdx.files.internal("dejavu.ttf")
		);
		FreeTypeFontGenerator.FreeTypeFontParameter parameter =
				new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.characters =
				" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~"
				+ "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
		parameter.size = 48;
		parameter.color = Color.WHITE;
		font = fontGenerator.generateFont(parameter);
		fontGenerator.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.justTouched()) {
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(pos);
			for (int i = 0; i < koloboks.length; i++) {
				if (koloboks[i].isHit(pos.x, pos.y)) {
					koloboks[i].hitCount++;
					if (koloboks[i].hitCount >= 1) {
						koloboks[i].isAlive = false;
						sndExplosion.play();
						score++;
					}
				}
			}

			for (int i = 0; i < mutant1s.length; i++) {
				if (mutant1s[i].isHit(pos.x, pos.y)) {
					mutant1s[i].hitCount++;
					if (mutant1s[i].hitCount >= 2) {
						mutant1s[i].isAlive = false;
						sndExplosion.play();
						score += 2;
					}
				}
			}
		}

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bg, 0, 0, SCR_WIDTH, SCR_HEIGHT);
		for (int i = 0; i < koloboks.length; i++)
			koloboks[i].draw(batch);
		for (int i = 0; i < mutant1s.length; i++)
			mutant1s[i].draw(batch);

		if (Gdx.input.isTouched()) {
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(pos);
			batch.draw(imgExplosion, pos.x-50, pos.y-50, 100, 100);
		}

		if (Gdx.input.justTouched())
			sndShot.play();

		font.setColor(Color.WHITE);
		if (score > 0) {
			font.draw(batch, String.valueOf(score), 100, 100);
		}

		batch.end();
		for (int i = 0; i < koloboks.length; i++)
			koloboks[i].move();
		for (int i = 0; i < mutant1s.length; i++)
			mutant1s[i].move();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for (int i = 0; i < koloboks.length; i++)
			koloboks[i].dispose();
		for (int i = 0; i < mutant1s.length; i++)
			mutant1s[i].dispose();
		bg.dispose();
		imgExplosion.dispose();
		sndExplosion.dispose();
		sndShot.dispose();
	}
}
