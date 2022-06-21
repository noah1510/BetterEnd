package org.betterx.betterend.integration.byg;

import org.betterx.bclib.integration.ModIntegration;
import org.betterx.betterend.integration.EndBiomeIntegration;

public class BYGIntegration extends ModIntegration implements EndBiomeIntegration {
    public BYGIntegration() {
        super("byg");
    }

    @Override
    public void init() {
		/*Block block = Integrations.BYG.getBlock("ivis_phylium");
		if (block != null) {
			TagManager.addTags(block, CommonBlockTags.END_STONES, CommonBlockTags.GEN_END_STONES);
		}
		BYGBlocks.register();
		BYGFeatures.register();
		BYGBiomes.register();*/
    }

    @Override
    public void addBiomes() {
        //BYGBiomes.addBiomes();
    }
}
