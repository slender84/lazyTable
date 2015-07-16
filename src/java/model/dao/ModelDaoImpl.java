
package model.dao;

import entities.Model;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.FilterDefinition;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("modelDao")

public class ModelDaoImpl implements ModelDao{
    
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    public Session getSession(){
        
        return sessionFactory.getCurrentSession();
        
    }
    
    
    @Override
    public List<Model> modelLazyList(int first,int pageSize,String sortField,SortOrder sortOrder,Map<String,Object> filters){
        
        String field=null;
        String order=null;
        List<Model> l=null;
        
        
        if(sortField!=null){
            
            switch(sortField){
                
                
                case "name":field="name";
                    break;
                case "year":field="year";
                    break;
                case "fuel":field="fuel";
                    break; 
                case "brand.country":field="brand.country";
                    break;
            }
            
            
            if(sortOrder.toString().equalsIgnoreCase("ascending")){
                order="asc";
            }else{
                order="desc";
            }
            
            
        }
        
        if(filters.containsKey("name")){
            getSession().enableFilter("name").setParameter("nameParam", filters.get("name"));
        }
        
        
        
        if(filters.containsKey("year")){
            
            //Integer i=Integer.parseInt(filters.get("year").toString());
            getSession().enableFilter("year").setParameter("yearParam",filters.get("year").toString());
        }
        
        if(filters.containsKey("fuel")){
            
           getSession().enableFilter("fuel").setParameter("fuelParam", filters.get("fuel"));
            
        }
        
        if(filters.containsKey("brand.country")){
            
            getSession().enableFilter("country").setParameter("countryParam", filters.get("brand.country"));
            
        }
        
        
        
        
        if(sortField==null){
            
            l=getSession().createQuery("select m from Model m").setFirstResult(first).setMaxResults(pageSize).list();
        }else{
            
            l=getSession().createQuery("select m from Model m order by "+field+" "+order).setFirstResult(first).setMaxResults(pageSize).list();
            
        }
        
        return l;
        
    }
    
    @Override
    public int count(Map<String,Object> filters){
        
        
        if(filters.containsKey("name")){
            getSession().enableFilter("name").setParameter("nameParam", filters.get("name"));
        }
        
        if(filters.containsKey("year")){
            getSession().enableFilter("year").setParameter("yearParam", filters.get("year"));
        }
        
        if(filters.containsKey("fuel")){
            
           getSession().enableFilter("fuel").setParameter("fuelParam", filters.get("fuel"));
            
        }
        
        if(filters.containsKey("brand.country")){
            
            
            
            
            getSession().enableFilter("country").setParameter("countryParam", filters.get("brand.country"));
            
        }
        
        
        return getSession().createQuery("select m from Model m").list().size();
        
        
        
    }
    
    @Override
    public List<Model> lista(){
        
        return getSession().createQuery("select m from Model m").list();
    }
    
    
    
    
}
