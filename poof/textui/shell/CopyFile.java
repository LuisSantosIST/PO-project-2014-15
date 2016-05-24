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
public class CopyFile extends Command<ActualState> /* FIXME: select core type for entity */ {

  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public CopyFile(ActualState as) {
    super("Copiar ficheiro para outro ", as);
  }

  /**
   * Execute the command.
   */
  @Override
  @SuppressWarnings("nls")
  public final void execute() throws InvalidOperation {
    Display d = new Display(title());

    Form f = new Form(title());
    InputString name1 = new InputString(f, Message.fileRequest());
    InputString name2 = new InputString(f, "segundo "+Message.fileRequest());
    f.parse();

    if (!entity().getActualDirectory().fileInDirectory(name2.value()))
      entity().getActualUser().createNewFile(entity().getActualDirectory(), name2.value());
    
    File file = entity().getActualDirectory().getFile(name2.value());
    file.addContent(entity().getActualDirectory().getFile(name1.value()).showContent());
  }
}