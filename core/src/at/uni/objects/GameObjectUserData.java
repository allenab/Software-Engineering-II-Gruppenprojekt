package at.uni.objects;

public final class GameObjectUserData {
    public  enum EUserDataType
    {
        PLAYER,
        BRICK,
        WALL,
        BOMB,
        POWERUP,
        EXPLOSION
    }

    public GameObjectUserData(GameObject object, EUserDataType type)
    {
        assert(object != null && type != null);
        this.gameObject = object;
        this.userDataTypetype = type;
    }

    public EUserDataType userDataTypetype;
    public GameObject gameObject = null;
}
