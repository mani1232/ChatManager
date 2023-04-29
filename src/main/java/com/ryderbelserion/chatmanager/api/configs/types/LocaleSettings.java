package com.ryderbelserion.chatmanager.api.configs.types;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.configurationdata.CommentsConfiguration;
import ch.jalu.configme.properties.Property;
import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

/**
 * @author RyderBelserion
 *
 * Description: The lang-en.yml options.
 */
public class LocaleSettings implements SettingsHolder {

    // Empty constructor required by SettingsHolder
    public LocaleSettings() {}

    @Override
    public void registerComments(CommentsConfiguration conf) {
        String[] header = {
                "Github: https://github.com/Crazy-Crew",
                "",
                "Issues: https://github.com/Crazy-Crew/ChatManager/issues",
                "Features: https://github.com/Crazy-Crew/ChatManager/issues",
                "Translations: https://github.com/Crazy-Crew/ChatManager/issues"
        };

        conf.setComment("misc", header);
    }

    public static final Property<String> UNKNOWN_COMMAND = newProperty("misc.unknown-command", "<red>This command is not known.</red>");

    public static final Property<String> OUT_OF_ORDER = newProperty("misc.out-of-order", "<red>This is currently out of order until further notice.</red>");

    public static final Property<String> SUCCESSFUL_CONVERSION = newProperty("misc.file-conversion.success", "<green>Successfully converted any possible files while doing %action%.</green>");

    public static final Property<String> FAILED_CONVERSION = newProperty("misc.file-conversion.failed", "<red>Failed to convert the files because: <gold>%reason%</gold></red>");

    public static final Property<String> FEATURE_DISABLED = newProperty("misc.feature-disabled", "<red>This feature is disabled.</red>");

    public static final Property<String> CORRECT_USAGE = newProperty("misc.correct-usage", "<red>The correct usage for this command is</red> <yellow>%usage%</yellow>");

    public static final Property<String> INVALID_PAGE = newProperty("help.invalid-page", "<red>The page</red> <yellow>%page%</yellow> <red>does not exist.</red>");

    public static final Property<String> PAGE_FORMAT = newProperty("help.page-format", "<gold>%command% %args%</gold> <gray>»</gray> <reset>%description%");

    public static final Property<String> HELP_HEADER = newProperty("help.header", "<gray>────────</gray> <gold>ChatManager Help %page%</gold> <gray>────────</gray>");

    public static final Property<String> HELP_FOOTER = newProperty("help.footer", "<gray>────────</gray> <gold>ChatManager Help %page%</gold> %button%");

    @Comment({
            "The only options that work here are run_command, suggest_command, copy_to_clipboard",
            "",
            "Warning: They are case-sensitive names so type them exactly as given above!",
            "",
            "This is what happens if you click the command in the /chatmanager help menu."
    })
    public static final Property<String> HOVER_ACTION = newProperty("help.hover.action", "copy_to_clipboard");

    public static final Property<String> HOVER_FORMAT = newProperty("help.hover.format", "<gray>Click me to run the command.</gray> <gold>%command%</gold>");

    public static final Property<String> PAGE_NEXT = newProperty("help.page-next", " <green>»»»</green>");

    public static final Property<String> PAGE_BACK = newProperty("help.page-back", " <red>«««</red>");

    public static final Property<String> GO_TO_PAGE = newProperty("help.go-to-page", "<gray>Go to page</gray> <gold>%page%</gold>");

    public static final Property<String> INTERNAL_ERROR = newProperty("errors.internal-error", "<red>An internal error has occurred. Please check the console for the full error.</red>");

    public static final Property<String> TOO_MANY_ARGS = newProperty("player.requirements.too-many-args", "<red>You put more arguments then I can handle.</red>");

    public static final Property<String> NOT_ENOUGH_ARGS = newProperty("player.requirements.not-enough-args", "<red>You did not supply enough arguments.</red>");

    public static final Property<String> MUST_BE_PLAYER = newProperty("player.requirements.must-be-player", "<red>You must be a player to use this command.</red>");

    public static final Property<String> MUST_BE_CONSOLE_SENDER = newProperty("player.requirements.must-be-console-sender", "<red>You must be using console to use this command.</red>");

    public static final Property<String> MUST_BE_LOOKING_AT_BLOCK = newProperty("player.requirements.must-be-looking-at-block", "<red>You must be looking at a block.</red>");

    public static final Property<String> COMMAND_CONFIRM_RELOAD = newProperty("command.reload.confirm-reload", "<yellow>Are you sure you want to reload the plugin?</yellow>");

    public static final Property<String> COMMAND_RELOAD = newProperty("command.reload.reload-completed", "<red>You have reloaded the plugin.</red>");
}