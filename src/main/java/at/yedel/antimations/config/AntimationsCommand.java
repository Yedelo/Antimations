package at.yedel.antimations.config;



import at.yedel.antimations.gui.AntimationsGui;
import at.yedel.antimations.utils.DelayedTask;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import static at.yedel.antimations.Antimations.minecraft;



public class AntimationsCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "antimations";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "§eUsage: /antimations";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        new DelayedTask(() -> minecraft.displayGuiScreen(new AntimationsGui(minecraft.currentScreen)));
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {return true;}
}
