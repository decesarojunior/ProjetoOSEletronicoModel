
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Produto;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Telmo
 */
public class TesteConsultarQuery {
    
    EntityManagerFactory emf;
    EntityManager em;

    public TesteConsultarQuery() {
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
    public void teste() throws IOException {
        
        // mostrar a consulta sem o parametro
        Query consulta = em.createQuery("from Produto where nome like 'M%'");
        List<Produto> lista = consulta.getResultList();
        for (Produto p : lista) {
            System.out.println("ID: " + p.getId() + " Nome: " + p.getNome());
        }
    
        
    }
    
}
