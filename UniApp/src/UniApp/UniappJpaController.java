/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UniApp;

import UniApp.exceptions.NonexistentEntityException;
import UniApp.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Cyclo
 */
public class UniappJpaController implements Serializable {

    public UniappJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Uniapp uniapp) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uniapp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUniapp(uniapp.getName()) != null) {
                throw new PreexistingEntityException("Uniapp " + uniapp + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Uniapp uniapp) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uniapp = em.merge(uniapp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = uniapp.getName();
                if (findUniapp(id) == null) {
                    throw new NonexistentEntityException("The uniapp with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Uniapp uniapp;
            try {
                uniapp = em.getReference(Uniapp.class, id);
                uniapp.getName();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uniapp with id " + id + " no longer exists.", enfe);
            }
            em.remove(uniapp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Uniapp> findUniappEntities() {
        return findUniappEntities(true, -1, -1);
    }

    public List<Uniapp> findUniappEntities(int maxResults, int firstResult) {
        return findUniappEntities(false, maxResults, firstResult);
    }

    private List<Uniapp> findUniappEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Uniapp.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Uniapp findUniapp(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Uniapp.class, id);
        } finally {
            em.close();
        }
    }

    public List<Object[]> getTopUniversities() {
    EntityManager em = getEntityManager(); 
    try {
        //Δημιουργούμε το query μας
        return em.createQuery("SELECT u.name, u.views FROM Uniapp u ORDER BY u.views DESC", Object[].class)
                
                .getResultList();
    } catch (Exception e) {
        e.printStackTrace();
        return new ArrayList<>();
    } finally {
        em.close();     }
}


    public int getUniappCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Uniapp> rt = cq.from(Uniapp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
