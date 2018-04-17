package at.uni.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import at.uni.net.packets.request.MessageRequest;

public class ServerListener extends Listener {

    @Override
    public void connected(Connection connection) {
        System.out.println("[Server] Client connected");
    }

    @Override
    public void disconnected(Connection connection) {
        System.out.println("[Server] Client disconnected");
    }

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof MessageRequest) {

        }
    }
}
