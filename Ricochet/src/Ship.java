import java.awt.*;


public class Ship{

    public int score;

    public int x, y, width = 100, height = 10;

    public int speed = 5;

    public Ship(Ricochet ricochet) {
        this.x = ricochet.width / 2 - width / 2 - 15;
        this.y = ricochet.height - height - 39;
    }


    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y , width, height);

    }

    public void move(boolean left) {


        if (left){
            if(x > 0){
                x-= speed;
            }

            else {
                x = 0;
            }
        }

        else {
            if (x + speed < Ricochet.ricochet.width - width - 15){
                x += speed;
            }

            else {
                x = Ricochet.ricochet.width - width - 15;
            }
        }
    }
}
