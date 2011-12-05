package org.jenkinsci.plugins.xmas;

import hudson.Extension;
import hudson.XmlFile;
import hudson.init.InitMilestone;
import hudson.init.Initializer;
import hudson.maven.MavenModuleSet;
import hudson.model.Saveable;
import hudson.model.TopLevelItem;
import hudson.model.Descriptor;
import hudson.model.Hudson;
import hudson.model.Project;
import hudson.model.listeners.SaveableListener;
import hudson.tasks.BuildWrapper;
import hudson.util.DescribableList;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Extension
public class XmasItemListener extends SaveableListener {

    private static final Logger LOGGER = Logger.getLogger(XmasItemListener.class.getName());

    @Initializer(after=InitMilestone.JOB_LOADED)
    public static void init() {
        for (TopLevelItem item : Hudson.getInstance().getItems()) {
            addXmas(item);
        }
    }

    @Override
    public void onChange(Saveable o, XmlFile file) {
        addXmas(o);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected static void addXmas(Saveable o) {
        if (o instanceof Project) {
            addXmas(((Project) o).getBuildWrappersList());
        }
        else if (o instanceof MavenModuleSet) {
            addXmas(((MavenModuleSet) o).getBuildWrappersList());
        }
    }

    @SuppressWarnings("unchecked")
    protected static void addXmas(DescribableList<BuildWrapper, Descriptor<BuildWrapper>> buildWrappers) {
        if (!buildWrappers.contains(Hudson.getInstance().getDescriptor(XmasBuildWrapper.class))) {
            try {
                buildWrappers.add(new XmasBuildWrapper(true));
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }
}
