package rellikolbaid.stridle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView pointsView; // Member variable for viewing points in the layout
    static int points = 1234; // Defines the total number of points. TODO: replace with actual counted steps
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Superclass always needs to be called. idk why tho

        // Set the user interface layout for the main activity.
        setContentView(R.layout.activity_main);

        pointsView = (TextView)this.findViewById(R.id.points);

        // Converts points variable from int to string for display
        String message = Integer.toString(points);

        // Sets text of the points acquired on the main page of the app
        // TODO: load saved points
        pointsView.setText(message);

    }

    protected void onStart() {
        super.onStart();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();

        // This makes sure the app kills all threads created by the app when it is destroyed
        android.os.Debug.stopMethodTracing();
    }



}