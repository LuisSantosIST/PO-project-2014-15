package poof.textui.shell;

import java.io.*;
import poof.core.*;
import java.util.*;

import pt.utl.ist.po.ui.*;

/**
 * Command for showing an entry of the current working directory.
 * §2.2.2.
 */
public class ShowBiggest extends Command<ActualState> /* FIXME: select core type for entity */ {

  /**
   * Constructor.
   * 
   * @param entity the target entity.
   */
  public ShowBiggest(ActualState as) {
    super("Mostra superiores a ", as);
  }

  /**
   * Execute the command.
   */
  @Override
  @SuppressWarnings("nls")
  public final void execute() throws InvalidOperation {
    Display d = new Display(title());

    Form f = new Form(title());
    InputInteger size = new InputInteger(f, "tamanho: ");
    f.parse();

    List<Entry> list = entity().getActualDirectory().entries();

    for (Entry e: list)
    {
      if (e.getSize() > size.value())
      {
        d.addNewLine(e.toString());
      }
    }
    d.display();
  }
}