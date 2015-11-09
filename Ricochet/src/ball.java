import java.awt.*;
import java.util.Random;

public class ball{

    public int x, y, width = 15, height = 15;

    public int motionX, motionY;

    public int speed = 5;

    public Random random;

    public ball(Ricochet ricochet){
        this.random = new Random();
        this.x = ricochet.width / 2 - this.width / 2 - 15;
        this.y = ricochet.height / 2 - this.height / 2 - 39;
        this.motionY = speed;
        this.motionX = 0;


    }

    public void update(Ship ship){

        if (ship.score > 5) {
            speed = 10;
        }
        else if (ship.score > 10) {
            speed = 15;
        }
        else if (ship.score > 20) {
            speed = 20;
        }
        else if (ship.score > 40) {
            speed = 25;
        }
        else if (ship.score > 60) {
            speed = 30;
        }
        else if (ship.score > 100) {
            speed = 40;
        }

        if(checkCollision(ship) == 1 ){
            this.motionY = -speed;
                this.motionX = -4 + random.nextInt(8);
                if (this.motionX == 0) {
                    motionX = 2;
                }


            ship.score++;
        }

        else if(checkCollision(ship) == 2){
            this.motionY = speed;
        }

        else if(checkCollision(ship) == 3){
            this.motionX = -this.motionX;
        }

        else if(checkCollision(ship) == 4){
            Ricochet.ricochet.gameStatus = 3;
        }

        this.x += motionX;
        this.y += motionY;
    }

    public int checkCollision(Ship ship){

        if (ship.y - ship.height < this.y && (ship.x < this.x && ship.x + ship.width > this.x)){
            return 1;
        }

        else if(this.y < 1) {
            return 2;
        }

        else if(this.x < 1 || this.x > Ricochet.ricochet.width - 39){
            return 3;
        }

        else if (ship.y < this.y){
            return 4;
        }

        return 0;
    }


    public void render(Graphics2D g){
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);

    }

}
