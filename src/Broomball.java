// ************************************************************************** //
//                                                                            //
//                                                  .--.                      //
//   Broomball.java                              __/ o  ".                    //
//                                                 ),    "-.                  //
//   By: dany <github.com/dgerard42>               |;;,      "-._             //
//                                                 ';;;,,    ",_ "=-._        //
//   Created: 2020/02/04 17:47:57 by dany            ':;;;;,,..-``"-._`"-.    //
//   Updated: 2020/02/10 21:48:42 by dany              _/_/`           `'"`   //
//                                                                            //
// ************************************************************************** //

import objectdraw.*;

public class Broomball extends WindowController {

    private static final int CANVAS_HEIGHT = 500;
    private static final int CANVAS_WIDTH = 500;
    private static final int DIRT_SIZE = 30;
    private static final int BROOM_SIZE = 10;
    private static final int OFFSET = (DIRT_SIZE / 2) - (DIRT_SIZE / 5);

    private FilledOval broom;
    private FilledRect dirt;
    private Location dirtStart = new Location(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);

    public void sweep() {

        int broomX = broom.getX() + BROOM_SIZE / 2;
        int broomY = broom.getY() + BROOM_SIZE / 2;
        int dirtX = dirt.getX() + DIRT_SIZE / 2;
        int dirtY = dirt.getY() + DIRT_SIZE / 2;


        if (broomX > dirtX && broomY < dirtY + OFFSET && broomY > dirtY - OFFSET) {
            dirt.move(-1, 0);
        } else if (broomY > dirtY && broomX < dirtX + OFFSET && broomX > dirtX - OFFSET) {
            dirt.move(0, -1);
        } else if (broomY < dirtY && broomX < dirtX + OFFSET && broomX > dirtX - OFFSET) {
            dirt.move(0, 1);
        } else if(broomX<dirtX && broomY < dirtY +OFFSET &&broomY >dirtY -OFFSET) {
            dirt.move(1,0);
        } else if (broomX > dirtX && broomY < dirtY) {
            dirt.move(-1, 1);
        } else if (broomY > dirtY && broomX < dirtX) {
            dirt.move(1, -1);
        } else if (broomY < dirtY && broomX < dirtX) {
            dirt.move(1, 1);
        } else {
            dirt.move(-1,-1);
        }
    }

    public void begin(){

        Location broomStart = new Location(CANVAS_WIDTH / 3, CANVAS_HEIGHT / 3);

        dirt = new FilledRect(dirtStart, DIRT_SIZE, DIRT_SIZE, canvas);
        broom = new FilledOval(broomStart, BROOM_SIZE, BROOM_SIZE, canvas);
        broom.hide();
    }

    public void onMouseDrag(Location point){

        int broomCenter = BROOM_SIZE / 2;

        broom.moveTo(point.getX() - broomCenter, point.getY() - broomCenter);
        broom.show();
        while (broom.overlaps(dirt))
            sweep();
    }

    public void onMouseRelease(Location point){

        broom.hide();
        dirt.moveTo(dirtStart);
    }

    public static void main(String args[]){

        new Broomball().startController(CANVAS_WIDTH, CANVAS_HEIGHT);
    }
}
