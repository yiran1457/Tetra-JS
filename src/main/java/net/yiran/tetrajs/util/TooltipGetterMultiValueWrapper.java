package net.yiran.tetrajs.util;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import se.mickelus.tetra.gui.stats.getter.IStatFormat;
import se.mickelus.tetra.gui.stats.getter.IStatGetter;
import se.mickelus.tetra.gui.stats.getter.ITooltipGetter;

import java.util.Arrays;

public class TooltipGetterMultiValueWrapper  implements ITooltipGetter {
    protected IStatGetter[] statGetter;
    protected IStatGetter[] extendStatGetter;
    protected IStatFormat[] formatters;
    protected String localizationKey;

    public TooltipGetterMultiValueWrapper(String localizationKey, IStatGetter[] statGetters, IStatFormat[] formatters, IStatGetter[] extendStatGetter) {
        this.localizationKey = localizationKey;
        this.statGetter = statGetters;
        this.formatters = formatters;
        this.extendStatGetter = extendStatGetter;
        if (statGetters.length != formatters.length) {
            throw new RuntimeException(String.format("Mismatching length of stat getters and formatters for '%s', gett: %d, form: %d", localizationKey, statGetters.length, formatters.length));
        }
    }

    public String getTooltipBase(Player player, ItemStack itemStack) {
        Object[] values = new String[this.statGetter.length];

        for(int i = 0; i < this.statGetter.length; ++i) {
            values[i] = this.formatters[i].get(this.statGetter[i].getValue(player, itemStack));
        }

        return I18n.get(this.localizationKey, values);
    }

    public boolean hasExtendedTooltip(Player player, ItemStack itemStack) {
        return I18n.exists(this.localizationKey + "_extended");
    }

    public String getTooltipExtension(Player player, ItemStack itemStack) {
        return I18n.get(this.localizationKey + "_extended", Arrays.stream(extendStatGetter).map(statGetter1 -> statGetter1.getValue(player, itemStack)).toArray());
    }
}
