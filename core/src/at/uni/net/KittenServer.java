package at.uni.net;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.uni.net.packets.request.MessageRequest;
import at.uni.objects.GameObject;
import at.uni.objects.Player;

public class KittenServer {

    private Server server;

    private String message;
    private boolean recivedMessage;
    private Map<Integer, GameObject> gameObjects;

    public KittenServer() throws IOException {

        recivedMessage = false;
        message = "";

        gameObjects = new HashMap<Integer, GameObject>();

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

    public int getNumberOfPlayers() {
        int count = 0;
        for(GameObject o : gameObjects.values()){
            if(o instanceof Player){
                count ++;
            }
        }
        return count;
    }

    public void addGameObject(Integer i, GameObject o) {
        gameObjects.put(i, o);
    }

    public void updatePlayer(Integer i, Vector2 pos){
        GameObject o = gameObjects.get(i);
        o.setPosition(pos.x, pos.y);
        gameObjects.put(i, o);
    }

    public GameObject getPlayer(Integer i) {
        return gameObjects.get(i);
    }

}
