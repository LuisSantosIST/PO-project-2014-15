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

/**
 * Command for showing an entry of the current working directory.
 * §2.2.2.
 */
public class ListEntry extends Command<ActualState> /* FIXME: select core type for entity */ {

  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public ListEntry(ActualState as) {
    super(MenuEntry.LS_ENTRY, as);
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

    f.parse();

    //lanca excepcao EntryUnknownException caso nao exista essa entrada
    d.add(entity().getActualDirectory().getEntry(name.value()).toString());

    d.display();
  }
}
