package at.uni.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.io.IOException;

import at.uni.Application;
import at.uni.net.KittenClient;

public class ChatScreen extends AbstractScreen {

    private BitmapFont font;
    private String message;

    private Label lblIp;
    private Label lblMessage;
    private TextField txtIp;
    private TextField txtMessage;
    private TextButton btnSend;
    private TextButton btnConnect;

    private KittenClient client;

    private OrthographicCamera camera;

    public ChatScreen(Application application) {
        super(application);

        font = new BitmapFont();
        message = "";

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT);
    }

    @Override
    public void load() {
        application.getSpriteBatch().setProjectionMatrix(camera.combined);

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));


        lblIp = new Label("IP:", skin);
        lblIp.setPosition(100, Application.VIEWPORT_HEIGHT - 40);
        stage.addActor(lblIp);

        txtIp = new TextField("", skin);
        txtIp.setPosition(120, Application.VIEWPORT_HEIGHT - 40);
        stage.addActor(txtIp);

        btnConnect = new TextButton("Connect", skin);
        btnConnect.setPosition(280, Application.VIEWPORT_HEIGHT - 40);
        btnConnect.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                String ip = txtIp.getText();

                if(!ip.isEmpty()) {
                    try {
                        client = new KittenClient(ip);
                        message = "Verbunden";
                    } catch (IOException e) {
                        message = e.getMessage();
                        //message = "Fehler beim Verbinden";
                    }
                } else {
                    message = "IP eingeben!";
                }

                super.touchUp(event, x, y, pointer, button);
            }
        });
        stage.addActor(btnConnect);

        lblMessage = new Label("Nachricht: ", skin);
        lblMessage.setPosition(100,Application.VIEWPORT_HEIGHT - 100);
        stage.addActor(lblMessage);

        txtMessage = new TextField("", skin);
        txtMessage.setPosition(180, Application.VIEWPORT_HEIGHT - 100);
        txtMessage.setSize(400, 30);
        stage.addActor(txtMessage);

        btnSend = new TextButton("Senden", skin);
        btnSend.setPosition(100, Application.VIEWPORT_HEIGHT - 160);
        btnSend.setSize(480, 50);
        btnSend.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                String txt = txtMessage.getText();
                if(!txt.isEmpty()) {
                    client.sendMessage(txt);
                } else {
                    message = "Sie müssen was eingeben";
                }

                super.touchUp(event, x, y, pointer, button);
            }
        });
        stage.addActor(btnSend);

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltatime) {
        if(client != null)
            if(client.recivedMessage()) {
                message = client.getMessage();
            }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        font.draw(sb, message, 400,100);
        sb.end();
    }

    @Override
    public void unload() {
        font.dispose();
    }
}
