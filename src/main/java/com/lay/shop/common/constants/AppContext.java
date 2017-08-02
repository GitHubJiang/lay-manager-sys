package com.lay.shop.common.constants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class AppContext {

    private static AppContext appContext = new AppContext();
    
    private Set<String> controlledUrlSet;
    
    private AppContext() {}
    
    public static AppContext getInstance() {
        return appContext;
    }

    /**
     * 受管控的URL列表
     * @author 李光辉
     * @return
     * @since
     */
    public Set<String> getControlledUrlSet() {
        return controlledUrlSet;
    }

    public void setControlledUrlSet(Set<String> controlledUrlSet) {
        this.controlledUrlSet = controlledUrlSet;
    }
    
    public static Set<String> initControlledUrlSet(List<String> urlList) {
        Set<String> set = new HashSet<String>(urlList.size());
        for (String url : urlList) {
            set.add(url);
        }
        
        return set;
    }

	
}
