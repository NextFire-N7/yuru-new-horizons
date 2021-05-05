package moe.yuru.newhorizons.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ObjectSet;
import com.kotcrab.vis.ui.widget.VisImageButton;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.BuildingInstance;
import moe.yuru.newhorizons.utils.AssetHelper;
import moe.yuru.newhorizons.utils.Event;
import moe.yuru.newhorizons.utils.Listener;

/**
 * Contains all the town buildings buttons. Listens to the game model for new
 * buildings.
 * 
 * @author NextFire
 */
public class MapStage extends Stage implements Listener {

    private YuruNewHorizons game;
    private ObjectSet<Texture> textures;

    /**
     * Creates a new MapStage and initializes already built buildings. It also
     * registers itself to the game model.
     * 
     * @param game the game instance
     */
    public MapStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());
        this.game = game;

        // Yes I'm aware of the libGDX asset manager but I'm too lazy to use it...
        textures = new ObjectSet<>();

        // Initialization
        for (BuildingInstance instance : game.getGameModel().getTown().getBuildings()) {
            addInstanceActor(instance);
        }

        // Registering
        game.getGameModel().addListener(this);
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
        // If a new building has been registered and validated by the model, call
        // addInstanceActor to add the newly missing button on stage.
        if (event.getSource() == game.getGameModel().getTown() && event.getName().equals("validated")) {
            addInstanceActor((BuildingInstance) event.getValue());
        }
    }

    /**
     * Adds the actor for the given {@link BuildingInstance} to the map.
     * 
     * @param instance a town {@link BuildingInstance}
     */
    private void addInstanceActor(BuildingInstance instance) {
        // Get the texture
        Texture iconTexture = AssetHelper.getIconTexture(instance.getModel());
        textures.add(iconTexture);

        // Place a new button where it must be placed
        VisImageButton button = new VisImageButton(new TextureRegionDrawable(new TextureRegion(iconTexture)));
        button.setSize(instance.getModel().getSizeX(), instance.getModel().getSizeY());
        button.setPosition(instance.getPosX(), instance.getPosY());
        addActor(button);

        // Add a controller to switch to the building screen when clicking on the button
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new BuildingScreen(game, instance));
            }
        });
    }

}
