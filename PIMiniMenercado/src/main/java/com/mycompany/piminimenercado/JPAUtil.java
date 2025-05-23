/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piminimenercado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    
    private static String PERSISTENCE_UNIT = "Mercado1-PU";
    private static EntityManager gerente;
    private static EntityManagerFactory fabrica;
    
    public static EntityManager conectar(){
        if(fabrica == null || !fabrica.isOpen()){
            fabrica = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        
        if(gerente == null || !gerente.isOpen()){
            gerente = fabrica.createEntityManager();
        }
        return gerente;
    }
    
    public static void desconectar(){
        if(gerente.isOpen() && gerente !=null){
            gerente.close();
            fabrica.close();
        }
    }
}
