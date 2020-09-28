package pattern.facade;

import java.util.ArrayList;
import java.util.List;

public class FacadeDemo {
    public static void main(String[] args) {
        //Without facade
        Buffer buffer = new Buffer(20, 30);
        Viewport viewport = new Viewport(buffer, 20, 30, 0, 0);
        Console console = new Console(20,30);
        console.addViewport(viewport);
        console.render();

        //with facade
        Console newConsole = Console.newConsole(20,30);
        console.render();
    }
}


class Buffer {
    private char[] characters;
    private int lineWidth;

    public Buffer(int lineWidth, int lineHeight) {
        this.lineWidth = lineWidth;
        this.characters = new char[lineHeight * lineWidth];
    }

    public char charAt(int x, int y) {
        return characters[y * lineWidth + x];
    }
}

class Viewport {
    private Buffer buffer;
    private int width;
    private int height;
    private int offsetX;
    private int offsety;

    public Viewport(Buffer buffer, int width, int height, int offsetX, int offsety) {
        this.buffer = buffer;
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsety = offsety;
    }

    public char charAt(int x, int y) {
        return buffer.charAt(x + offsetX, y + offsety);
    }
}

class Console {
    private List<Viewport> viewports = new ArrayList<>();
    int width, height;

    public Console(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addViewport(Viewport viewport) {
        viewports.add(viewport);
    }

    public void render() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                for (Viewport viewport : viewports) {
                    System.out.print(viewport.charAt(x, y));
                }
            }
            System.out.println();
        }
    }

    //This is facade
    public static Console newConsole(int width, int height) {
        Buffer buffer = new Buffer(width, height);
        Viewport viewport = new Viewport(buffer, width, height, 0, 0);
        Console console = new Console(width,height);
        console.addViewport(viewport);
        return console;
    }
}