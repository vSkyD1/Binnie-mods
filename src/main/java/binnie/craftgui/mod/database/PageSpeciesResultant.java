package binnie.craftgui.mod.database;

import binnie.craftgui.controls.ControlText;
import binnie.craftgui.controls.ControlTextCentered;
import binnie.craftgui.core.IWidget;
import forestry.api.genetics.IAlleleSpecies;

public class PageSpeciesResultant extends PageSpecies {
    private ControlText pageSpeciesResultant_Title;
    private ControlMutationBox pageSpeciesResultant_List;

    public PageSpeciesResultant(final IWidget parent, final DatabaseTab tab) {
        super(parent, tab);
        this.pageSpeciesResultant_Title = new ControlTextCentered(this, 8.0f, "Resultant Mutations");
        this.pageSpeciesResultant_List = new ControlMutationBox(this, 4, 20, 136, 152, ControlMutationBox.Type.Resultant);
    }

    @Override
    public void onValueChanged(final IAlleleSpecies species) {
        this.pageSpeciesResultant_List.setSpecies(species);
    }
}
