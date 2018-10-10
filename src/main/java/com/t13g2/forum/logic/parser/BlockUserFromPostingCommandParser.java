package com.t13g2.forum.logic.parser;

import static com.t13g2.forum.logic.parser.CliSyntax.PREFIX_USER_NAME;

import java.util.stream.Stream;

import com.t13g2.forum.commons.core.Messages;
import com.t13g2.forum.logic.commands.BlockUserFromPostingCommand;
import com.t13g2.forum.logic.parser.exceptions.ParseException;
import com.t13g2.forum.model.forum.User;


/**
 * Parses input arguments and block a certain user from posting.
 */
public class BlockUserFromPostingCommandParser implements Parser<BlockUserFromPostingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the BlockUserFromPostingCommand.
     * and returns an BlockUserFromPostingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public BlockUserFromPostingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_USER_NAME);
        if (!arePrefixesPresent(argMultimap, PREFIX_USER_NAME)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                BlockUserFromPostingCommand.MESSAGE_USAGE));
        }

        /*go to storage and get user id  bases on user name, then get user from user id*/
        User userToBlock = new User();

        return new BlockUserFromPostingCommand(userToBlock);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}