package net.qiu.advancedbodyparam.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.qiu.advancedbodyparam.QsAdvancedBodyParameters.MOD_ID;

@Mixin(InventoryScreen.class)
public class statusHud {

    @Unique
    private static final Identifier BACKGROUND = new Identifier(MOD_ID, "textures/gui/status_hud_background.png");

    @Inject(method = "drawBackground", at = @At("TAIL"))
    private void drawExtraPanel(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo ci) {
        InventoryScreen screen = (InventoryScreen) (Object) this;

        // Calculate the starting position of the vanilla inventory
        int x = (screen.width - 176) / 2;
        int y = (screen.height - 166) / 2;

        // Position of our new panel (e.g., attached right next to the right side of the inventory)
        int extraPanelX = x + 176;
        int extraPanelY = y;

        context.drawTexture(BACKGROUND,
                extraPanelX, extraPanelY,
                0,0,
                65, 87);

        context.drawTexture(BACKGROUND,
                extraPanelX + 7, extraPanelY + 7,
                65, 0,
                51, 72);
    }
}
