package org.betterx.betterend.registry;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

import org.betterx.betterend.client.gui.EndStoneSmelterScreen;
import org.betterx.betterend.client.gui.EndStoneSmelterScreenHandler;

public class EndScreens {
    public static void register() {
        ScreenRegistry.register(EndStoneSmelterScreenHandler.HANDLER_TYPE, EndStoneSmelterScreen::new);
    }
}
