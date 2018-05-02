package at.uni.net;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import at.uni.net.packets.request.MessageRequest;
import at.uni.objects.GameObject;

public class KittenServer {

    private Server server;

    private String message;
    private boolean recivedMessage;
    private List<GameObject> gameObjects;

    public KittenServer() throws IOException {

        recivedMessage = false;
        message = "";

        gameObjects = new ArrayList<GameObject>();

        server = new Server();
        server.addListener(new ServerListener(this));
        server.bind(KryoUtil.TCP_PORT, KryoUtil.UDP_PORT);
        server.start();

        KryoUtil.registerPackets(server.getKryo());
    }

    public void setRecivedMessage(boolean recived) {
        this.recivedMessage = recived;
    }

    public boolean recivedMessage() {
        return recivedMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void sendMessage(String message) {
        MessageRequest mr = new MessageRequest();
        mr.message = message;
        server.sendToAllTCP(mr);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void addGameObject(GameObject o) {
        gameObjects.add(o);
    }

}
