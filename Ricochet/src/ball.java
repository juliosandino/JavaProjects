import java.awt.*;
import java.util.Random;

public class ball{

    public int x, y, width = 15, height = 15;

    public int motionX, motionY;

    public int speedBall = 2;

    public Random random;

    public ball(Ricochet ricochet){

        this.random = new Random();
        this.x = ricochet.width / 2 - this.width / 2 - 15;
        this.y = ricochet.height / 2 - this.height / 2 - 39;
        this.motionY = speedBall;
        this.motionX = 0;
    }

    public void update(Ship ship){

        if (ship.score > 5 && ship.score <= 10) {
            speedBall = 3;
        }
        else if (ship.score > 10 && ship.score <= 20) {
            speedBall = 4;
        }
        else if (ship.score > 20 && ship.score <= 30) {
            speedBall = 6;
        }
        else if (ship.score > 40) {
            speedBall = 10;
            ship.speed = 10;
        }

        if(checkCollision(ship) == 1 ){
            this.motionY = -speedBall;
            this.motionX = -3 + random.nextInt(6);
            if (this.motionX == 0) {
                motionX = random.nextInt(4);
            }


            ship.score++;
        }

        else if(checkCollision(ship) == 2){
            this.motionY = speedBall;
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

        else if (ship.y < this.y || this.y > 600){
            return 4;
        }

        return 0;
    }


    public void render(Graphics2D g){

        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }

}
