package at.uni.handlers;

import java.util.HashMap;
import java.util.Map;

import at.uni.Application;
import at.uni.screens.AbstractScreen;
import at.uni.screens.ChatScreen;
import at.uni.screens.ChatServerScreen;
import at.uni.screens.ConnectionScreen;
import at.uni.screens.MainGameScreen;
import at.uni.screens.MainMenuScreen;
import at.uni.screens.SettingsScreen;


public class GameScreenManager {

    private final Application application;
    private Map<STATE, AbstractScreen> screens;


    public GameScreenManager(Application application){
        this.application = application;

        initGameManager();
    }

    public enum STATE{
        MAIN_MENU,
        PLAY,
        CHAT,
        CHATSERVER,
        SETTINGS,
        CONNECTION,
    }

    private void initGameManager(){
        /*
        screens = new HashMap<STATE, AbstractScreen>();
        screens.put(STATE.MAIN_MENU, new MainMenuScreen(application));
        screens.put(STATE.PLAY, new MainGameScreen(application));
        screens.put(STATE.CHAT, new ChatScreen(application));
        screens.put(STATE.CHATSERVER, new ChatServerScreen(application));
        screens.put(STATE.SETTINGS, new SettingsScreen(application));
        screens.put(STATE.CONNECTION, new ConnectionScreen(application));
        */
    }

    public Application getApplication(){
        return application;
    }

    public void setScreen(STATE screen){
        //application.setScreen(screens.get(screen));
        if(application.getScreen() != null)
            application.getScreen().dispose();

        switch(screen)
        {
            case MAIN_MENU:
                application.setScreen(new MainMenuScreen((application)));
                break;
            case SETTINGS:
                application.setScreen(new SettingsScreen((application)));
                break;
            case CONNECTION:
                application.setScreen(new ConnectionScreen((application)));
                break;
            case PLAY:
                application.setScreen(new MainGameScreen((application)));
                break;
        }


    }

    public void dispose(){
        for(AbstractScreen screen : screens.values()){
            if(screen != null){
                screen.dispose();
            }
        }
    }

}
