public abstract class BaseFood implements Food {
    protected int value;
    protected String color;
    protected int x, y;

    public BaseFood(int value, String color, int x, int y) {
        this.value = value;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}