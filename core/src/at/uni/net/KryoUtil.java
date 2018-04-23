package at.uni.net;

import com.esotericsoftware.kryo.Kryo;

import at.uni.net.packets.request.MessageRequest;
import at.uni.net.packets.response.MessageResponse;

/**
 * Created by Dominik on 17.04.2018.
 */

public class KryoUtil {

    public static final int TCP_PORT = 25565;
    public static final int UDP_PORT = 25566;
    public static final int TIMEOUT = 5000;

    private KryoUtil() {}

    public static void registerPackets(Kryo kryo) {
        kryo.register(MessageRequest.class);

        kryo.register(MessageResponse.class);
    }

}
