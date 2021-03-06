
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Usuario;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Telmo Junior
 */
public class TesteConsultarNamedQuery {
    
    EntityManagerFactory emf;
    EntityManager em;

    
    public TesteConsultarNamedQuery() {
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
        List<Usuario> lista = em.createNamedQuery("todosUsuarioOrdemNome").getResultList();
        for (Usuario u : lista){
            System.out.println("Nome: " + u.getNomeUsuario());
            System.out.println("Senha: " + u.getSenha());
        }
        
        List<Usuario> usus = em.createNamedQuery("autenticacaoUsuario").setParameter("paramNome", "teste").setParameter("paramSenha", "123").getResultList();
        if(usus.isEmpty()){
            System.out.println("Nao autenticou");
        }else{
            System.out.println("Usuario "+usus.get(0).getNome()+"autenticado !!!");
        }
        
    }
    
}
