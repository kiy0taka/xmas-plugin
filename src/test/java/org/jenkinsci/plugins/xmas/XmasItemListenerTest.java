package org.jenkinsci.plugins.xmas;

import hudson.maven.MavenModuleSet;
import hudson.model.FreeStyleProject;

import java.io.IOException;

import org.jenkinsci.plugins.xmas.XmasBuildWrapper.DescriptorImpl;
import org.jvnet.hudson.test.HudsonTestCase;
import org.jvnet.hudson.test.recipes.LocalData;

public class XmasItemListenerTest extends HudsonTestCase {

    @LocalData
    public void testAddXmas_without_BuildWrappers() throws IOException {
        DescriptorImpl descriptor = (DescriptorImpl) hudson.getDescriptor(XmasBuildWrapper.class);

        FreeStyleProject freeStyleProject = (FreeStyleProject) hudson.getItem("free");
        assertTrue(freeStyleProject.getBuildWrappersList().contains(descriptor));

        MavenModuleSet mavenModuleSet = (MavenModuleSet) hudson.getItem("maven");
        assertTrue(mavenModuleSet.getBuildWrappersList().contains(descriptor));
    }

}
