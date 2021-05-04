package moe.yuru.newhorizons.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.BuildingInstance;
import moe.yuru.newhorizons.utils.AssetHelper;

public class BuildingScreen implements Screen {

    private YuruNewHorizons game;
    private Texture background;
    private BuildingCharacterStage buildingCharacterStage;
    private InputMultiplexer inputMultiplexer;

    public BuildingScreen(YuruNewHorizons game, BuildingInstance instance) {
        this.game = game;

        buildingCharacterStage = new BuildingCharacterStage(game, instance.getModel());
        buildingCharacterStage.addRepeatCharaSoundListener();

        background = AssetHelper.getCoverTexture(instance.getModel());

        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(buildingCharacterStage);

        // TODO: associated stage w/ exit button
        inputMultiplexer.addProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                super.touchDown(screenX, screenY, pointer, button);
                dispose();
                game.setScreen(game.getGameScreen());
                return true;
            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
        buildingCharacterStage.playCharaSound();
    }

    @Override
    public void render(float delta) {
        game.getFpslogger().log();
        game.getCamera().update();

        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, 1280, 720);
        game.getBatch().end();

        buildingCharacterStage.act(delta);
        buildingCharacterStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        game.getViewport().update(width, height);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        background.dispose();
        buildingCharacterStage.dispose();
    }

}
