package ru.betterend.blocks.basis;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MaterialColor;

import ru.bclib.api.tag.CommonBlockTags;

import ru.bclib.blocks.BaseTerrainBlock;
import ru.bclib.interfaces.TagProvider;
import ru.betterend.interfaces.PottableTerrain;

import java.util.List;

public class EndTerrainBlock extends BaseTerrainBlock implements PottableTerrain, TagProvider {
	public EndTerrainBlock(MaterialColor color) {
		super(Blocks.END_STONE, color);
	}
	
	@Override
	public void addTags(List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		blockTags.add(CommonBlockTags.END_STONES);
	}
}
