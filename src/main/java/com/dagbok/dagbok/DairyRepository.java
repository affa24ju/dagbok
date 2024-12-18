package com.dagbok.dagbok;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DairyRepository extends CrudRepository<Dairy, Integer>{
    
    //För att hitta inlägg som inte är deletad (soft delete)
    //Iterable<Dairy> findAllByIsDeletedFalse();

    //För att visa bara de inlägg som inte är deletad och inte har framtida datum
    List<Dairy>findAllByIsDeletedFalseAndDateLessThanEqual(java.sql.Date currentDate);
    
}
