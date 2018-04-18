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

import at.uni.Application;
import at.uni.net.KittenClient;

public class ChatScreen extends AbstractScreen {

    private BitmapFont font;

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

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT);
    }

    @Override
    public void show() {
        super.show();
        application.getSpriteBatch().setProjectionMatrix(camera.combined);

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        lblIp = new Label("IP:", skin);
        lblIp.setPosition(100, 100);
        stage.addActor(lblIp);

        txtIp = new TextField("", skin);
        txtIp.setPosition(120, 100);
        txtIp.setSize(200, 50);
        stage.addActor(txtIp);

        btnConnect = new TextButton("Connect", skin);
        btnConnect.setPosition(100, 70);
        btnConnect.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                test();
            }
        });
        stage.addActor(btnConnect);
    }

    private void test(){
        System.out.println("test");
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

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();

    }
}
