package tech.frits.scorecounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int team1;
    int team2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void IncreaseScoreClick(View view) {
        switch (view.getId()) {
            case R.id.t1_button_plus_1:
                team1++;
                this.UpdateScores();
                break;
            case R.id.t1_button_plus_2:
                team1+=2;
                this.UpdateScores();
                break;
            case R.id.t1_button_plus_3:
                team1+=3;
                this.UpdateScores();
                break;
            case R.id.t2_button_plus_1:
                team2++;
                this.UpdateScores();
                break;
            case R.id.t2_button_plus_2:
                team2+=2;
                this.UpdateScores();
                break;
            case R.id.t2_button_plus_3:
                team2+=3;
                this.UpdateScores();
                break;
        }
    }

    public void ResetScores(View view) {
        team1 = 0;
        team2 = 0;

        this.UpdateScores();
    }

    public void UpdateScores() {
        TextView team1_text_view = (TextView) findViewById(R.id.score_team1_text_view);
        TextView team2_text_view = (TextView) findViewById(R.id.score_team_2_text_view);

        team1_text_view.setText("" + team1);
        team2_text_view.setText("" + team2);
    }
}
