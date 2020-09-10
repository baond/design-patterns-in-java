package pattern.adapter;

/**
 * Adaptor design pattern using to transfer a interface to new form to able to using by another interface
 */
public class AdapterDemo {
    Square square = new Square(2);
    Rectangle rectangle = new SquareToRectangleAdapter(square);
}

class Square
{
    public int side;

    public Square(int side)
    {
        this.side = side;
    }
}

interface Rectangle
{
    int getWidth();
    int getHeight();

    default int getArea()
    {
        return getWidth() * getHeight();
    }
}

class SquareToRectangleAdapter implements Rectangle
{
    private Square square;

    public SquareToRectangleAdapter(Square square)
    {
        this.square = square;
    }

    @Override
    public int getWidth()
    {
        return square.side;
    }

    @Override
    public int getHeight()
    {
        return square.side;
    }
}