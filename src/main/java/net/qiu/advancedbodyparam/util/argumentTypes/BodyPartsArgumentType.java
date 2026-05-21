package net.qiu.advancedbodyparam.util.argumentTypes;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;
import net.qiu.advancedbodyparam.util.BodyParts;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class BodyPartsArgumentType implements ArgumentType<BodyParts> {

    private static final DynamicCommandExceptionType INVALID_BODY_PART_ELEMENT =
            new DynamicCommandExceptionType(obj -> Text.translatable("qadvancedbodyparam.commands.error.invalid_body_enum", obj));

    private BodyPartsArgumentType() {}

    public static BodyPartsArgumentType bodyPartsArgument() {
        return new BodyPartsArgumentType();
    }

    public static BodyParts getBodyPart(CommandContext<?> context, String argumentName) {
        return context.getArgument(argumentName, BodyParts.class);
    }

    private static String toInputString(BodyParts part) {
        return part.name().toLowerCase().replace("_", "");
    }

    @Override
    public BodyParts parse(StringReader reader) throws CommandSyntaxException {
        String input = reader.readUnquotedString();

        return Arrays.stream(BodyParts.values())
                .filter(bp -> toInputString(bp).equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> INVALID_BODY_PART_ELEMENT.createWithContext(reader, input));
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return CommandSource.suggestMatching(
                Arrays.stream(BodyParts.values()).map(BodyPartsArgumentType::toInputString),
                builder
        );
    }

    @Override
    public Collection<String> getExamples() {
        return Arrays.stream(BodyParts.values())
                .map(BodyPartsArgumentType::toInputString)
                .collect(Collectors.toList());
    }
}
