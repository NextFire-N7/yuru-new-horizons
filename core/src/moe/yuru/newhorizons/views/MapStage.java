package moe.yuru.newhorizons.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ObjectSet;
import com.kotcrab.vis.ui.widget.VisImageButton;

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
    private ObjectSet<Texture> textures;
    private VisImageButton toPlaceButton;
    private Vector3 mouse_position;

    /**
     * @param game the game instance
     */
    public MapStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());
        this.game = game;
        mapArea = new Rectangle(0, 144, 768, 576);
        toPlaceButton = null;
        textures = new ObjectSet<>();
        mouse_position = new Vector3();

        for (BuildingInstance instance : game.getGameModel().getTown().getBuildings()) {
            addInstanceActor(instance);
        }

        game.getGameModel().addListener(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (toPlaceButton != null) {
            mouse_position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getViewport().unproject(mouse_position);
            toPlaceButton.setPosition(mouse_position.x - toPlaceButton.getWidth() / 2,
                    mouse_position.y - toPlaceButton.getHeight() / 2);
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
    public void processEvent(Event event) {
        if (event.getSource() == game.getGameModel().getTown()) {
            switch (event.getName()) {
            case "toPlace":
                if (event.getValue() != null) {
                    setToPlace((Building) event.getValue());
                }
                break;
            case "validated":
                addInstanceActor((BuildingInstance) event.getValue());
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
        VisImageButton button = new VisImageButton(new TextureRegionDrawable(new TextureRegion(iconTexture)));
        button.setSize(instance.getModel().getSizeX(), instance.getModel().getSizeY());
        button.setPosition(instance.getPosX(), instance.getPosY());
        addActor(button);

        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new BuildingScreen(game, instance));
            }
        });
    }

    /**
     * Creates the moving on screen building for position confirmation.
     * 
     * @param building the {@link Building} to be placed
     */
    private void setToPlace(Building building) {
        Texture iconTexture = AssetHelper.getIconTexture(building);
        toPlaceButton = new VisImageButton(new TextureRegionDrawable(new TextureRegion(iconTexture)));
        toPlaceButton.setSize(building.getSizeX(), building.getSizeY());
        addActor(toPlaceButton);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (mapArea.contains(x + toPlaceButton.getWidth() / 2, y - toPlaceButton.getHeight() / 2)) {
                    removeListener(this);
                    game.getGameModel().getTown().validateConstruction(x - toPlaceButton.getWidth() / 2,
                            y - toPlaceButton.getHeight() / 2);
                    toPlaceButton.remove();
                    toPlaceButton = null;
                    iconTexture.dispose();
                }
            }
        });
    }

}
