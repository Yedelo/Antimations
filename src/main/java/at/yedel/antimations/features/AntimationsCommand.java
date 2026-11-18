package at.yedel.antimations.features;


import at.yedel.antimations.config.AntimationsConfig;
import org.polyfrost.oneconfig.api.commands.v1.factories.annotated.Command;
import org.polyfrost.oneconfig.api.commands.v1.factories.annotated.Handler;



@Command(
    value = "antimations",
    description = "The main command of Animations"
)
public class AntimationsCommand {
    private static final AntimationsCommand INSTANCE = new AntimationsCommand();

    public static AntimationsCommand getInstance() {
        return INSTANCE;
    }

    private AntimationsCommand() {}

    @Handler
    public void main() {
        AntimationsConfig.getInstance().open();
    }
}
