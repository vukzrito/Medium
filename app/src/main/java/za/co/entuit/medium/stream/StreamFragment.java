package za.co.entuit.medium.stream;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;

import za.co.entuit.medium.MainActivity;
import za.co.entuit.medium.R;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by RVukela on 2017/04/14.
 */

public class StreamFragment extends Fragment implements StreamContract.View, PreferenceChangeListener {


    private StreamContract.UserActionsListener userActionsListener;
    private NotificationManager notificationManager;
    private static final int STREAM_NOTIFICATION_ID =0;
    private ImageButton btnPlay;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stream, container, false);
        progressBar = (ProgressBar) rootView.findViewById(R.id.stream_progress_indicator);
        btnPlay = (ImageButton) rootView.findViewById(R.id.btn_start_stream);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userActionsListener.play(getPreferredStationUrl());
            }
        });
        userActionsListener = new StreamPresenter(this);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        userActionsListener.play(getPreferredStationUrl());

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        userActionsListener.stop();
        notifyStreamStopped();
    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(getView(), message,Snackbar.LENGTH_INDEFINITE).setAction("RETRY", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userActionsListener.play(getPreferredStationUrl());
            }
        }).show();
    }

    @Override
    public void notifyStreamStarted() {
        initNotificationBar();
        Toast.makeText(getContext(), "Stream started", Toast.LENGTH_LONG).show();
        btnPlay.setImageResource(R.drawable.ic_pause_circle_outline_white_48dp);
    }

    @Override
    public void notifyStreamStopped() {
        clearStreamNotifications();
    }

    @Override
    public void clearStreamNotifications() {
        if(notificationManager!=null){
            notificationManager.cancel(STREAM_NOTIFICATION_ID);
        }
    }

    @Override
    public void showProgressIndicator(boolean active, int percentage) {
        if(active){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

    void initNotificationBar(){
        Intent notIntent = new Intent(getContext(), MainActivity.class);
        notIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),0,
                notIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(getContext());
        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.logo)
                .setOngoing(true)
                .setContentTitle("Medium")
                .setContentText("Now playing");

        Notification notification = builder.build();
        notificationManager = (NotificationManager)getActivity()
                .getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(STREAM_NOTIFICATION_ID, notification);
    }

    @Override
    public void preferenceChange(PreferenceChangeEvent evt) {
        if(evt.getKey() =="preferred_stations_list" ){
            userActionsListener.play(evt.getNewValue());
        }
    }

    private String getPreferredStationUrl(){
        SharedPreferences sp = getActivity().getPreferences(MODE_PRIVATE);
        String station = sp.getString("preferred_stations_list",getString(R.string.pref_default_station_url) );
        return station;

    }
}
