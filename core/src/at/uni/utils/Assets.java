package at.uni.utils;

import com.badlogic.gdx.assets.AssetManager;

public final class Assets {

    private Assets(){}

    public static final String WALL_TEXTURE = "texture/wall.png";


    public static void loadAssets(AssetManager assetManager){


        //assetManager.load(WALL_TEXTURE);



        assetManager.finishLoading();
    }

}
