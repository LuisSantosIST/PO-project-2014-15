package poof.textui.shell;

import java.io.IOException;
import poof.core.*;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.InvalidOperation;

import poof.textui.exception.EntryUnknownException;
import poof.textui.exception.AccessDeniedException;
import poof.textui.exception.IllegalRemovalException;

// FIXME: import project-specific classes

/**
 * Command for removing an entry of the current working directory.
 * §2.2.3.
 */
public class RemoveEntry extends Command<ActualState> /* FIXME: select core type for receiver */ {

  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public RemoveEntry(ActualState as) {
    super(MenuEntry.RM, as);
  }

  /**
   * Execute the command.
   */
  @Override
  @SuppressWarnings("nls")
  public final void execute() throws InvalidOperation {
    Form f = new Form(title());
    InputString name = new InputString(f, Message.nameRequest());

    f.parse();

    //lanca excepcao: EntryUnkownException, AccessDeniedException, IllegalRemovalException
    entity().getActualUser().removeEntry(entity().getActualDirectory(), name.value());
    
  }
}
