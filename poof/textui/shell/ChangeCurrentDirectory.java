package poof.textui.shell;

import java.io.IOException;
import poof.core.*;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.InvalidOperation;

import poof.textui.exception.EntryUnknownException;
import poof.textui.exception.IsNotDirectoryException;

// FIXME: import project-specific classes

/**
 * Command for changing current working directory.
 * §2.2.4.
 */
public class ChangeCurrentDirectory extends Command<ActualState> /* FIXME: select core type for entity */ {

  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public ChangeCurrentDirectory(ActualState as) {
    super(MenuEntry.CD, as);
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

    //getDirectory lanca duas excepcoes: EntryUnknownException, IsNotDirectoryException
    entity().changeActualDirectory(entity().getActualDirectory().getDirectory(name.value()));
  }
}
