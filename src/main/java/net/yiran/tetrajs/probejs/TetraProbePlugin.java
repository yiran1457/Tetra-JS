package net.yiran.tetrajs.probejs;

import net.minecraft.resources.ResourceLocation;
import net.yiran.tetrajs.util.StaticMapGetterHelper;
import se.mickelus.tetra.aspect.ItemAspect;
import se.mickelus.tetra.effect.ItemEffect;
import zzzank.probejs.docs.assignments.SpecialTypes;
import zzzank.probejs.features.kubejs.BindingFilter;
import zzzank.probejs.features.kubejs.EventJSFilter;
import zzzank.probejs.lang.schema.SchemaDump;
import zzzank.probejs.lang.snippet.SnippetDump;
import zzzank.probejs.lang.transpiler.Transpiler;
import zzzank.probejs.lang.transpiler.TypeConverter;
import zzzank.probejs.lang.transpiler.transformation.ClassTransformerRegistration;
import zzzank.probejs.lang.typescript.RequestAwareFiles;
import zzzank.probejs.lang.typescript.ScriptDump;
import zzzank.probejs.lang.typescript.code.member.TypeDecl;
import zzzank.probejs.lang.typescript.code.ts.Wrapped;
import zzzank.probejs.lang.typescript.code.type.BaseType;
import zzzank.probejs.lang.typescript.code.type.Types;
import zzzank.probejs.lang.typescript.code.type.js.JSLambdaType;
import zzzank.probejs.plugin.ProbeJSPlugin;

import java.util.Map;
import java.util.Set;

public class TetraProbePlugin implements ProbeJSPlugin {
    @Override
    public void addPredefinedTypes(TypeConverter converter) {
    }

    @Override
    public void denyTypes(Transpiler transpiler) {
    }

    @Override
    public void modifyFiles(RequestAwareFiles files) {
    }

    @Override
    public void addGlobals(ScriptDump scriptDump) {
        var special = new Wrapped.Namespace(SpecialTypes.NAMESPACE);
        {
            var types = StaticMapGetterHelper.getEffects()
                    .values()
                    .stream()
                    .map(ItemEffect::getKey)
                    .sorted()
                    .map(Types::literal)
                    .toArray(BaseType[]::new);
            var declaration = new TypeDecl("ItemEffect", Types.or(types));
            special.addCode(declaration);
        }
        {
            var types = StaticMapGetterHelper.getAspects()
                    .values()
                    .stream()
                    .map(ItemAspect::getKey)
                    .sorted()
                    .map(Types::literal)
                    .toArray(BaseType[]::new);
            var declaration = new TypeDecl("ItemAspect", Types.or(types));
            special.addCode(declaration);
        }
        scriptDump.addGlobal("tetra_types", special);
    }

    @Override
    public void assignType(ScriptDump scriptDump) {
        scriptDump.assignType(ItemEffect.class, Types.primitive("Special.ItemEffect"));
        scriptDump.assignType(ItemAspect.class, Types.primitive("Special.ItemAspect"));
    }

    @Override
    public Set<Class<?>> provideJavaClass(ScriptDump scriptDump) {
        return ProbeJSPlugin.super.provideJavaClass(scriptDump);
    }

    @Override
    public void disableEventDumps(EventJSFilter filter) {
    }

    @Override
    public void addVSCodeSnippets(SnippetDump dump) {
        dump.snippet("ItemEffect")
                .prefix("@ItemEffect")
                .choices(
                        StaticMapGetterHelper.getEffects()
                                .values()
                                .stream()
                                .map(ItemEffect::getKey)
                                .sorted()
                                .toList()
                );
        dump.snippet("ItemAspect")
                .prefix("@ItemAspect")
                .choices(
                        StaticMapGetterHelper.getAspects()
                                .values()
                                .stream()
                                .map(ItemAspect::getKey)
                                .sorted()
                                .toList()
                );
    }

    @Override
    public void addJsonSchema(SchemaDump dump) {
    }

    @Override
    public void addPredefinedRecipeDoc(ScriptDump scriptDump, Map<ResourceLocation, JSLambdaType> predefined) {
    }

    @Override
    public void denyBindings(BindingFilter filter) {
    }

    @Override
    public void registerClassTransformer(ClassTransformerRegistration registration) {
    }
}
