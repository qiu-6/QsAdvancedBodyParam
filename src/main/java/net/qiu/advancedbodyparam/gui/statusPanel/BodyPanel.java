package net.qiu.advancedbodyparam.gui.statusPanel;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.qiu.advancedbodyparam.util.BodyParts;

public class BodyPanel extends HoverableWidget {

    private final Text tooltip;

    public BodyPanel(int x, int y, int multiplier, Identifier texture, BodyParts parts, String modelType) {
        super(
                getX(x, parts, modelType), y,
                getRegionalWidth(parts, modelType) * multiplier,
                parts.getRegionHeight() * multiplier,
                texture,
                parts.getU(), parts.getV(),
                getRegionalWidth(parts, modelType),
                parts.getRegionHeight(),
                64, 64);

        tooltip = Text.literal(parts.name().replace("_", " "));
    }

    private static int getX(int x, BodyParts parts, String modelType) {

        if (!parts.equals(BodyParts.RIGHT_ARM)) return x;

        return x + (modelType.equals("slim") ? 2 : 0);
    }

    private static int getRegionalWidth(BodyParts parts, String modelType) {

        if (!parts.name().contains("ARM")) return parts.getRegionWidth();

        return modelType.equals("slim") ? 3 : 4;
    }

    @Override
    protected void renderHovered(DrawContext context, int mouseX, int mouseY, float delta) {

        if (tooltip == null) return;

        context.drawTooltip(
                MinecraftClient.getInstance().textRenderer,
                tooltip,
                mouseX, mouseY);
    }
}
