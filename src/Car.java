import javax.swing.*;
import java.awt.*;

public class Car extends JPanel{

    public CustomRectangel Formola;
    private String standingGuy = "src/mercedese f1.png" ;
    public Car() {

        this.Formola = new CustomRectangel(125,500, standingGuy);
    }



    public void MoveLeft() {
        this.Formola.MoveLeftCar();

    }

    public void MoveRighet() {
        this.Formola.MoveRighetCar();
    }




    public CustomRectangel getFront() {
        return this.Formola;
    }
    Toolkit tool = Toolkit.getDefaultToolkit();
    Image guyImage = tool.getImage(this.standingGuy);

    public void paintComponent(Graphics g) {
        g.drawImage(guyImage, this.Formola.getX(),  this.Formola.getY(), this.Formola.getWidth(), this.Formola.getHeighet(),this);


    }
}
