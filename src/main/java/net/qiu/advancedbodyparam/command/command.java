package net.qiu.advancedbodyparam.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.EnumArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.qiu.advancedbodyparam.QsAdvancedBodyParameters;
import net.qiu.advancedbodyparam.components.entity.EntityComponentReg;
import net.qiu.advancedbodyparam.util.BodyParts;
import net.qiu.advancedbodyparam.util.argumentTypes.BodyPartsArgumentType;
import net.qiu.advancedbodyparam.util.status.StatusHelper;

import java.util.*;

public class command {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {

        QsAdvancedBodyParameters.LOGGER.info("Registering commands for " + QsAdvancedBodyParameters.MOD_NAME);

        dispatcher.register(CommandManager.literal("qsadvancedbodyparam")

                .then(CommandManager.literal("bloodLevel")

                        .requires(source -> source.hasPermissionLevel(1))
                        .then(CommandManager.literal("get")
                                .executes(ctx ->
                                        getBlood(ctx, null)
                                )

                                .then(CommandManager.argument("target", EntityArgumentType.player())
                                        .executes(ctx ->
                                                getBlood(
                                                        ctx,
                                                        EntityArgumentType.getPlayer(ctx, "target"))
                                        )
                                )
                        )

                        .requires(source -> source.hasPermissionLevel(2))
                        .then(CommandManager.literal("set")
                                .then(CommandManager.argument("blood", IntegerArgumentType.integer(0, 60))
                                        .executes(ctx ->
                                                setBlood(ctx, List.of(), IntegerArgumentType.getInteger(ctx, "blood"))
                                        )
                                )

                                .then(CommandManager.argument("targets", EntityArgumentType.players())
                                        .then(CommandManager.argument("blood", IntegerArgumentType.integer(0, 60))
                                                .executes(ctx ->
                                                        setBlood(
                                                                ctx,
                                                                EntityArgumentType.getPlayers(ctx, "targets"),
                                                                IntegerArgumentType.getInteger(ctx, "blood")
                                                        )
                                                )
                                        )
                                )
                        )
                )

                .then(CommandManager.literal("status")

                        .requires(source -> source.hasPermissionLevel(1))
                        .then(CommandManager.literal("get")
                                .then(CommandManager.argument("bodypart", BodyPartsArgumentType.bodyPartsArgument())
                                        .executes(ctx ->
                                                getStatus(
                                                        ctx,
                                                        null,
                                                        BodyPartsArgumentType.getBodyPart(ctx, "bodypart")
                                                )
                                        )

                                        .then(CommandManager.argument("target", EntityArgumentType.player())
                                                .executes(ctx ->
                                                        getStatus(
                                                                ctx,
                                                                EntityArgumentType.getPlayer(ctx, "target"),
                                                                BodyPartsArgumentType.getBodyPart(ctx, "bodypart")
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static int getBlood(CommandContext<ServerCommandSource> ctx, ServerPlayerEntity player) {

        ServerCommandSource source = ctx.getSource();

        ServerPlayerEntity serverPlayer = (player != null) ? player : source.getPlayer();

        if (serverPlayer == null) {
            source.sendError(Text.translatable("commands.qadvancedbodyparam.sourceerror"));
            return 0;
        }

        return EntityComponentReg.BLOOD_COMPONENT.maybeGet(serverPlayer).map(bloodComponent -> {
            source.sendFeedback(() -> Text.translatable("commands.qadvancedbodyparam.getblood", serverPlayer.getName().getString(), bloodComponent.getBlood()), false);
            return 1;
        }).orElseGet(() -> {
            source.sendError(Text.translatable("commands.qadvancedbodyparam.componenterror", EntityComponentReg.BLOOD_COMPONENT.getId().getNamespace(), serverPlayer.getName().getString()));
            QsAdvancedBodyParameters.LOGGER.error("Could not find blood component on {}", serverPlayer.getName().getString());
            return 0;
        });
    }

    private static int setBlood(CommandContext<ServerCommandSource> ctx, Collection<ServerPlayerEntity> players, int newBlood) {

        ServerCommandSource source = ctx.getSource();

        int len = players.size();

        ServerPlayerEntity sourcePlayer = source.getPlayer();

        if (len <= 1) {

            ServerPlayerEntity serverPlayer = (len != 0) ? players.stream().findFirst().get() : sourcePlayer;

            if (serverPlayer == null) {
                source.sendError(Text.translatable("commands.qadvancedbodyparam.sourceerror"));
                return 0;
            }

            return EntityComponentReg.BLOOD_COMPONENT.maybeGet(serverPlayer).map(bloodComponent -> {
                bloodComponent.setBlood(newBlood);
                source.sendFeedback(() -> Text.translatable("commands.qadvancedbodyparam.setblood", serverPlayer.getName().getString(), bloodComponent.getBlood()), false);

                QsAdvancedBodyParameters.LOGGER.info(
                        "Blood level of {} is set to {}{}", serverPlayer.getName().getString(), bloodComponent.getBlood(),
                        (sourcePlayer != null) ? String.format("by %s", sourcePlayer.getName()) : "");

                return 1;
            }).orElseGet(() -> {

                source.sendError(Text.translatable("commands.qadvancedbodyparam.componenterror",
                        EntityComponentReg.BLOOD_COMPONENT.getId().getPath(),
                        serverPlayer.getName().getString()));
                QsAdvancedBodyParameters.LOGGER.error("Could not find blood component on {}", serverPlayer.getName().getString());

                return 0;
            });
        } else {

            Collection<String> success = new HashSet<>();
            Map<String, ServerPlayerEntity> fail = new HashMap<>();

            for (ServerPlayerEntity player : players) {

                EntityComponentReg.BLOOD_COMPONENT.maybeGet(player).ifPresentOrElse(bloodComponent -> {
                    bloodComponent.setBlood(newBlood);
                    success.add(player.getName().getString());
                }, () -> fail.put(player.getName().getString(), player));
            }

            if (!success.isEmpty()) {

                source.sendFeedback(() -> Text.translatable("commands.qadvancedbodyparam.setmultipleblood",
                        success.size(),
                        Math.max(0, Math.min(newBlood, 60))),
                        false);

                QsAdvancedBodyParameters.LOGGER.info("Blood level of {} is set to {}{}",
                        String.join(", ", success),
                        Math.max(0, Math.min(newBlood, 60)),
                        sourcePlayer != null ? String.format(" by %s", sourcePlayer.getName()) : "");
            }

            if (!fail.isEmpty()) {

                source.sendError(Text.translatable("commands.qadvancedbodyparam.componenterror",
                        EntityComponentReg.BLOOD_COMPONENT.getId().getPath(),
                        String.join(", ", fail.keySet())));

                QsAdvancedBodyParameters.LOGGER.error("Could not find blood component on {}", String.join(", ", fail.keySet()));

                fail.forEach((name, player) -> player.networkHandler.disconnect(Text.translatable("qadvancedbodyparam.disconnect.no_component", EntityComponentReg.STATUS_COMPONENT.getId().getPath())));
            }

            return success.size();
        }
    }

    private static int getStatus(CommandContext<ServerCommandSource> ctx, ServerPlayerEntity player, BodyParts part) {

        ServerCommandSource source = ctx.getSource();

        ServerPlayerEntity serverPlayer = (player != null) ? player : source.getPlayer();

        if (serverPlayer == null) {
            source.sendError(Text.translatable("commands.qadvancedbodyparam.sourceerror"));
            return 0;
        }

        return EntityComponentReg.STATUS_COMPONENT.maybeGet(serverPlayer).map(statusComponent -> {

            HashSet<StatusHelper> helperSet = statusComponent.getStatusSet(part);

            if (helperSet.isEmpty()) {
                source.sendFeedback(() -> Text.translatable("commands.qadvancedbodyparam.getstatusempty", part.name().toLowerCase().replace("_", " "), serverPlayer.getName().getString()), false);
                return 1;
            }

            source.sendFeedback(() -> Text.translatable("commands.qadvancedbodyparam.getstatus", part.name().toLowerCase().replace("_", " "), serverPlayer.getName().getString()), false);

            for (StatusHelper helper : helperSet) {

                String statusName = helper.getStatus().name().toLowerCase().replace("_", " ");

                source.sendFeedback(() -> Text.translatable("commands.qadvancedbodyparam.getstatuslist", statusName, helper.getDuration(), helper.getIntensity()), false);
            }

            return 1;
        }).orElseGet(() -> {

            source.sendError(Text.translatable("commands.qadvancedbodyparam.componenterror", EntityComponentReg.STATUS_COMPONENT.getId().getPath(), serverPlayer.getName().getString()));
            QsAdvancedBodyParameters.LOGGER.error("Could not find status component on {}", serverPlayer.getName().getString());
            serverPlayer.networkHandler.disconnect(Text.translatable("qadvancedbodyparam.disconnect.no_component", EntityComponentReg.STATUS_COMPONENT.getId().getPath()));
            return 0;
        });
    }
}
