package at.uni.net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

import at.uni.net.packets.request.MessageRequest;
import at.uni.net.packets.response.MessageResponse;

public class KittenServer {

    public static final int PORT = 25565;

    private Server server;

    public KittenServer() {
        try {
            server = new Server();
            server.bind(PORT);
            server.addListener(new ServerListener());

            registerPackets();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerPackets() {
        Kryo kryo = server.getKryo();
        kryo.register(MessageRequest.class);
        kryo.register(MessageResponse.class);
    }

}
