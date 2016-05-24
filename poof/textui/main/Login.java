package poof.textui.main;

import poof.core.*;
import java.io.IOException;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.InvalidOperation;

import poof.textui.exception.UserUnknownException;

// FIXME: import project-specific classes

/**
 * Command for the login option.
 * §2.1.2.
 */
public class Login extends Command<ActualState> /* FIXME: select core type for entity */ {

  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public Login(ActualState as) {
    super(MenuEntry.LOGIN, as);
  }

  /**
   * Execute the command.
   */
  @Override
  @SuppressWarnings("nls")
  public final void execute() throws InvalidOperation { //nao devia ser UserUnknownException?
    Form f = new Form(title());
    InputString userId = new InputString(f, Message.usernameRequest());

    f.parse();

    //getUser lanca a excepcao; UserUnknownException
    User u = entity().getFileSystem().getUser(userId.value());

    entity().changeActualUser(u);
    entity().changeActualDirectory(u.getHomeDirectory());
  }
}
