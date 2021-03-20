
package libraryFunctions;


public interface IPrintable {
    //Gets the fields which are printable from the objects
    //Format of printable fields should be "Forename: Value, Surname: Value, ... "
    String getPrintableString();
}
