package ru.betterend.blocks.basis;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import ru.bclib.api.tag.CommonBlockTags;
import ru.bclib.blocks.BasePlantBlock;
import ru.betterend.interfaces.PottablePlant;

import java.util.function.Function;

public class EndPlantBlock extends BasePlantBlock implements PottablePlant {
	public EndPlantBlock() {
		this(false, p->p);
	}

	public EndPlantBlock(int light) {
		this(light, p->p);
	}
	public EndPlantBlock(int light, Function<Properties, Properties> propMod) {
		this(false, light, propMod);
	}

	public EndPlantBlock(boolean replaceable) {
		super(replaceable);
	}
	public EndPlantBlock(boolean replaceable, Function<Properties, Properties> propMod) {
		super(replaceable, propMod);
	}

	public EndPlantBlock(boolean replaceable, int light){
		this(replaceable, light, p->p);
	}
	public EndPlantBlock(boolean replaceable, int light, Function<Properties, Properties> propMod) {
		super(replaceable, light, propMod);
	}
	
	public EndPlantBlock(Properties settings) {
		super(settings);
	}
	
	@Override
	protected boolean isTerrain(BlockState state) {
		return state.is(CommonBlockTags.END_STONES);
	}
	
	@Override
	public boolean canPlantOn(Block block) {
		return isTerrain(block.defaultBlockState());
	}
	
	@Override
	public boolean canBePotted() {
		return getStateDefinition().getProperties().isEmpty();
	}
}
