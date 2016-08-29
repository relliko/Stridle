package rellikolbaid.stridle;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mStepDetectorSensor;

    private TextView pointsView; // Member variable for viewing points value in the layout

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Bundle has to do with save states.
        super.onCreate(savedInstanceState); // Superclass always needs to be called. idk why tho
        // Set the user interface layout for the main activity.
        setContentView(R.layout.activity_main);

        // Sensor setup
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mStepDetectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        mSensorManager.registerListener(this, mStepDetectorSensor,
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    /**
     * For the step detector sensor.
     * @param event
     */
    public void onSensorChanged(SensorEvent event) {
        GameCore.addSteps(); // Sends signal to GameCore class to add +1 to steps counter
        GameCore.pointsCalc();

        // Gets points value from GameCore and displays the number on the main screen
        //TODO: Use getString instead
        pointsView.setText("Activity Points: " + GameCore.getInstance().getPointsString());
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
        // Display the activity points whenever the activity is in foreground.
        pointsView = (TextView) this.findViewById(R.id.points);
        pointsView.setText("Activity Points: " + GameCore.getInstance().getPointsString());
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * When this is called the activity is no longer visible and should release almost all
     * resources that aren't needed while the user is not using it. Intensive shut-down
     * operations should be performed here.
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mSensorManager.unregisterListener(this, mStepDetectorSensor);
        // This makes sure the app kills all threads created by the app when it is destroyed.
        android.os.Debug.stopMethodTracing();
    }
}