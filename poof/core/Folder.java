package poof.core;
import java.util.*;
import poof.textui.exception.EntryExistsException;
import poof.textui.exception.IllegalRemovalException;
import poof.textui.exception.EntryUnknownException;
import poof.textui.exception.IsNotDirectoryException;
import poof.textui.exception.IsNotFileException; 
/*
*A Interface Folder vai conter todos os metodos responsaveis pelo comportamento generico que uma pasta deve ter. Exemplos
*	disto seram as Directories e as SpecialEntries uma vez que em ambos os casos nos queremos referir a diretorias com a
*	pequena diferenca na realizacao de algumas funcionalidades, uma vez que as SpecialEntries apenas servem de mascara para
*	as directorias. 
*/
public interface Folder
{
	/*
	*createSubDirectory:
	*		@param name: o nome que desejamos que sub-directorio a criar venha a ter.
	*		@param owner: o utilizador responsavel pelo sub-directorio a criar.
	*		@exception EntryExistsException:lancada no caso de ja existir uma entrada dentro do directorio em causa com
											 o nome pretendido.
	*/
	public void createSubDirectory(String name, User owner) throws EntryExistsException;

	/*
	*showAbsolutePath:
	*		@return: retorna uma String com o caminho da raiz do FileSystem("/") ate ao directorio em causa.
	*/
	public  String showAbsolutePath();

	/*
	*putNewFile:
	*		@param: newEntry vai ser a entrada que vamos querer colocar dentro do directorio em causa.
	*		@exception EntryExistsException: caso essa entrada ja exista no directorio de destino e lancada a excepcao.
	*/
	public void putNewFile(File newEntry) throws EntryExistsException;

	/*
	*putNewDirectory:
	*		@param: newEntry vai ser a entrada que vamos querer colocar dentro do directorio em causa.
	*		@exception EntryExistsException: caso essa entrada ja exista no directorio de destino e lancada a excepcao.
	*/
	public void putNewDirectory(Directory newEntry) throws EntryExistsException;

	/*
	*createFile:
	*		@param name: o nome com o qual vamos querer criar o ficheiro.
	*		@exception EntryExistsException: caso ja exista uma entrada com esse nome no directorio em causa.
	*/
	public void createFile(String name, User owner) throws EntryExistsException;

	/*
	*deleteEntry:
	*		@param name: o nome da entrada que vamos querer apagar do directorio em causa.
	*		@exception IllegalRemovalException:	caso tentemos retirar o "." ou o "..".
	*		@exception EntryUnknownException: caso essa entrada nao exista.
	*/
	public void deleteEntry(String name) throws IllegalRemovalException, EntryUnknownException;

	/*
	*directories:
	*		@return: return da ArrayList que vai conter todas as entradas do directorio em causa.
	*/
	public List<Entry> entries();

	/*
    *fileExists:
    *		@param name: nome do ficheiro que queremos ver se existe.
    *		@return: true em caso de existencia.
    *		@exception IsNotFileException: lancada caso se trate de uma Folder.
    */
	public boolean fileExists(String name) throws IsNotFileException;

	/*
    *directoryExists:
    *		@param name: nome do directorio que queremos ver se existe.
    *		@return: true em caso de existencia.
    *		@exception IsNotDirectoryException: lancada caso se trate de um File.
    */
	public boolean directoryExists(String name) throws IsNotDirectoryException;

	/*
    *entryUnknown:
    *		@param name: nome da entrada que queremos ver se existe.
    *		@return: true em caso de existencia.
    *		@exception EntryUnknownException: lancada caso essa entrada nao exista.
   	*Nota: a razao de criacao deste metodo, tendo em conta a existencia de um metodo que verifica a existencia de uma entry e o
   		   lancamento da excepcao EntryUnknownException.	
    */
	public boolean entryUnknown(String name) throws EntryUnknownException;

	/*
    *entryExists:
    *		@param name: nome da entrada que queremos ver se existe.
    *		@return: true em caso de existencia, false caso contrario.
    *		@exception EntryUnknownException: lancada caso essa entrada nao exista nem nos directotios nem nos ficheiros
    											 da pasta em questao.
    */
	public boolean entryExists(String name) throws EntryExistsException;

	/*
    *getEntry:
    *		@param name: nome da entrada que queremos obter.
    *		@return: retorna a entrada pretendida.
    *		@exception EntryUnknownException: caso nao exista essa entrada.
    */
	public Entry getEntry(String name) throws EntryUnknownException;

	/*
    *getDirectory:
    *		@param name: nome do directorio que queremos obter.
    *		@return: retorna o directorio que estamos a procura.
    *		@exception EntryUnknownException: caso nao exista nenhuma entrada com esse nome.
    *		@exception IsNotDirectoryExcepetion: caso o nome passado como argumento seja relativo a um file.
    */
	public  Directory getDirectory(String name) throws EntryUnknownException, IsNotDirectoryException;

	/*
    *getFile:
    *		@param name: nome do ficheiro que queremos obter.
    *		@return: retorna o ficheiro que estamos a procura.
    *		@exception EntryUnknownException: caso nao exista nenhuma entrada com esse nome.
    *		@exception IsNotDirectoryExcepetion: caso o nome passado como argumento seja relativo a um directorio.
    */
	public  File getFile(String name) throws EntryUnknownException, IsNotFileException;
}