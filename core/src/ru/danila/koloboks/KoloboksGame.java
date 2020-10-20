package ru.danila.koloboks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class KoloboksGame extends ApplicationAdapter {
	SpriteBatch batch;

	Kolobok[] koloboks = new Kolobok[100];

	@Override
	public void create () {
		batch = new SpriteBatch();
		for (int i = 0; i < koloboks.length; i++)
			koloboks[i] = new Kolobok(MathUtils.random(Gdx.graphics.getWidth()),
					                  MathUtils.random(Gdx.graphics.getHeight()),
					                  MathUtils.random(-2f, 2f),
					                  MathUtils.random(-2f, 2f));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (int i = 0; i < koloboks.length; i++)
			koloboks[i].draw(batch);
		batch.end();
		for (int i = 0; i < koloboks.length; i++)
			koloboks[i].move();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for (int i = 0; i < koloboks.length; i++)
			koloboks[i].dispose();
	}
}
