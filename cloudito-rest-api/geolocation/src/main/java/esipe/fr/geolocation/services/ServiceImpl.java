package esipe.fr.geolocation.services;

import java.util.List;
import java.util.Optional;

public interface ServiceImpl<T>{

    T save(T t);
    Optional<T> findById(long id);
    void delete(T t);
    List<T> findAll();


}