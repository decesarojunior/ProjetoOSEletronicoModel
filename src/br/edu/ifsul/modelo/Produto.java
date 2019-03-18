/*
 * Composição: aplica-se a atributos do tipo complexo ou composto, não se aplica a tipos primitivos. No relacionamento do tipo composição, existe uma dependencia, ou seja, arquivo não existe sem o produto.
(Arquivo compoem Produto)       

No entanto, no relacionamento do tipo agregação, o agregado vive sem o agregador.

O losango é possicionado no lado da classe agregadora. Sempre do lado do losango vai ter a cardinalidade 1.

Geralmente na agregação por composição os objetos agregados são instanciados dentro da classe agregadora,já que
são, totalmente dependentes.

fonte: https://www.ateomomento.com.br/uml-classes-agregacao/

alto acoplamento: inclusão de atribuições que não sao inerentes
baixa coesao: mistura de conceitos.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Telmo Junior
 */

@Entity
@Table(name = "produto")
public class Produto implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_produto", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    @Length(max = 50, message = "O nome não pode ter mais que {max} caracteres")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    
    @Column(name = "descricao", length = 200, columnDefinition = "text")
    private String descricao;
    
    @Min(message = "O preço não pode ser negativo", value = 0)
    @NotNull(message = "O preço deve ser informado")
    @Column(name = "preco", nullable = false, columnDefinition = "numeric(12,2)")
    private Double preco;
    
    @NotNull(message = "A marca deve ser informada")
    @ManyToOne
    @JoinColumn(name = "marca", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_produto_marca"))
    private Marca marca;//associação: produto referencia marca (nesse caso, como tem seta direcional, é  unidirecional)
    
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL,
        orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Arquivo> arquivos;//composição, ou seja, arquivo nao vive sem o produto. Arquivo por sua vez, referencia Produto. É bidirecional pois, não tem sentido
    
    
    
    
    
    
    
    
    
    
    public void adicionarArquivo(Arquivo obj) {
        obj.setProduto(this);
        this.arquivos.add(obj);
    }

    public void removerArquivo(int index) {
        this.arquivos.remove(index);
    }
    
    public Produto(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
