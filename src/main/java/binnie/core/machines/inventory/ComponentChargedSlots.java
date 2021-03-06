package binnie.core.machines.inventory;

import binnie.core.machines.Machine;
import binnie.core.machines.MachineComponent;
import binnie.core.machines.network.INetwork;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.HashMap;
import java.util.Map;

public class ComponentChargedSlots extends MachineComponent implements INetwork.GuiNBT, IChargedSlots {
    private Map<Integer, Float> charges;

    public ComponentChargedSlots(final Machine machine) {
        super(machine);
        this.charges = new HashMap<Integer, Float>();
    }

    @Override
    public void readFromNBT(final NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        this.charges.clear();
        final NBTTagList list = nbttagcompound.getTagList("charges", 10);
        for (int i = 0; i < list.tagCount(); ++i) {
            final NBTTagCompound tag = list.getCompoundTagAt(i);
            this.charges.put((int) tag.getByte("i"), tag.getByte("v") / 100.0f);
        }
    }

    @Override
    public void writeToNBT(final NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        final NBTTagList chargeList = new NBTTagList();
        for (final Map.Entry<Integer, Float> entry : this.charges.entrySet()) {
            final NBTTagCompound chargesNBT = new NBTTagCompound();
            chargesNBT.setByte("i", (byte) (0 + entry.getKey()));
            chargesNBT.setByte("v", (byte) (entry.getValue() * 100.0f));
            chargeList.appendTag(chargesNBT);
        }
        nbttagcompound.setTag("charges", chargeList);
    }

    public void addCharge(final int slot) {
        this.charges.put(slot, 0.0f);
    }

    public void recieveGuiNBT(final Side side, final EntityPlayer player, final String name, final NBTTagCompound nbt) {
        if (name.equals("slot-charges")) {
            for (final int i : this.charges.keySet()) {
                this.charges.put(i, nbt.getShort("" + i) / 100.0f);
            }
        }
    }

    public void sendGuiNBT(final Map<String, NBTTagCompound> nbt) {
        final NBTTagCompound tag = new NBTTagCompound();
        for (final int i : this.charges.keySet()) {
            tag.setShort("" + i, (short) (this.charges.get(i) * 100.0f));
        }
        nbt.put("slot-charges", tag);
    }

    @Override
    public float getCharge(final int slot) {
        return this.charges.containsKey(slot) ? this.charges.get(slot) : 0.0f;
    }

    @Override
    public void setCharge(final int slot, float charge) {
        if (charge > 1.0f) {
            charge = 1.0f;
        }
        if (charge < 0.0f) {
            charge = 0.0f;
        }
        if (this.charges.containsKey(slot)) {
            this.charges.put(slot, charge);
        }
    }

    @Override
    public void onUpdate() {
        for (final int slot : this.charges.keySet()) {
            if (this.getCharge(slot) <= 0.0f && this.getUtil().decreaseStack(slot, 1) != null) {
                this.setCharge(slot, 1.0f);
            }
        }
    }

    @Override
    public void alterCharge(final int slot, final float charge) {
        this.setCharge(slot, this.getCharge(slot) + charge);
    }
}
