package at.uni.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import at.uni.net.packets.response.MessageResponse;

public class ClientListener extends Listener {

    @Override
    public void connected(Connection connection) {
        System.out.println("[Client] Connected");
    }

    @Override
    public void disconnected(Connection connection) {
        System.out.println("[Client] Disconnected");
    }

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof MessageResponse) {

        }
    }
}
