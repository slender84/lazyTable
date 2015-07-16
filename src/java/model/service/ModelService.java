
package model.service;

import entities.Model;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;


public interface ModelService {
    
    
     public List<Model> modelLazyList(int first,int pageSize,String sortField,SortOrder sortOrder,Map<String,Object> filters);
     public int count(Map<String,Object> filters);
    
    
}
