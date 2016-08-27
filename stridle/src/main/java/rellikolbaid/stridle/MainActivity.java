package rellikolbaid.stridle;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView pointsView; // Member variable for viewing points value in the layout
    static int points = 1234; // Defines the total number of points. TODO: replace with actual counted steps
    @Override
    protected void onCreate(Bundle savedInstanceState) { // Bundle has to do with save states.
        super.onCreate(savedInstanceState); // Superclass always needs to be called. idk why tho

        // Set the user interface layout for the main activity.
        setContentView(R.layout.activity_main);

        pointsView = (TextView)this.findViewById(R.id.points);

        // Converts points variable from int to string for display
        final String message = Integer.toString(points);

        // Sets text of the points acquired on the main page of the app
        // TODO: load saved points
        pointsView.setText(message);

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                points += 1;
                                System.out.println(points);
                                pointsView.setText(message);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();

    }

    protected void onStart() {
        /**
         * Every time the activity becomes visible this is called.
         */
        super.onStart();
    }

    protected void onResume() {
        /**
         * Called at any time that the activity is in the foreground.
         */
        super.onResume();
    }

    protected void onPause() {
        /**
         * This is when the activity is still partially visible but the user may be leaving it
         * like when they're opening a menu over it. Save anything that must be autosaved etc
         */
        super.onPause();
    }

    protected void onStop() {
        /**
         * When this is called the activity is no longer visible and should release almost all
         * resources that aren't needed while the user is not using it. Intensive shut-down
         * operations should be performed here.
         */
        super.onStop();
    }

    protected void onRestart() {
        super.onRestart();
        /**
         * Called if the user returns while the activity is stopped. It is followed by onStart()
         * and onResume().
         */
    }

    protected void onDestroy() {
        super.onDestroy();

        // This makes sure the app kills all threads created by the app when it is destroyed.
        // We need this since we're going to have background threading going.
        android.os.Debug.stopMethodTracing();
    }

}