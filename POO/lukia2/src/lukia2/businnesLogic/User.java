package lukia2.businnesLogic;

import javax.swing.*;

public class User {

    private String id;
    private String name;
    private String last_Name;
    private String email;
    private String phone_Number;
    private String password;
    private String address;
    public User(String id, String name, String last_Name, String email, String address, String phone_Number, String password) {
        super();
        this.id = id;
        this.name = name;
        this.last_Name = last_Name;
        this.email = email;
        this.address = address;
        this.phone_Number = phone_Number;
        this.password = password;
    }
    public User() {
        // TODO Auto-generated constructor stub
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void registrarUsuario() {

    }

    /*
     * Description: enviar la contrasena almacenada
     * TODO: especificar el tipo de retorno, atributos de entrada
     */
    public void recordarContrasena() {

    }

    /*
     * Description: realiza la edicion de un usario usando el numero de celular
     * TODO: Completar logica retornado un String
     * */
    public String editarUsuario (String cedula) {


        return "Usuario ingresado OK";
    }

    /*
     * Description: Listar los usuarios registrados
     * TODO: Completar logica retornado un String
     * */
    public void verListaUsuarios() {

    }

    /*
     * Description: Desactivar un usuario
     * TODO: Completar logica retornado un String
     * */
    public void desactivarUsuario(String cedula) {

    }



}
