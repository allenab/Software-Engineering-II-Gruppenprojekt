package at.uni.net;

import com.esotericsoftware.kryonet.Client;

import java.io.IOException;

import at.uni.net.packets.request.KittenRequest;
import at.uni.net.packets.request.MessageRequest;
import at.uni.objects.GameObject;

public class KittenClient {

    private Client client;

    private Integer playerId;
    private GameObject localPlayer;
    private GameObject remotePlayer;
    private boolean connected;

    private String message;
    private boolean recivedMessage;

    public KittenClient(String host) throws IOException {

        playerId = null;
        connected = false;

        recivedMessage = false;
        message = "";

        client = new Client();
        client.addListener(new ClientListener(this));
        client.start();

        client.connect(KryoUtil.TIMEOUT, host, KryoUtil.TCP_PORT, KryoUtil.UDP_PORT);

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

    public void connected(){
        this.connected = true;
    }

    public GameObject getLocalPlayer() {
        return localPlayer;
    }

    public void setLocalPlayer(GameObject localPlayer) {
        this.localPlayer = localPlayer;
    }

    public GameObject getRemotePlayer() {
        return remotePlayer;
    }

    public void setRemotePlayer(GameObject remotePlayer) {
        this.remotePlayer = remotePlayer;
    }

    public void updatePlayers(GameObject localPlayer){
        KittenRequest request = new KittenRequest();
        request.id = playerId;
        request.player = localPlayer;
        client.sendUDP(request);
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
        client.sendTCP(mr);
    }

}
