
package unaplanilla2.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import unaplanilla2.util.LocalDateAdapter;

/**
 *
 * @author Carlos
 */
@XmlRootElement(name = "EmpleadoDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class EmpleadoDto {

    @XmlTransient
    public SimpleStringProperty empId;
    @XmlTransient
    public SimpleStringProperty empNombre;
    @XmlTransient
    public SimpleStringProperty empPapellido;
    @XmlTransient
    public SimpleStringProperty empSapellido;
    @XmlTransient
    public SimpleStringProperty empCedula;
    @XmlTransient
    public ObjectProperty<String> empGenero;
    @XmlTransient
    public SimpleStringProperty empCorreo;
    @XmlTransient
    public SimpleBooleanProperty empAdministrador;
    @XmlTransient
    public SimpleStringProperty empUsuario;
    @XmlTransient
    public SimpleStringProperty empClave;
    @XmlTransient
    public ObjectProperty<LocalDate> empFingreso;
    @XmlTransient
    public ObjectProperty<LocalDate> empFsalida;
    @XmlTransient
    public SimpleBooleanProperty empEstado;
    @XmlTransient
    private String Token;
    @XmlTransient
    private Boolean modificado;
    @XmlTransient
    private Long empVersion;

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public EmpleadoDto() {
        this.modificado = false;
        this.empId = new SimpleStringProperty();
        this.empNombre = new SimpleStringProperty();
        this.empPapellido = new SimpleStringProperty();
        this.empSapellido = new SimpleStringProperty();
        this.empCedula = new SimpleStringProperty();
        this.empGenero = new SimpleObjectProperty();
        this.empCorreo = new SimpleStringProperty();
        this.empAdministrador = new SimpleBooleanProperty(false);
        this.empUsuario = new SimpleStringProperty();
        this.empClave = new SimpleStringProperty();
        this.empFingreso = new SimpleObjectProperty();
        this.empFsalida = new SimpleObjectProperty();
        this.empEstado = new SimpleBooleanProperty(true);
        this.empVersion = new Long(0);

    }

    public Long getEmpId() {
        if(empId.get()!=null && !empId.get().isEmpty())
            return Long.valueOf(empId.get());
        else
            return null;
    }

    public void setEmpId(Long empId) {
        this.empId.set(empId.toString());
    }

    public String getEmpNombre() {
        return empNombre.get();
    }

    public void setEmpNombre(String empNombre) {
        this.empNombre.set(empNombre);
    }

    public String getEmpPapellido() {
        return empPapellido.get();
    }

    public void setEmpPapellido(String empPapellido) {
        this.empPapellido.set(empPapellido);
    }

    public String getEmpSapellido() {
        return empSapellido.get();
    }

    public void setEmpSapellido(String empSapellido) {
        this.empSapellido.set(empSapellido);
    }

    public String getEmpCedula() {
        return empCedula.get();
    }

    public void setEmpCedula(String empCedula) {
        this.empCedula.set(empCedula);
    }

    public String getEmpGenero() {
        return empGenero.get();
    }

    public void setEmpGenero(String empGenero) {
        this.empGenero.set(empGenero);
    }

    public String getEmpCorreo() {
        return empCorreo.get();
    }

    public void setEmpCorreo(String empCorreo) {
        this.empCorreo.set(empCorreo);
    }

    public String getEmpAdministrador() {
        return empAdministrador.getValue() ? "S" : "N";
    }

    public void setEmpAdministrador(String empAdministrador) {
        this.empAdministrador.setValue(empAdministrador.equalsIgnoreCase("S"));
    }

    public String getEmpUsuario() {
        return empUsuario.get();
    }

    public void setEmpUsuario(String empUsuario) {
        this.empUsuario.set(empUsuario);
    }

    public String getEmpClave() {
        return empClave.get();
    }

    public void setEmpClave(String empClave) {
        this.empClave.set(empClave);
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getEmpFingreso() {
        return empFingreso.get();
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public void setEmpFingreso(LocalDate empFingreso) {
        this.empFingreso.set(empFingreso);
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getEmpFsalida() {
        return empFsalida.get();
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public void setEmpFsalida(LocalDate empFsalida) {
        this.empFsalida.set(empFsalida);
    }

    public String getEmpEstado() {
        return empEstado.getValue()?"A":"I";
    }

    public void setEmpEstado(String empEstado) {
        this.empEstado.setValue(empEstado.equalsIgnoreCase("A"));
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public Long getEmpVersion() {
        return empVersion;
    }

    public void setEmpVersion(Long empVersion) {
        this.empVersion = empVersion;
    }
    
    
    
    @Override
    public String toString() {
        return "EmpleadoDto{" + "empId=" + empId + ", empNombre=" + empNombre + ", empPapellido=" + empPapellido + ", empSapellido=" + empSapellido + ", empCedula=" + empCedula + ", empGenero=" + empGenero + ", empCorreo=" + empCorreo + ", empAdministrador=" + empAdministrador + ", empUsuario=" + empUsuario + ", empClave=" + empClave + ", empFingreso=" + empFingreso + ", empFsalida=" + empFsalida + ", empEstado=" + empEstado + '}';
    }

}
