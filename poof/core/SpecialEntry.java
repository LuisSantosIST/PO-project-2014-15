package poof.core;
import java.util.*;
import poof.textui.exception.EntryExistsException;
import poof.textui.exception.IllegalRemovalException;
import poof.textui.exception.EntryUnknownException;
import poof.textui.exception.IsNotDirectoryException;
import poof.textui.exception.IsNotFileException;
/*
*A class SpecialEntry destina-se a representar entradas especiais como o "." e o ".." funcionando como um apontador para algo do tipo
*	directorio e implementando toda uma interface do tipo Folder que vai manipular o directorio para o qual aponta.
*/
public class SpecialEntry extends Entry implements Folder
{	
	//directorio que esta a representar.
	Directory _link;

	/*
	*construtor:
	*		@param link: directorio para onde vai apontar.
	*		@param name: nome que queremos que a entrada tenha, no caso desta aplicacao vai tomar o nome "." ou "..".
	*Nota: 
	*	todos os outros campos herdados do tipo Entry iram estar a null, uma vez que o que interessa sao os campos do directorio link.
	*/
	public SpecialEntry(Directory link, String name)
	{
		super(name, null);
		_link = link;
	}

	/*
	*{@inheritDoc}
	*getPermission: aplicado ao directorio mascarado, _link.
	*/
	@Override
	@SuppressWarnings("nls")
	public Boolean getPermission()
	{
		return _link.getPermission();
	}

	/*
	*{@inheritDoc}
	*getSize: aplicado ao directorio mascarado, _link.
	*/
	@Override
	@SuppressWarnings("nls")
	public int getSize()
	{
		return _link.getSize();
	}

	/*
	*{@inheritDoc}
	*getOwner: aplicado ao directorio mascarado, _link.
	*/
	@Override
	@SuppressWarnings("nls")
	public User getOwner()
	{
		return _link.getOwner();
	}

	/*{@inheritDoc}*/
	@Override
	@SuppressWarnings("nls")
	public void isRemovable() throws IllegalRemovalException
	{
		throw new IllegalRemovalException();
	}

	/*
	*getLinkedDirectory:
	*		@return: retorna o directorio que esta a mascarar/apontar.
	*/
	public Directory getLinkedDirectory()
	{
		return _link;
	}

	/*
	**{@inheritDoc}
	*toString:
	*		@return: retorna a string que representa este SpecialEntry.
	*/
	@Override
	@SuppressWarnings("nls")
	public String toString()
	{
		return "d " + super.toString();
	}

	/*
	*{@inheritDoc}
	*createSubDirectory: aplicado ao directorio mascarado, _link.
	*/
	public void createSubDirectory(String name, User owner) throws EntryExistsException
	{
		_link.createSubDirectory(name, owner);
	}

	/*
	*{@inheritDoc}
	*showAbsolutePath: aplicado ao directorio mascarado, _link.
	*/
	public  String showAbsolutePath()
	{
		return _link.showAbsolutePath();
	}

	/*
	*{@inheritDoc}
	*getFather: aplicado ao directorio mascarado, _link.
	*/
	public Directory getFather()
	{
		return _link.getFather();
	}

	/*
	**{@inheritDoc}
	*putNewFile: aplicado ao directorio mascarado, _link.
	*/
	public void putNewFile(File newEntry) throws EntryExistsException
	{
		_link.putNewFile(newEntry);
	}

	/*
	**{@inheritDoc}
	*putNewDirectory: aplicado ao directorio mascarado, _link.
	*/
	public void putNewDirectory(Directory newEntry) throws EntryExistsException
	{
		_link.putNewDirectory(newEntry);
	}

	/*
	**{@inheritDoc}
	*createFile: aplicado ao directorio mascarado, _link.
	*/
	public void createFile(String name, User owner) throws EntryExistsException
	{
		_link.createFile(name, owner);
	}

	/*
	**{@inheritDoc}
	*deleteEntry: aplicado ao directorio mascarado, _link.
	*/
	public void deleteEntry(String name) throws IllegalRemovalException, EntryUnknownException
	{
		_link.deleteEntry(name);
	}

	/*
	**{@inheritDoc}
	*entries: aplicado ao directorio mascarado, _link.
	*/
	public List<Entry> entries()
	{
		return _link.entries();
	}

	/*
	**{@inheritDoc}
	*fileExists: aplicado ao directorio mascarado, _link.
	*/
	public boolean fileExists(String name) throws IsNotFileException
	{
		return _link.fileExists(name);
	}

	/*
	**{@inheritDoc}
	*directoryExists: aplicado ao directorio mascarado, _link.
	*/
	public boolean directoryExists(String name) throws IsNotDirectoryException
	{
		return _link.directoryExists(name);
	}

	/*
	**{@inheritDoc}
	*entryUnknown: aplicado ao directorio mascarado, _link.
	*/
	public boolean entryUnknown(String name) throws EntryUnknownException
	{
		return _link.entryUnknown(name);
	}

	/*
	*{@inheritDoc}
	*entryExists: aplicado ao directorio mascarado, _link.
	*/
	public boolean entryExists(String name) throws EntryExistsException
	{
		return _link.entryExists(name);
	}

	/*
	*{@inheritDoc}
	*getEntry: aplicado ao directorio mascarado, _link.
	*/
	public Entry getEntry(String name) throws EntryUnknownException
    {
    	return _link.getEntry(name);
    }

    /*
    *{@inheritDoc}
    *getDirectory: aplicado ao directorio mascarado, _link.
    */
	public  Directory getDirectory(String name) throws EntryUnknownException, IsNotDirectoryException
	{
		return _link.getDirectory(name);
	}

	/*
	*{@inheritDoc}
	*getFile:  aplicado ao directorio mascarado, _link.
	*/
	public  File getFile(String name) throws EntryUnknownException, IsNotFileException
	{
		return _link.getFile(name);
	}

}