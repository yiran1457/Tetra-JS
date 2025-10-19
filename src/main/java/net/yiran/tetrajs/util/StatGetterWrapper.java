package net.yiran.tetrajs.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import se.mickelus.tetra.gui.stats.getter.IStatGetter;

public class StatGetterWrapper implements IStatGetter {
    public IStatGetter statGetter;
    public ShowCheck showCheck;

    public StatGetterWrapper(IStatGetter statGetter, ShowCheck check) {
        this.statGetter = statGetter;
        this.showCheck = check;
    }

    @Override
    public boolean shouldShow(Player player, ItemStack currentStack, ItemStack previewStack) {
        return showCheck.check(player, currentStack, previewStack, statGetter);
    }

    @Override
    public double getValue(Player player, ItemStack itemStack) {
        return statGetter.getValue(player, itemStack);
    }

    @Override
    public double getValue(Player player, ItemStack itemStack, String s) {
        return statGetter.getValue(player, itemStack, s);
    }

    @Override
    public double getValue(Player player, ItemStack itemStack, String s, String s1) {
        return statGetter.getValue(player, itemStack, s, s1);
    }

    @FunctionalInterface
    public interface ShowCheck {
        boolean check(Player player, ItemStack currentStack, ItemStack previewStack, IStatGetter statGetter);
    }
}
