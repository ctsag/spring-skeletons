package gr.nothingness.springskeletons.datatests.repositories;

import javax.persistence.EntityManager;

public class DeleteByOriginRepositoryImpl implements DeleteByOriginRepository {

  private final EntityManager entityManager;

  public DeleteByOriginRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public void deleteByOrigin(String origin) {
    entityManager.createNativeQuery("DELETE FROM flight WHERE origin = ?")
        .setParameter(1, origin)
        .executeUpdate();
  }

}
