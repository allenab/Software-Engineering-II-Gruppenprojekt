package at.uni.net;

import com.badlogic.gdx.physics.box2d.World;
import com.esotericsoftware.kryo.Kryo;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import at.uni.net.packets.request.JoinRequest;
import at.uni.net.packets.request.KittenRequest;
import at.uni.net.packets.request.MessageRequest;
import at.uni.net.packets.response.JoinResponse;
import at.uni.net.packets.response.KittenResponse;
import at.uni.net.packets.response.MessageResponse;
import at.uni.objects.Bomb;
import at.uni.objects.GameObject;
import at.uni.objects.Player;

/**
 * Created by Dominik on 17.04.2018.
 */

public class KryoUtil {

    public static final int TCP_PORT = 65420;
    public static final int UDP_PORT = 65421;
    public static final int TIMEOUT = 5000;

    private KryoUtil() {}

    public static void registerPackets(Kryo kryo) {
        kryo.register(MessageRequest.class);
        kryo.register(KittenRequest.class);
        kryo.register(JoinRequest.class);

        kryo.register(MessageResponse.class);
        kryo.register(KittenResponse.class);
        kryo.register(JoinResponse.class);

        kryo.register(GameObject[].class);
        kryo.register(Player.class);
        kryo.register(Bomb.class);
        kryo.register(World.class);
    }

    public static List<String> getIPAddresses() {
        List<String> addresses = new ArrayList<String>();
        try {

            for(NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                for(InetAddress address : Collections.list(networkInterface.getInetAddresses())) {
                    if(address instanceof Inet4Address) {
                        String ip = address.getHostAddress();
                        if(!ip.startsWith("127"))
                            addresses.add(address.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) { }
        return addresses;
    }

}
