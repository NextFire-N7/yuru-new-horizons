package moe.yuru.newhorizons.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import moe.yuru.newhorizons.AssetHelper;
import moe.yuru.newhorizons.YuruNewHorizons;

/**
 * Town map layout {@link Stage}.
 */
public class LayoutStage extends Stage {

    private Texture layoutTexture;

    /**
     * @param game the game instance
     */
    public LayoutStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());

        layoutTexture = AssetHelper.getLayoutTexture(game.getModel().getTown());
        TextureRegion layoutTextureRegion = new TextureRegion(layoutTexture, 480, 380, 1680, 1370);
        Image layoutImage = new Image(layoutTextureRegion);

        float scaling = this.getHeight() / layoutImage.getHeight();
        layoutImage.setSize(scaling * layoutImage.getWidth(), scaling * layoutImage.getHeight());

        this.addActor(layoutImage);
    }

    @Override
    public void dispose() {
        super.dispose();
        layoutTexture.dispose();
    }

}
