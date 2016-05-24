package poof.textui.shell;

import java.io.IOException;
import poof.core.*;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.InvalidOperation;

// FIXME: import project-specific classes

/**
 * Command for viewing the content of a file of the current working directory.
 * §2.2.9.
 */
public class ViewFile extends Command<ActualState> /* FIXME: select core type for entity */ {

  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public ViewFile(ActualState as) {
    super(MenuEntry.CAT, as);
  }

  /**
   * Execute the command.
   */
  @Override
  @SuppressWarnings("nls")
  public final void execute() throws InvalidOperation {
    Display d = new Display(title());

    Form f = new Form(title());
    InputString name = new InputString(f, Message.fileRequest());

    f.parse();

    //getFile ja lanca as excepcoes; EntryUnknownException, IsNotFileException
    d.add(entity().getActualDirectory().getFile(name.value()).showContent());
    d.display();
    
  }
}
