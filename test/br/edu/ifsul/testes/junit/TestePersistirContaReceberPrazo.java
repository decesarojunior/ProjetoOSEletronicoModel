package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.OrdemServico;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jorge
 */
public class TestePersistirContaReceberPrazo {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirContaReceberPrazo() {
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
        OrdemServico os = em.find(OrdemServico.class, 2);
        os.gerarContasReceber();
        em.getTransaction().begin();
        em.persist(os);
        em.getTransaction().commit();
    }

}
