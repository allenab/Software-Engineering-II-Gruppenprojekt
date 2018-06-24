package at.uni.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import at.uni.screens.MainGameScreen;

public final class PowerupMoreBombs extends Powerup {
    public PowerupMoreBombs(World world, float x, float y) {
        super(world, x, y);
        this.type = EPowerUpType.MOREBOMBS;
        texture = new Texture("images/powerup_morebombs.png");
    }

    public void OnCollectedByPlayer(Player player, MainGameScreen screen){
        super.OnCollectedByPlayer(player, screen);
        player.increaseMaxBombCount();;
    }
}
