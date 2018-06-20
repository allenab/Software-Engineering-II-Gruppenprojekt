package at.uni.net;

import com.esotericsoftware.kryonet.Client;

import java.io.IOException;

import at.uni.net.packets.request.JoinRequest;
import at.uni.net.packets.request.KittenRequest;
import at.uni.net.packets.request.MessageRequest;
import at.uni.objects.GameObject;

public class KittenClient {

    private Client client;
    private Integer playerId;
    private boolean connected;

    public KittenClient(String host) throws IOException {

        playerId = null;
        connected = false;

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

    public void setConnected(){
        this.connected = true;
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

}
