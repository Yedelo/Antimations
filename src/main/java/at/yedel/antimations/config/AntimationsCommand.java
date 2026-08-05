package at.yedel.antimations.config;



import cc.polyfrost.oneconfig.libs.universal.ChatColor;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;



@Command(
    value = "antimations",
    description = "The main command of Animations",
    chatColor = ChatColor.GREEN
)
public class AntimationsCommand {
    private static final AntimationsCommand INSTANCE = new AntimationsCommand();

    public static AntimationsCommand getInstance() {
        return INSTANCE;
    }

    private AntimationsCommand() {}

    @Main
    public void main() {
        AntimationsConfig.getInstance().openGui();
    }
}
