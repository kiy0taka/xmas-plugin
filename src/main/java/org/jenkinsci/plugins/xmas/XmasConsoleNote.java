package org.jenkinsci.plugins.xmas;

import hudson.MarkupText;
import hudson.console.ConsoleAnnotator;
import hudson.console.ConsoleNote;

/**
 * Christmas is cancelled. Didn't you know?
 *
 * @author Kohsuke Kawaguchi
 */
public class XmasConsoleNote extends ConsoleNote {
    @Override
    public ConsoleAnnotator annotate(Object context, MarkupText text, int charPos) {
        text.addMarkup(charPos,"<div><img src='http://kohsuke.org/wp-content/uploads/2011/12/christmas-is-cancelled.jpg'></div>");
        return null;
    }
}
