package ru.danila.koloboks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;

public class KoloboksGame extends ApplicationAdapter {
	public static final int SCR_WIDTH = 1280;
	public static final int SCR_HEIGHT = 720;

	OrthographicCamera camera;
	SpriteBatch batch;
	Texture bg;

	BitmapFont font;

	Kolobok[] koloboks = new Kolobok[25];
	Mutant1[] mutant1s = new Mutant1[15];

	int nClick = 0;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
		batch = new SpriteBatch();
		for (int i = 0; i < koloboks.length; i++) {
			int img_size = MathUtils.random(8,64);
			koloboks[i] = new Kolobok(
					MathUtils.random(SCR_WIDTH - img_size),
					MathUtils.random(SCR_HEIGHT - img_size),
					MathUtils.random(-2f, 2f),
					MathUtils.random(-2f, 2f),
					img_size, img_size
			);
		}
		for (int i = 0; i < mutant1s.length; i++) {
			int img_size = MathUtils.random(6,48);
			mutant1s[i] = new Mutant1(
					MathUtils.random(SCR_WIDTH - img_size),
					MathUtils.random(SCR_HEIGHT - img_size),
					MathUtils.random(-2f, 2f),
					MathUtils.random(-2f, 2f),
					img_size, img_size
			);
		}

		bg = new Texture("stars2.png");

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
			nClick++;
		}

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bg, 0, 0, SCR_WIDTH, SCR_HEIGHT);
		for (int i = 0; i < koloboks.length; i++)
			koloboks[i].draw(batch);
		for (int i = 0; i < mutant1s.length; i++)
			mutant1s[i].draw(batch);

		font.setColor(Color.WHITE);
		if (nClick > 0) {
			font.draw(batch, String.valueOf(nClick), 100, 100);
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
	}
}
