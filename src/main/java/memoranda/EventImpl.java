/**
 * EventImpl.java
 * Created on 08.03.2003, 13:20:13 Alex
 * Package: net.sf.memoranda
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */

package main.java.memoranda;

import main.java.memoranda.date.*;
import main.java.memoranda.util.*;
import nu.xom.*;

import java.util.*;

/**
 *
 */
/*$Id: EventImpl.java,v 1.9 2004/10/06 16:00:11 ivanrise Exp $*/
public class EventImpl implements Event, Comparable {

    private Element element = null;

    /**
     * Constructor for EventImpl.
     */
    public EventImpl(Element elem) {
        element = elem;
    }


    /**
     * @see main.java.memoranda.Event#getHour()
     */
    public int getHour() {
        return Integer.parseInt(element.getAttribute("hour").getValue());
    }

    /**
     * @see main.java.memoranda.Event#getMinute()
     */
    public int getMinute() {
        return Integer.parseInt(element.getAttribute("min").getValue());
    }

    public String getTimeString() {
        return Local.getTimeString(getHour(), getMinute());
    }


    /**
     * @see main.java.memoranda.Event#getText()
     */
    public String getText() {
        return element.getValue();
    }

    /**
     * @see main.java.memoranda.Event#getContent()
     */
    public Element getContent() {
        return element;
    }

    /**
     * @see main.java.memoranda.Event#isRepeatable()
     */
    public boolean isRepeatable() {
        return getStartDate() != null;
    }

    /**
     * @see main.java.memoranda.Event#getStartDate()
     */
    public CalendarDate getStartDate() {
        Attribute a = element.getAttribute("startDate");
        if (a != null) {
            return new CalendarDate(a.getValue());
        }
        return null;
    }

    /**
     * @see main.java.memoranda.Event#getEndDate()
     */
    public CalendarDate getEndDate() {
        Attribute a = element.getAttribute("endDate");
        if (a != null) {
            return new CalendarDate(a.getValue());
        }
        return null;
    }

    /**
     * @see main.java.memoranda.Event#getPeriod()
     */
    public int getPeriod() {
        Attribute a = element.getAttribute("period");
        if (a != null) {
            return Integer.parseInt(a.getValue());
        }
        return 0;
    }

    /**
     * @see main.java.memoranda.Event#getId()
     */
    public String getId() {
        Attribute a = element.getAttribute("id");
        if (a != null) {
            return a.getValue();
        }
        return null;
    }

    /**
     * @see main.java.memoranda.Event#getRepeat()
     */
    public int getRepeat() {
        Attribute a = element.getAttribute("repeat-type");
        if (a != null) {
            return Integer.parseInt(a.getValue());
        }
        return 0;
    }

    /**
     * @see main.java.memoranda.Event#getTime()
     */
    public Date getTime() {
        //Deprecated methods
        //Date d = new Date();
        //d.setHours(getHour());
        //d.setMinutes(getMinute());
        //d.setSeconds(0);
        //End deprecated methods

        Date d = new Date(); //Revision to fix deprecated methods (jcscoobyrs) 12-NOV-2003 14:26:00
        Calendar calendar = new GregorianCalendar(Local.getCurrentLocale()); //Revision to fix deprecated methods (jcscoobyrs) 12-NOV-2003 14:26:00
        calendar.setTime(d); //Revision to fix deprecated methods (jcscoobyrs) 12-NOV-2003 14:26:00
        calendar.set(Calendar.HOUR_OF_DAY, getHour()); //Revision to fix deprecated methods (jcscoobyrs) 12-NOV-2003 14:26:00
        calendar.set(Calendar.MINUTE, getMinute()); //Revision to fix deprecated methods (jcscoobyrs) 12-NOV-2003 14:26:00
        calendar.set(Calendar.SECOND, 0); //Revision to fix deprecated methods (jcscoobyrs) 12-NOV-2003 14:26:00
        d = calendar.getTime(); //Revision to fix deprecated methods (jcscoobyrs) 12-NOV-2003 14:26:00
        return d;
    }

    /**
     * @see main.java.memoranda.Event#getWorkinDays()
     */
    public boolean getWorkingDays() {
        Attribute a = element.getAttribute("workingDays");
        return a != null && a.getValue().equals("true");
    }

//    public int compareTo(Object o) {
//        Event event = (Event) o;
//        return (getHour() * 60 + getMinute()) - (event.getHour() * 60 + event.getMinute());
//    }
    public int compareTo(Object o) {
        if (o instanceof Event) {
            Event event = (Event) o;
            int thisTime = getHour() * 60 + getMinute();
            int otherTime = event.getHour() * 60 + event.getMinute();

            if (thisTime < otherTime) {
                return -1;
            } else if (thisTime > otherTime) {
                return 1;
            } else {
                return 0;
            }
        }

        throw new IllegalArgumentException("Cannot compare different types.");
    }

}
