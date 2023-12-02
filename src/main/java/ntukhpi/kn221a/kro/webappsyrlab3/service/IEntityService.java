package ntukhpi.kn221a.kro.webappsyrlab3.service;

import java.util.List;

public interface IEntityService <T>{
    List<T> getAllEntities();
    T saveEntity(T T);
    T getEntityById(Long id);
    T updateEntity(T T);
    void deleteEntityById(Long id);
}
