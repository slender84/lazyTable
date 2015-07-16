
package model.service;

import entities.Model;
import java.util.List;
import java.util.Map;
import model.dao.ModelDao;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("modelService")
@Transactional
public class ModelServiceImpl implements ModelService{
    
   @Autowired 
   private ModelDao modelDao;

    public ModelDao getModelDao() {
        return modelDao;
    }

    public void setModelDao(ModelDao modelDao) {
        this.modelDao = modelDao;
    }
    
    
    
    @Override
    public List<Model> modelLazyList(int first,int pageSize,String sortField,SortOrder sortOrder,Map<String,Object> filters){
        
        return modelDao.modelLazyList(first, pageSize, sortField, sortOrder, filters);
        
        
    }
    
    @Override
     public int count(Map<String,Object> filters){
    
    return modelDao.count(filters);
    
    
}
}