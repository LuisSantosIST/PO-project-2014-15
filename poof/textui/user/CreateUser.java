package poof.textui.user;

import java.io.IOException;
import poof.core.*;
import poof.textui.exception.AccessDeniedException;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.InvalidOperation;

// FIXME: import project-specific classes

/**
 * Command for creating a user.
 * §2.3.1.
 */
public class CreateUser extends Command<ActualState> /* FIXME: select core type for entity */ {

  //private int _ids = 0;
  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public CreateUser(ActualState as) {
    super(MenuEntry.CREATE_USER, as);
  }

  /**
   * Execute the command.
   */
  @Override
  @SuppressWarnings("nls")
  public final void execute() throws InvalidOperation
  {
    Form f = new Form(title());
    InputString userId = new InputString(f, Message.usernameRequest());
    InputString name = new InputString(f, Message.nameRequest());

    f.parse();

    //createNewUser lanca a excepcao UserExistsException e AccessDeniedException
    entity().getActualUser().createNewUser(entity().getFileSystem(), name.value(), userId.value());
  }
}
