package net.qiu.advancedbodyparam.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.qiu.advancedbodyparam.gui.statusPanel.bodyPanel;
import net.qiu.advancedbodyparam.util.BodyParts;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.qiu.advancedbodyparam.QsAdvancedBodyParameters.MOD_ID;

@Mixin(InventoryScreen.class)
public class statusHud extends Screen {

    @Final
    @Shadow
    private RecipeBookWidget recipeBook;

    @Unique
    private static final Identifier BACKGROUND = new Identifier(MOD_ID, "textures/gui/status_hud_background.png");
    @Unique
    private static final Identifier BUTTON = new Identifier(MOD_ID, "textures/gui/button.png");

    @Unique
    private TexturedButtonWidget statusButton;

    @Unique
    private boolean showStatusPanel = false;

    @Unique
    private static final int RECIPE_BOOK_OFFSET = 77;
    @Unique
    private static final int INVENTORY_WIDTH = 176;
    @Unique
    private static final int INVENTORY_HEIGHT = 166;
    @Unique
    private static final int PANEL_WIDTH = 65;

    @Unique
    private int getInventoryX() {
        int base = (this.width - INVENTORY_WIDTH) / 2;
        return recipeBook.isOpen() ? base + RECIPE_BOOK_OFFSET : base;
    }

    @Unique
    private int getInventoryY() {
        return (this.height - INVENTORY_HEIGHT) / 2;
    }

    protected statusHud(Text title) {
        super(title);
    }

    @Inject(method = "drawBackground", at = @At("TAIL"))
    private void drawExtraPanel(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo ci) {

        if (!showStatusPanel) return;

        int x = getInventoryX();
        int y = getInventoryY();

        context.drawTexture(BACKGROUND,
                x - PANEL_WIDTH, y,
                0,0,
                PANEL_WIDTH, 86);

        context.drawTexture(BACKGROUND,
                x - PANEL_WIDTH + 7 + 51 + 3, y + 7 + 3,
                54, 90,
                10, 72);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void initButtons(CallbackInfo ci) {

        int x = getInventoryX();
        int y = getInventoryY();

        statusButton = new TexturedButtonWidget(
                x + 134, y + 61,
                21, 18,
                0, 0,
                19,
                BUTTON,
                256, 256,
                button -> {
                    showStatusPanel = !showStatusPanel;

                    if (showStatusPanel && recipeBook.isOpen()) {
                        recipeBook.toggleOpen();
                    }

                    if (client == null) return;

                    this.init(this.client, this.width, this.height);
                }
        );

        this.addDrawableChild(statusButton);

        if (!showStatusPanel) return;

        if (client == null) return;
        if (client.player == null) return;
        if (!client.player.hasSkinTexture()) return;

        Identifier skin = client.player.getSkinTexture();
        if (client.getNetworkHandler() == null) return;
        var networkEntry = client.getNetworkHandler().getPlayerListEntry(client.player.getUuid());
        if (networkEntry == null) return;

        String modelType = networkEntry.getModel();

        bodyPanel head = new bodyPanel(
                x - PANEL_WIDTH + 19, y + 10,
                2,
                skin,
                BodyParts.HEAD,
                modelType
        );

        bodyPanel torso = new bodyPanel(
                x - PANEL_WIDTH + 19, y + 27,
                2,
                skin,
                BodyParts.TORSO,
                modelType
        );

        bodyPanel leftArm = new bodyPanel(
                x - PANEL_WIDTH + 36, y + 27,
                2,
                skin,
                BodyParts.LEFT_ARM,
                modelType
        );

        bodyPanel rightArm = new bodyPanel(
                x - PANEL_WIDTH + 10, y + 27,
                2,
                skin,
                BodyParts.RIGHT_ARM,
                modelType
        );

        bodyPanel leftLeg = new bodyPanel(
                x - PANEL_WIDTH + 28, y + 52,
                2,
                skin,
                BodyParts.LEFT_LEG,
                modelType
        );

        bodyPanel rightLeg = new bodyPanel(
                x - PANEL_WIDTH + 18, y + 52,
                2,
                skin,
                BodyParts.RIGHT_LEG,
                modelType
        );

        this.addDrawableChild(head);
        this.addDrawableChild(torso);
        this.addDrawableChild(leftArm);
        this.addDrawableChild(rightArm);
        this.addDrawableChild(leftLeg);
        this.addDrawableChild(rightLeg);
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void updateButtonPosition(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (statusButton == null) return;

        int x = getInventoryX();
        int y = getInventoryY();

        statusButton.setX(x + 134);
        statusButton.setY(y + 61);
    }

    @Inject(method = "mouseClicked", at = @At("TAIL"))
    private void closeStatusPanel(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        if (recipeBook.isOpen() && showStatusPanel) {
            showStatusPanel = false;
        }
    }
}
