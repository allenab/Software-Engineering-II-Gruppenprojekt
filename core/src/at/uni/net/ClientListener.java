package at.uni.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import at.uni.net.packets.request.MessageRequest;
import at.uni.net.packets.request.StartRequest;
import at.uni.net.packets.response.DisconnectResponse;
import at.uni.net.packets.response.JoinResponse;
import at.uni.net.packets.response.KittenResponse;
import at.uni.net.packets.response.MessageResponse;
import at.uni.net.packets.response.StartResponse;

public class ClientListener extends Listener {

    private KittenClient client;

    public ClientListener(KittenClient client) {
        this.client = client;
    }

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
        if (object instanceof JoinResponse) {
            JoinResponse response = (JoinResponse) object;
            if (response.joined) {
                client.setPlayerId(response.id);
                client.setConnected(true);
            }

        } else if(object instanceof KittenResponse) {
            KittenResponse response = (KittenResponse) object;
            client.setObjects(response.objects);

        }else if(object instanceof MessageRequest) {
            /*MessageRequest request = (MessageRequest) object;

            client.setRecivedMessage(true);
            client.setMessage(request.message);

            MessageResponse response = new MessageResponse();
            response.success = true;
            connection.sendTCP(response);*/
        } else if(object instanceof MessageResponse) {
            System.out.println("[Client] Message successfully send!");
        } else if(object instanceof DisconnectResponse) {
            DisconnectResponse response = (DisconnectResponse) object;
            if(response.isDisconnected) {
                client.setConnected(false);
            }
        } else if(object instanceof StartRequest) {
            StartRequest request = (StartRequest) object;
            client.startGame(true);

            StartResponse response = new StartResponse();
            connection.sendUDP(response);
        }
    }
}
