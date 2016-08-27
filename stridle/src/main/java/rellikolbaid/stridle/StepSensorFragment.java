package rellikolbaid.stridle;

import android.app.Activity;
import android.app.Fragment;
import android.hardware.Sensor;
import android.hardware.SensorManager;

/**
 * Created by Rellikolbaid on 8/26/2016.
 */

// TODO: Figure this out
public class StepSensorFragment extends Fragment {
    public static final int STEP_COUNTER = 1; // The sensors are referenced by integers I think
    // Steps counted
    private int nSteps = 0;

    private void registerStepCounter() {
        SensorManager sensorManager =
                (SensorManager) getActivity().getSystemService(Activity.SENSOR_SERVICE);

        Sensor sensor = sensorManager.getDefaultSensor(STEP_COUNTER);


    }

}
