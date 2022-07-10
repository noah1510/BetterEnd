package org.betterx.betterend.registry;

import org.betterx.betterend.client.BetterEndClient;
import org.betterx.betterend.client.gui.EndStoneSmelterScreen;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class EndScreens {
    public static void register() {
        ScreenRegistry.register(BetterEndClient.HANDLER_TYPE, EndStoneSmelterScreen::new);
    }
}
