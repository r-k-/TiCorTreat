package org.healing.ticortreat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import static android.os.Build.VERSION_CODES.KITKAT;

public class MainActivity extends Activity {

    int REQUEST_CODE = 42;
    Context context = this;
    Uri uri = null;
    TextView audioTrackPath;
    TextView positionLabel;
    Button startButton;
    ImageButton stepStartButton;
    MediaPlayer mediaPlayer;

    class CustomHandler extends Handler {

        private boolean completed = false;
        public boolean Completed() { return completed; }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int id = msg.getData().getInt("Id");
            switch (id) {
                case 1:
                    positionLabel.setText(R.string.position_1);
                    stepStartButton.setImageResource(R.mipmap.position_1);
                    break;

                case 2:
                    positionLabel.setText(R.string.position_2);
                    stepStartButton.setImageResource(R.mipmap.position_2);
                    break;

                case 3:
                    positionLabel.setText(R.string.position_3);
                    stepStartButton.setImageResource(R.mipmap.position_3);
                    break;

                case 4:
                    positionLabel.setText(R.string.position_4);
                    stepStartButton.setImageResource(R.mipmap.position_4);
                    break;

                case 5:
                    positionLabel.setText(R.string.position_5);
                    stepStartButton.setImageResource(R.mipmap.position_5);
                    break;

                case 6:
                    positionLabel.setText(R.string.position_6);
                    stepStartButton.setImageResource(R.mipmap.position_6);
                    break;

                case 7:
                    positionLabel.setText(R.string.position_7);
                    stepStartButton.setImageResource(R.mipmap.position_7);
                    break;

                case 8:
                    positionLabel.setText(R.string.position_8);
                    stepStartButton.setImageResource(R.mipmap.position_8);
                    break;

                case 9:
                    positionLabel.setText(R.string.position_9);
                    stepStartButton.setImageResource(R.mipmap.position_9);
                    break;

                case 10:
                    positionLabel.setText(R.string.position_10);
                    stepStartButton.setImageResource(R.mipmap.position_10);
                    break;

                case 11:
                    positionLabel.setText(R.string.position_11);
                    stepStartButton.setImageResource(R.mipmap.position_11);
                    break;

                case 12:
                    positionLabel.setText(R.string.position_12);
                    stepStartButton.setImageResource(R.mipmap.position_12);
                    break;

                case 13:
                    positionLabel.setText(R.string.position_13);
                    stepStartButton.setImageResource(R.mipmap.position_13);
                    break;

                case 14:
                    positionLabel.setText(R.string.position_14);
                    stepStartButton.setImageResource(R.mipmap.position_14);
                    break;

                case 15:
                    positionLabel.setText(R.string.position_15);
                    stepStartButton.setImageResource(R.mipmap.position_15);
                    break;

                case 16:
                    positionLabel.setText(R.string.position_16);
                    stepStartButton.setImageResource(R.mipmap.position_16);
                    break;

                case 17:
                    positionLabel.setText(R.string.position_17);
                    stepStartButton.setImageResource(R.mipmap.position_17);
                    break;

                case 18:
                    positionLabel.setText(R.string.position_18);
                    stepStartButton.setImageResource(R.mipmap.position_18);
                    break;

                case 19:
                    positionLabel.setText(R.string.position_19);
                    stepStartButton.setImageResource(R.mipmap.position_19);
                    break;

                case 20:
                    positionLabel.setText(R.string.position_20);
                    stepStartButton.setImageResource(R.mipmap.position_20);
                    break;

                case 21:
                    positionLabel.setText(R.string.position_21);
                    stepStartButton.setImageResource(R.mipmap.position_21);
                    break;

                case 22:
                    positionLabel.setText(R.string.position_22);
                    stepStartButton.setImageResource(R.mipmap.position_22);
                    break;

                case 23:
                    positionLabel.setText(R.string.position_23);
                    stepStartButton.setImageResource(R.mipmap.position_23);
                    break;

            }
            new Thread(RingBell.getInstance()).start();

            completed = (id==24);
            if (id < 24) {
                Message m = new Message();
                m.setData(RingBell.getInstance().getRingBundle());
                this.sendMessageDelayed(m, 180000);
            } else if (id == 24) {
                positionLabel.setText(R.string.end_text);
                stepStartButton.setImageResource(R.mipmap.ic_launcher);
                stepStartButton.setEnabled(true);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE && data != null) {
                uri = data.getData();
                audioTrackPath.setText(uri.getPath());
                startButton.setEnabled(true);

                try {
                    mediaPlayer.setDataSource(context, uri);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    Log.e("OnActivityResult", "Media source failure.");
                }
                RingBell.getInstance().setupRingBell(context);
                Toast.makeText(this, "Audio file " + uri.getPath(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer = new MediaPlayer();
        RingBell.getInstance().setupRingBell(context);

        final CustomHandler handler = new CustomHandler();

        setContentView(R.layout.activity_main);

        audioTrackPath = (TextView) findViewById(R.id.audioFileTextView);
        positionLabel = (TextView) findViewById(R.id.PositionTextView);

        Button aFileButton = (Button) findViewById(R.id.audioTrackbutton);
        aFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                Intent intent = null;
                if (android.os.Build.VERSION.SDK_INT >= KITKAT)
                    intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                if (intent != null) {
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("audio/*");
                    startActivityForResult(intent, REQUEST_CODE);
                }
            }
        });

        stepStartButton = (ImageButton) findViewById(R.id.stepStartButton);
        stepStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (handler.Completed()) {
                    if (mediaPlayer != null && mediaPlayer.isPlaying())
                        mediaPlayer.stop();
                    stepStartButton.setImageResource(R.mipmap.ic_launcher);
                    positionLabel.setText(R.string.centering_then_contact_with_the_receiver);
                    stepStartButton.setEnabled(false);
                } else {
                    stepStartButton.setEnabled(false);
                    startButton.setEnabled(false);

                    Message m = new Message();
                    m.setData(RingBell.getInstance().getRingBundle());
                    handler.sendMessageDelayed(m, 100);
                }
            }
        });

        startButton = (Button) findViewById(R.id.treatmentStartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (uri != null) {
                    stepStartButton.setVisibility(View.VISIBLE);
                    stepStartButton.setEnabled(true);
                    startButton.setEnabled(false);

                    mediaPlayer = MediaPlayer.create(context, uri);
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.release();
                        }
                    });
                    mediaPlayer.setLooping(false);
                }
            }
        });
        startButton.setEnabled(false);
    }

}
