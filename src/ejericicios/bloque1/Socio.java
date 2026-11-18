package ejericicios.bloque1;

public class Socio {
    private String carne;
    private String nombre;
    private String fechaAlta;
    private String correo; // Puede estar vacío o null

    public Socio(String carne, String nombre, String fechaAlta, String correo) {
        this.carne = carne;
        this.nombre = nombre;
        this.fechaAlta = fechaAlta;
        this.correo = correo;
    }

    public String getCarne() { return carne; }
    public String getNombre() { return nombre; }
    public String getFechaAlta() { return fechaAlta; }
    public String getCorreo() { return correo; }

    // Método para saber si no tiene correo
    public boolean noTieneCorreo() {
        return correo == null || correo.trim().isEmpty();
    }
}