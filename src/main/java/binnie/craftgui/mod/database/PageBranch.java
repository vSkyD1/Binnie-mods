package binnie.craftgui.mod.database;

import binnie.craftgui.core.IWidget;
import forestry.api.genetics.IClassification;

abstract class PageBranch extends PageAbstract<IClassification> {
    public PageBranch(final IWidget parent, final DatabaseTab tab) {
        super(parent, tab);
    }
}
