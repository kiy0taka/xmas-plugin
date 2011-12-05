package org.jenkinsci.plugins.xmas;

import hudson.model.FreeStyleBuild;
import hudson.model.Result;
import hudson.model.FreeStyleProject;
import hudson.tasks.Shell;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.FileUtils;
import org.jvnet.hudson.test.HudsonTestCase;

public class XmasBuildWrapperTest extends HudsonTestCase {

    public void testBuildShouldFail() throws IOException, InterruptedException, ExecutionException {
        FreeStyleProject project = createFreeStyleProject();
        project.getBuildersList().add(new Shell("echo hello"));
        project.getBuildWrappersList().replace(new XmasBuildWrapper(true));

        FreeStyleBuild build = project.scheduleBuild2(0).get();
        assertEquals(Result.FAILURE, build.getResult());

        String s = FileUtils.readFileToString(build.getLogFile());
        assertTrue(s.contains("爆発しろ！"));
    }
}
