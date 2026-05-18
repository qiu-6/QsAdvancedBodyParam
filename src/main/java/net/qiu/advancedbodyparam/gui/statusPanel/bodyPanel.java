package net.qiu.advancedbodyparam.gui.statusPanel;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import net.qiu.advancedbodyparam.QsAdvancedBodyParameters;
import net.qiu.advancedbodyparam.util.enums.bodyParts;

public class bodyPanel extends hoverableWidget{

    public bodyPanel(int x, int y, int multiplier, Identifier texture, bodyParts parts, String modelType) {
        super(
                getX(x, parts, modelType), y,
                getRegionalWidth(parts, modelType) * multiplier,
                parts.getRegionHeight() * multiplier,
                texture,
                parts.getU(), parts.getV(),
                getRegionalWidth(parts, modelType),
                parts.getRegionHeight(),
                64, 64);
    }

    private static int getX(int x, bodyParts parts, String modelType) {

        if (!parts.equals(bodyParts.RIGHT_ARM)) return x;

        return x + (modelType.equals("slim") ? 2 : 0);
    }

    private static int getRegionalWidth(bodyParts parts, String modelType) {

        if (!parts.name().contains("ARM")) return parts.getRegionWidth();

        QsAdvancedBodyParameters.LOGGER.info(String.valueOf(modelType.equals("slim") ? 3 : 4));
        return modelType.equals("slim") ? 3 : 4;
    }

    @Override
    protected void renderHovered(DrawContext context, int mouseX, int mouseY, float delta) {
        // Keep the parent's brightness overlay
        super.renderHovered(context, mouseX, mouseY, delta);
    }
}
