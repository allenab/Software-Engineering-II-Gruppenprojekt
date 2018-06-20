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

import at.uni.Application;
import at.uni.handlers.GameScreenManager;
import at.uni.net.KittenServer;
import at.uni.net.KryoUtil;

public class LobbyScreen extends AbstractScreen {

    private OrthographicCamera camera;
    private TextField txtIp;
    private Label lblIp;
    private Label title;

    private long soundID;

    public LobbyScreen(Application application) {
        super(application);

        Application.bgLoop = Gdx.audio.newSound(Gdx.files.internal("sounds/yummie_shortBGloop.mp3"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT);

    }

    @Override
    public void load() {

        this.soundID = Application.bgLoop.loop();

        stage.clear();

        application.getSpriteBatch().setProjectionMatrix(camera.combined);

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        title = new Label("Lobby", skin);
        title.setPosition(Application.VIEWPORT_WIDTH / 2 - 100, Application.VIEWPORT_HEIGHT - 20);
        stage.addActor(title);

        if(!application.isServer()) {
            lblIp = new Label("Server IP:", skin);
            lblIp.setPosition(45, 330);
            stage.addActor(lblIp);

            txtIp = new TextField("", skin);
            txtIp.setPosition(145, 330);
            stage.addActor(txtIp);

            //creates the Connection button with the text, its position and the size

            TextButton btnConnection = new TextButton("Connect", skin);
            btnConnection.setSize(180,33);
            btnConnection.setPosition(350, 330);

            btnConnection.addListener(new ClickListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    super.touchUp(event, x, y, pointer, button);
                    System.out.println("Connection!!");
                }
            });

            stage.addActor(btnConnection);
        }

        //creates the game start button with the text, its position and the size
        if(application.isServer()) {
            try {
                if(application.getServer() == null)
                application.setServer(new KittenServer());
            } catch (IOException e) {
                e.printStackTrace();
            }

            lblIp = new Label("Server IP:", skin);
            lblIp.setPosition(45, 330);
            stage.addActor(lblIp);

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
                    //bgLoop.stop(soundID);
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
                application.getGameScreenManager().setScreen(GameScreenManager.STATE.MAIN_MENU);
            }
        });

        stage.addActor(btnExit);
    }

    @Override
    public void unload() {
        Application.bgLoop.dispose();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltatime) {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

}
