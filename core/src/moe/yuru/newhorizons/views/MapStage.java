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
 * Town buildings vizualisation {@link Stage}.
 */
public class MapStage extends Stage implements Listener {

    private YuruNewHorizons game;
    private ObjectSet<Texture> textures;

    /**
     * @param game the game instance
     */
    public MapStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());
        this.game = game;
        textures = new ObjectSet<>();

        for (BuildingInstance instance : game.getGameModel().getTown().getBuildings()) {
            addInstanceActor(instance);
        }

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

}
