package org.healing.ticortreat;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Rings a bell and update counter.
 *
 * Created by r-k- on 03/06/17.
 */

class RingBell implements Runnable    {
    static private RingBell instance = new RingBell();

    private RingBell() {}

    static RingBell getInstance() {
        return instance;
    }

    private MediaPlayer player;
    private Bundle ringBundle = new Bundle();
    Bundle getRingBundle() {
        return ringBundle;
    }

    void setupRingBell(Context context) {
        if (ringBundle.getInt("Id") == 0)
            ringBundle.putInt("Id", 1);
        else if (ringBundle.getInt("Id") > 23)
            ringBundle.putInt("Id", 1);
//        if (mediaPlayer != null)
//            this.player = mediaPlayer;
//        else
            this.player = MediaPlayer.create(context, R.raw.bell);
    }

    @Override
    public void run() {
        int id = ringBundle.getInt("Id");
        id++;
        ringBundle.remove("Id");
        ringBundle.putInt("Id", id);

        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
        player.setLooping(false);

    }
}
