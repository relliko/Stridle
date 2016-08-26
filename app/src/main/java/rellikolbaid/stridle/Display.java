package rellikolbaid.stridle;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Rellikolbaid on 8/25/2016.
 */
public class Display extends AppCompatActivity implements Runnable {
    int points = 0;

    @Override
    public void run() {
        setContentView(R.layout.activity_main);
        TextView textView = (TextView)this.findViewById(R.id.points);
        String message = Integer.toString(points);
        while(true) {
            textView.setText(message);
        }

    }

}
