/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.tsi.ipli.alunoservice;


import java.util.List;
import javax.persistence.EntityManager;




/**
 *
 * @author Rodrigo
 */

public class AlunoDao{

    private EntityManager em;
    private Class clazz;

    public AlunoDao() {
        
        em = FabricaGerenciadordeEntidades.getEntityManager();
    }

    public void persistir(Aluno aluno) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        
        em.persist(aluno);
        em.getTransaction().commit();
    }

    public void remover(int id) {
        em.getTransaction().begin();
        em.remove(buscarPorId(id));
        em.getTransaction().commit();
    }
    

    public void alterar(Aluno aluno) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.merge(aluno);
        em.getTransaction().commit();
    }

   public Aluno buscarPorId(int id) {
        return em.find(Aluno.class, id);
    }


   public List<Aluno> listarTodos() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT a ");
        query.append("FROM ");
        query.append("Aluno ");
        query.append("a");
        
        
        
        
        try{
            
            List<Aluno> lista = em.createQuery(query.toString()).getResultList();  
            
            System.out.println("============================"+lista.size());
            
            return lista;
            
        }catch(Exception erroSql){
            System.out.println("errro sql"+erroSql.getMessage());  
        }
       return null; 
    }
    
    public EntityManager getEm() {
        return em;
    }
}
