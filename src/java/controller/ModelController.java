
package controller;

import entities.Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import model.service.ModelService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;



@ManagedBean
@ViewScoped
public class ModelController implements Serializable{

    public ModelController() {
    }
    
    @ManagedProperty(value="#{modelService}")
    private ModelService modelService;

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
    
    
    
    
    private LazyDataModel<Model> model;
    private List<Model> modelList;

    private List<Model> lista;
    
    
    
    public LazyDataModel<Model> getModel() {
        return model;
    }

    public void setModel(LazyDataModel<Model> model) {
        this.model = model;
    }

    public List<Model> getModelList() {
        return modelList;
    }

    public void setModelList(List<Model> modelList) {
        this.modelList = modelList;
    }

    public List<Model> getLista() {
        return lista;
    }

    public void setLista(List<Model> lista) {
        this.lista = lista;
    }
    
    
    
    
    
    @PostConstruct
    public void init(){
       
        //lista=modelDao.lista();
        
        
        model=new LazyDataModel<Model>() {
            
            @Override
            public List<Model> load(int first,int PageSize,String sortField,SortOrder sortOrder,Map<String,Object> filters){
                

                
                
                
                
                modelList=modelService.modelLazyList(first, PageSize, sortField, sortOrder,filters);
                //modelList=new ArrayList();
                setRowCount(modelService.count(filters));
                return modelList;
            }
            
            
            
            @Override
            public Object getRowKey(Model model){
                
                return model.getName();
                
            }
            
            @Override
            public Model getRowData(String rowKey){
                
                
                for(Model m:modelList){
                if(m.getName().equals(rowKey))
                    return m;
          
                }
        
        
        return null;
        
        
    }
    
    
   };
        
 }
    
}
