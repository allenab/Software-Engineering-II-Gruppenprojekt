package at.uni.handlers;

import java.util.HashMap;
import java.util.Map;

import at.uni.Application;
import at.uni.screens.AbstractScreen;
import at.uni.screens.MainGameScreen;
import at.uni.screens.MainMenuScreen;


public class GameScreenManager {

    private final Application application;
    private Map<STATE, AbstractScreen> screens;


    public GameScreenManager(Application application){
        this.application = application;

        initGameManager();
    }

    public enum STATE{
        MAIN_MENU,
        PLAY
    }

    private void initGameManager(){
        screens = new HashMap<STATE, AbstractScreen>();
        screens.put(STATE.MAIN_MENU, new MainMenuScreen(application));
        screens.put(STATE.PLAY, new MainGameScreen(application));
    }

    public Application getApplication(){
        return application;
    }

    public void setScreen(STATE screen){
        application.setScreen(screens.get(screen));
    }

    public void dispose(){
        for(AbstractScreen screen : screens.values()){
            if(screen != null){
                screen.dispose();
            }
        }
    }

}
