package za.co.entuit.medium.stream;

import android.util.Log;

/**
 * Created by RVukela on 2017/04/14.
 */

public class StreamPresenter implements StreamContract.UserActionsListener {


    private static final String LOG_TAG = StreamPresenter.class.getCanonicalName();
    private StreamContract.View view;
    private StreamService streamService;

    public StreamPresenter(StreamContract.View view) {
        this.view = view;
        streamService = new StreamServiceImpl();
    }
    @Override
    public void play() {
        streamService.playStream(new StreamService.StartStreamCallback() {
            @Override
            public void onStreamStarted() {
                view.notifyStreamStarted();
            }

            @Override
            public void onError() {
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


}
