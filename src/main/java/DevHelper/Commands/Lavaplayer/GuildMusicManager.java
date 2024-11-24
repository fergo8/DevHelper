package DevHelper.Commands.Lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;

import java.nio.ByteBuffer;

public class GuildMusicManager {
    // O player de áudio
    public final AudioPlayer player;
    public final TrackScheduler scheduler;

    public GuildMusicManager(AudioPlayerManager manager){
        player = manager.createPlayer(); // Cria um player de áudio
        scheduler = new TrackScheduler(player); // Cria um escalonador de faixas
        player.addListener(scheduler); // Adiciona o escalonador ao player
    }

    public AudioSendHandler getSendHandler(){
        return new AudioSendHandler() {
            private AudioFrame lastFrame; // O último frame de áudio enviado

            // Método chamado para verificar se é possível fornecer áudio
            @Override
            public boolean canProvide() {
                lastFrame = player.provide();
                return lastFrame != null;
            }

            /**
 * Provides 20 milliseconds of audio data.
  * @return A ByteBuffer containing the audio data.
 */
@Override
public ByteBuffer provide20MsAudio(){
    return ByteBuffer.wrap(lastFrame.getData());
}

/**
 * Indicates whether the provided audio is in Opus format.
 * @return true if the audio is in Opus format, false otherwise.
 */
@Override
public boolean isOpus(){
    return true;
}
        };
    }
}
