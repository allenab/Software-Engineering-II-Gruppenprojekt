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
import at.uni.net.KittenServer;

public class ChatServerScreen extends AbstractScreen {

    private BitmapFont font;
    private String message;

    private Label lblIp;
    private Label lblMessage;
    private TextField txtMessage;
    private TextButton btnSend;

    private KittenServer server;

    private OrthographicCamera camera;

    public ChatServerScreen(Application application) {
        super(application);

        font = new BitmapFont();
        message = "";

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT);
    }

    @Override
    public void load() {
        application.getSpriteBatch().setProjectionMatrix(camera.combined);

        try {
            server = new KittenServer();
            message = "Warte auf Verbindungen...";
        } catch (IOException e) {
            message = e.getMessage();
            //message = "Fehler, Server konnte nicht gestartet werden.";
        }

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        lblIp = new Label("IP:", skin);
        lblIp.setPosition(100, Application.VIEWPORT_HEIGHT - 50);
        stage.addActor(lblIp);

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
                    server.sendMessage(txt);
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
        if(server.recivedMessage()) {
            message = server.getMessage();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        font.draw(sb, message, 400,Application.VIEWPORT_HEIGHT - 180);
        sb.end();
    }

    @Override
    public void unload() {
        font.dispose();
    }
}
