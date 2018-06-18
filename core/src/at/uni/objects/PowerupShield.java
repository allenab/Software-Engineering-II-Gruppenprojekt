package at.uni.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import at.uni.utils.InputData;

public final class PowerupShield extends Powerup {

    public PowerupShield(World world, float x, float y)
    {
        super(world, x,y);
        texture = new Texture("images/powerup_shield.png");
        this.type = EPowerUpType.SHIELD;
    }
    @Override
    public void OnCollectedByPlayer(Player player)
    {
        player.activateShield();
        return;
    }

}
