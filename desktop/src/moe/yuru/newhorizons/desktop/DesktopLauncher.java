package moe.yuru.newhorizons.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import moe.yuru.newhorizons.YuruNewHorizons;

public class DesktopLauncher {

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Yuru New Horizons");
        config.setWindowedMode(1280, 720);
        new Lwjgl3Application(new YuruNewHorizons(), config);
    }

}
