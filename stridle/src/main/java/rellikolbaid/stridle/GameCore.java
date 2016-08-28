package rellikolbaid.stridle;

/**
 * SPAGHETTI CODE AHOY!
 */
public class GameCore {
    private static int lifetimeSteps = -1; //TODO: save/load this
    private static int sessionSteps = -1;
    private static int points = 0;

    /**
     * Core points calculation method.
     */
    private void pointsCalc() {
        points = lifetimeSteps + (sessionSteps * (1/10));
    }

    public void addSteps() {
        sessionSteps += 1;
        lifetimeSteps += 1;
        pointsCalc();
    }

    public void addPoints(int numPoints) {
        points += numPoints;
    }

    public int getPoints() {
        return points;
    }

    public String getPointsString() {
        return Integer.toString(points);
    }

    public int getSessionSteps() {
        return sessionSteps;
    }

    public void reset() {
        lifetimeSteps = 0;
        sessionSteps = 0;
        points = 0;
    }
}
