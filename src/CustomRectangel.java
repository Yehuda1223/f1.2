import java.awt.*;

public class CustomRectangel extends Canvas {
    private int x;
    private int y;
    final int width = 50;
    final int heighet = 120;
    public String photo;


    public int limit(int a) {
        if (a < 50) {
            return 50;
        } else if (a > 480) {
            return 480;
        } else {
            return a;
        }
    }


    public int getWidth() {
        return width;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getHeighet() {
        return heighet;
    }

    public String getPhoto() {
        return photo;
    }


    public void paint(Graphics Graphics, String pic) {

        Toolkit tool = Toolkit.getDefaultToolkit();
        Image i = tool.getImage(pic);
        Graphics.drawImage(i, this.x + 1, this.y, this.width, this.heighet, this);
    }

    public CustomRectangel(int x, int y, String guy) {

        this.x = x;
        this.y = y;
        this.photo = guy;

    }

    public void MoveRighetCar() {
        this.x = x + 5;
        this.x = limit(x);

    }

    public void MoveLeftCar() {
        this.x = x - 5;
        this.x = limit(x);
    }

    public void MoveUpCar() {
        this.y = y - 5;
        limit(x);
    }

    public void MoveDownCar() {
        this.y = y + 5;
        limit(x);
    }

    public void MoveRighetObstacles() {
        this.x++;

    }

    public void MoveLeftObstacles() {
        this.x--;
    }

    public void MoveUpObstacles() {
        this.y--;
    }

    public void MoveDownObstacles() {
        this.y++;

    }

    public boolean CheckCollision(CustomRectangel other) {
        boolean collision = false;
        Rectangle thisRectangel = new Rectangle(this.x, this.y, this.width, this.heighet);
        Rectangle otherRectangel = new Rectangle(other.x, other.y, other.width, other.heighet);
        if (thisRectangel.intersects(otherRectangel)) {
         //   play=false;
            collision = true;
        }
        return collision;
    }

    public void paint(Graphics g) {

        g.fillRect(this.x, this.y, this.width, this.heighet);
    }
}
