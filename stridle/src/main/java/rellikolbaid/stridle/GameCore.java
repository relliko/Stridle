package rellikolbaid.stridle;

//TODO: Reward for hitting a certain number of steps in a day as part of the mechanics.
/**
 * SPAGHETTI CODE AHOY!
 */
public class GameCore {

    private static int lifetimeSteps = 0;
    private static int sessionSteps = 0;
    private static long points = 0L; // This is the number the player sees on the main screen.
    private static int experience = 0;
    private static short level = 1;

    /**
     * The following two lines allow stats to be collected by other classes.
     * This technique allows there to only ever be one instance of this class
     * in the virtual machine. This type of class is called a singleton.
     *
     */
    private static final GameCore core = new GameCore();

    public static GameCore getInstance() {
        return core;
    }

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
        addExperience(1);
    }

    public static void addExperience(int exp) {
        experience += exp;
    }


    public static void calculateLevel() {
        level = (short) Math.floor(0.2 * Math.sqrt(experience));
    }

    public static void reset() {
        lifetimeSteps = 0;
        sessionSteps = 0;
        points = 0;
        experience = 0;
        level = 1;
    }

    public static void addPoints(int numPoints) {
        points += numPoints;
    }

    public static void setLifetimeSteps(int numSteps) {
        lifetimeSteps = numSteps;
    }

    public static void setPoints(long numPoints) {
        points = numPoints;
    }

    public static void setExperience(int exp) {
        experience = exp;
    }

    public static long getPoints() {
        return points;
    }

    public static String getPointsString() {
        return Long.toString(points);
    }

    public static int getLifetimeSteps() {
        return lifetimeSteps;
    }

    public static int getSessionSteps() {
        return sessionSteps;
    }

    public static short getLevel() {
        return level;
    }

    public static int getExperience() {
        return experience;
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
