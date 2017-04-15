package za.co.entuit.medium.stream;

/**
 * Created by RVukela on 2017/04/14.
 */

public  interface StreamContract {
    interface View{
        void showErrorMessage(String message);
        void notifyStreamStarted();
        void notifyStreamStopped();
        void clearStreamNotifications();
        void showProgressIndicator(boolean active, int percentage);

    }
    interface UserActionsListener{
        void play();
        void stop();
        void shutdown();
    }
}
