package rellikolbaid.stridle;

import android.content.Intent;

/**
 * SPAGHETTI CODE AHOY!
 */
public class GameCore {
    private static int lifetimeSteps = -1; //TODO: save/load this
    private static int sessionSteps = -1;
    private static float points = 0;

    /**
     * The following two lines allow stats to be collected by other classes
     */
    private static final GameCore core = new GameCore();
    public static GameCore getInstance() {return core;}

    /**
     * Core points calculation method.
     */
    private void pointsCalc() {
        points = lifetimeSteps + (sessionSteps * 0.1F);
    }

    public void addSteps() {
        sessionSteps += 1;
        lifetimeSteps += 1;
        pointsCalc();
    }

    public void addPoints(int numPoints) {points += numPoints;}

    public float getPoints() {return points;}

    public String getPointsString() {return Float.toString(points);}

    public int getSessionSteps() {return sessionSteps;}

    public void reset() {
        lifetimeSteps = 0;
        sessionSteps = 0;
        points = 0;
    }
}
