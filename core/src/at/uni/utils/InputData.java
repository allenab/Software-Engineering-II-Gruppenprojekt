package at.uni.utils;

import java.util.HashMap;
import java.util.Map;

public class InputData {

    private static Map<Button, Boolean> states;
    private static Map<Button, Boolean> oldState;

    public enum Button{
        Forward,
        Backward,
        Right,
        Left
    }

    static {
        states = new HashMap<Button, Boolean>();
        oldState = new HashMap<Button, Boolean>();
        init();
    }

    private static void init(){

        for(Button btn : Button.values()){
            states.put(btn, false);
            oldState.put(btn, false);
        }

    }

    public static void update(){
        for(Button btn : Button.values()){
            oldState.put(btn, states.get(btn));
        }
    }

    public static void setButtonState(Button btn, boolean state){
        states.put(btn, state);
    }

    public static boolean isButtonDown(Button btn){
        return states.get(btn);
    }

    public static boolean isButtonPressed(Button btn){
        return states.get(btn) && !oldState.get(btn);
    }

}
