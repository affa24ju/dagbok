package com.dagbok.dagbok;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DairyRepository extends CrudRepository<Dairy, Integer>{
    
    //To find only non-deleted entries
    //Iterable<Dairy> findAllByIsDeletedFalse();
    List<Dairy>findAllByIsDeletedFalseAndDateLessThanEqual(java.sql.Date currentDate);
    
}
