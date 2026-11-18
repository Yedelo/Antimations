package at.yedel.antimations.features;



import at.yedel.antimations.config.AntimationsConfig;
import at.yedel.antimations.launch.AntimationsConstants;
import org.apache.logging.log4j.LogManager;
import org.polyfrost.oneconfig.api.commands.v1.factories.annotated.Command;
import org.polyfrost.oneconfig.api.commands.v1.factories.annotated.Handler;
import org.polyfrost.oneconfig.api.platform.v1.Platform;

import java.lang.reflect.Field;



@Command(
    value = "antimations",
    description = "The main command of Animations"
)
public class AntimationsCommand {
    private static final String LOGO = "§5§l< Antimations >";
    private static final AntimationsCommand INSTANCE = new AntimationsCommand();

    public static AntimationsCommand getInstance() {
        return INSTANCE;
    }

    private AntimationsCommand() {}

    @Handler
    public void main() {
        AntimationsConfig.getInstance().open();
    }

    @Handler(description = "Shows mod constants and build information such as the project version.")
    public void constants() {
        try {
            Platform.compatibility().displayChatMessage(LOGO + " §eConstants:");
            for (Field field : AntimationsConstants.class.getDeclaredFields()) {
                Platform.compatibility().displayChatMessage(LOGO + " " + field.getName() + ": §r" + field.get(null));
            }
        }
        catch (IllegalAccessException e) {
            Platform.compatibility().displayChatMessage(LOGO + " §cCouldn't get mod constants!");
            LogManager.getLogger("Antimations").error("Couldn't get mod constants!", e);
        }
    }
}
