package poof.textui.shell;

import java.io.IOException;
import poof.core.*;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.InvalidOperation;

// FIXME: import project-specific classes


import poof.textui.exception.EntryUnknownException;
import poof.textui.exception.AccessDeniedException;
import poof.textui.exception.UserUnknownException;

/**
 * Command for changing the owner of an entry of the current working directory.
 * §2.2.11.
 */
public class ChangeOwner extends Command<ActualState> /* FIXME: select core type for entity */ {

  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public ChangeOwner(ActualState as) {
    super(MenuEntry.CHOWN, as);
  }

  /**
   * Execute the command.
   */
  @Override
  @SuppressWarnings("nls")
  public final void execute() throws InvalidOperation {
    Display d = new Display(title());

    Form f = new Form(title());
    InputString entryName = new InputString(f, Message.nameRequest());
    InputString username = new InputString(f, Message.usernameRequest());
    f.parse();

    entity().getActualDirectory().entryUnknown(entryName.value()); //lanca excepcao EntryUnknownException

    Entry entrada = entity().getActualDirectory().getEntry(entryName.value());
    User u = entity().getFileSystem().getUser(username.value());
    entity().getActualUser().changeEntryOwner(entrada, u); 
    // getUser lanca excepcao UserUnknownException e changeEntryOwner lanca a excepcao AccessDeniedException

  }
}
