package poof.core;
import java.util.*;
import poof.textui.exception.EntryExistsException;
import poof.textui.exception.IllegalRemovalException;
import poof.textui.exception.EntryUnknownException;
/*
*A class File vai representar um ficheiro sobre o qual podemos realizar uma serie de operacoes.
*/
public class File extends Entry
{	
	//String que vai conter o conteudo do ficheiro em causa.
	private String _content = "";
	//Directory representa o directorio que vai conter o ficheiro em questao.
	private Directory _father;

	/*
	*construtor:
	*		@param name: nome do ficheiro que queremos crias.
	*		@param owner: user responsavel pelo ficheiro a criar.
	*		@param father: directorio que vai conter o ficheiro.
	*/
	public File(String name, User owner, Directory father)
	{
		super(name, owner);
		_father = father;
	}

	@Override
	@SuppressWarnings("nls")
	public int getSize()
	{
		if (_content.equals(""))
			return 0;
		return _content.length() + 1;
	}

	@Override
	@SuppressWarnings("nls")
	public void isRemovable() throws IllegalRemovalException { }

	@Override
	@SuppressWarnings("nls")
	public Directory getFather()
	{
		return _father;
	}

	@Override
	@SuppressWarnings("nls")
	public String toString()
	{
		return "- " + super.toString();
	}

	/*
	*changeDirectory:
	*		@param newDirectory: directorio para onde queremos mudar o ficheiro em causa.
	*		@exception EntryExistsException: lancada se ja existir uma entrada com o nome deste ficheiro dentro do directorio destino.
	*		@exception IlligalRemovalException: lancada pelo metodo deleteEntry.
	*		@exception EntryUnknownException: lancada pelo metodo deleteEntry.
	*/
	public void changeDirectory(Directory newDirectory) throws EntryExistsException, IllegalRemovalException, EntryUnknownException
	{
		newDirectory.putNewFile(this);
		_father.deleteEntry(this.getName());
		_father = newDirectory;
	}

	/*
	*addContent:
	*		@param content: conteudo que vamos querer adicionar ao ficheiro.
	*/
	public void addContent(String content)
	{
		if (_content.equals(""))
		{
			_content = content;	
		}
		else
		{
			_content += "\n" + content;
		}
	}

	/*
	*showContent:
	*		@return: retorna o conteudo dentro do ficheiro.
	*/
	public String showContent()
	{
		return _content;
	}

}