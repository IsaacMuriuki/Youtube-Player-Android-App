package personal.project.youtubeplayer;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final String TAG = "YoutubeActivity";
    static final String GOOGLE_API_KEY = ""; //insert your google API key
    static final String YOUTUBE_VIDEO_ID = ""; // insert your youtube video ID that you want to play
    static final String YOUTUBE_PLAYLIST = ""; // insert your youtube playlist ID that you want to play

    // Initialize the play window for the YouTube videos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube, null);
        setContentView(layout);

        YouTubePlayerView playerView = new YouTubePlayerView(this);
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(playerView);
        playerView.initialize(GOOGLE_API_KEY, this);
    }

    // When initialization is a success, then play the video
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.d(TAG, "onInitializationSuccess: provider is " + provider.getClass().toString());
        Toast.makeText(this, "Initialized Youtube player successively", Toast.LENGTH_LONG).show();

        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        if (!wasRestored){
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }
    }

    // When initialization fails display error message
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE = 1;

        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        }   else {
            String errorMessage = String.format("There was an error initializing the YoutubePlayer (%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        // Displays a pop-up message when the video is set to play
        public void onPlaying() {
            Toast.makeText(YoutubeActivity.this, "Video is playing ok", Toast.LENGTH_LONG).show();
        }

        @Override
        // Displays a pop-up message when the video is set to pause
        public void onPaused() {
            Toast.makeText(YoutubeActivity.this, "Video has paused", Toast.LENGTH_LONG).show();
        }

        @Override
        // Displays a pop-up message when the video is set to play
        public void onStopped() {
            Toast.makeText(YoutubeActivity.this, "Video has stopped", Toast.LENGTH_LONG).show();
        }

        @Override
        // Displays a pop-up message when the video is buffering
        public void onBuffering(boolean b) {
            Toast.makeText(YoutubeActivity.this, "Video is buffering", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        // Displays a pop-up message when an ad has started
        public void onAdStarted() {
            Toast.makeText(YoutubeActivity.this, "Ad has begun", Toast.LENGTH_LONG).show();
        }

        @Override
        // Displays a pop-up message when the video starts
        public void onVideoStarted() {
            Toast.makeText(YoutubeActivity.this, "Video has started", Toast.LENGTH_LONG).show();
        }

        @Override
        // Displays a pop-up message when the video ends
        public void onVideoEnded() {
            Toast.makeText(YoutubeActivity.this, "You have completed the video !", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };
}