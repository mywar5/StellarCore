package github.kasuminova.stellarcore.mixin;

import github.kasuminova.stellarcore.common.NewOptimizations.MathematicalSubstitution.MathTransformer;
import github.kasuminova.stellarcore.common.config.StellarCoreConfig;
import github.kasuminova.stellarcore.common.integration.censoredasm.CensoredASMCompat;
import github.kasuminova.stellarcore.common.mod.Mods;
import github.kasuminova.stellarcore.common.util.StellarEnvironment;
import github.kasuminova.stellarcore.common.util.StellarLog;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.mixin.Mixins;
import net.minecraft.client.Minecraft;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;

@SuppressWarnings("unused")
public class StellarCoreEarlyMixinLoader implements IFMLLoadingPlugin {
    private static final Map<String, BooleanSupplier> MIXIN_CONFIGS = new LinkedHashMap<>();

    static {
        StellarEnvironment.init();

        addMixinCFG("mixins.stellar_core_minecraft_advancements.json",           () -> StellarCoreConfig.FEATURES.vanilla.asyncAdvancementSerialize);
        addMixinCFG("mixins.stellar_core_minecraft_bakedmodel.json",             () -> StellarCoreConfig.PERFORMANCE.vanilla.simpleBakedModelCanonicalization);
        addMixinCFG("mixins.stellar_core_minecraft_bakedquad.json",              () -> StellarCoreConfig.PERFORMANCE.vanilla.bakedQuadVertexDataCanonicalization);
        addMixinCFG("mixins.stellar_core_minecraft_blockfaceuv.json",            () -> StellarCoreConfig.PERFORMANCE.vanilla.blockFaceUVsCanonicalization);
        addMixinCFG("mixins.stellar_core_minecraft_blockpart.json",              () -> StellarCoreConfig.PERFORMANCE.vanilla.blockPartDataStructure);
        addMixinCFG("mixins.stellar_core_minecraft_blockstateimpl.json",         () -> StellarCoreConfig.PERFORMANCE.vanilla.blockStateImplementationHashCodeCache);
        addMixinCFG("mixins.stellar_core_minecraft_chunktecache.json",           () -> StellarCoreConfig.PERFORMANCE.vanilla.chunkTECache);
        addMixinCFG("mixins.stellar_core_minecraft_chunktemap.json",             () -> StellarCoreConfig.PERFORMANCE.vanilla.chunkTEMap);
        addMixinCFG("mixins.stellar_core_minecraft_chunktequeue.json",           () -> StellarCoreConfig.PERFORMANCE.vanilla.chunkTEQueue);
        addMixinCFG("mixins.stellar_core_minecraft_classmultimap.json",          () -> StellarCoreConfig.PERFORMANCE.vanilla.classMultiMap);
        addMixinCFG("mixins.stellar_core_minecraft_entitytracker.json",          () -> StellarCoreConfig.PERFORMANCE.vanilla.entitytracker);
        addMixinCFG("mixins.stellar_core_minecraft_itemstack_cap.json",          () -> StellarCoreConfig.PERFORMANCE.vanilla.asyncItemStackCapabilityInit && !(CensoredASMCompat.isPresent() && CensoredASMCompat.checkDelayItemStackCapInitEnabled()));
        addMixinCFG("mixins.stellar_core_minecraft_longnbtkiller.json",          () -> StellarCoreConfig.BUG_FIXES.vanilla.longNBTKiller);
        addMixinCFG("mixins.stellar_core_minecraft_modelblock.json",             () -> StellarCoreConfig.PERFORMANCE.vanilla.modelBlockStringCanonicalization && CensoredASMCompat.isPresent());
        addMixinCFG("mixins.stellar_core_minecraft_nbtmaplist.json",             () -> StellarCoreConfig.PERFORMANCE.vanilla.nbtTag && !(CensoredASMCompat.isPresent() && CensoredASMCompat.checkNBTMapModified()));
        addMixinCFG("mixins.stellar_core_minecraft_nbtpool.json",                () -> StellarCoreConfig.PERFORMANCE.vanilla.nbtPrimitiveConstantsPool);
        addMixinCFG("mixins.stellar_core_minecraft_nethandlerplayserver.json",   () -> StellarCoreConfig.BUG_FIXES.container.containerInteract);
        addMixinCFG("mixins.stellar_core_minecraft_nnlist.json",                 () -> StellarCoreConfig.PERFORMANCE.vanilla.nonNullList);
        addMixinCFG("mixins.stellar_core_minecraft_noglerror.json",              () -> StellarCoreConfig.PERFORMANCE.vanilla.noGlError);
        addMixinCFG("mixins.stellar_core_minecraft_property.json",               () -> StellarCoreConfig.PERFORMANCE.vanilla.propertyEnumHashCodeCache);
        addMixinCFG("mixins.stellar_core_minecraft_randomtick.json",             () -> StellarCoreConfig.PERFORMANCE.vanilla.parallelRandomBlockTicker);
        addMixinCFG("mixins.stellar_core_minecraft_renderglobal.json",           () -> StellarCoreConfig.PERFORMANCE.vanilla.alwaysDeferChunkUpdates);
        addMixinCFG("mixins.stellar_core_minecraft_resourcelocation.json",       () -> StellarCoreConfig.PERFORMANCE.vanilla.resourceLocationCanonicalization && !StellarCoreConfig.PERFORMANCE.vanilla.resourceLocationCanonicalizationAsync);
        addMixinCFG("mixins.stellar_core_minecraft_resourcelocation_async.json", () -> StellarCoreConfig.PERFORMANCE.vanilla.resourceLocationCanonicalization && StellarCoreConfig.PERFORMANCE.vanilla.resourceLocationCanonicalizationAsync);
        addMixinCFG("mixins.stellar_core_minecraft_resourcepack.json",           () -> StellarCoreConfig.PERFORMANCE.vanilla.resourceExistStateCache);
        addMixinCFG("mixins.stellar_core_minecraft_world.json",                  () -> StellarCoreConfig.PERFORMANCE.vanilla.capturedBlockSnapshots);
        addMixinCFG("mixins.stellar_core_minecraft_world_load.json",             () -> StellarCoreConfig.FEATURES.vanilla.handleClientWorldLoad);
        addMixinCFG("mixins.stellar_core_minecraft_world_pos_judgement.json",    () -> StellarCoreConfig.PERFORMANCE.vanilla.worldBlockPosJudgement);
        addMixinCFG("mixins.stellar_core_minecraft_worldclient.json",            () -> StellarCoreConfig.BUG_FIXES.vanilla.clientNullBlockPacket);
        addMixinCFG("mixins.stellar_core_minecraft_worldserver.json",            () -> StellarCoreConfig.PERFORMANCE.vanilla.worldServerGetPendingBlockUpdates);
        addMixinCFG("mixins.stellar_core_minecraft_statemapperbase.json",        () -> StellarCoreConfig.PERFORMANCE.vanilla.parallelModelLoader || StellarCoreConfig.PERFORMANCE.vanilla.stateMapperBase);
        addMixinCFG("mixins.stellar_core_minecraft_stitcher.json",               () -> StellarCoreConfig.PERFORMANCE.vanilla.stitcherCache);
//        addMixinCFG("mixins.stellar_core_minecraft_texturemap.json",      () -> StellarCoreConfig.PERFORMANCE.vanilla.parallelTextureMapLoad);
        addMixinCFG("mixins.stellar_core_minecraft_texture_load.json",           () -> StellarCoreConfig.PERFORMANCE.vanilla.parallelTextureLoad);
        addMixinCFG("mixins.stellar_core_forge.json",                            () -> StellarCoreConfig.PERFORMANCE.customLoadingScreen.splashProgress);
        addMixinCFG("mixins.stellar_core_forge_asmdatatable.json",               () -> StellarCoreConfig.PERFORMANCE.forge.asmDataTable);
        addMixinCFG("mixins.stellar_core_forge_asmmodparser.json",               () -> StellarCoreConfig.PERFORMANCE.forge.asmModParser);
        addMixinCFG("mixins.stellar_core_forge_bakedquad.json",                  () -> StellarCoreConfig.PERFORMANCE.forge.unpackedBakedQuadDataCanonicalization);
        addMixinCFG("mixins.stellar_core_forge_bakedquad_vertexdata.json",       () -> StellarCoreConfig.PERFORMANCE.forge.unpackedBakedQuadVertexDataCanonicalization);
        addMixinCFG("mixins.stellar_core_forge_capability.json",                 () -> StellarCoreConfig.PERFORMANCE.forge.deallocateEmptyCapabilityNBT);
        addMixinCFG("mixins.stellar_core_forge_chunkmanager.json",               () -> StellarCoreConfig.PERFORMANCE.forge.chunkManager);
        addMixinCFG("mixins.stellar_core_forge_modelloader.json",                () -> StellarCoreConfig.PERFORMANCE.vanilla.parallelModelLoader);
        addMixinCFG("mixins.stellar_core_forge_registry.json",                   () -> StellarCoreConfig.FEATURES.vanilla.forgeRegistryRemoveList.length > 0);
        addMixinCFG("mixins.stellar_core_hudcaching.json",                       () -> StellarCoreConfig.PERFORMANCE.vanilla.hudCaching);
        addMixinCFG("mixins.stellar_core_ebwizardry_early.json",                 () -> StellarCoreConfig.PERFORMANCE.ebWizardry.dispenserCastingData && !Mods.TICK_CENTRAL.loaded());
    }

    private static void addMixinCFG(final String mixinConfig) {
        MIXIN_CONFIGS.put(mixinConfig, () -> true);
    }

    private static void addMixinCFG(final String mixinConfig, final BooleanSupplier conditions) {
        MIXIN_CONFIGS.put(mixinConfig, conditions);
    }

    public static boolean isCleanroomLoader() {
        try {
            Class.forName("com.cleanroommc.boot.Main");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    // Noop

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{MathTransformer.class.getName()};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(final Map<String, Object> data) {
        MIXIN_CONFIGS.forEach((config, supplier) -> {
            if (supplier == null) {
                StellarLog.LOG.warn("[StellarCore-MixinLoader] Mixin config {} is not found in config map! It will never be loaded.", config);
                return;
            }
            boolean shouldLoad = supplier.getAsBoolean();
            if (!shouldLoad) {
                StellarLog.LOG.info("[StellarCore-MixinLoader] Mixin config {} is disabled by config or mod is not loaded.", config);
                return;
            }
            StellarLog.LOG.info("[StellarCore-MixinLoader] Adding {} to mixin configuration.", config);
            Mixins.addConfiguration(config);
        });
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
