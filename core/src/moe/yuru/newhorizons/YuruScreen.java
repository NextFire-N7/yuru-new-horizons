package moe.yuru.newhorizons;

import com.badlogic.gdx.Screen;

/**
 * Abstract implementation of {@link Screen} for this game. So that we don't
 * have to override the {@link Screen#resize()} method each time we write a new
 * screen.
 */
public abstract class YuruScreen implements Screen {

    protected final YuruNewHorizons game;

    public YuruScreen(final YuruNewHorizons game) {
        this.game = game;
    }

    @Override
    public void resize(int width, int height) {
        game.getViewport().update(width, height);
    }

}
