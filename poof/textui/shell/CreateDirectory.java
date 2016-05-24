package poof.textui.shell;

import java.io.IOException;
import poof.core.*;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.InvalidOperation;

import poof.textui.exception.EntryExistsException;
import poof.textui.exception.AccessDeniedException;

// FIXME: import project-specific classes

/**
 * Command for creating a directory in the current working directory.
 * §2.2.6.
 */
public class CreateDirectory extends Command<ActualState> /* FIXME: select core type for entity */ {
  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public CreateDirectory(ActualState as) {
    super(MenuEntry.MKDIR, as);
  }

  /**
   * Execute the command.
   */
  @Override
  @SuppressWarnings("nls")
  public final void execute() throws InvalidOperation {
    Form f = new Form(title());
    InputString name = new InputString(f, Message.directoryRequest());

    f.parse();

    //createDirectory lanca a excepcao AccessDeniedException e a EntryExistsException 
    entity().getActualUser().createDirectory(entity().getActualDirectory(), name.value());
  }
}
