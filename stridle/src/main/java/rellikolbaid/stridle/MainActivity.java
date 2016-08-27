package rellikolbaid.stridle;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mStepCounterSensor;
    private TextView pointsView; // Member variable for viewing points value in the layout

    static int points = 0; // Defines the total number of points. TODO: replace with actual counted steps

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Bundle has to do with save states.
        super.onCreate(savedInstanceState); // Superclass always needs to be called. idk why tho


        // Set the user interface layout for the main activity.
        setContentView(R.layout.activity_main);

        pointsView = (TextView)this.findViewById(R.id.points);

        // Converts points variable from int to string for display
        String message = Integer.toString(points);

        // Sets text of the points acquired on the main page of the app
        // TODO: load saved points
        pointsView.setText(message);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mStepCounterSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
    }

    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        points += 1;

        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            pointsView.setText("Activity Points: " + points);
        }
    }

    // The interface required this to be here but I left it blank :^)
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    protected void onResume() {
        /**
         * Called at any time that the activity is in the foreground.
         */
        super.onResume();

        mSensorManager.registerListener(this, mStepCounterSensor,
                SensorManager.SENSOR_DELAY_FASTEST);
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

        // This makes sure the app kills all threads created by the app when it is destroyed.
        // We need this since we're going to have background threading going.
        mSensorManager.unregisterListener(this, mStepCounterSensor);
        android.os.Debug.stopMethodTracing();
    }

}