package at.uni.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import at.uni.screens.MainGameScreen;
import at.uni.utils.InputData;

public final class PowerupSpeedUp extends Powerup {
    public PowerupSpeedUp(World world, float x, float y) {
        super(world, x, y);
        this.type = EPowerUpType.SPEEDUP;
        texture = new Texture("images/powerup_speedup.png");
    }

    public void OnCollectedByPlayer(Player player, MainGameScreen screen){
        super.OnCollectedByPlayer(player, screen);
        player.modifyMovementSpeed(2);;
    }

    @Override
    public void handleInput(InputData data) {

    }
}
