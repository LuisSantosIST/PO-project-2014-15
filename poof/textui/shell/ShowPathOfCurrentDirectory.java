package poof.textui.shell;

import java.io.*;
import poof.core.*;
import java.util.*;

import pt.utl.ist.po.ui.*;
// FIXME: import project-specific classes

/**
 * Command for showing the full path of the current working directory.
 * §2.2.7.
 */
public class ShowPathOfCurrentDirectory extends Command<ActualState> /* FIXME: select core type for entity */ {

  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public ShowPathOfCurrentDirectory(ActualState as) {
    super(MenuEntry.PWD, as);
  }

  /**
   * Execute the command.
   */
  @Override
  @SuppressWarnings("nls")
  public final void execute()
  {
    Display d = new Display(title());

    Directory actual = entity().getActualDirectory();

    d.add(actual.showAbsolutePath());

    d.display();
  }
}
