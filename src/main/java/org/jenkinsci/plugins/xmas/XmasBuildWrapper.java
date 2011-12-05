package org.jenkinsci.plugins.xmas;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.BuildListener;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.tasks.BuildWrapper;
import hudson.tasks.BuildWrapperDescriptor;

import java.io.IOException;
import java.util.Calendar;

import net.sf.json.JSONObject;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

public class XmasBuildWrapper extends BuildWrapper {

    private boolean force;

    @DataBoundConstructor
    public XmasBuildWrapper(boolean force) {
        this.force = force;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    @Override
    public Environment setUp(@SuppressWarnings("rawtypes") AbstractBuild build, Launcher launcher, BuildListener listener) throws IOException, InterruptedException {
        return new BuildWrapper.Environment() {
            @Override
            public boolean tearDown(@SuppressWarnings("rawtypes") AbstractBuild build, BuildListener listener) throws IOException, InterruptedException {
                if (isForce() || isXmasWeek(Calendar.getInstance())) {
                    listener.getLogger().println("爆発しろ！");
                    return false;
                }
                return true;
            }
        };
    }

    @Extension
    public static final class DescriptorImpl extends BuildWrapperDescriptor {

        @Override
        public boolean isApplicable(AbstractProject<?, ?> item) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return "X'mas";
        }

        @Override
        public BuildWrapper newInstance(StaplerRequest req, JSONObject formData) throws hudson.model.Descriptor.FormException {
            return req.bindJSON(clazz, formData);
        }
    }

    public boolean isXmasWeek(Calendar calendar) {
        int month = calendar.get(Calendar.MONTH);
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        return month == 11 && days >= 19 && days <= 25;
    }
}
