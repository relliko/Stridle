package rellikolbaid.stridle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    /**TextView textView = (TextView)this.findViewById(R.id.points);
    Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                int points = 0; // set initial points value. change to load saved val later
                final String message = Integer.toString(points);
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(message);
                    }
                });
            }
        }

    }; */

}