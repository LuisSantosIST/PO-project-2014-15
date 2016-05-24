package poof.core;
import java.util.*;

public class CompositeIterator implements Iterator<Entry>
{
	Stack<Iterator<Entry>> stack = new Stack<Iterator<Entry>>();

	public CompositeIterator(Iterator<Entry> iterator)
	{
		stack.push(iterator);
	}

	public Entry next()
	{
		if (hasNext())
		{
			Iterator<Entry> iterator = stack.peek();
			Entry component = iterator.next();
			if (component instanceof Directory) // se esse component for um directorio temos de iterar sobre ele.
				stack.push(component.iterator());
			return component;
		}
		else
			return null;
	}
	
	public boolean hasNext()
	{
		if (stack.empty())
			return false;

		Iterator<Entry> iterator = stack.peek();
		if (!iterator.hasNext())
		{
			stack.pop();
			return hasNext();
		}
		else
			return true;
	}

	public void remove()
	{
		throw new UnsupportedOperationException();
	}	 
}
