package esipe.fr.cloudito_model;

import io.swagger.annotations.ApiModel;


@ApiModel(description = "std object for request results")

public class PageData   {
    private Integer page = null;

    private Integer nbElements = null;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getNbElements() {
        return nbElements;
    }

    public void setNbElements(Integer nbElements) {
        this.nbElements = nbElements;
    }


}