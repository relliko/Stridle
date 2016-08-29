package rellikolbaid.stridle;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Rellikolbaid on 8/29/2016.
 */
public class KeyValueDB {
    private SharedPreferences sharedPreferences;

    public KeyValueDB() {
        // Blank
    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences("stats", Context.MODE_PRIVATE);
    }

    public static float getPoints(Context context) {
        return getPrefs(context).getFloat("points", 0); // The zero is returned if points not set.
    }

    public static int getSteps(Context context) {
        return getPrefs(context).getInt("lifetime_steps", 0);
    }

    public static void setPoints(Context context) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        float points = GameCore.getPoints();
        editor.putFloat("points", points);
        editor.apply();
    }

    public static void setSteps(Context context) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        int steps = GameCore.getLifetimeSteps();
        editor.putInt("lifetime_steps", steps);
        editor.apply();
    }

    public static void saveStats(Context context) {
        setSteps(context);
        setPoints(context);
    }

    public static void loadStats(Context context) {
        int steps = getSteps(context);
        float points = getPoints(context);
        GameCore.setLifetimeSteps(steps);
        GameCore.setPoints(points);
    }
}