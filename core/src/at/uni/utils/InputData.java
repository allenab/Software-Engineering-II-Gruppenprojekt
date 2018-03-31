package at.uni.utils;

import java.util.HashMap;
import java.util.Map;

public class InputData {

    private static Map<Key, Boolean> states;
    private static Map<Key, Boolean> oldState;

    public enum Key{
        Forward,
        Backward,
        Right,
        Left
    }

    static {
        states = new HashMap<Key, Boolean>();
        oldState = new HashMap<Key, Boolean>();
        init();
    }

    private static void init(){

        for(Key btn : Key.values()){
            states.put(btn, false);
            oldState.put(btn, false);
        }

    }

    public static void update(){
        for(Key k : Key.values()){
            oldState.put(k, states.get(k));
        }
    }

    public static void setKeyState(Key k, boolean state){
        states.put(k, state);
    }

    public static boolean isKeyDown(Key k){
        return states.get(k);
    }

    public static boolean isKeyPressed(Key k){
        return states.get(k) && !oldState.get(k);
    }

}
