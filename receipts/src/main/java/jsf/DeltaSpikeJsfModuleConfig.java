package jsf;


import javax.enterprise.inject.Specializes;

import org.apache.deltaspike.jsf.api.config.JsfModuleConfig;
import org.apache.deltaspike.jsf.spi.scope.window.ClientWindowConfig.ClientWindowRenderMode;

@Specializes
public class DeltaSpikeJsfModuleConfig extends JsfModuleConfig {
    private static final long serialVersionUID = -2415069457080599502L;

    @Override
    public ClientWindowRenderMode getDefaultWindowMode() {
        return ClientWindowRenderMode.NONE;
    }

    @Override
    public boolean isAlwaysKeepMessages() {
        return false;
    }

    @Override
    public boolean isContainerManagedValidatorsEnabled() {
        return false;
    }

    @Override
    public boolean isContainerManagedConvertersEnabled() {
        return false;
    }

}