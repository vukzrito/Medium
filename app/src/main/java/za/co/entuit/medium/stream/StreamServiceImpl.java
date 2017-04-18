package za.co.entuit.medium.stream;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

import static android.media.MediaPlayer.*;


public class StreamServiceImpl implements StreamService, OnPreparedListener{
    private MediaPlayer mediaPlayer = null;
    private  OnErrorListener errorListener;
    private OnBufferingUpdateListener bufferingUpdateListener;
    private StartStreamCallback startStreamCallback;

    public StreamServiceImpl(OnErrorListener errorListener, OnBufferingUpdateListener bufferingUpdateListener){
        this.errorListener = errorListener;
        this.bufferingUpdateListener = bufferingUpdateListener;
    }

    private void shutdown(){
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    private void  init(String url){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnBufferingUpdateListener(bufferingUpdateListener);
        mediaPlayer.setOnErrorListener(errorListener);
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
        startStreamCallback.onStreamStarted();
    }

    @Override
    public void playStream(String streamUrl, StartStreamCallback callback) {
        if(mediaPlayer == null)
            init(streamUrl);

        this.startStreamCallback = callback;
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }


    }

    @Override
    public void stopStream(StopStreamCallback callback) {
       shutdown();
        callback.onStreamStopped();
    }


}
