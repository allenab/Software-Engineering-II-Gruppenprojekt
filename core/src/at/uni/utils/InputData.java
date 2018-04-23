package at.uni.utils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.HashMap;
import java.util.Map;

public class InputData {

    private static Map<Key, Boolean> states;
    private static Map<Key, Boolean> oldState;
    private static Map<Integer, Vector2> touchPoints;
    private static Map<Integer, Vector2> releasePoints;
    private static Map<Integer, Boolean> touchStates;

    public enum Key{
        Forward,
        Backward,
        Right,
        Left,
        Space
    }

    static {
        states = new HashMap<Key, Boolean>();
        oldState = new HashMap<Key, Boolean>();
        touchPoints = new HashMap<Integer, Vector2>();
        releasePoints = new HashMap<Integer, Vector2>();
        touchStates = new HashMap<Integer, Boolean>();
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

    public static void setTouchPoint(int pointer, float x, float y){
        touchPoints.put(pointer, new Vector2(x,y));
        touchStates.put(pointer, true);
    }

    public static void setReleasePoint(int pointer, float x, float y){
        releasePoints.put(pointer, new Vector2(x,y));
        touchStates.put(pointer, false);
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

    public static boolean isPressed(int pointer, Rectangle bounds){
        if(touchStates.get(pointer) == null) return false;
        if(!touchStates.get(pointer)) return false;

        Vector2 point = touchPoints.get(pointer);
        if(point != null){
            if(bounds.contains(point)) return true;
        }

        return false;
    }

    public static boolean isTouched(int pointer, Rectangle bounds){
        if(touchStates.get(pointer) == null) return false;
        if(touchStates.get(pointer)) return false;

        Vector2 touchPoint = touchPoints.get(pointer);
        Vector2 releasePoint = releasePoints.get(pointer);

        if(touchPoint != null && releasePoint != null){
            if(bounds.contains(touchPoint) && bounds.contains(releasePoint)) {
                touchPoints.remove(pointer);
                releasePoints.remove(pointer);
                return true;
            }
        }

        return false;
    }

}
