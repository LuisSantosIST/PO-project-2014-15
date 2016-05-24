package poof.textui.main;

import java.io.*;
import poof.core.*;
import java.util.*;
import pt.utl.ist.po.ui.*;

// FIXME: import project-specific classes

/**
 * Command for loading a file system and the last logged user stored in the given file.
 */
public class Open extends Command<ActualState> /* FIXME: select core type for entity */ {

  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public Open(ActualState as) {
    super(MenuEntry.OPEN, as);
  }
  
  /**
   * Execute the command.
   */
  @Override
  @SuppressWarnings("nls")
  public final void execute() throws InvalidOperation {
    Form f = new Form(title());
    InputString filename = new InputString(f, Message.openFile());

    f.parse();
  
    entity().loadStatus(filename.toString());
    
    ((MainMenu)menu()).showOptionsNonEmpty();
  }
}
