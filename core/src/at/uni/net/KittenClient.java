package at.uni.net;

import com.esotericsoftware.kryonet.Client;

import java.io.IOException;

import at.uni.net.packets.request.MessageRequest;

public class KittenClient {

    private Client client;

    private String message;
    private boolean recivedMessage;

    public KittenClient(String host) throws IOException {

        recivedMessage = false;
        message = "";

        client = new Client();
        client.addListener(new ClientListener(this));
        client.start();

        client.connect(KryoUtil.TIMEOUT, host, KryoUtil.TCP_PORT, KryoUtil.UDP_PORT);

        KryoUtil.registerPackets(client.getKryo());
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
