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

    public static long loadPoints(Context context) {
        return getPrefs(context).getLong("points", 0); // The zero is returned if points not set.
    }

    public static int loadSteps(Context context) {
        return getPrefs(context).getInt("lifetime_steps", 0);
    }

    public static int loadExperience(Context context) {
        return getPrefs(context).getInt("experience", 0);
    }

    public static void savePoints(Context context) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        long points = GameCore.getPoints();
        editor.putLong("points", points);
        editor.apply();
    }

    public static void saveSteps(Context context) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        int steps = GameCore.getLifetimeSteps();
        editor.putInt("lifetime_steps", steps);
        editor.apply();
    }

    public static void saveExperience(Context context) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        int experience = GameCore.getExperience();
        editor.putInt("experience", experience);
        editor.apply();
    }

    public static void saveStats(Context context) {
        saveSteps(context);
        savePoints(context);
        saveExperience(context);
    }

    public static void loadStats(Context context) {
        int steps = loadSteps(context);
        long points = loadPoints(context);
        int experience = loadExperience(context);
        GameCore.setLifetimeSteps(steps);
        GameCore.setPoints(points);
        GameCore.setExperience(experience);

    }

    public static void wipeStats(Context context) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.clear();
        editor.commit();
    }
}