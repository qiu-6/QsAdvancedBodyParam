package net.qiu.advancedbodyparam.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
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

    @Unique
    private static final Identifier BUTTON = new Identifier(MOD_ID, "textures/gui/button.png");

    @Inject(method = "drawBackground", at = @At("TAIL"))
    private void drawExtraPanel(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo ci) {
        InventoryScreen screen = (InventoryScreen) (Object) this;

        // Calculate the starting position of the vanilla inventory
        int x = (screen.width - 176) / 2;
        int y = (screen.height - 166) / 2;

        context.drawTexture(BACKGROUND,
                x + 176, y,
                0,0,
                65, 86);

        context.drawTexture(BACKGROUND,
                x + 176 + 7, y + 7,
                0, 86,
                51, 72);

        context.drawTexture(BACKGROUND,
                x + 176 + 7 + 51 + 3, y + 7 + 3,
                54, 90,
                10, 72);

        context.drawTexture(BUTTON,
                x + 134, y + 61,
                0, 0,
                20, 18
                );
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void drawButtons(CallbackInfo ci) {

        InventoryScreen screen = (InventoryScreen) (Object) this;

        TexturedButtonWidget statusButton = new TexturedButtonWidget(
                screen
        )
    }
}
