package net.qiu.advancedbodyparam.gui.statusPanel;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * A non-clickable, hoverable texture widget for Minecraft 1.20.1 Fabric.
 *
 * Out of the box it renders a semi-transparent white highlight over the
 * texture while the cursor is inside its bounds. Override {@link #renderHovered}
 * to replace or extend that behaviour in subclasses.
 *
 * Minimal usage:
 * <pre>{@code
 * this.addDrawableChild(new TextureWidget(
 *     x, y, 32, 32,
 *     new Identifier("mymod", "textures/gui/icon.png")
 * ));
 * }</pre>
 *
 * Atlas usage (sample a 16×16 region at UV 32,0 inside a 256×256 sheet):
 * <pre>{@code
 * this.addDrawableChild(new TextureWidget(
 *     x, y, 16, 16,
 *     new Identifier("mymod", "textures/gui/atlas.png"),
 *     32, 0, 16, 16, 256, 256
 * ));
 * }</pre>
 */
public abstract class hoverableWidget extends ClickableWidget {

    protected Identifier texture;

    protected int u = 0;
    protected int v = 0;
    protected int regionWidth;
    protected int regionHeight;
    protected int textureWidth;
    protected int textureHeight;

    public hoverableWidget(int x, int y, int width, int height, Identifier texture) {
        this(x, y, width, height, texture, 0, 0, width, height, width, height);
    }

    public hoverableWidget(int x, int y, int width, int height,
                         Identifier texture,
                         int u, int v,
                         int regionWidth, int regionHeight,
                         int textureWidth, int textureHeight) {
        super(x, y, width, height, Text.empty());

        this.texture       = texture;
        this.u             = u;
        this.v             = v;
        this.regionWidth   = regionWidth;
        this.regionHeight  = regionHeight;
        this.textureWidth  = textureWidth;
        this.textureHeight = textureHeight;

        this.active = false;
    }

    @Override
    protected final void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        drawBaseTexture(context);

        if (isMouseOver(mouseX, mouseY)) {
            renderHovered(context, mouseX, mouseY, delta);
        }
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return this.visible
                && mouseX >= getX() && mouseX < getX() + width
                && mouseY >= getY() && mouseY < getY() + height;
    }

    protected void drawBaseTexture(DrawContext context) {
        context.drawTexture(
                texture,
                getX(), getY(),
                width, height,          // drawn size on screen
                u, v,                   // texture offset
                regionWidth, regionHeight,  // region to sample
                textureWidth, textureHeight
        );
    }

    /**
     * Called every frame while the cursor hovers over this widget.
     * Implement in subclasses to draw highlights, tooltips, glows, etc.
     *
     * @param context The draw context for this frame
     * @param mouseX  Current mouse X (screen coordinates)
     * @param mouseY  Current mouse Y (screen coordinates)
     * @param delta   Partial tick
     */
    protected void renderHovered(DrawContext context, int mouseX, int mouseY, float delta) {}

    @Override
    public final boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }

    @Override
    public final boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        return false;
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
        // intentionally empty
    }
}
