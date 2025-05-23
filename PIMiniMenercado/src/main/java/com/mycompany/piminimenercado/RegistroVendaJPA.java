/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piminimenercado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaov
 */
public class RegistroVendaJPA {
        public static void cadastrar(RegistroVenda rv){
        EntityManager manager = JPAUtil.conectar();
        try{
            manager.getTransaction().begin();
            manager.persist(rv);
            manager.getTransaction().commit();
        }catch(Exception e){
            manager.getTransaction().rollback();
        }finally{
            JPAUtil.desconectar();
        }
    }
    
    public static List<RegistroVenda> listar(){
        List<RegistroVenda> lista = new ArrayList<>();
        
        EntityManager manager = JPAUtil.conectar();
        
        try{
            Query sql = manager.createQuery("SELECT * FROM RegistroVenda ");
            lista = sql.getResultList();
        }catch(Exception e){
            manager.getTransaction().rollback();
        }finally{
            JPAUtil.desconectar();
        }
        return lista;
    }
    
    public static List<RegistroVenda> buscarRegistro (String filtro){
        List<RegistroVenda> lista = new ArrayList<>();
        
        EntityManager manager = JPAUtil.conectar();
        
        try {
            if (filtro == null || filtro.isEmpty()) {
                Query sql = manager.createQuery("SELECT r FROM RegistroVenda r");
                lista = sql.getResultList();
            } else {
                Query sql = manager.createQuery("SELECT r FROM RegistroVenda r WHERE r.nome LIKE :nome");
                sql.setParameter("nome", "%" + filtro + "%");
                lista = sql.getResultList();
            }
        } catch(Exception e) {
            manager.getTransaction().rollback();
        } finally {
            JPAUtil.desconectar();
        }
        return lista;
    }
}
