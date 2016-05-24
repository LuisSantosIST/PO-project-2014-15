package poof.textui.main;

import java.io.IOException;

import pt.utl.ist.po.ui.*;
import poof.core.*;
import poof.textui.user.CreateUser;
import poof.textui.user.ListUsers;

// FIXME: import project-specific classes

/**
 * Command for showing the user menu.
 */
public class ShowMenuUser extends Command<ActualState> /* FIXME: select core type for entity */ {
  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public ShowMenuUser(ActualState as) {
    super(MenuEntry.MENU_USER_MGT, as); //
  }

  /**
   * Execute the command.
   */
  @Override
  @SuppressWarnings("nls")
  public final void execute() throws InvalidOperation {
    Command<?>[] commands = {
      new CreateUser(entity()),
      new ListUsers(entity()),
    };
    
    Menu userMenu = new Menu(poof.textui.user.MenuEntry.TITLE, commands);
    userMenu.open();
  }
}
