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

public class MapStage extends Stage {

    private YuruNewHorizons game;
    private Rectangle mapArea;
    private Image toAdd;
    private Array<Texture> textures;
    private Vector3 mouse_position;

    public MapStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());
        this.game = game;
        mapArea = new Rectangle(0, 144, 768, 576);
        toAdd = null;
        textures = new Array<>();
        mouse_position = new Vector3();
        for (BuildingInstance instance : game.getModel().getTown().getBuildings()) {
            Texture iconTexture = AssetHelper.getIconTexture(instance.getModel());
            textures.add(iconTexture);
            toAdd = new Image(iconTexture);
            toAdd.setSize(instance.getModel().getSizeX(), instance.getModel().getSizeY());
            toAdd.setPosition(instance.getPosX(), instance.getPosY());
            addActor(toAdd);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (toAdd != null) {
            mouse_position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getViewport().unproject(mouse_position);
            toAdd.setPosition(mouse_position.x - toAdd.getWidth() / 2, mouse_position.y - toAdd.getHeight() / 2);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        for (Texture texture : textures) {
            texture.dispose();
        }
    }

    public void addBuildingTask(Building building) {
        Texture iconTexture = AssetHelper.getIconTexture(building);
        textures.add(iconTexture);
        toAdd = new Image(iconTexture);
        toAdd.setSize(building.getSizeX(), building.getSizeY());
        addActor(toAdd);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (mapArea.contains(x, y)) {
                    game.getModel().getTown().getBuildings().add(new BuildingInstance(building, x, y));
                    game.getModel().getTown().addCoins(building.getStats(1).getCoinCost());
                    game.getModel().getTown().addResources(building.getFaction(),
                            building.getStats(1).getResourcesCost());
                    toAdd = null;
                }
            }
        });
    }

}
