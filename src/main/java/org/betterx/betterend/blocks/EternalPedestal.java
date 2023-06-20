package org.betterx.betterend.blocks;

import de.ambertation.wunderlib.math.Float3;
import org.betterx.bclib.behaviours.interfaces.BehaviourStone;
import org.betterx.bclib.interfaces.ClientLevelAccess;
import org.betterx.betterend.blocks.basis.PedestalBlock;
import org.betterx.betterend.blocks.entities.EternalPedestalEntity;
import org.betterx.betterend.client.render.EternalCrystalRenderer;
import org.betterx.betterend.client.render.PedestalItemRenderer;
import org.betterx.betterend.registry.EndBlocks;
import org.betterx.betterend.registry.EndPortals;
import org.betterx.betterend.rituals.EternalRitual;

import net.minecraft.client.particle.Particle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import com.google.common.collect.Lists;

import java.util.List;

public class EternalPedestal extends PedestalBlock implements BehaviourStone {
    public static final BooleanProperty ACTIVATED = EndBlockProperties.ACTIVE;

    public EternalPedestal() {
        super(EndBlocks.FLAVOLITE_RUNED_ETERNAL);
        this.registerDefaultState(defaultBlockState().setValue(ACTIVATED, false));
    }

    @Override
    public void checkRitual(Level sourceLevel, Player player, BlockPos pos) {
        BlockEntity blockEntity = sourceLevel.getBlockEntity(pos);
        if (blockEntity instanceof EternalPedestalEntity) {
            EternalPedestalEntity pedestal = (EternalPedestalEntity) blockEntity;
            BlockState updatedState = sourceLevel.getBlockState(pos);
            if (pedestal.isEmpty()) {
                if (pedestal.hasRitual()) {
                    EternalRitual ritual = pedestal.getRitual();
                    if (ritual.isActive()) {
                        if (ritual.getWorld() == null) ritual.setWorld(sourceLevel);
                        ResourceLocation targetWorld = ritual.getTargetWorldId();
                        int portalId;
                        if (targetWorld != null) {
                            portalId = EndPortals.getPortalIdByWorld(targetWorld);
                        } else {
                            portalId = EndPortals.getPortalIdByWorld(EndPortals.OVERWORLD_ID);
                        }
                        ritual.disablePortal(portalId);
                    }
                }
                sourceLevel.setBlockAndUpdate(pos, updatedState.setValue(ACTIVATED, false).setValue(HAS_LIGHT, false));
            } else {
                ItemStack itemStack = pedestal.getItem(0);
                ResourceLocation id = BuiltInRegistries.ITEM.getKey(itemStack.getItem());
                if (EndPortals.isAvailableItem(id)) {
                    sourceLevel.setBlockAndUpdate(
                            pos,
                            updatedState.setValue(ACTIVATED, true).setValue(HAS_LIGHT, true)
                    );
                    if (pedestal.hasRitual()) {
                        if (pedestal.getRitual().getWorld() == null) pedestal.getRitual().setWorld(sourceLevel);
                        pedestal.getRitual().checkStructure(player);
                    } else {
                        EternalRitual ritual = new EternalRitual(sourceLevel, pos);
                        pedestal.linkRitual(ritual);
                        ritual.checkStructure(player);
                    }
                }
            }
        }
    }

    @Override
    @Deprecated
    public BlockState updateShape(
            BlockState state,
            Direction direction,
            BlockState newState,
            LevelAccessor world,
            BlockPos pos,
            BlockPos posFrom
    ) {
        BlockState updated = super.updateShape(state, direction, newState, world, pos, posFrom);
        if (!updated.is(this)) return updated;
        if (!this.isPlaceable(updated)) {
            return updated.setValue(ACTIVATED, false);
        }
        return updated;
    }

    @Override
    @Deprecated
    public float getDestroyProgress(BlockState state, Player player, BlockGetter world, BlockPos pos) {
        return 0.0F;
    }

    @Override
    public float getExplosionResistance() {
        return Blocks.BEDROCK.getExplosionResistance();
    }

    @Override
    public boolean dropFromExplosion(Explosion explosion) {
        return false;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        if (state.is(this)) {
            EndBlockProperties.PedestalState currentState = state.getValue(EndBlockProperties.PEDESTAL_STATE);
            if (currentState.equals(EndBlockProperties.PedestalState.BOTTOM) || currentState.equals(EndBlockProperties.PedestalState.PILLAR)) {
                return Lists.newArrayList();
            }
        }
        List<ItemStack> drop = Lists.newArrayList();
        BlockEntity blockEntity = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof EternalPedestalEntity) {
            EternalPedestalEntity pedestal = (EternalPedestalEntity) blockEntity;
            if (!pedestal.isEmpty()) {
                drop.add(pedestal.getItem(0));
            }
        }
        return drop;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateManager) {
        super.createBlockStateDefinition(stateManager);
        stateManager.add(ACTIVATED);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new EternalPedestalEntity(blockPos, blockState);
    }

    @Override
    public boolean hasUniqueEntity() {
        return true;
    }

    @Environment(EnvType.CLIENT)
    private void dispatchParticles(Level level, BlockPos blockPos, RandomSource random) {
        if (level instanceof ClientLevelAccess clientLevel) {
            if (level.getBlockEntity(blockPos) instanceof EternalPedestalEntity pedestal
                    && pedestal.hasRitual()) {
                BlockState state = level.getBlockState(blockPos);
                //if (state.getOptionalValue(ACTIVATED).orElse(false))
                {
                    EternalRitual ritual = pedestal.getRitual();
                    if (ritual != null
                            && ritual.getCenter() != null
                            && (ritual.isActive() || ritual.willActivate())
                    ) {
                        final var start = Float3.of(blockPos);
                        final var dir = Float3
                                .of(ritual.getCenter())
                                .sub(start)
                                .normalized()
                                .mul(ritual.willActivate() ? 0.2 : 0.05);
                        float[] color = EternalCrystalRenderer.colors(PedestalItemRenderer.getGemAge());

                        for (int i = 0; i < random.nextInt(
                                ritual.willActivate() ? 30 : 2,
                                ritual.willActivate() ? 60 : 10
                        ); i++) {
                            Float3 rnd = Float3.of(
                                    random.nextFloat() * 0.3 - 0.15,
                                    random.nextFloat() * -0.1,
                                    random.nextFloat() * 0.3 - 0.15
                            ).add(dir);
                            SimpleParticleType particleOptions = ParticleTypes.EFFECT;
                            final Particle particle = clientLevel.bcl_addParticle(
                                    particleOptions,
                                    blockPos.getX() + 0.2 + random.nextFloat() * 0.6,
                                    blockPos.getY() + 1 + random.nextFloat() * 0.7,
                                    blockPos.getZ() + 0.2 + random.nextFloat() * 0.6,
                                    0,
                                    0,
                                    0
                            );
                            if (particle == null) continue;
                            particle.setColor(color[0], color[1], color[2]);
                            particle.setParticleSpeed(rnd.x, rnd.y, rnd.z);
                        }
                    }
                }
            }
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        super.animateTick(blockState, level, blockPos, randomSource);
        dispatchParticles(level, blockPos, randomSource);
    }
}
