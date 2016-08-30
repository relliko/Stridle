package rellikolbaid.stridle;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity {
    TextView textView;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
    }

    protected void onResume() {
        super.onResume();
        updateStatsView();

    }

    public void resetStats(View view) {
        mContext = this;
        GameCore.getInstance().reset();
        updateStatsView();
        KeyValueDB.wipeStats(mContext);
    }

    public void updateStatsView() {
        textView = (TextView) this.findViewById(R.id.points);
        textView.setText("Activity Points: " + GameCore.getInstance().getPointsString());
        textView = (TextView) this.findViewById(R.id.lifetimeSteps);
        textView.setText("Lifetime Steps: " + GameCore.getInstance().getLifetimeStepsString());
        textView = (TextView) this.findViewById(R.id.sessionSteps);
        textView.setText("Session Steps: " + GameCore.getInstance().getSessionStepsString());
    }
}
