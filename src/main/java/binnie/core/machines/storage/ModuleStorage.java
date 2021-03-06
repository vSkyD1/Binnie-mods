package binnie.core.machines.storage;

import binnie.core.BinnieCore;
import binnie.core.IInitializable;
import binnie.core.machines.MachineGroup;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModuleStorage implements IInitializable {
    @Override
    public void preInit() {
        (BinnieCore.packageCompartment = new MachineGroup(BinnieCore.instance, "storage", "storage", Compartment.values())).setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public void init() {
    }

    @Override
    public void postInit() {
        final String ironGear = OreDictionary.getOres("gearIron").isEmpty() ? "ingotIron" : "gearIron";
        final String goldGear = OreDictionary.getOres("gearGold").isEmpty() ? "ingotGold" : "gearGold";
        final String diamondGear = "gemDiamond";
        GameRegistry.addRecipe(new ShapedOreRecipe(Compartment.Compartment.get(1), "pcp", "cbc", "pcp", 'b', Items.book, 'c', Blocks.chest, 'p', Blocks.stone_button));
        GameRegistry.addRecipe(new ShapedOreRecipe(Compartment.CompartmentCopper.get(1), "pcp", "cbc", "pcp", 'b', Compartment.Compartment.get(1), 'c', "gearCopper", 'p', Blocks.stone_button));
        GameRegistry.addRecipe(new ShapedOreRecipe(Compartment.CompartmentBronze.get(1), "pcp", "cbc", "pcp", 'b', Compartment.CompartmentCopper.get(1), 'c', "gearBronze", 'p', Items.gold_nugget));
        GameRegistry.addRecipe(new ShapedOreRecipe(Compartment.CompartmentIron.get(1), "pcp", "cbc", "pcp", 'b', Compartment.CompartmentCopper.get(1), 'c', ironGear, 'p', Items.gold_nugget));
        GameRegistry.addRecipe(new ShapedOreRecipe(Compartment.CompartmentGold.get(1), "pcp", "cbc", "pcp", 'b', Compartment.CompartmentIron.get(1), 'c', goldGear, 'p', Items.emerald));
        GameRegistry.addRecipe(new ShapedOreRecipe(Compartment.CompartmentDiamond.get(1), "pcp", "cbc", "pcp", 'b', Compartment.CompartmentGold.get(1), 'c', diamondGear, 'p', Items.emerald));
    }
}
