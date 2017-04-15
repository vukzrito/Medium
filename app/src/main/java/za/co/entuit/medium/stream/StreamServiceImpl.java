package za.co.entuit.medium.stream;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

import static android.media.MediaPlayer.*;

/**
 * Created by RVukela on 2017/04/14.
 */

public class StreamServiceImpl implements StreamService, OnPreparedListener{
    private static final String ACTION_PLAY = "com.example.action.PLAY";
    private MediaPlayer mediaPlayer = null;
    private static final String url = "http://capeant.antfarm.co.za:8000/yarona";
    private  OnErrorListener errorListener;
    private OnBufferingUpdateListener bufferingUpdateListener;
    private  OnCompletionListener completionListener;

    public StreamServiceImpl(OnErrorListener errorListener, OnBufferingUpdateListener bufferingUpdateListener,
                             OnCompletionListener completionListener){
        this.errorListener = errorListener;
        this.bufferingUpdateListener = bufferingUpdateListener;
        this.completionListener = completionListener;
    }

    private void shutdown(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    private void  init(){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnBufferingUpdateListener(bufferingUpdateListener);
        mediaPlayer.setOnErrorListener(errorListener);
        mediaPlayer.setOnCompletionListener(completionListener);
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

    @Override
    public void playStream(StartStreamCallback callback) {
        if(mediaPlayer == null)
            init();

        mediaPlayer.start();
        callback.onStreamStarted();
    }

    @Override
    public void stopStream(StopStreamCallback callback) {
        mediaPlayer.stop();
        callback.onStreamStopped();
    }


}
