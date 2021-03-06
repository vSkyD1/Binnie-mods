package binnie.craftgui.genetics.machine;

import binnie.core.AbstractMod;
import binnie.craftgui.core.geometry.Position;
import binnie.craftgui.minecraft.GUIIcon;
import binnie.craftgui.minecraft.Window;
import binnie.craftgui.minecraft.control.*;
import binnie.craftgui.resource.Texture;
import binnie.craftgui.resource.minecraft.StandardTexture;
import binnie.genetics.Genetics;
import binnie.genetics.core.GeneticsTexture;
import binnie.genetics.machine.Incubator;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class WindowIncubator extends WindowMachine {
    static Texture ProgressBase;
    static Texture Progress;

    static {
        WindowIncubator.ProgressBase = new StandardTexture(0, 91, 38, 32, GeneticsTexture.GUIProcess);
        WindowIncubator.Progress = new StandardTexture(38, 91, 38, 32, GeneticsTexture.GUIProcess);
    }

    public WindowIncubator(final EntityPlayer player, final IInventory inventory, final Side side) {
        super(228, 196, player, inventory, side);
    }

    public static Window create(final EntityPlayer player, final IInventory inventory, final Side side) {
        return new WindowIncubator(player, inventory, side);
    }

    @Override
    public void initialiseClient() {
        this.setTitle("Incubator");
        int x = 16;
        final int y = 32;
        new ControlLiquidTank(this, x, y).setTankID(0);
        x += 26;
        new ControlSlotArray(this, x, y + 3, 1, 3).create(Incubator.slotQueue);
        x += 20;
        new ControlIconDisplay(this, x, y + 3 + 10, GUIIcon.ArrowRight.getIcon());
        x += 18;
        new ControlMachineProgress(this, x, y + 6, WindowIncubator.ProgressBase, WindowIncubator.Progress, Position.Left);
        new ControlSlot(this, x + 11, y + 3 + 10).assign(3);
        x += 40;
        new ControlIconDisplay(this, x, y + 3 + 10, GUIIcon.ArrowRight.getIcon());
        x += 18;
        new ControlSlotArray(this, x, y + 3, 1, 3).create(Incubator.slotOutput);
        x += 26;
        new ControlLiquidTank(this, x, y).setTankID(1);
        x += 34;
        new ControlEnergyBar(this, x, y + 3, 16, 54, Position.Bottom);
        new ControlErrorState(this, 91.0f, 82.0f);
        new ControlPlayerInventory(this);
    }

    @Override
    public String getTitle() {
        return "Incubator";
    }

    @Override
    protected AbstractMod getMod() {
        return Genetics.instance;
    }

    @Override
    protected String getName() {
        return "Incubator";
    }
}
