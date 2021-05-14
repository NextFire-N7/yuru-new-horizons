package moe.yuru.newhorizons.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.BuildingInstance;
import moe.yuru.newhorizons.utils.AssetHelper;

/**
 * Appears in game when clicking on any building instance button on the map.
 * 
 * @author NextFire
 */
public class BuildingScreen implements Screen {
    // TODO: Finish it

    private YuruNewHorizons game;
    private Texture background;
    private CharacterStage characterStage;
    private InputMultiplexer inputMultiplexer;

    /**
     * Creates a new building screen personalized for the given building instance.
     * 
     * @param game     the game instance
     * @param instance the building instance
     */
    public BuildingScreen(YuruNewHorizons game, BuildingInstance instance) {
        this.game = game;

        // Left character stage
        characterStage = new CharacterStage(game, instance.getModel());
        characterStage.addRepeatCharaSoundListener();

        background = AssetHelper.getCoverTexture(instance.getModel());

        // Inputs
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(characterStage);

        // TODO: associated stage w/ exit button
        inputMultiplexer.addProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                super.touchDown(screenX, screenY, pointer, button);
                game.setScreen(game.getGameScreen());
                dispose();
                return true;
            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
        characterStage.playCharaSound();
    }

    @Override
    public void render(float delta) {
        game.getFpslogger().log();
        game.getCamera().update();

        // Draw background
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, 1280, 720);
        game.getBatch().end();

        characterStage.act(delta);
        characterStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        game.getViewport().update(width, height);
    }

    @Override
    public void pause() {
        game.getGameScreen().pause();
    }

    @Override
    public void resume() {
        game.getGameScreen().resume();
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        background.dispose();
        characterStage.dispose();
    }

}
