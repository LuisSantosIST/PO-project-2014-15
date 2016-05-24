package poof.textui.shell;

import java.io.IOException;
import poof.core.*;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.InvalidOperation;

public class SearchLargestEntry extends Command<ActualState> /* FIXME: select core type for entity */ {

  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public SearchLargestEntry(ActualState as) {
    super("entrada maior", as);
  }

  /**
   * Execute the command.
   */
  @Override
  @SuppressWarnings("nls")
  public final void execute() throws InvalidOperation {
    Display d = new Display(title());

    Entry largest = entity().getFileSystem().lookForLargest();
    d.add(largest.toString());
    d.display();    
  }
}