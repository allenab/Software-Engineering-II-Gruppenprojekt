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

    private Map<Integer, GameObject> gameObjects;

    public KittenServer() throws IOException {

        gameObjects = new HashMap<Integer, GameObject>();

        server = new Server();
        server.addListener(new ServerListener(this));
        server.bind(KryoUtil.TCP_PORT, KryoUtil.UDP_PORT);
        server.start();

        KryoUtil.registerPackets(server.getKryo());
    }

    public Server getServer() {
        return server;
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

    public List<GameObject> getPlayers(){
        List<GameObject> players = new ArrayList<GameObject>();
        for(GameObject o : gameObjects.values()){
            if(o instanceof Player){
                players.add(o);
            }
        }
        return players;
    }

    public void addGameObject(Integer i, GameObject o) {
        gameObjects.put(i, o);
    }

    public GameObject[] getGameObjects() {
        GameObject[] players = new GameObject[gameObjects.size()];
        for(int i = 0; i < players.length; i++) {
            players[i] = gameObjects.get(i);
        }
        return players;
    }

    public void update(Integer i, Vector2 pos){
        GameObject o = gameObjects.get(i);
        o.setPosition(pos.x, pos.y);
        gameObjects.put(i, o);
    }

    public void close() {
        server.close();
    }

    public GameObject getGameObject(Integer i) {
        return gameObjects.get(i);
    }

}
