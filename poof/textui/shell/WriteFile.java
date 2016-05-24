package poof.textui.shell;
import poof.core.*;

import java.io.IOException;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.InvalidOperation;


import poof.textui.exception.EntryUnknownException;
import poof.textui.exception.AccessDeniedException;
import poof.textui.exception.IsNotFileException;
// FIXME: import project-specific classes

/**
 * Command for writing in a file of the current working directory.
 * §2.2.8.
 */
public class WriteFile extends Command<ActualState> /* FIXME: select core type for entity */ {

  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public WriteFile(ActualState as) {
    super(MenuEntry.APPEND, as);
  }

  /**
   * Execute the command.
   */
  @Override
  @SuppressWarnings("nls")
  public final void execute() throws InvalidOperation {
    Form f = new Form(title());
    InputString name = new InputString(f, Message.fileRequest());
    InputString text = new InputString(f, Message.textRequest());

    f.parse();


    if (text.value().length() < 20)
    {
        //getFile lanca duas excepcoes; EntryUnknownException, IsNotFileException
      File entrada = entity().getActualDirectory().getFile(name.value());

      //lanca a excepcao AccessDeniedException
      entity().getActualUser().writeOnFile(entrada, text.value()); 
      
    }
    else
      throw new InvalidOperation("texto maior que 20 caracteres ");
  }
}
