package at.uni.net;

import com.esotericsoftware.kryonet.Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.uni.net.packets.request.DisconnectRequest;
import at.uni.net.packets.request.JoinRequest;
import at.uni.net.packets.request.KittenRequest;
import at.uni.net.packets.request.MessageRequest;
import at.uni.objects.GameObject;

public class KittenClient {

    private Client client;
    private Integer playerId;
    private boolean connected;
    private boolean gameStart;
    private List<GameObject> objects;

    public KittenClient(String host) throws IOException {

        playerId = null;
        connected = false;
        gameStart = false;

        client = new Client();
        client.addListener(new ClientListener(this));
        client.start();

        client.connect(KryoUtil.TIMEOUT, host, KryoUtil.TCP_PORT, KryoUtil.UDP_PORT);

        objects = new ArrayList<GameObject>();

        KryoUtil.registerPackets(client.getKryo());
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer id){
        this.playerId = id;
    }

    public boolean isConnected(){
        return connected;
    }

    public void setConnected(boolean connected){
        this.connected = connected;
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    public void setObjects(GameObject[] objects) {
        this.objects = Arrays.asList(objects);
    }

    /*public void updatePlayers(GameObject localPlayer){
        KittenRequest request = new KittenRequest();
        request.id = playerId;
        request.player = localPlayer;
        client.sendUDP(request);
    }*/

    public void join(String name){
        JoinRequest request = new JoinRequest();
        request.playerName = name;
        client.sendTCP(request);
    }

    public void startGame(boolean b){
        this.gameStart = b;
    }

    public boolean isGameStarted() {
        return this.gameStart;
    }

    public void close() {
        client.close();
    }

    public void leave() {
        DisconnectRequest request = new DisconnectRequest();
        request.id = playerId;
        client.sendTCP(request);
    }
}
