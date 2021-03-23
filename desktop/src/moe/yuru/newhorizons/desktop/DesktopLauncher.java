package moe.yuru.newhorizons.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import moe.yuru.newhorizons.YuruNewHorizons;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Yuru New Horizons";
        config.width = 1280;
        config.height = 720;
        new LwjglApplication(new YuruNewHorizons(), config);
    }

}
