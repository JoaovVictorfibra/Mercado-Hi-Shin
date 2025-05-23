/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piminimenercado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ProdutoJPA {
    public static void cadastrar(Produto p){
        if(codigoExiste(p.getCodigo())){
            JOptionPane.showMessageDialog(null, "codigo já existe" + p.getCodigo());
            return;
        }
        EntityManager manager = JPAUtil.conectar();
        try{
            manager.getTransaction().begin();
            manager.persist(p);
            manager.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Produto castrado com secesso");
        }catch(Exception e){
            manager.getTransaction().rollback();
        }finally{
            JPAUtil.desconectar();
        }
    }
    
    public static List<Produto> listar(){
        List<Produto> lista = new ArrayList<Produto>();
        
        EntityManager manager = JPAUtil.conectar();
        
        try{
            Query sql = manager.createQuery("SELECT * FROM Produto ");
            lista = sql.getResultList();
        }catch(Exception e){
            manager.getTransaction().rollback();
        }finally{
            JPAUtil.desconectar();
        }
        return lista;
    }
    
    public static List<Produto> buscarPorProduto (String filtro){
        List<Produto> lista = new ArrayList<>();
        
        EntityManager manager = JPAUtil.conectar();
        
        try{
            Query sql = manager.createQuery("SELECT n FROM Produto n WHERE (:nome IS NULL OR n.nome LIKE :nome)");
            sql.setParameter("nome", filtro.isEmpty() ? null: "%" + filtro + "%");
            lista = sql.getResultList();
        }catch(Exception e){
            manager.getTransaction().rollback();
        }finally{
            JPAUtil.desconectar();
        }
        return lista;
    }
    
    public static void excuir (int id){
        EntityManager manager = JPAUtil.conectar();
        
        try{
            manager.getTransaction().begin();
            Produto p = manager.find(Produto.class, id);
            if(p != null){
                manager.remove(p);
            }
            manager.getTransaction().commit();
        }catch(Exception e){
            manager.getTransaction().rollback();
        }finally{
            JPAUtil.desconectar();
                    
        }
    }
    
    public static boolean codigoExiste(int codigo){
        EntityManager manager = JPAUtil.conectar();
        boolean existe = false;
        try{
            Query query = manager.createQuery("SELECT COUNT(p) FROM Produto p WHERE p.codigo = :codigo");
            query.setParameter("codigo", codigo);
            Long count = (Long) query.getSingleResult();
            existe = count > 0;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JPAUtil.desconectar();
        }
        return existe;
    }
    
}
