package poof.textui.main;

import java.io.IOException;
import poof.core.*;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputInteger;
import pt.utl.ist.po.ui.InvalidOperation;

// FIXME: import project-specific classes

/**
 * Command for creating a new file system and logging the root user.
 */
public class New extends Command<ActualState> /* FIXME: select core type for entity */ {
  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public New(ActualState as) {
    super(MenuEntry.NEW, as);
  }

  /**
   * Execute the command.
   */
  @Override
  @SuppressWarnings("nls")
  public final void execute() throws InvalidOperation { //em que sentido isto pode lançar uma excepcao?
  ActualState newAs = new ActualState();
  
  entity().changeActualUser(newAs.getActualUser());
  entity().changeActualDirectory(newAs.getActualDirectory());
  entity().changeActualFileSystem(newAs.getFileSystem());

  ((MainMenu)menu()).showOptionsNonEmpty();
  }
}
