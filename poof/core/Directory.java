package poof.core;
import java.util.*;
import poof.textui.exception.*;
/*
*A class Directory vai representar um directorio de trabalho sobre o qual podemos realizar uma serie de operacoes.
*/
public class Directory extends Entry implements Folder
{
	//Map que vai guardar as Entradas presentes dentro do directorio.
	Iterator<Entry> iterator = null;
	private Map<String, Entry> _entries = new TreeMap<String, Entry>();
	
	/*
	*construtor:
	*		@param name: e o nome com que vamos querer aquela pasta.
	*		@param owner: o dono responsavel pela pasta a criar.
	*/
	public Directory(String name, User owner)
	{
		super(name, owner);

		SpecialEntry point = new SpecialEntry(this, ".");
		SpecialEntry pointPoint = new SpecialEntry(this, "..");

		_entries.put(pointPoint.getName(), pointPoint);
		_entries.put(point.getName(), point);
	}

	/*
	*construtor:
	*		@param name: e o nome com que vamos querer aquela pasta.
	*		@param owner: o dono responsavel pela pasta a criar.
	*		@param father: vai ser a pasta que vai conter esta pasta ("..").
	*/
	public Directory(String name, User owner, Directory father)
	{
		super(name, owner); 

		SpecialEntry point = new SpecialEntry(this, ".");
		SpecialEntry pointPoint = new SpecialEntry(father, "..");

		_entries.put(pointPoint.getName(), pointPoint);
		_entries.put(point.getName(), point); 
	}

	@Override
	@SuppressWarnings("nls")
	public int getSize()
	{
		return _entries.size() * 8;
	}
	/*
	*iterator:
	*		@return: retorna o iterador.
	*/
	@Override
	@SuppressWarnings("nls")
	public Iterator<Entry> iterator() 
	{
        return new CompositeIterator(this.entries().iterator());
    }

	/*{@inheritDoc}*/
	@Override
	@SuppressWarnings("nls")
	public void isRemovable() throws IllegalRemovalException { }

	/*{@inheritDoc}*/
	public void createSubDirectory(String name, User owner) throws EntryExistsException
	{
		this.entryExists(name);

		Directory d = new Directory(name, owner, this); 
		_entries.put(d.getName(), d);
	}

	/*{@inheritDoc}*/
	public  String showAbsolutePath()
	{
		List<Directory> list = new ArrayList<Directory>();
		Directory actual = this;
		String path = "/";

		list.add(actual);
		while (!actual.getName().equals("/"))
		{
		 	actual = actual.getFather();
			list.add(actual);
		}

		Collections.reverse(list);
		list.remove(0);
    	int n = list.size();

    	for(Directory dir: list)
    	{
    		path += dir.getName();
      		if(--n == 0)
        		break;
      		path += "/";
    	}

		 return path; 
	}

	/*{@inheritDoc}*/
	@Override
	@SuppressWarnings("nls")
	public String toString()
	{
		return "d " + super.toString();
	}

	
	/*{@inheritDoc}*/
	@Override
	@SuppressWarnings("nls")
	public Directory getFather()
	{
		SpecialEntry father = (SpecialEntry)_entries.get("..");
		return father.getLinkedDirectory();
	}

	/*{@inheritDoc}*/
	public void putNewFile(File newEntry) throws EntryExistsException
	{
		this.entryExists(newEntry.getName());

		_entries.put(newEntry.getName(), newEntry);
	}

	/*{@inheritDoc}*/
	public void putNewDirectory(Directory newEntry) throws EntryExistsException
	{
		this.entryExists(newEntry.getName());

		_entries.put(newEntry.getName(), newEntry);
	}

	/*{@inheritDoc}*/
	public void createFile(String name, User owner) throws EntryExistsException
	{
		this.entryExists(name);

		File f = new File(name, owner, this);
		_entries.put(f.getName(),  f);
	}

	/*{@inheritDoc}*/
	public void deleteEntry(String name) throws IllegalRemovalException, EntryUnknownException
	{
		this.entryUnknown(name);
		_entries.get(name).isRemovable();
		_entries.remove(name);
	}

	/*{@inheritDoc}*/
	public List<Entry> entries()
	{
		return new ArrayList<Entry>(_entries.values());
	}


	/*{@inheritDoc}*/
	public Collection<String> entriesNames()
	{
		return Collections.unmodifiableCollection(_entries.keySet());
    }

    /*{@inheritDoc}*/
    public boolean fileExists(String name) throws IsNotFileException
    {
		if (_entries.containsKey(name) && _entries.get(name) instanceof File)
			return true;
		throw new IsNotFileException(name);
    }

	/*{@inheritDoc}*/
    public boolean directoryExists(String name) throws IsNotDirectoryException
    {
    	if (_entries.containsKey(name) && _entries.get(name) instanceof Folder)
    		return true;
		throw new IsNotDirectoryException(name);
    }

    /*{@inheritDoc}*/
    public boolean entryUnknown(String name) throws EntryUnknownException
    {
    	if (_entries.containsKey(name))
    		return true;
    	throw new EntryUnknownException(name);
    }

    /*{@inheritDoc}*/
    public boolean entryExists(String name) throws EntryExistsException
    {
    	if (_entries.containsKey(name))
    		throw new EntryExistsException(name);
    	return false;
    }

    /*{@inheritDoc}*/
    public Entry getEntry(String name) throws EntryUnknownException
    {
    	this.entryUnknown(name);
    	return _entries.get(name);
    }

    /*{@inheritDoc}*/
    public  Directory getDirectory(String name) throws EntryUnknownException, IsNotDirectoryException
    {
    	this.entryUnknown(name);
    	this.directoryExists(name);
    	
    	if (_entries.get(name) instanceof SpecialEntry)
    	{
    		SpecialEntry entry = (SpecialEntry)_entries.get(name);
    		return entry.getLinkedDirectory();
    	}
    	else
    		return (Directory)_entries.get(name);
    }

    /*{@inheritDoc}*/
    public  File getFile(String name) throws EntryUnknownException, IsNotFileException
    {
    	this.entryUnknown(name);
    	this.fileExists(name);
   
    	return	(File)_entries.get(name);
    }

    public boolean fileInDirectory(String name)
    {
		if (_entries.containsKey(name) && _entries.get(name) instanceof File)
			return true;
		return false;
    }
}
