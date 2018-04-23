package at.uni.net;

import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

import at.uni.net.packets.request.MessageRequest;

public class KittenServer {

    private Server server;

    private String message;
    private boolean recivedMessage;


    public KittenServer() throws IOException {

        recivedMessage = false;
        message = "";

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

}
