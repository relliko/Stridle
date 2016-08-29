package rellikolbaid.stridle;

//TODO: Reward for hitting a certain number of steps in a day as part of the mechanics.
/**
 * SPAGHETTI CODE AHOY!
 */
public class GameCore {

    private static int lifetimeSteps = 0;
    private static int sessionSteps = 0;
    private static float points = 0; // This is the number the player sees on the main screen.

    /**
     * The following two lines allow stats to be collected by other classes.
     * This technique allows there to only ever be one instance of this class
     * in the virtual machine. This type of class is called a singleton.
     *
     */
    private static final GameCore core = new GameCore();
    public static GameCore getInstance() {return core;}

    /**
     * Core points calculation method.
     * //TODO: Design a formula for points generation.
     */
    public static void pointsCalc() {
        points = lifetimeSteps;
    }

    public static void addSteps() {
        sessionSteps += 1;
        lifetimeSteps += 1;
    }

    public static void reset() {
        lifetimeSteps = 0;
        sessionSteps = 0;
        points = 0;
    }

    public static void addPoints(int numPoints) {
        points += numPoints;
    }

    public static void setLifetimeSteps(int numSteps) {
        lifetimeSteps = numSteps;
    }

    public static void setPoints(Float numPoints) {
        points = numPoints;
    }

    public static float getPoints() {
        return points;
    }

    public static String getPointsString() {
        return Float.toString(points);
    }

    public static int getLifetimeSteps() {
        return lifetimeSteps;
    }

    public static int getSessionSteps() {
        return sessionSteps;
    }

    public static String getSessionStepsString() {
        String steps = Integer.toString(sessionSteps);
        return steps;
    }

    public static String getLifetimeStepsString() {
        String steps = Integer.toString(lifetimeSteps);
        return steps;
    }
}
