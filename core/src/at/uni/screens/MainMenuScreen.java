package at.uni.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import at.uni.Application;
import at.uni.utils.InputData;

public class MainMenuScreen extends AbstractScreen {

    private BitmapFont font;
    private OrthographicCamera camera;

    public MainMenuScreen(Application application) {
        super(application);

        font = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT);

    }

    @Override
    public void show() {

        application.getSpriteBatch().setProjectionMatrix(camera.combined);


        // creates the pack for the buttons, its skin, the font used on the buttons, and the stage(screen)

        atlas = new TextureAtlas("buttons.pack");
        skin = new Skin();
        skin.addRegions(atlas);
        font = new BitmapFont(Gdx.files.internal("font.fnt"), false);

        //creates the play button with the text, its position and the size

        btnPlay = new TextButton("Play", style);
        btnPlay.setSize(80, 50);
        btnPlay.setPosition(100, 200);

        btnPlay.addListener(new InputListener() {

             if(InputData.isTouched(0, new Rectangle(100, Application.VIEWPORT_HEIGHT - 100, 50 , 20)){
                System.out.println("PLAY");
            })


        });

        //creates the exit button with the text, its position and the size

        btnExit = new TextButton("Exit", style);
        btnExit.setSize(80,50);
        btnExit.setPosition(300, 100);

        btnExit.addListener(new InputListener() {

            if(InputData.isPressed(1, new Rectangle(100,Application.VIEWPORT_HEIGHT - 100,50,20))){
                System.out.println("EXIT");
            }

        });

        //creates the setting button with the text, its position and the size

        btnExit = new TextButton("Settings", style);
        btnExit.setSize(80,50);
        btnExit.setPosition(500, 100);

        btnExit.addListener(new InputListener() {

            if(InputData.isPressed(1, new Rectangle(100,Application.VIEWPORT_HEIGHT - 100,50,20))){
                System.out.println("SETTINGS");
            }

        });

        stage.addActor(btnPlay);
        stage.addActor(btnExit);
        stage.addActor(btnSettings);

        super.show();
    }

    @Override
    public void handleInput() {

        }
    }

    @Override
    public void update(float deltatime) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        font.draw(sb, "GAME MENU", 100, 100);
        sb.end();

        Gdx. gl .glClearColor (1, 1, 1, 1);
        Gdx. gl .glClear (GL10. GL_COLOR_BUFFER_BIT );

        if (Gdx. Eingabe .isKeyPressed (Input.Keys. LEFT )) {
            if (Gdx. Eingabe .isKeyPressed (Input.Keys. CONTROL_LEFT ))
                //In Liste speichern
                //Wenn kein Name vorhanden kann man das Spiel nicht starten und es soll eine Fehlermeldung:'
                //Bitte Namen eingeben!! ausgeben.
            }

    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
