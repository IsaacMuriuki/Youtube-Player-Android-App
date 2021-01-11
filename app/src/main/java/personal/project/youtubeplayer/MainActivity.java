package personal.project.youtubeplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

// Applications' home page
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    // Initialization of the butttons
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSingle = (Button) findViewById(R.id.btnPlaySingle);
        Button btnStandAlone = (Button) findViewById(R.id.btnStandAlone);
        btnSingle.setOnClickListener(this);
        btnStandAlone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        switch(view.getId()){
            // Play the single video
            case R.id.btnPlaySingle:
                intent = new Intent(this, YoutubeActivity.class);
                break;

            case R.id.btnStandAlone:
                // Play the playlist
                intent = new Intent(this, StandaloneActivity.class);
                break;

            default:
        }

        if(intent != null){
            startActivity(intent);
        }
    }
}