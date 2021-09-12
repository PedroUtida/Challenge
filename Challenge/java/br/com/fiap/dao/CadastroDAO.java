package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Cadastro;
import br.com.fiap.util.EntityManagerFacade;

public class CadastroDAO {

	private EntityManager manager = EntityManagerFacade.getEntityManager();

	public void save(Cadastro cadastro) {
		manager.getTransaction().begin();
		manager.persist(cadastro);
		manager.getTransaction().commit();
		
		manager.clear();
		
	}

	public List<Cadastro> getAll() {
		String jpql = "SELECT s FROM Cadastro s";
		TypedQuery<Cadastro> query = manager.createQuery(jpql, Cadastro.class);
		return query.getResultList();
		
	}

	public Cadastro findById(Long id) {
		return manager.find(Cadastro.class, id);
		
	}

	public void update(Cadastro cadastro) {
		manager.getTransaction().begin();
		manager.merge(cadastro);
		manager.flush();
		manager.getTransaction().commit();
	
	}
	public void delete(Cadastro cadastro) {
		manager.getTransaction().begin();
		manager.remove(cadastro);
		manager.getTransaction().commit();
	}
}
