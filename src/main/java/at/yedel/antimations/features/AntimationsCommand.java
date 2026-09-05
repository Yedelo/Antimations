package at.yedel.antimations.features;


import at.yedel.antimations.config.AntimationsConfig;
//? if v0 {
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
//?} else {
/*import org.polyfrost.oneconfig.api.commands.v1.factories.annotated.Command;
import org.polyfrost.oneconfig.api.commands.v1.factories.annotated.Handler;
*///?}



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

    //~ if v1 '@Main' -> '@Handler'
    @Main
    public void main() {
        AntimationsConfig.getInstance().open();
    }
}
