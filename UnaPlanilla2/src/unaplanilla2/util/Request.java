/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unaplanilla2.util;

import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 *
 * @author ccarranza
 */
// Comunicarse con el web Service

public class Request {

    private Client client;
    private Invocation.Builder builder;
    private WebTarget webTarget;
    private Response response;

    public Request() { // define el cliente
        this.client = ClientBuilder.newClient();
        
    }

    public Request(String target) {
        this.client = ClientBuilder.newClient();
        
        setTarget(target);
    }

    public Request(String target, String parametros, Map<String, Object> valores) {//otro constructor que además de definir el targer le puede dar los demás valores
        this.client = ClientBuilder.newClient();
        this.webTarget = client.target(AppContext.getInstance().get("resturl") + target).path(parametros).resolveTemplates(valores);/*a la hora de definir el target del cliente está sacando la clase apppcontext del
        hashmap que hay dentro de esa clase appcontext está sacando el resturl que es la información del archivo de propiedades que vimos 
        que es la primera parte del llamado al web service más ya propiamente el metodo que queremos acceder*/ 
        this.builder = webTarget.request(MediaType.APPLICATION_JSON);
        //builder.headers();
        MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
        headers.add("Content-Type", "application/json; charset=UTF-8");//para no tener problemas con tíldes o ñ y así, es como un estandar
        if(AppContext.getInstance().get("Token") != null){
            headers.add("Authorization", AppContext.getInstance().get("Token").toString());
        }
        builder.headers(headers);
        /*basicamente define la conexión*/
    }

    /**
     * Ingresa el objetivo de la petición
     *
     * @param target Objetivo de la petición
     */
    public void setTarget(String target) {
        this.webTarget = client.target(AppContext.getInstance().get("resturl") + target);
        this.builder = webTarget.request(MediaType.APPLICATION_JSON);
        MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        if(AppContext.getInstance().get("Token") != null){
            headers.add("Authorization", AppContext.getInstance().get("Token").toString());
        }
        builder.headers(headers);
    }

    public void setHeader(String nombre, Object valor) {
        builder.header(nombre, valor);
    }

    public void setHeader(MultivaluedMap<String, Object> valores) {
        valores.add("Content-Type", "application/json; charset=UTF-8");
        builder.headers(valores);
    }

    public void get() {
        response = builder.get();
    }

    public void post(Object clazz) {
        Entity<?> entity = Entity.entity(clazz, "application/json; charset=UTF-8");
        response = builder.post(entity);
    }

    public void put(Object clazz) {
        Entity<?> entity = Entity.entity(clazz, "application/json; charset=UTF-8");
        response = builder.put(entity);
    }

    public void delete() {
        response = builder.delete();
    }

    public int getStatus() {
        return response.getStatus();
    }
    
    public Boolean isError(){
        return getStatus() != HttpServletResponse.SC_OK;
    }
  
    public String getError() {
        if (response.getStatus() != HttpServletResponse.SC_OK) {
            String mensaje;
            if (response.getMediaType().equals(MediaType.APPLICATION_JSON_TYPE)) {
                mensaje = response.readEntity(String.class);
            } else {
                mensaje = response.getStatusInfo().getReasonPhrase();
            }
            return mensaje;
        }
        return null;
    }
    
    public Object readEntity(Class clazz) {
        return response.readEntity(clazz);
    }
    
    public Object readEntity(GenericType<?> genericType) {
        return response.readEntity(genericType);
    }

}
