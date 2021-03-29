package moe.yuru.newhorizons.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.badlogic.gdx.backends.gwt.preloader.Preloader;
import com.badlogic.gdx.graphics.g2d.freetype.gwt.FreetypeInjector;
import com.badlogic.gdx.graphics.g2d.freetype.gwt.inject.OnCompletion;
import com.google.gwt.core.client.GWT;

import moe.yuru.newhorizons.YuruNewHorizons;

public class HtmlLauncher extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig() {
        // Resizable application, uses available space in browser
        return new GwtApplicationConfiguration(true);
        // Fixed size application:
        // return new GwtApplicationConfiguration(1280, 720);
    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new YuruNewHorizons();
    }

    @Override
    public void onModuleLoad() {
        FreetypeInjector.inject(new OnCompletion() {
            public void run() {
                HtmlLauncher.super.onModuleLoad();
            }
        });
    }

    @Override
    public Preloader.PreloaderCallback getPreloaderCallback() {
        return createPreloaderPanel(GWT.getHostPageBaseURL() + "logo_preload.png");
    }

}
