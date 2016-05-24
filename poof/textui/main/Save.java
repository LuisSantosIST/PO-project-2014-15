package poof.textui.main;

import java.io.*;
import poof.core.*;
import java.util.*;
import pt.utl.ist.po.ui.*;

// FIXME: import project-specific classes

/**
 * Command for saving the relevant applicaion state.
 */
public class Save extends Command<ActualState> /* FIXME: select core type for entity */ {

  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public Save(ActualState as) {
    super(MenuEntry.SAVE, as);
  }

  /**
   * Execute the command.
   */
  @Override
  @SuppressWarnings("nls")
  public final void execute() throws InvalidOperation {
    Form f = new Form(title());
    InputString filename = new InputString(f, Message.newSaveAs());

    f.parse();

    entity().saveStatus(filename.toString());
  }
}
