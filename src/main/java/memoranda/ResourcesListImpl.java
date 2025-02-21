/**
 * ResourcesListImpl.java
 * Created on 24.03.2003, 18:30:31 Alex
 * Package: net.sf.memoranda
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */

package main.java.memoranda;

import main.java.memoranda.util.*;
import nu.xom.*;

import java.io.*;
import java.util.*;

/**
 *
 */
/*$Id: ResourcesListImpl.java,v 1.5 2007/03/20 06:21:46 alexeya Exp $*/
public class ResourcesListImpl implements ResourcesList {

    private Project project = null;
    private Document doc = null;
    private Element root = null;

    /**
     * Constructor for TaskListImpl.
     */
    public ResourcesListImpl(Document doc, Project prj) {
        this.doc = doc;
        root = this.doc.getRootElement();
        project = prj;
    }

    public ResourcesListImpl(Project prj) {
        root = new Element("resources-list");
        doc = new Document(root);
        project = prj;
    }

    public Vector getAllResources() {
        Vector v = new Vector();
        Elements rs = root.getChildElements("resource");
        for (int i = 0; i < rs.size(); i++) {
            v.add(new Resource(rs.get(i).getAttribute("path").getValue(), rs.get(i).getAttribute("isInetShortcut") != null, rs.get(i).getAttribute("isProjectFile") != null));
        }
        return v;
    }

    /**
     * @see main.java.memoranda.ResourcesList#getResource(java.lang.String)
     */
    public Resource getResource(String path) {
        Elements rs = root.getChildElements("resource");
        for (int i = 0; i < rs.size(); i++) {
            if (rs.get(i).getAttribute("path").getValue().equals(path)) {
                return new Resource(rs.get(i).getAttribute("path").getValue(), rs.get(i).getAttribute("isInetShortcut") != null, rs.get(i).getAttribute("isProjectFile") != null);
            }
        }
        return null;
    }

    /**
     * @see main.java.memoranda.ResourcesList#addResource(java.lang.String, boolean)
     */
    public void addResource(String path, boolean isInternetShortcut, boolean isProjectFile) {
        Element el = new Element("resource");
        el.addAttribute(new Attribute("id", Util.generateId()));
        el.addAttribute(new Attribute("path", path));
        if (isInternetShortcut) {
            el.addAttribute(new Attribute("isInetShortcut", "true"));
        }
        if (isProjectFile) {
            el.addAttribute(new Attribute("isProjectFile", "true"));
        }
        root.appendChild(el);
    }

    public void addResource(String path) {
        addResource(path, false, false);
    }

    /**
     * @see main.java.memoranda.ResourcesList#removeResource(java.lang.String)
     */
    public void removeResource(String path) {
        Elements rs = root.getChildElements("resource");
        for (int i = 0; i < rs.size(); i++) {
            if (rs.get(i).getAttribute("path").getValue().equals(path)) {
                if (getResource(path).isProjectFile()) {
                    File f = new File(path);
                    System.out.println("[DEBUG] Removing file " + path);
                    f.delete();
                }
                root.removeChild(rs.get(i));
            }
        }
    }


    /**
     * @see main.java.memoranda.ResourcesList#getAllResourcesCount()
     */
    public int getAllResourcesCount() {
        return root.getChildElements("resource").size();
    }

    /**
     * @see main.java.memoranda.ResourcesList#getXmlContent()
     */
    public Document getXmlContent() {
        return doc;
    }
}
