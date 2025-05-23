/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piminimenercado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;


public class FuncionarioJPA {
    
    public static Funcionario validarFuncionario(Funcionario f){
        EntityManager manager = JPAUtil.conectar();
        try{
            Query consulta = manager.createQuery("SELECT f FROM Funcionario f WHERE f.login = :login AND f.senha = :senha");
            consulta.setParameter("login", f.getLogin());
            consulta.setParameter("senha", f.getSenha());
            List<Funcionario> listaFuncionario = consulta.getResultList();
            
            if(!listaFuncionario.isEmpty()){
                return listaFuncionario.get(0);
            }
        }catch(Exception e){
            manager.getTransaction().rollback();
        }finally{
            JPAUtil.desconectar();
        }
        return null;
    }
    
    public static void cadastrar(Funcionario f){
        EntityManager manager = JPAUtil.conectar();
        try{
            manager.getTransaction().begin();
            manager.persist(f);
            manager.getTransaction().commit();
        }catch(Exception e){
            manager.getTransaction().rollback();
        }finally{
            JPAUtil.desconectar();
        }
    }
}
