package poof.textui.user;


import java.util.*;
import java.io.*;
import pt.utl.ist.po.ui.*;
import poof.core.*;

// FIXME: import project-specific classes

/**
 * Command for the showing existing users.
 * §2.3.2.
 */
public class ListUsers extends Command<ActualState> /* FIXME: select core type for entity */ {

  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public ListUsers(ActualState as) {
    super(MenuEntry.LIST_USERS, as);
  }

  @Override
  @SuppressWarnings("nls")
  public final void execute()
  { 
      FileSystem fileSystem = entity().getFileSystem();
      Display d = new Display(title());

      List<User> list = fileSystem.users();

      for(User i: list)
      {
        d.addNewLine(i.toString());
      }

      d.display();
  }
}
