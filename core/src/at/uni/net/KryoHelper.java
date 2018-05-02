package at.uni.net;

import com.esotericsoftware.kryo.Kryo;

import at.uni.net.packets.request.MessageRequest;
import at.uni.net.packets.response.MessageResponse;

/**
 * Created by Dominik on 17.04.2018.
 */

public class KryoHelper {

    private KryoHelper() {}

    public static void registerPackets(Kryo kryo) {
        kryo.register(MessageRequest.class);

        kryo.register(MessageResponse.class);
    }

}
