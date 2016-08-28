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

    private static GameCore gameCore = new GameCore();

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Bundle has to do with save states.
        super.onCreate(savedInstanceState); // Superclass always needs to be called. idk why tho
        // Set the user interface layout for the main activity.
        setContentView(R.layout.activity_main);

        // Display the activity points
        pointsView = (TextView) this.findViewById(R.id.points);
        pointsView.setText("Activity Points: " + gameCore.getPoints());

        // Sensor setup
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mStepDetectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        mSensorManager.registerListener(this, mStepDetectorSensor,
                SensorManager.SENSOR_DELAY_GAME);
    }

    public void onSensorChanged(SensorEvent event) {
        gameCore.addSteps(); // Sends signal to GameCore class to add +1 to steps counter

        // Gets points value from GameCore and displays the number on the main screen
        pointsView.setText("Activity Points: " + gameCore.getPoints()); //TODO: Use getString instead
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
        Intent intent = new Intent (this, UpgradesActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        /**
         * Called at any time that the activity is in the foreground.
         */
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        /**
         * When this is called the activity is no longer visible and should release almost all
         * resources that aren't needed while the user is not using it. Intensive shut-down
         * operations should be performed here.
         */
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