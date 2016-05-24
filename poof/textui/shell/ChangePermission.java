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

/**
 * Command for changing the permission of an entry of the current working directory.
 * §2.2.10.
 */
public class ChangePermission extends Command<ActualState> /* FIXME: select core type for entity */ {

  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public ChangePermission(ActualState as) {
    super(MenuEntry.CHMOD, as);
  }

  /**
   * Execute the command.
   */
  @Override
  @SuppressWarnings("nls")
  public final void execute() throws InvalidOperation {
    Display d = new Display(title());

    Form f = new Form(title());
    InputString name = new InputString(f, Message.nameRequest());
    InputString permission = new InputString(f, Message.writeMode());
    f.parse();

    entity().getActualDirectory().entryUnknown(name.value()); //lanca excepcao EntryUnknownException

    Entry entrada = entity().getActualDirectory().getEntry(name.value());

    if (permission.value().equals("s"))
      entity().getActualUser().changeEntryPermissions(entrada, true); //lanca excepecao AcessDeniedException

    else
      entity().getActualUser().changeEntryPermissions(entrada, false);
  }
}
