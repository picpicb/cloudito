package esipe.fr.cloudito_model;

import io.swagger.annotations.ApiModel;
import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "list of detection find request")
public class DetectionList extends PageData{
    private List<CustomerDetection> data = null;

    public DetectionList addDataItem(CustomerDetection dataItem){
        if(this.data == null){
            this.data = new ArrayList<CustomerDetection>();
        }
        this.data.add(dataItem);
        return this;
    }

    public List<CustomerDetection> getData(){
        return data;
    }

    public void setData(List<CustomerDetection> data){
        this.data = data;
    }

}
