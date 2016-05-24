package poof.core;
import java.util.*;
import poof.textui.exception.IllegalRemovalException;
/*
*Class abstracta que vai permitir reutilizacao de codigo e a representacao de comportamento generico(interface) tanto para a class
*	Directory como para a class File.
*/
public abstract class Entry implements java.io.Serializable
{	
	//String que vai guardar o nome do ficheiro.
	private String _name;
	//Boolean que vai permitir saber se e uma entrada publica ou privada.
	private Boolean _public = false;
	//User responsavel pela entrada em causa.
	private User _owner;

	/*
	*construtor:
	*		@param name: o nome da entrada que queremos criar.
	*		@param owner: o user que vai ficar responsavel pela entrada.
	*/
	public Entry(String name, User owner)
	{
		_name = name;
		_owner = owner;
	}

	/*
	*isRemovable:
	*		@exception IllegalRemovalException: lancada caso  entrada nao seja removivel.
	*/
	public abstract void isRemovable() throws IllegalRemovalException;

	/*
	*getFather:
	*		@return: retorna o directorio pai da entrada em questao.
	*/
	public abstract Directory getFather();

	/*
	*getSize:
	*		@return: retorna o tamanho da entrada em questao.
	*/
	public abstract int getSize();

	/*
	*iterator:
	*		@return: retorna o iterador.
	*/

	public Iterator<Entry> iterator() {
        return new NullIterator();
    }
	

	/*
	*getPermission:
	*		@return: retorna a permissao da entrada.
	*/
	public Boolean getPermission()
	{
		return _public;
	}

	/*
	*rename:
	*		@param newName: nome que vamos querer que a entrada adopte no futuro.
	*/
	public void rename(String newName)
	{
		_name = newName;
	}

	/*
	*getName:
	*		@return: retorna o nome da entrada.
	*/
	public String getName()
	{
		return _name;
	}

	/*
	*getOwner:
	*		@return: retorna o utilizador responsavel pela entrada em causa.
	*/
	public User getOwner()
	{
		return _owner;
	}

	/*
	*changePermission:
	*		@param permission: o valor logico(true ou false) que queremos que a entrada venha a ter.
	*/
	public void changePermission(Boolean permission)
	{
		_public = permission;
	}

	/*
	*changeOwner:
	*		@param newOwner: o utilizador que queremos que no futuro seja responsavel pela entrada em questao.
	*/
	public void changeOwner(User newOwner) 
	{
		_owner = newOwner; 
	}

	/*
	*equals:
	*		@param e: entrada com que queremos comparar a entrada em questao.
	*/
	public Boolean equals( Entry e)
	{
		return _name.equals(e.getName());
	}

	/*
	*toString:
	*		@return: retorna a String com que vamos representar a entrada em questao.
	*/
	public String toString()
	{
		String permission;

		if (this.getPermission())
			permission = "w";
		else 
			permission = "-";
		return permission + " " + this.getOwner().getUsername() + " " + this.getSize() + " " + this.getName();
	}

}