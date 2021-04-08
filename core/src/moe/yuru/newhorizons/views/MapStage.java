package moe.yuru.newhorizons.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.Building;
import moe.yuru.newhorizons.models.BuildingInstance;
import moe.yuru.newhorizons.utils.AssetHelper;

/**
 * Town buildings vizualisation {@link Stage}.
 */
public class MapStage extends Stage {

    private YuruNewHorizons game;
    private Rectangle mapArea;
    private Array<Texture> textures;
    private Image toBePlacedImage;
    private Vector3 mouse_position;

    /**
     * @param game the game instance
     */
    public MapStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());
        this.game = game;
        mapArea = new Rectangle(0, 144, 768, 576);
        toBePlacedImage = null;
        textures = new Array<>();
        mouse_position = new Vector3();

        for (BuildingInstance instance : game.getModel().getTown().getBuildings()) {
            addInstanceActor(instance);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (toBePlacedImage == null && game.getModel().getTown().getToConstruct() != null) {
            newToBePlaced(game.getModel().getTown().getToConstruct());
        }
        if (toBePlacedImage != null) {
            mouse_position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getViewport().unproject(mouse_position);
            toBePlacedImage.setPosition(mouse_position.x - toBePlacedImage.getWidth() / 2,
                    mouse_position.y - toBePlacedImage.getHeight() / 2);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        for (Texture texture : textures) {
            texture.dispose();
        }
    }

    /**
     * Add the actor for the given {@link BuildingInstance} to the map.
     * 
     * @param instance a town {@link BuildingInstance}
     */
    private void addInstanceActor(BuildingInstance instance) {
        Texture iconTexture = AssetHelper.getIconTexture(instance.getModel());
        textures.add(iconTexture);
        Image iconImage = new Image(iconTexture);
        iconImage.setSize(instance.getModel().getSizeX(), instance.getModel().getSizeY());
        iconImage.setPosition(instance.getPosX(), instance.getPosY());
        addActor(iconImage);
    }

    /**
     * Create the moving on screen building for position confirmation.
     * 
     * @param building the {@link Building} to be placed
     */
    private void newToBePlaced(Building building) {
        Texture iconTexture = AssetHelper.getIconTexture(building);
        toBePlacedImage = new Image(iconTexture);
        toBePlacedImage.setSize(building.getSizeX(), building.getSizeY());
        addActor(toBePlacedImage);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (mapArea.contains(x, y)) {
                    removeListener(this);
                    BuildingInstance instance = game.getModel().getTown().validateConstruction(
                            x - toBePlacedImage.getWidth() / 2, y - toBePlacedImage.getHeight() / 2);
                    toBePlacedImage.remove();
                    toBePlacedImage = null;
                    iconTexture.dispose();
                    addInstanceActor(instance);
                }
            }
        });
    }

}
