package at.uni.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import at.uni.Application;
import at.uni.handlers.GameScreenManager;
import at.uni.net.KittenClient;
import at.uni.net.KittenServer;
import at.uni.net.KryoUtil;
import at.uni.objects.GameObject;

public class LobbyScreen extends AbstractScreen {

    private OrthographicCamera camera;
    private Label lblPlayers;
    private Label lblIp;
    private Label title;

    private long soundID;

    public LobbyScreen(Application application) {
        super(application);

        Application.setBgLoop(Gdx.audio.newSound(Gdx.files.internal("sounds/yummie_shortBGloop.mp3")));
        
    }

    @Override
    public void load() {

        this.soundID = Application.getBgLoop().loop();

        stage.clear();

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        title = new Label("Lobby", skin);
        title.setPosition(Application.VIEWPORT_WIDTH / 2 - 100, Application.VIEWPORT_HEIGHT - 20);
        stage.addActor(title);

        if(!application.isServer()) {
            lblPlayers = new Label("Players:", skin);
            lblPlayers.setPosition(45, 300);
            stage.addActor(lblPlayers);
        }

        //creates the game start button with the text, its position and the size
        if(application.isServer()) {
            try {
                if(application.getServer() == null)
                application.setServer(new KittenServer());

                if(application.getClient() == null)
                    application.setClient(new KittenClient("127.0.0.1"));
                    application.getClient().join(UUID.randomUUID().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            lblIp = new Label("Server IP:", skin);
            lblIp.setPosition(45, 330);
            stage.addActor(lblIp);

            lblPlayers = new Label("Players: ", skin);
            lblPlayers.setPosition(45, 300);
            stage.addActor(lblPlayers);

            List<String> ipAddresses = KryoUtil.getIPAddresses();
            if(ipAddresses.size() != 0){
                String s = "Server IP: ";
                for(String ip : ipAddresses){
                    s += ip + "\n";
                }
                lblIp.setText(s);
            }

            TextButton btnGamestart = new TextButton("Start", skin);
            btnGamestart.setSize(Application.VIEWPORT_WIDTH / 6, Application.VIEWPORT_HEIGHT / 4);
            btnGamestart.setPosition(45, 100);

            btnGamestart.addListener(new ClickListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    super.touchUp(event, x, y, pointer, button);

                    application.getServer().startGame();

                    application.getGameScreenManager().setScreen(GameScreenManager.STATE.PLAY);
                }
            });

            stage.addActor(btnGamestart);
        }

        //creates the exit button with the text, its position and the size

        TextButton btnExit = new TextButton("Exit", skin);
        btnExit.setSize(180,50);
        btnExit.setPosition(495, 100);

        btnExit.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);

                if(!application.isServer()){
                    if(application.getClient().isConnected()) {
                        application.getClient().leave();
                    }
                }

                application.getGameScreenManager().setScreen(GameScreenManager.STATE.MAIN_MENU);
            }
        });

        stage.addActor(btnExit);
    }

    @Override
    public void unload() {
        Application.getBgLoop().dispose();
        if(!application.isServer()){
            if(application.getClient().isConnected()) {
                application.getClient().leave();
            }
        }
        if(application.getServer() != null) {
            application.getServer().close();
        }
        if(application.getClient() != null) {
            application.getClient().close();
        }
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltatime) {
        if(application.isServer() && application.getServer() != null){
            List<GameObject> players = application.getServer().getPlayers();
            String lobby = "";
            for(GameObject p : players) {
                lobby += p.getName() + "\n";
            }
            lblPlayers.setText("Players:\n" + lobby);
        } else if(!application.isServer() && application.getClient() != null) {
            List<GameObject> players = application.getClient().getObjects();
            String lobby = "";
            for(GameObject p : players) {
                lobby += p.getName() + "\n";
            }
            lblPlayers.setText("Players:\n" + lobby);

            if(application.getClient().isGameStarted()) {
                application.getGameScreenManager().setScreen(GameScreenManager.STATE.PLAY);
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {

    }

}
