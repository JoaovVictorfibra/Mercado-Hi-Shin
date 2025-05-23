/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piminimenercado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;

@Entity(name = "RegistroVenda")
public class RegistroVenda {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private double total;
   private String nome;
   private String Forma_Pagamento;

   @Column(name = "hora")
   private LocalDateTime hora;

   public RegistroVenda() {
   }

   public RegistroVenda(int id, String nome, double total, String Forma_Pagamento) {
       this.id = id;
       this.nome = nome;
       this.total = total;
       this.Forma_Pagamento = Forma_Pagamento;
   }

   @PrePersist
   public void prePersist() {
       // Preenche a hora com a data e hora atual antes de persistir
       if (this.hora == null) {
           this.hora = LocalDateTime.now();
       }
   }

   // Getters and setters

   public int getId() {
       return id;
   }

   public void setId(int id) {
       this.id = id;
   }

   public double getTotal() {
       return total;
   }

   public void setTotal(double total) {
       this.total = total;
   }

   public String getForma_Pagamento() {
       return Forma_Pagamento;
   }

   public void setForma_Pagamento(String Forma_Pagamento) {
       this.Forma_Pagamento = Forma_Pagamento;
   }

   public LocalDateTime getHora() {
       return hora;
   }

   public void setHora(LocalDateTime hora) {
       this.hora = hora;
   }

   public String getNome() {
       return nome;
   }

   public void setNome(String nome) {
       this.nome = nome;
   }
   
}