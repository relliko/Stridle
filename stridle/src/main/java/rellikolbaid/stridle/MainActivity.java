package rellikolbaid.stridle;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    Context mContext; // Used to quickly specify context in each part of the activity lifecycle.

    private SensorManager mSensorManager;
    private Sensor mStepDetectorSensor;

    private TextView textView; // Member variable for viewing points value in the layout

    // Exp bar member variables
    private int progress = 0;
    private Handler handler = new Handler();
    private ProgressBar expBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Bundle has to do with save states.
        super.onCreate(savedInstanceState); // Superclass always needs to be called. idk why tho
        mContext = this; // muh context
        // Set the user interface layout for the main activity.
        setContentView(R.layout.activity_main);

        // Sensor setup
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mStepDetectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        mSensorManager.registerListener(this, mStepDetectorSensor,
                SensorManager.SENSOR_DELAY_FASTEST);

        // Load saved stats
        KeyValueDB.loadStats(mContext);

        // Initial calculation and setting of level.
        double level = GameCore.calculateLevel();
        GameCore.setLevel((int) Math.floor(level));
    }

    /**
     * For the step detector sensor.
     * @param event
     */
    public void onSensorChanged(SensorEvent event) {
        GameCore.addSteps(); // Sends signal to GameCore class to add +1 to steps counter
        GameCore.pointsCalc();

        // Gets points value from GameCore and displays the number on the main screen
        //TODO: Use getString instead and learn to do a UI thread
        textView = (TextView) this.findViewById(R.id.points);
        textView.setText("Activity Points: " + GameCore.getInstance().getPointsString());

        textView = (TextView) this.findViewById(R.id.levelView);
        textView.setText("Level: " + GameCore.getInstance().getLevel());

        // Exp bar
        expBar = (ProgressBar)this.findViewById(R.id.expBar);
        handler.post(new Runnable() {
            public void run() {
                // Calculate level using saved exp value because level is not saved locally.
                double level = GameCore.calculateLevel();
                // Rounds down the level to an integer and sets it in the GameCore.
                GameCore.setLevel(level);
                // Cuts off the decimal at the end for subtraction on next line.
                int iPart = (int) level;
                // Leaves only the decimal on the original number.
                double fPart = level - iPart;
                // Ensures progress is a whole integer.
                progress = (int) (fPart * 100);
                expBar.setProgress(progress);
            }
        });
    }

    // The interface required this to be here but I left it blank :^)
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    /**
     * Code for opening the activities for the buttons
     */
    public void openSettingsActivity(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);

    }

    public void openUpgradesActivity(View view) {
        Intent intent = new Intent(this, UpgradesActivity.class);
        startActivity(intent);
    }

    public void openStatsActivity(View view) {
        Intent intent = new Intent (this, StatsActivity.class);
        startActivity(intent);
    }

    /**
     * Called at any time that the activity is in the foreground.
     */
    @Override
    protected void onResume() {
        super.onResume();
        //TODO: Figure out how to make a UI thread and put this shit there, or better yet use strings.xml
        // Setup of initial display of the varying TextViews.
        textView = (TextView) this.findViewById(R.id.points);
        textView.setText("Activity Points: " + GameCore.getInstance().getPointsString());
        textView = (TextView) this.findViewById(R.id.levelView);
        textView.setText("Level: " + GameCore.getInstance().getLevel());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mContext = this;

        // Save stats
        KeyValueDB.saveStats(mContext);
    }

    /**
     * When this is called the activity is no longer visible and should release almost all
     * resources that aren't needed while the user is not using it. Intensive shut-down
     * operations should be performed here.
     */
    @Override
    protected void onStop() {
        super.onStop();
        mContext = this;

        // Save stats
        KeyValueDB.saveStats(mContext);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContext = this;

        mSensorManager.unregisterListener(this, mStepDetectorSensor);
        // This makes sure the app kills all threads created by the app when it is destroyed.
        android.os.Debug.stopMethodTracing();

        // Save stats
        KeyValueDB.saveStats(mContext);
    }
}