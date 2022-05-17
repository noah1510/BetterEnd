package ru.betterend.blocks;

import net.minecraft.world.level.block.state.BlockBehaviour;
import ru.bclib.blocks.BaseVineBlock;

public class FilaluxBlock extends BaseVineBlock {
	public FilaluxBlock() {
		super(15, true, p->p.offsetType(BlockBehaviour.OffsetType.NONE));
	}
}
