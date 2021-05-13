package moe.yuru.newhorizons.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ObjectSet;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisLabel;

import moe.yuru.newhorizons.models.Building;
import moe.yuru.newhorizons.models.BuildingInstance;
import moe.yuru.newhorizons.utils.AssetHelper;

/**
 * Glorified {@link VisImageButton} with building icon and level.
 * 
 * @author NextFire
 */
public class BuildingButton extends VisImageButton {

    private static ObjectSet<Texture> textures = new ObjectSet<>();
    private BuildingInstance instance;
    private VisLabel levelLabel;

    /**
     * New button with only the icon.
     * 
     * @param model building model
     */
    public BuildingButton(Building model) {
        super(getDrawable(model));
        setSize(model.getSizeX(), model.getSizeY());
        levelLabel = new VisLabel();
        add(levelLabel);
    }

    /**
     * New button with icon and level.
     * 
     * @param instance building instance
     */
    public BuildingButton(BuildingInstance instance) {
        this(instance.getModel());
        setPosition(instance.getPosX(), instance.getPosY());
        this.instance = instance;
        updateLevel();
    }

    /**
     * Disposes all the textures.
     */
    public static void disposeAll() {
        for (Texture texture : textures) {
            texture.dispose();
        }
        textures.clear();
    }

    /**
     * Updates the level on the button.
     */
    public void updateLevel() {
        levelLabel.setText("(" + instance.getLevel() + ")");
    }

    /**
     * @param model building model
     * @return building icon to pass into the super ctor
     */
    private static Drawable getDrawable(Building model) {
        Texture texture = AssetHelper.getIconTexture(model);
        textures.add(texture);
        return new TextureRegionDrawable(new TextureRegion(texture));
    }

}
