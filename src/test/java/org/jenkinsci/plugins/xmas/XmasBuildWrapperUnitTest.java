package org.jenkinsci.plugins.xmas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

public class XmasBuildWrapperUnitTest {

    @Test
    public void testIsXmas() {
        XmasBuildWrapper wrapper = new XmasBuildWrapper(false);
        assertFalse(wrapper.isXmasWeek(createCalendar("20111218")));
        assertTrue(wrapper.isXmasWeek(createCalendar("20111219")));
        assertTrue(wrapper.isXmasWeek(createCalendar("20111220")));
        assertTrue(wrapper.isXmasWeek(createCalendar("20111221")));
        assertTrue(wrapper.isXmasWeek(createCalendar("20111222")));
        assertTrue(wrapper.isXmasWeek(createCalendar("20111223")));
        assertTrue(wrapper.isXmasWeek(createCalendar("20111224")));
        assertTrue(wrapper.isXmasWeek(createCalendar("20111225")));
        assertFalse(wrapper.isXmasWeek(createCalendar("20111226")));
    }

    private Calendar createCalendar(String yyyyMMdd) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        try {
            Calendar result = Calendar.getInstance();
            result.setTime(fmt.parse(yyyyMMdd));
            return result;
        } catch (ParseException e) {
            throw new IllegalArgumentException(yyyyMMdd, e);
        }
        
    }
}
