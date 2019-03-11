
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Estado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Telmo
 */
public class TestePersistirEstado {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirEstado() {
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
        
        Estado e = new Estado();//nesse momento o e está no estado: New
        e.setNome("Rio Grande do Sul");
        e.setUf("RS");
        em.getTransaction().begin();
        em.persist(e);//o status do objeto e passa para: Managed
        em.getTransaction().commit();
        
        
        
        
        
        
        
        
        List<Estado> lista = em.createQuery("from Estado order by nome").getResultList();
        for (Estado es : lista){
            System.out.println("ID: " + es.getId() + 
                    " Nome: " + es.getNome() + " UF: " + es.getUf());	
        }
        
        Estado obj = new Estado();
	       Validator validador =  Validation.buildDefaultValidatorFactory().getValidator();
	java.util.Set<ConstraintViolation<Estado>>  erros = validador.validate(obj);
	for (ConstraintViolation<Estado> erro : erros){
		System.out.println("Erro: "+erro.getMessage());
	}
    }
    
}
