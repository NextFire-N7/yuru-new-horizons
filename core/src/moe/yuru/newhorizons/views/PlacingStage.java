package moe.yuru.newhorizons.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.Building;
import moe.yuru.newhorizons.utils.Event;
import moe.yuru.newhorizons.utils.Listener;

/**
 * Controller for building position choice. It essentially consists of a moving
 * building button which follows player mouse when a building is waiting to be
 * placed. For that it listens to the game model.
 * 
 * @author NextFire
 */
public class PlacingStage extends Stage implements Listener {

    private YuruNewHorizons game;
    private Rectangle mapArea;
    private BuildingButton toPlaceButton;
    private Vector3 mouse_position;

    /**
     * @param game the game instance
     */
    public PlacingStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());
        this.game = game;

        // Area corresponding to the map, you should not be able to place a building
        // elsewhere :oof:
        mapArea = new Rectangle(0, 144, 768, 576);

        toPlaceButton = null;
        mouse_position = new Vector3();

        // Registering
        game.getGameModel().addListener(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // If a moving button is defined, follow player's cursor
        if (toPlaceButton != null) {
            mouse_position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getViewport().unproject(mouse_position);
            toPlaceButton.setPosition(mouse_position.x - toPlaceButton.getWidth() / 2,
                    mouse_position.y - toPlaceButton.getHeight() / 2);
        }
    }

    @Override
    public void processEvent(Event event) {
        // If a building is to place, trigger setToPlace
        if (event.getSource() == game.getGameModel() && event.getName().equals("toPlace") && event.getValue() != null) {
            setToPlace((Building) event.getValue());
        }
    }

    /**
     * Defines the moving button for position confirmation.
     * 
     * @param building the {@link Building} to be placed
     */
    private void setToPlace(Building building) {
        // Create a new button
        toPlaceButton = new BuildingButton(building);
        addActor(toPlaceButton);

        // If the player clicks on screen...
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // ...at a valid position...
                if (mapArea.contains(x + toPlaceButton.getWidth() / 2, y - toPlaceButton.getHeight() / 2)) {
                    removeListener(this);
                    // ...ask the model to validate the construction at the mouse position...
                    game.getGameModel().validateConstruction(x - toPlaceButton.getWidth() / 2,
                            y - toPlaceButton.getHeight() / 2);
                    // ...then destruct this button...
                    toPlaceButton.remove();
                    toPlaceButton = null;
                }
            }
        });
        // ...don't worry, it will reappear soon on the MapStage!
    }

}
