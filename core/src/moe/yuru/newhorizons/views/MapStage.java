package moe.yuru.newhorizons.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.utils.AssetHelper;

/**
 * Town map {@link Stage}.
 */
public class MapStage extends Stage {

    private Texture mapTexture;

    /**
     * @param game the game instance
     */
    public MapStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());

        mapTexture = AssetHelper.getMapTexture(game.getModel().getTown());
        TextureRegion mapTextureRegion = new TextureRegion(mapTexture, 480, 380, 1680, 1370);
        Image mapImage = new Image(mapTextureRegion);

        float scaling = this.getHeight() / mapImage.getHeight();
        mapImage.setSize(scaling * mapImage.getWidth(), scaling * mapImage.getHeight());

        this.addActor(mapImage);
    }

    @Override
    public void dispose() {
        super.dispose();
        mapTexture.dispose();
    }

}
