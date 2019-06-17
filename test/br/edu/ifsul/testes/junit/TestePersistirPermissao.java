package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Permissao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jorge
 */
public class TestePersistirPermissao {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirPermissao() {
    }
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("OSEletronicosModelPU");
        em = emf.createEntityManager();        
    }
    
    @After
    public void tearDown() {
        em.close();
        emf.close();
    }
    
    @Test
    public void teste(){
        Permissao p1 = new Permissao();
        p1.setNome("ADMINISTRADOR");
        p1.setDescricao("Acesso a privado/*");
        
        Permissao p2 = new Permissao();
        p2.setNome("USUARIO");
        p2.setDescricao("Acesso a privado/ordemservico/*");
        
        Permissao p3 = new Permissao();
        p3.setNome("CLIENTE");
        p3.setDescricao("Acesso a definir");
         
        em.getTransaction().begin();
        //em.persist(p1);
        //em.persist(p2);
        em.persist(p3);
        em.getTransaction().commit();        
    }
    
}
