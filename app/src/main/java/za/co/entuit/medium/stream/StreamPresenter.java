package za.co.entuit.medium.stream;

import android.media.MediaPlayer;
import android.util.Log;

/**
 * Created by RVukela on 2017/04/14.
 */

public class StreamPresenter implements StreamContract.UserActionsListener {


    private static final String LOG_TAG = StreamPresenter.class.getCanonicalName();
    private StreamContract.View view;
    private StreamService streamService;
    private MediaPlayer.OnBufferingUpdateListener bufferingUpdateListener;
    private MediaPlayer.OnErrorListener errorListener;

    public StreamPresenter(StreamContract.View view) {
        this.view = view;
        initListeners();
        streamService = new StreamServiceImpl(errorListener,bufferingUpdateListener);
    }
    @Override
    public void play(String streamUrl) {
        view.showProgressIndicator(true, 0);
        streamService.playStream(streamUrl,new StreamService.StartStreamCallback() {

            @Override
            public void onStreamStarted() {
                view.showProgressIndicator(false, 100);
                view.notifyStreamStarted();
            }

            @Override
            public void onError() {
                view.showProgressIndicator(false, -1);
                view.showErrorMessage("Unable to connect to stream");
            }
        });

    }

    @Override
    public void stop() {
        streamService.stopStream(new StreamService.StopStreamCallback() {
            @Override
            public void onStreamStopped() {
                view.notifyStreamStopped();
            }

            @Override
            public void onError() {
                Log.e(LOG_TAG, "Error stopping stream", new Throwable());
            }
        });
    }

    @Override
    public void shutdown() {

    }

    private void initListeners(){
        bufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {

              /*  if(percent ==100){
                    view.showProgressIndicator(false, percent);
                }*/
            }
        };
        errorListener = new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
               if(what == MediaPlayer.MEDIA_ERROR_SERVER_DIED || what == MediaPlayer.MEDIA_ERROR_IO
                       || what == MediaPlayer.MEDIA_ERROR_TIMED_OUT){
                   view.showErrorMessage("Error playing stream");
                   return true;
               }

             return false;
            }
        };

    }


}
