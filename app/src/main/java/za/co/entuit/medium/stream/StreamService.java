package za.co.entuit.medium.stream;

/**
 * Created by RVukela on 2017/04/14.
 */

public interface StreamService {
    interface StartStreamCallback {
        void onStreamStarted();
        void onError();
    }

    interface StopStreamCallback{
        void onStreamStopped();
        void onError();
    }

    void playStream(String streamUrl, StartStreamCallback callback);
    void stopStream(StopStreamCallback callback);
}
