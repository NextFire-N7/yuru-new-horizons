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
import moe.yuru.newhorizons.utils.Event;
import moe.yuru.newhorizons.utils.Listener;

/**
 * Town buildings vizualisation {@link Stage}.
 */
public class MapStage extends Stage implements Listener {

    private YuruNewHorizons game;
    private Rectangle mapArea;
    private Array<Texture> textures;
    private Image toPlaceImage;
    private Vector3 mouse_position;

    /**
     * @param game the game instance
     */
    public MapStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());
        this.game = game;
        mapArea = new Rectangle(0, 144, 768, 576);
        toPlaceImage = null;
        textures = new Array<>();
        mouse_position = new Vector3();

        for (BuildingInstance instance : game.getGameModel().getTown().getBuildings()) {
            addInstanceActor(instance);
        }

        game.getGameModel().addListener(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (toPlaceImage != null) {
            mouse_position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getViewport().unproject(mouse_position);
            toPlaceImage.setPosition(mouse_position.x - toPlaceImage.getWidth() / 2,
                    mouse_position.y - toPlaceImage.getHeight() / 2);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        for (Texture texture : textures) {
            texture.dispose();
        }
    }

    @Override
    public void processEvent(Event evt) {
        if (evt.getSource() == game.getGameModel().getTown()) {
            switch (evt.getName()) {
            case "toPlace":
                setToPlace((Building) evt.getObject());
                break;
            case "validated":
                addInstanceActor((BuildingInstance) evt.getObject());
                break;
            default:
                break;
            }
        }
    }

    /**
     * Adds the actor for the given {@link BuildingInstance} to the map.
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
     * Creates the moving on screen building for position confirmation.
     * 
     * @param building the {@link Building} to be placed
     */
    private void setToPlace(Building building) {
        Texture iconTexture = AssetHelper.getIconTexture(building);
        toPlaceImage = new Image(iconTexture);
        toPlaceImage.setSize(building.getSizeX(), building.getSizeY());
        addActor(toPlaceImage);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (mapArea.contains(x, y)) {
                    removeListener(this);
                    game.getGameModel().getTown().validateConstruction(x - toPlaceImage.getWidth() / 2,
                            y - toPlaceImage.getHeight() / 2);
                    toPlaceImage.remove();
                    toPlaceImage = null;
                    iconTexture.dispose();
                }
            }
        });
    }

}
