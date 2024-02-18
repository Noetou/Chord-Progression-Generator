import android.content.Context
import android.media.MediaPlayer

class AudioPlayerForMediaPlayer(private val context: Context) : AudioPlayer {
    private var mediaPlayer: MediaPlayer? = null

    override fun play(audioResourceId: Int) {
        stop()
        mediaPlayer = MediaPlayer.create(context, audioResourceId)
        mediaPlayer?.start()
    }

    override fun stop() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
        }
        mediaPlayer?.release()
    }
}