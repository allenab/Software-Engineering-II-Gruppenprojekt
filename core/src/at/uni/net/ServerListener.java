package at.uni.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import at.uni.net.packets.request.MessageRequest;
import at.uni.net.packets.response.MessageResponse;

public class ServerListener extends Listener {

    private KittenServer server;

    public ServerListener(KittenServer server) {
        this.server = server;
    }

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
            MessageRequest request = (MessageRequest) object;

            server.setRecivedMessage(true);
            server.setMessage(request.message);

            MessageResponse response = new MessageResponse();
            response.success = true;
            connection.sendTCP(response);
        } else if(object instanceof MessageResponse) {
            System.out.println("[Server] Message successfully send!");
        }
    }
}
