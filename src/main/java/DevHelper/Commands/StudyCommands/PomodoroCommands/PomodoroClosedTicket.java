package DevHelper.Commands.StudyCommands.PomodoroCommands;

import DevHelper.ICommand;
import DevHelper.Listeners.PomodoroListener;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PomodoroClosedTicket implements ICommand {
    @Override
    public String getName() {
        return "fechar-pomodoro";
    }

    @Override
    public String getDescription() {
        return "Fecha o canal dedicado ao seu pomodoro.";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Guild guild = event.getGuild();
        Member member = event.getMember();

        if(guild != null && member != null){
            PomodoroListener.closePomodoroTicket(guild, member);
            event.reply("🍅 Canal do Pomodoro fechado!").setEphemeral(true).queue();
        } else {
            event.reply("⚠️ Ocorreu um erro ao fechar o canal.").setEphemeral(true).queue();
        }
    }
}
