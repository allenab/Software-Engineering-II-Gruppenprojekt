package at.uni.net;

import com.esotericsoftware.kryonet.Client;

import java.io.IOException;

public class KittenClient {

    public static final int TIMEOUT = 100;

    private Client client;

    public KittenClient(String host) {
        try {

            client = new Client();
            client.start();

            client.connect(TIMEOUT, host, KittenServer.PORT);

            KryoHelper.registerPackets(client.getKryo());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
