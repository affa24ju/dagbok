package com.dagbok.dagbok;

import org.springframework.data.repository.CrudRepository;

public interface DairyRepository extends CrudRepository<Dairy, Integer>{
    
    //To find only non-deleted entries
    Iterable<Dairy> findAllByIsDeletedFalse();
    
}
