package za.co.entuit.medium.stream;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

/**
 * Created by RVukela on 2017/04/14.
 */

public class StreamService  implements MediaPlayer.OnPreparedListener{
    private static final String ACTION_PLAY = "com.example.action.PLAY";
    MediaPlayer mediaPlayer = null;
    String url = "http://capeant.antfarm.co.za:8000/yarona";



    public void play(){
        mediaPlayer.start();
    }

    public void stop(){
        mediaPlayer.stop();
    }

    public void shutdown(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    public void  init(){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync(); // prepare async to not block main thread
        } catch (IOException e) {
            Log.e("Stream Error","Error starting stream!!!",e );
        }

        mediaPlayer.setOnPreparedListener(this);
    }


    /** Called when MediaPlayer is ready */
    public void onPrepared(MediaPlayer player) {
        player.start();
    }

}
