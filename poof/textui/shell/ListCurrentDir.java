package poof.textui.shell;

import java.io.*;
import poof.core.*;
import java.util.*;

import pt.utl.ist.po.ui.*;

// FIXME: import project-specific classes

/**
 * Command for showing the content of working directory.
 * §2.2.1.
 */
public class ListCurrentDir extends Command<ActualState> /* FIXME: select core type for entity */ {

  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public ListCurrentDir(ActualState as) {
    super(MenuEntry.LS, as);
  }

  /**
   * Execute the command.
   */
  @Override
  @SuppressWarnings("nls")
  public final void execute() 
  {
    Display d = new Display(title());
    Directory actualdirectory = entity().getActualDirectory();

    List<Entry> entries = actualdirectory.entries();
    
    for (Entry f: entries)
    {
      d.addNewLine(f.toString());
    }
    
    d.display();
  }

}
