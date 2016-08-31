package rellikolbaid.stridle;

//TODO: Reward for hitting a certain number of steps in a day as part of the mechanics.
//TODO: Restructure EXP and levels. It's a mess.
/**
 * SPAGHETTI CODE AHOY!
 */
public class GameCore {

    private static final float EXP_CONSTANT = 0.2F;

    private static int lifetimeSteps = 0;
    private static int sessionSteps = 0;
    private static long points = 0L; // This is the number the player sees on the main screen.
    private static int experience = 0;
    private static int level = 1;

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


    /**
     * Returns decimal level value (i.e. 2.65) and is called in onCreate of the MainActivity
     * in order to calculate the integer level and use the end of the float for the exp bar.
     * @return
     */
    public static double calculateLevel() {
        return (EXP_CONSTANT * Math.sqrt(experience));
    }

    /**
     * Returns percentage towards the next level.
     * @return
     */
    public static int percentIntoLevel() {
        // Calculate level using saved exp value because level is not saved locally.
        double level = GameCore.calculateLevel();
        // Rounds down the level to an integer and sets it in the GameCore.
        GameCore.setLevel(level);
        // Cuts off the decimal at the end for subtraction on next line.
        int iPart = (int) level;
        // Leaves only the decimal on the original number.
        double fPart = level - iPart;
        // Ensures progress is a whole integer.
        return (int) (fPart * 100);
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

    /**
     * Floors the calculated level from calculateLevel() and converts it to an int before storing
     * it in the list of member variables in the GameCore.
     * //TODO: Simply combine with calculateLevel() and simplify MainActivity?
     * @param lvl
     */
    public static void setLevel(double lvl) {
        level = (int) Math.floor(lvl) + 1;
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

    public static int getLevel() {
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
