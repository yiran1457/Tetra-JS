package net.yiran.tetrajs.util;

import se.mickelus.tetra.aspect.ItemAspect;
import se.mickelus.tetra.effect.ItemEffect;

import java.util.Map;

public class StaticMapGetterHelper {
    public static Map<String, ItemEffect> EffectMap ;
    public static Map<String, ItemEffect> getEffects(){
        return EffectMap;
    }
    public static Map<String, ItemAspect> AspectsMap;
    public static Map<String, ItemAspect> getAspects(){
        return AspectsMap;
    }
}
