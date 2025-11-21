package net.yiran.tetrajs.kubejs.builders.items;

import com.google.gson.JsonParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.yiran.tetrajs.items.ShieldModularItem;

public class ShieldModularItemBuilder extends AbstractWithSlotModularItemBuilder<ShieldModularItemBuilder> {
    public ShieldModularItemBuilder(ResourceLocation itemID) {
        super(itemID);
    }

    @Override
    public void genModel() {
        this.modelJson(JsonParser.parseString("""
                    {
                    "parent": "builtin/entity",
                    "gui_light": "front",
                    "textures": {
                        "particle": "block/dark_oak_planks"
                    },
                    "display": {
                        "thirdperson_righthand": {
                            "rotation": [ 0, 90, 0 ],
                            "translation": [ 10, 6, -4 ],
                            "scale": [ 1, 1, 1 ]
                        },
                        "thirdperson_lefthand": {
                            "rotation": [ 0, 90, 0 ],
                            "translation": [ 10, 6, 12 ],
                            "scale": [ 1, 1, 1 ]
                        },
                        "firstperson_righthand": {
                            "rotation": [ 0, 180, 5 ],
                            "translation": [ -10, 2, -10 ],
                            "scale": [ 1.25, 1.25, 1.25 ]
                        },
                        "firstperson_lefthand": {
                            "rotation": [ 0, 180, 5 ],
                            "translation": [ 10, 0, -10 ],
                            "scale": [ 1.25, 1.25, 1.25 ]
                        },
                        "gui": {
                            "rotation": [ 15, -25, -5 ],
                            "translation": [ 2, 3, 0 ],
                            "scale": [ 0.65, 0.65, 0.65 ]
                        },
                        "fixed": {
                            "rotation": [ 0, 180, 0 ],
                            "translation": [ -2, 4, -5 ],
                            "scale": [ 0.5, 0.5, 0.5 ]
                        },
                        "ground": {
                            "rotation": [ 0, 0, 0 ],
                            "translation": [ 2, 4, 2 ],
                            "scale": [ 0.25, 0.25, 0.25 ]
                        }
                    },
                    "overrides": [
                        {
                            "predicate": {
                                "blocking": 1
                            },
                            "model": "tetra:item/modular_shield_blocking"
                        },
                        {
                            "predicate": {
                                "throwing": 1
                            },
                            "model": "tetra:item/modular_shield_throwing"
                        }
                    ]
                }
                """).getAsJsonObject());
    }

    @Override
    public Item createObject() {
        return new ShieldModularItem(id, majorsData, minorsData, canHone, honeBase, honeIntegrityMultiplier, synergiesPath);
    }
}
