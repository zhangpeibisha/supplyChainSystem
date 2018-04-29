package freemaker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11723 on 2017/5/6.
 */
public class FreeParam {
    private Model model;
    private List<Model> models = new ArrayList<>();

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }
}
