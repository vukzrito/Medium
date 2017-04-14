package za.co.entuit.medium.stream;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

import za.co.entuit.medium.MainActivity;
import za.co.entuit.medium.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by RVukela on 2017/04/14.
 */

public class StreamFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stream, container, false);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        String url = "http://capeant.antfarm.co.za:8000/yarona"; // your URL here
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });// might take long! (for buffering, etc)
        } catch (IOException e) {
            Log.e("Stream Error","Error starting stream!!!",e );
        }

        mediaPlayer.start();
        initNotificationBar();


    }

    void initNotificationBar(){
        Intent notIntent = new Intent(getContext(), MainActivity.class);
        notIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),0,
                notIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(getContext());
        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setOngoing(true)
                .setContentTitle("Medium")
                .setContentText("Now playing");
        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager)getActivity()
                .getSystemService(NOTIFICATION_SERVICE);


        notificationManager.notify(0, notification);
    }
}
