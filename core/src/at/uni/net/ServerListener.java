package at.uni.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import at.uni.net.packets.request.JoinRequest;
import at.uni.net.packets.request.KittenRequest;
import at.uni.net.packets.request.MessageRequest;
import at.uni.net.packets.response.JoinResponse;
import at.uni.net.packets.response.KittenResponse;
import at.uni.net.packets.response.MessageResponse;
import at.uni.objects.Player;

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
        if(object instanceof JoinRequest){
            JoinRequest request = (JoinRequest) object;

            int numberOfPlayers = server.getNumberOfPlayers();

            JoinResponse response = new JoinResponse();
            response.serverFull = numberOfPlayers >= 4;
            response.joined = false;

            if(!(numberOfPlayers >= 4)){
                server.addGameObject(numberOfPlayers, null);
                response.joined = true;
                response.id = numberOfPlayers;
            }

            connection.sendTCP(response);


        } else if(object instanceof KittenRequest) {
            KittenRequest request = (KittenRequest) object;

            server.updatePlayer(request.id, request.player.getPosition());

            KittenResponse response = new KittenResponse();
            response.playerOne = server.getPlayer(0);
            response.playerTwo = server.getPlayer(1);
            connection.sendUDP(response);


        } else if(object instanceof MessageRequest) {
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
