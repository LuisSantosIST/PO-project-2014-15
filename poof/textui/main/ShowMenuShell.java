package poof.textui.main;

import java.io.*;
import poof.core.*;

import pt.utl.ist.po.ui.*;

import poof.textui.shell.*;

// FIXME: import project-specific classes

/**
 * Command for showing the shell menu.
 */
public class ShowMenuShell extends Command<ActualState> /* FIXME: select core type for entity */ {

  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public ShowMenuShell(ActualState as) {
    super(MenuEntry.MENU_SHELL, as);
  }

  /**
   * Execute the command.
   */
  @Override
  @SuppressWarnings("nls")
  public final void execute() throws InvalidOperation {
    //FIXME /*FIXME: entity argument*/
    Command<?>[] commands = {
      new ListCurrentDir(entity()),
      new ListEntry(entity()),
      new CopyFile(entity()),
      new RemoveEntry(entity()),
      new ShowBiggest(entity()),
      new ChangeCurrentDirectory(entity()),
      new CreateFile(entity()),
      new CreateDirectory(entity()),
      new ShowPathOfCurrentDirectory(entity()),
      new WriteFile(entity()),
      new ViewFile(entity()),
      new ChangePermission(entity()),
      new ChangeOwner(entity())
      //,new SearchLargestEntry(entity())
    };
    
    Menu shellMenu = new Menu(poof.textui.shell.MenuEntry.TITLE, commands);
    shellMenu.open();
  }
}
